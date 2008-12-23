package votebox.crypto.interop;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sexpression.ASExpression;
import sexpression.ListExpression;
import votebox.crypto.BallotEncrypter;

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
	public void testBallotEncrypter() throws Exception{
		ListExpression choice1 = new ListExpression("B21", "1");
		ListExpression choice2 = new ListExpression("B22", "0");
		ListExpression choice3 = new ListExpression("B23", "0");
		
		ListExpression ballot = new ListExpression(choice1, choice2, choice3);
		
		PublicKey pubKey = PublicKey.makePartialKey(128);
		@SuppressWarnings("unused")
		PrivateKey priv = pubKey.genKeyPair();
		
		ListExpression encrypted = BallotEncrypter.SINGLETON.encryptSublistWithProof(ballot, pubKey);
		ASExpression vote = ((ListExpression)encrypted.get(0)).get(1);
		ASExpression proof = ((ListExpression)encrypted.get(1)).get(1);
		
		PublicKey finalPubKey = PublicKey.fromString(((ListExpression)encrypted.get(2)).get(1).toString());
		
		VoteProof vProof = VoteProof.fromString(proof.toString());
		Vote vVote = Vote.fromString(vote.toString());
		
		Assert.assertTrue("Vote should verify", vProof.verify(vVote, finalPubKey, 0, 1));
		
		choice1 = new ListExpression("B21", "1");
		choice2 = new ListExpression("B22", "0");
		choice3 = new ListExpression("B23", "1");
		
		ballot = new ListExpression(choice1, choice2, choice3);
		
		encrypted = BallotEncrypter.SINGLETON.encryptSublistWithProof(ballot, pubKey);
		vote = ((ListExpression)encrypted.get(0)).get(1);
		proof = ((ListExpression)encrypted.get(1)).get(1);
		
		finalPubKey = PublicKey.fromString(((ListExpression)encrypted.get(2)).get(1).toString());
		
		vProof = VoteProof.fromString(proof.toString());
		vVote = Vote.fromString(vote.toString());
		
		Assert.assertFalse("Vote shouldn't verify", vProof.verify(vVote, finalPubKey, 0, 1));
	}
	
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
			
			AdderInteger p = pubKey.getP();
			AdderInteger q = pubKey.getQ();
			AdderInteger g = pubKey.getG();
			AdderInteger f = pubKey.getF();
			AdderInteger finalH = new AdderInteger(AdderInteger.ONE, p);
			
			AdderInteger gvalue = g.pow((poly).
                    evaluate(new AdderInteger(AdderInteger.ZERO, q)));
			finalH = finalH.multiply(gvalue);
			
			PublicKey finalPubKey = new PublicKey(p, g, finalH, f);
			
			//Generate the final private key
			List<ElgamalCiphertext> ciphertexts = new ArrayList<ElgamalCiphertext>();
			ElgamalCiphertext ciphertext = pubKey.encryptPoly(poly.evaluate(new AdderInteger(0, pubKey.getQ())));
			ciphertexts.add(ciphertext);
			PrivateKey finalPrivKey = privKey.getFinalPrivKey(ciphertexts);
			
			Election election = new Election(pubKey.getP());
			
			List<AdderInteger> value = new ArrayList<AdderInteger>();
			
			for(int j = 0; j < 5 + (int)(Math.random() * 6.0); j++){
				value.add(new AdderInteger(0));
			}//for
			
			int one = (int)(Math.random()*(double)value.size());
			
			value.remove(one);
			value.add(one, new AdderInteger(1));
			
			Vote vote = finalPubKey.encrypt(value);
			
			VoteProof proof = new VoteProof();
			//This may need to be (.., .., .., 1, 1)... way to go Adder docs!
			proof.compute(vote, finalPubKey, value, 0, 1);
			
			election.castVote(vote);
			
			Assert.assertEquals("Votes equivalent", vote.toString(), Converter.toVote(Converter.toSExpression(vote)).toString());
			proofs.add(proof);
			
			Assert.assertTrue("Proof Confirmation", proof.verify(vote, finalPubKey, 0, 1));
			
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