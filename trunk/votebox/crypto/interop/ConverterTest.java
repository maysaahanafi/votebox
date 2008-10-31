package votebox.crypto.interop;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sexpression.ASExpression;

import edu.uconn.cse.adder.AdderInteger;
import edu.uconn.cse.adder.Election;
import edu.uconn.cse.adder.ElgamalCiphertext;
import edu.uconn.cse.adder.MembershipProof;
import edu.uconn.cse.adder.Polynomial;
import edu.uconn.cse.adder.PrivateKey;
import edu.uconn.cse.adder.PublicKey;
import edu.uconn.cse.adder.Vote;
import edu.uconn.cse.adder.VoteProof;


public class ConverterTest {
	
	@Test
	public void testMembership() throws Exception{
		Class mP = MembershipProof.class;
		Constructor con = mP.getDeclaredConstructor(new Class[]{
			AdderInteger.class,
			AdderInteger.class,
			List.class,
			List.class,
			List.class,
			List.class
		});
		
		Field _p = mP.getDeclaredField("p");
		Field _q = mP.getDeclaredField("q");
		Field _y = mP.getDeclaredField("yList");
		Field _z = mP.getDeclaredField("zList");
		Field _c = mP.getDeclaredField("cList");
		Field _s = mP.getDeclaredField("sList");
		
		_p.setAccessible(true);
		_q.setAccessible(true);
		_y.setAccessible(true);
		_z.setAccessible(true);
		_c.setAccessible(true);
		_s.setAccessible(true);
		
		con.setAccessible(true);
		
		for(int i = 0; i < 100; i++){
			System.out.println("Membership #"+(i+1));
			
			List<AdderInteger> y = new ArrayList<AdderInteger>();
			List<AdderInteger> z = new ArrayList<AdderInteger>();
			List<AdderInteger> c = new ArrayList<AdderInteger>();
			List<AdderInteger> s = new ArrayList<AdderInteger>();

			for(int j = 0; j < 20; j++){
				y.add(AdderInteger.random((int)(Math.random() * 100000.0)));
				z.add(AdderInteger.random((int)(Math.random() * 100000.0)));
				s.add(AdderInteger.random((int)(Math.random() * 100000.0)));
				c.add(AdderInteger.random((int)(Math.random() * 100000.0)));
			}//for

			AdderInteger p = AdderInteger.safePrime(128);
			AdderInteger q = p.subtract(AdderInteger.ONE).divide(AdderInteger.TWO);
			
			MembershipProof proof = (MembershipProof)con.newInstance(new Object[]{
					p,
					q,
					y,
					z,
					s,
					c});

			String originStr = proof.toString();
			ASExpression copyExp = Converter.toSExpression(proof);
			MembershipProof copyProof = Converter.toMembershipProof(copyExp);
			String copyStr = copyProof.toString();
			
			Assert.assertEquals("p", _p.get(proof), _p.get(copyProof));
			Assert.assertEquals("q", _q.get(proof), _q.get(copyProof));
			
			Assert.assertEquals("y", _y.get(proof), _y.get(copyProof));
			Assert.assertEquals("z", _z.get(proof), _z.get(copyProof));
			Assert.assertEquals("c", _c.get(proof), _c.get(copyProof));
			Assert.assertEquals("s", _s.get(proof), _s.get(copyProof));
			
			Assert.assertEquals("Equivalent", originStr, copyStr);
		}
	}
	
	@Test
	public void testVote(){
		for(int i = 0; i < 100; i++){
			List<ElgamalCiphertext> ciphers = new ArrayList<ElgamalCiphertext>();
			
			for(int j = 0; j < 50; j++){
				try{
					ElgamalCiphertext original = new ElgamalCiphertext(
							AdderInteger.random((int)(Math.random() * 100000.0)),
							AdderInteger.random((int)(Math.random() * 100000.0)),
							AdderInteger.random((int)(Math.random() * 100000.0)),
							AdderInteger.random((int)(Math.random() * 100000.0)));

					ciphers.add(original);
				}catch(ArithmeticException e){
					j--;
					continue;
				}
			}
			
			Vote original = new Vote(ciphers);
			
			String origin = original.toString();
			ASExpression copy = Converter.toSExpression(original);
			
			Assert.assertEquals("Equivalent", origin, Converter.toVote(copy).toString());
			System.out.println("Vote: Check #"+(i+1)+" Done");
		}
	}
	
	@Test
	public void testCiphertext(){
		for(int i = 0; i < 100; i++){
			try{
				ElgamalCiphertext original = new ElgamalCiphertext(
						AdderInteger.random((int)(Math.random() * 100000.0)),
						AdderInteger.random((int)(Math.random() * 100000.0)),
						AdderInteger.random((int)(Math.random() * 100000.0)),
						AdderInteger.random((int)(Math.random() * 100000.0)));
			
				Assert.assertEquals("Equivalent", original.toString(), Converter.toCiphertext(Converter.toSExpression(original)).toString());
			}catch(ArithmeticException e){
				i--;
				continue;
			}
			
			System.out.println("Ciphertext: Check #"+(i+1)+" Done");
		}
	}
	
	@Test
	public void testInteger(){
		for(int i = 0; i < 100; i++){
			AdderInteger original = AdderInteger.random((int)(Math.random() * 100000.0));
			
			Assert.assertEquals("Equivalent", original.toString(), Converter.toAdderInteger(Converter.toSExpression(original)).toString());
			System.out.println("Integer: Check #"+(i+1)+" Done");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testTotal(){
		List<VoteProof> proofs = new ArrayList<VoteProof>();
		
		for(int i = 0; i < 100; i++){
			System.out.println("Trial #"+(i+1));
			PublicKey pubKey = PublicKey.makePartialKey(128);
			PrivateKey privKey = pubKey.genKeyPair();
			
			Polynomial poly = new Polynomial(pubKey.getP(), pubKey.getG(), pubKey.getF(), 0);
			
			//Generate the final public key
			PublicKey finalPubKey = new PublicKey(pubKey.getP(), pubKey.getG(),
					(new AdderInteger(AdderInteger.ONE, pubKey.getP())).multiply(
							pubKey.getG().pow(poly.evaluate(new AdderInteger(AdderInteger.ZERO, pubKey.getQ())))), pubKey.getF());
			
			//Generate the final private key
			List<ElgamalCiphertext> ciphertexts = new ArrayList<ElgamalCiphertext>();
			ElgamalCiphertext ciphertext = pubKey.encryptPoly(poly.evaluate(new AdderInteger(0, pubKey.getQ())));
			ciphertexts.add(ciphertext);
			PrivateKey finalPrivKey = privKey.getFinalPrivKey(ciphertexts);
			
			Election election = new Election(pubKey.getP());
			
			List<AdderInteger> value = new ArrayList<AdderInteger>();
			
			for(int j = 0; j < 5; j++)
				value.add(AdderInteger.random(AdderInteger.TWO));
			
			Vote vote = finalPubKey.encrypt(value);
			VoteProof proof = new VoteProof();
			//This may need to be (.., .., .., 1, 1)... way to go Adder docs!
			proof.compute(vote, finalPubKey, value, 0, 1);
			
			election.castVote(vote);
			
			Assert.assertEquals("Votes equivalent", vote.toString(), Converter.toVote(Converter.toSExpression(vote)).toString());
			
			/*String proofStr = proof.toString();
			ASExpression copyExp = Converter.toSExpression(proof);
			
			System.out.println("\t"+copyExp);
			
			String copyStr = Converter.toVoteProof(copyExp).toString();
			
			System.out.println("\t" + copyStr);
			
			Assert.assertEquals("Proofs equivalent", proofStr, copyStr);*/
			proofs.add(proof);
			
			Vote cipherSum = election.sumVotes();
			List<AdderInteger> partialSum = finalPrivKey.partialDecrypt(cipherSum);
			AdderInteger coeff = new AdderInteger(0);
			
			List<List<AdderInteger>> partialSums = new ArrayList<List<AdderInteger>>();
			partialSums.add(partialSum);
			
			List<AdderInteger> coeffs = new ArrayList<AdderInteger>();
			coeffs.add(coeff);
			
			List<AdderInteger> results = election.getFinalSum(partialSums, coeffs, cipherSum, finalPubKey);
			
			Assert.assertEquals("Results are same size as values", value.size(), results.size());
			for(int j = 0; j < value.size(); j++)
				Assert.assertEquals("Selection #"+j+" matches", value.get(j), results.get(j));
		}
	}
}