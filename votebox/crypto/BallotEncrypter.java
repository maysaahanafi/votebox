/**
  * This file is part of VoteBox.
  * 
  * VoteBox is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License version 3 as published by
  * the Free Software Foundation.
  * 
  * You should have received a copy of the GNU General Public License
  * along with VoteBox, found in the root of any distribution or
  * repository containing all or part of VoteBox.
  * 
  * THIS SOFTWARE IS PROVIDED BY WILLIAM MARSH RICE UNIVERSITY, HOUSTON,
  * TX AND IS PROVIDED 'AS IS' AND WITHOUT ANY EXPRESS, IMPLIED OR
  * STATUTORY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, WARRANTIES OF
  * ACCURACY, COMPLETENESS, AND NONINFRINGEMENT.  THE SOFTWARE USER SHALL
  * INDEMNIFY, DEFEND AND HOLD HARMLESS RICE UNIVERSITY AND ITS FACULTY,
  * STAFF AND STUDENTS FROM ANY AND ALL CLAIMS, ACTIONS, DAMAGES, LOSSES,
  * LIABILITIES, COSTS AND EXPENSES, INCLUDING ATTORNEYS' FEES AND COURT
  * COSTS, DIRECTLY OR INDIRECTLY ARISING OUR OF OR IN CONNECTION WITH
  * ACCESS OR USE OF THE SOFTWARE.
 */

package votebox.crypto;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.uconn.cse.adder.AdderInteger;
import edu.uconn.cse.adder.Polynomial;
import edu.uconn.cse.adder.PublicKey;
import edu.uconn.cse.adder.Vote;
import edu.uconn.cse.adder.VoteProof;

import auditorium.Key;

import sexpression.*;
import votebox.crypto.interop.Converter;
import votebox.middle.IBallotVars;
import votebox.middle.ballot.*;

public class BallotEncrypter {

    public static final BallotEncrypter SINGLETON = new BallotEncrypter();

    private List<BigInteger> _randomList;
    private ListExpression _recentBallot;

    private BallotEncrypter() {
    }

    /**
     * Take an unenctyped ballot and make it encrypted, while also generating a NIZK.
     * 
     * @param ballot 
     *          This is the pre-encrypt ballot in the form ((race-id counter) ...)
     * @param publicKey
     *          this is an Adder-style public key
     * @return An ListExpression of the form ([vote] [proof])
     */
    public ListExpression encryptWithProof(ListExpression ballot, PublicKey pubKey){
    	List<AdderInteger> choices = new ArrayList<AdderInteger>();
    	
    	for(int i = 0; i < ballot.size(); i++){
    		ListExpression choice = (ListExpression)ballot.get(i);
    		choices.add(new AdderInteger(new Integer(choice.get(1).toString())));
    	}//for
    	
    	Polynomial poly = new Polynomial(pubKey.getP(), pubKey.getG(), pubKey.getF(), 0);
    	PublicKey finalPubKey = new PublicKey(pubKey.getP(), pubKey.getG(),
    			(new AdderInteger(AdderInteger.ONE, pubKey.getP())).multiply(
    					pubKey.getG().pow(poly.evaluate(new AdderInteger(AdderInteger.ZERO, pubKey.getQ())))), pubKey.getF());
    	
    	Vote vote = finalPubKey.encrypt(choices);
    	VoteProof proof = new VoteProof();
    	//This may need to be (....., 1, 1); docs are ambiguous
    	proof.compute(vote, pubKey, choices, 0, 1);
    	
    	return new ListExpression(Converter.toSExpression(vote), Converter.toSExpression(proof));
    }
    
    /**
     * Take an unencrypted ballot form and make it encrypted.
     * 
     * @param ballot
     *            This is the pre-encrypt ballot in the form ((race-id
     *            counter)...)
     * @param publicKey
     * 			  this is the public ElGamal key used to encrypt the ballot
     * @return This method returns the encrypted form of ballot in the form
     *         ((race-id E(counter))...)
     */
    public ListExpression encrypt(ListExpression ballot, Key publicKey) {
    	ElGamalCrypto.SINGLETON.clearRecentRandomness();
        ArrayList<ASExpression> encryptedpairs = new ArrayList<ASExpression>();
        for (ASExpression ase : ballot) {
            ListExpression le = (ListExpression) ase;
            StringExpression id = (StringExpression) le.get(0);
            StringExpression count = (StringExpression) le.get(1);
            /*Pair<BigInteger> cipher = ElGamalCrypto.SINGLETON.encrypt(
                    publicKey, new BigInteger(count
                            .getBytes()));*/
            
            Pair<BigInteger> cipher = ElGamalCrypto.SINGLETON.encrypt(publicKey, new BigInteger(count.toString()));
            /*ASExpression cipherase = new ListExpression(StringExpression
                    .makeString(cipher.get1().toByteArray()), StringExpression
                    .makeString(cipher.get2().toByteArray()));*/
            ASExpression cipherase = new ListExpression(StringExpression.makeString(cipher.get1().toString()), 
            		StringExpression.makeString(cipher.get2().toString()));
            encryptedpairs.add(new ListExpression(id, cipherase));
        }
        _recentBallot = new ListExpression(encryptedpairs);
        _randomList = ElGamalCrypto.SINGLETON.getRecentRandomness();
        ElGamalCrypto.SINGLETON.clearRecentRandomness();
        return _recentBallot;
    }

    /**
     * Decrypt a ballot using the r-values (not the decryption key).
     * 
     * @param ballot
     *            This is the ballot, formatted ((race-id encrypted-counter)...)
     * @param rVals
     *            These are the r-values, formatted ((race-id r-value)...)
     * @param publicKey
     * 			   The necissary ElGamal public key.
     * @return This method returns the decrypted ballot, formatted ((race-id
     *         plaintext-counter)...)
     */
    public ListExpression decrypt(ListExpression ballot, ListExpression rVals, Key publicKey) {
        if (ballot.size() != rVals.size())
            throw new RuntimeException("sizes must match");
        if (Ballot.BALLOT_PATTERN.match(ballot) == NoMatch.SINGLETON)
            throw new RuntimeException("ballot incorrectly formatted");
        if (Ballot.BALLOT_PATTERN.match(rVals) == NoMatch.SINGLETON)
            throw new RuntimeException("r-vals incorrectly formatted");

        ArrayList<ASExpression> decryptedpairs = new ArrayList<ASExpression>(
                ballot.size());
        Iterator<ASExpression> ballotitr = ballot.iterator();
        Iterator<ASExpression> ritr = rVals.iterator();
        while (ballotitr.hasNext()) {
            ListExpression ballotnext = (ListExpression) ballotitr.next();
            ListExpression rnext = (ListExpression) ritr.next();

            if (!ballotnext.get(0).equals(rnext.get(0)))
                throw new RuntimeException(
                        "incorrect set of r-values: uids do not match");

            ASExpression uid = ballotnext.get(0);
            /*BigInteger r = new BigInteger(((StringExpression) rnext.get(1))
                    .getBytes());
            BigInteger cipher1 = new BigInteger(
                    ((StringExpression) ((ListExpression) ballotnext.get(1))
                            .get(0)).getBytes());
            BigInteger cipher2 = new BigInteger(
                    ((StringExpression) ((ListExpression) ballotnext.get(1))
                            .get(1)).getBytes());*/
            BigInteger r = new BigInteger(((StringExpression) rnext.get(1)).toString());
            BigInteger cipher1 = new BigInteger(((StringExpression) ((ListExpression)ballotnext.get(1)).get(0)).toString());
            BigInteger cipher2 = new BigInteger(((StringExpression) ((ListExpression)ballotnext.get(1)).get(1)).toString());
            
            Pair<BigInteger> cipher = new Pair<BigInteger>(cipher1, cipher2);
            BigInteger plaincounter = ElGamalCrypto.SINGLETON.decrypt(r,
                    publicKey, cipher);
            /*decryptedpairs.add(new ListExpression(uid, StringExpression
                    .makeString(plaincounter.toByteArray())));*/
            decryptedpairs.add(new ListExpression(uid, StringExpression
                    .makeString(plaincounter.toString())));
        }
        return new ListExpression(decryptedpairs);
    }

    /**
     * Get the most recent random list.
     * 
     * @return This method returns the random list in the form ((uid rvalue)...)
     */
    public ListExpression getRecentRandom() {
        ArrayList<ASExpression> pairs = new ArrayList<ASExpression>();

        Iterator<ASExpression> ballotitr = _recentBallot.iterator();
        Iterator<BigInteger> ritr = _randomList.iterator();

        while (ballotitr.hasNext()) {
            ListExpression ballotpair = (ListExpression) ballotitr.next();
            BigInteger r = ritr.next();
            pairs.add(new ListExpression(ballotpair.get(0), StringExpression
                    .makeString(r.toString())));
        }

        return new ListExpression(pairs);
    }

    /**
     * Get the result of the most recent encrypt call.
     * 
     * @return This method returns the most recent encryption.
     */
    public ListExpression getRecentEncryptedBallot() {
        return _recentBallot;
    }

    /**
     * Clear the state.
     */
    public void clear() {
        _recentBallot = null;
        _randomList = null;
    }

    /**
     * I'm going to use this main as a sandbox for generating performance
     * numbers using this encryption, etc.
     * 
     * @param args
     */
    public static void main(String[] args) throws Exception {
        BallotParser parser = new BallotParser();
        Ballot b = parser.getBallot(new IBallotVars() {

            public String getBallotFile() {
                return "ballot.xml";
            }

            public String getBallotPath() {
                return "";
            }

            public URL getBallotSchema() {
                return getClass().getResource(
                        "/votebox/middle/schema/ballot_schema.xsd");

            }

            public String getLayoutFile() {
                return null;
            }

            public URL getLayoutSchema() {
                return null;
            }
        });

        ThreadMXBean t = ManagementFactory.getThreadMXBean();
        
        ListExpression b_count = (ListExpression) b.getCastBallot();
        
        ListExpression b_count_crypt = SINGLETON.encrypt(b_count, STATICKEY.SINGLETON.PUBLIC_KEY);
        ListExpression rVals = SINGLETON.getRecentRandom();
        long start = t.getCurrentThreadCpuTime();
        
        @SuppressWarnings("unused")
		ListExpression b_count_crypt_decrypt = SINGLETON.decrypt(b_count_crypt, rVals, STATICKEY.SINGLETON.PUBLIC_KEY);
        long end = t.getCurrentThreadCpuTime();
        
        System.err.println(end-start);
        
        /*
        File f = new File("out");
        FileOutputStream fo = new FileOutputStream(f);
        byte[] bytes = b_count_crypt.toVerbatim();
        fo.write(bytes);
        fo.close();
        */
    }
}
