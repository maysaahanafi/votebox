package supervisor.model.tallier;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import auditorium.Bugout;

import edu.uconn.cse.adder.AdderInteger;
import edu.uconn.cse.adder.Election;
import edu.uconn.cse.adder.ElgamalCiphertext;
import edu.uconn.cse.adder.Polynomial;
import edu.uconn.cse.adder.PrivateKey;
import edu.uconn.cse.adder.PublicKey;
import edu.uconn.cse.adder.Vote;
import edu.uconn.cse.adder.VoteProof;

import sexpression.ASExpression;
import sexpression.ListExpression;
import sexpression.stream.ASEInputStreamReader;

/**
 * Tallier for elections run with NIZKs but without the commit-challenge model enabled.
 * @author Montrose
 *
 */
public class EncryptedTallierWithNIZKs implements ITallier {
	private PrivateKey _privateKey = null;
	private PublicKey _publicKey = null;
	private PublicKey _finalPublicKey = null;
	private PrivateKey _finalPrivateKey = null;
	
	private Map<String, Election> _results = new HashMap<String, Election>();
	
	/**
	 * Constructor.
	 * 
	 * @param pub - The PublicKey used to encrypt votes to be tallied.
	 * @param priv - The PrivateKey to be used to decrypt the totals.
	 */
	public EncryptedTallierWithNIZKs(PublicKey pub, PrivateKey priv){
		_privateKey = priv;
		_publicKey = pub;
		
		_finalPublicKey = generateFinalPublicKey();
		_finalPrivateKey = generateFinalPrivateKey();
	}
	
	public void challenged(ASExpression nonce) {
		throw new RuntimeException("EncryptedTallierWithNIZKs.challenged NOT IMPLEMENTED");
	}

	public void confirmed(ASExpression nonce) {
		throw new RuntimeException("EncryptedTallierWithNIZKs.confirmed NOT IMPLEMENTED");
	}

	@SuppressWarnings("unchecked")
	public Map<String, BigInteger> getReport() {
		Map<String, BigInteger> report = new HashMap<String, BigInteger>();
		
		for(String group : _results.keySet()){
			Election election = _results.get(group);
			
			Vote cipherSum = election.sumVotes();
			List<AdderInteger> partialSum = (List<AdderInteger>)_finalPrivateKey.partialDecrypt(cipherSum);
			AdderInteger coeff = new AdderInteger(0);

			List<List<AdderInteger>> partialSums = new ArrayList<List<AdderInteger>>();
			partialSums.add(partialSum);

			List<AdderInteger> coeffs = new ArrayList<AdderInteger>();
			coeffs.add(coeff);

			List<AdderInteger> results = election.getFinalSum(partialSums, coeffs, cipherSum, _finalPublicKey);
			String[] ids = group.split(",");
			
			for(int i = 0; i < ids.length; i++)
				report.put(ids[i], results.get(i).bigintValue());
		}//for
		
		return report;
	}

	public void recordVotes(byte[] ballotBytes, ASExpression nonce) {
		System.out.println("EncryptedTallierWithNIZKs.recordVotes(..., "+nonce+")");
		
		ASEInputStreamReader in = new ASEInputStreamReader(
				new ByteArrayInputStream(ballotBytes));
		
		try {
			ASExpression sexp = in.read();
			//Check that the ballot is well-formed
			ListExpression ballot = (ListExpression)sexp;
				
			for(int i = 0; i < ballot.size(); i++){
				ListExpression raceGroup = (ListExpression)ballot.get(i);
				ListExpression voteE = (ListExpression)raceGroup.get(0);
				ListExpression voteIdsE = (ListExpression)raceGroup.get(1);
				ListExpression proofE = (ListExpression)raceGroup.get(2);
				ListExpression publicKeyE = (ListExpression)raceGroup.get(3);
				
				confirmValid(voteE, voteIdsE, proofE, publicKeyE);
				
				Vote vote = Vote.fromString(voteE.get(1).toString());
				List<String> voteIds = new ArrayList<String>();
				for(int j = 1; j < voteIdsE.size(); j++)
					voteIds.add(voteIdsE.get(1).toString());
				
				VoteProof voteProof = VoteProof.fromString(proofE.get(1).toString());
				
				PublicKey suppliedPublicKey = PublicKey.fromString(publicKeyE.get(1).toString());
				
				if(!(suppliedPublicKey.toString().equals(_finalPublicKey.toString()))){
					Bugout.err("Expected supplied final PublicKey to match generated\nSupplied: "+suppliedPublicKey+"\nGenerated: "+_finalPublicKey);
					Bugout.err("Rejected ballot:\n"+new String(ballotBytes));
					return;
				}
				
				if(!voteProof.verify(vote, _finalPublicKey, 0, 1)){
					Bugout.err("Ballot failed NIZK test");
					Bugout.err("Rejected ballot:\n"+new String(ballotBytes));
					return;
				}
				
				String subElectionId = makeId(voteIds);
				
				Election election = _results.get(subElectionId);
				
				if(election == null)
					election = new Election(_publicKey.getP());
				
				election.castVote(vote);
				
				_results.put(subElectionId, election);
			}//for
		}catch(Exception e){
			Bugout.err("Malformed ballot received <"+e.getMessage()+">");
			Bugout.err("Rejected ballot:\n"+new String(ballotBytes));
		}
	}

	/**
	 * Using nizks imposes structure on our race format we haven't had before.
	 * This method is 
	 * @param voteIds
	 * @return
	 */
	private String makeId(List<String> voteIds){
		String str = voteIds.get(0);
		for(int i = 1; i < voteIds.size(); i++)
			str+=","+voteIds.get(i);
		
		return str;
	}
	
	/**
	 * Confirms that the vote, voteIds, proof, and publicKey fields pulled out of a ballot are well-formed.
	 * 
	 * @param vote
	 * @param voteIds
	 * @param proof
	 * @param publicKey
	 */
	private void confirmValid(ListExpression vote, ListExpression voteIds, ListExpression proof, ListExpression publicKey){
		if(!vote.get(0).equals("vote"))
			throw new RuntimeException("Missing \"vote\"");
		
		if(!voteIds.get(0).equals("vote-ids"))
			throw new RuntimeException("Missing \"vote-ids\"");
		
		if(!proof.get(0).equals("proof"))
			throw new RuntimeException("Missing \"proof\"");
		
		if(!publicKey.get(0).equals("public-key"))
			throw new RuntimeException("Missing \"public-key\"");
	}
	
	/**
	 * Generates the "final" public key using the pre-generated public key.
	 * This is needed to actually tally and perform NIZK verification.
	 * 
	 * @return the new PublicKey
	 */
	private PublicKey generateFinalPublicKey(){
		Polynomial poly = new Polynomial(_publicKey.getP(), _publicKey.getG(), _publicKey.getF(), 0);
		
		AdderInteger p = _publicKey.getP();
		AdderInteger q = _publicKey.getQ();
		AdderInteger g = _publicKey.getG();
		AdderInteger f = _publicKey.getF();
		AdderInteger finalH = new AdderInteger(AdderInteger.ONE, p);
		
		AdderInteger gvalue = g.pow((poly).
                evaluate(new AdderInteger(AdderInteger.ZERO, q)));
		finalH = finalH.multiply(gvalue);
		
		PublicKey finalPublicKey = new PublicKey(p, g, finalH, f);
		
		return finalPublicKey;
	}
	
	/**
	 * Generates the "final" PrivateKey from the pre-generated one.
	 * This is needed to decrypt the totals calculated with the corresponding final public key.
	 * 
	 * @return the new PrivateKey
	 */
	private PrivateKey generateFinalPrivateKey(){
		//Generate the final private key
		Polynomial poly = new Polynomial(_publicKey.getP(), _publicKey.getG(), _publicKey.getF(), 0);
		
		List<ElgamalCiphertext> ciphertexts = new ArrayList<ElgamalCiphertext>();
		ElgamalCiphertext ciphertext = _publicKey.encryptPoly(poly.evaluate(new AdderInteger(0, _publicKey.getQ())));
		ciphertexts.add(ciphertext);
		PrivateKey finalPrivKey = _privateKey.getFinalPrivKey(ciphertexts);
		
		return finalPrivKey;
	}
}
