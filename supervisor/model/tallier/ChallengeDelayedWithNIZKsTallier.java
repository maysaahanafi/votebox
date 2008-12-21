package supervisor.model.tallier;

import java.math.BigInteger;
import java.util.Map;

import edu.uconn.cse.adder.PrivateKey;
import edu.uconn.cse.adder.PublicKey;

import sexpression.ASExpression;

public class ChallengeDelayedWithNIZKsTallier implements ITallier {

	public ChallengeDelayedWithNIZKsTallier(PublicKey pubKey, PrivateKey privKey){
		throw new RuntimeException("ChallengeDelayedWithNIZKsTallier is not IMPLEMENTED!");
	}
	
	public void challenged(ASExpression nonce) {
		// TODO Auto-generated method stub

	}

	public void confirmed(ASExpression nonce) {
		// TODO Auto-generated method stub

	}

	public Map<String, BigInteger> getReport() {
		// TODO Auto-generated method stub
		return null;
	}

	public void recordVotes(byte[] ballot, ASExpression nonce) {
		// TODO Auto-generated method stub

	}

}
