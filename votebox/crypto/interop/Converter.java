package votebox.crypto.interop;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import sexpression.ASExpression;
import sexpression.ListExpression;
import sexpression.StringExpression;
import edu.uconn.cse.adder.AdderInteger;
import edu.uconn.cse.adder.ElgamalCiphertext;
import edu.uconn.cse.adder.InvalidMembershipProofException;
import edu.uconn.cse.adder.MembershipProof;
import edu.uconn.cse.adder.Vote;
import edu.uconn.cse.adder.VoteProof;

/**
 * Class to aid in converting to and from Adder classes.
 * 
 * @author Montrose
 */
public class Converter {
	/**
	 * Converts an Adder VoteProof into a SExpression.
	 * 
	 * @param v - the Adder VoteProof
	 * @return an ASExpression equivalent
	 */
	@SuppressWarnings("unchecked")
	public static ASExpression toSExpression(VoteProof v){
		try{
			Class cProof = VoteProof.class;
			Field _proofList = cProof.getDeclaredField("proofList");
			Field _sumProof = cProof.getDeclaredField("sumProof");
			
			_proofList.setAccessible(true);
			_sumProof.setAccessible(true);
			
			ASExpression sumProof = new ListExpression(StringExpression.makeString("sumProof"),
					toSExpression((MembershipProof)_sumProof.get(v)));
			
			List<MembershipProof> proofL = (List<MembershipProof>)_proofList.get(v);
			ArrayList<ASExpression> proofE = new ArrayList<ASExpression>();
			for(MembershipProof proof : proofL)
				proofE.add(toSExpression(proof));
			
			proofE.add(0, StringExpression.makeString("proofList"));
			
			ASExpression proofList = new ListExpression(proofE);
			
			
			return new ListExpression(sumProof, proofList);
		}catch(Exception e){
			throw new RuntimeException(e);
		}//catch
	}
	
	/**
	 * Converts a SExpression into an Adder VoteProof
	 * 
	 * @param exp - the SExpression
	 * @return the equivalent Adder VoteProof
	 */
	public static VoteProof toVoteProof(ASExpression exp){
		try{
			Class voteC = VoteProof.class;
			Constructor con = voteC.getDeclaredConstructor(new Class[]{
					MembershipProof.class,
					List.class
			});

			con.setAccessible(true);
			
			ListExpression list = (ListExpression)exp;

			ListExpression sumProof = (ListExpression)list.get(0);
			ListExpression proofList = (ListExpression)list.get(1);

			MembershipProof sumProofI = toMembershipProof(sumProof.get(1));
			List<MembershipProof> proofListI = new ArrayList<MembershipProof>();

			for(int i = 1; i < proofList.size(); i++)
				proofListI.add(toMembershipProof(proofList.get(i)));

			VoteProof proof = (VoteProof) con.newInstance(new Object[]{
					sumProofI,
					proofListI
			});
			
			return proof;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Converts an AdderInteger to a SExpression
	 * 
	 * @param i - the AdderInteger
	 * @return an ASExpression equivalent
	 */
	public static ASExpression toSExpression(AdderInteger i){
		return new ListExpression(
				StringExpression.makeString(i.getValue().toString()),
				StringExpression.makeString(i.getModulus().toString()));
	}
	
	/**
	 * Converts an Adder Vote into an SExpression
	 * 
	 * @param v - the Adder Vote
	 * @return an ASExpression equivalent
	 */
	@SuppressWarnings("unchecked")
	public static ASExpression toSExpression(Vote v){
		ArrayList<ASExpression> exps = new ArrayList<ASExpression>();
		
		for(ElgamalCiphertext cipher : (List<ElgamalCiphertext>)v.getCipherList())
			exps.add(toSExpression(cipher));
		
		return new ListExpression(exps);
	}
	
	/**
	 * Converts a SExpression to an Adder Vote.
	 * @param exp - the Auditorium SExpression
	 * @return an Adder Vote equivalent
	 */
	public static Vote toVote(ASExpression exp){
		ListExpression list = (ListExpression)exp;
		
		List<ElgamalCiphertext> ciphers = new ArrayList<ElgamalCiphertext>();
		
		for(int i = 0; i < list.size(); i++)
			ciphers.add(toCiphertext(list.get(i)));
		
		String strEquivalent = "";
		
		for(int i = 0; i < ciphers.size(); i++)
			strEquivalent+=ciphers.get(i).toString()+" ";
		
		return Vote.fromString(strEquivalent.trim());
	}
	
	/**
	 * Converts a SExpression to an AdderInteger.
	 * 
	 * @param exp - the SExpression
	 * @return the equivalent AdderInteger
	 */
	public static AdderInteger toAdderInteger(ASExpression exp){
		ListExpression list = (ListExpression)exp;
		
		BigInteger value = new BigInteger(list.get(0).toString());
		BigInteger mod = new BigInteger(list.get(1).toString());
		
		/*if(!(mod.equals(BigInteger.ZERO)))
			return new AdderInteger(value, mod);
		
		return new AdderInteger(value.toString(), 10);*/
		
		try{
			Class cInt = AdderInteger.class;
			Field fVal = cInt.getDeclaredField("val");
			Field fMod = cInt.getDeclaredField("m");

			fVal.setAccessible(true);
			fMod.setAccessible(true);
			
			AdderInteger i = new AdderInteger();
			fVal.set(i, value);
			fMod.set(i, mod);
			
			return i;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Converts an Adder ElgamalCiphertext into an Auditorium friendly SExpression
	 * @param cipher - the ciphertext to convert
	 * @return an ASExpression equivalent to cipher
	 */
	public static ASExpression toSExpression(ElgamalCiphertext cipher){
		ListExpression p = new ListExpression(StringExpression.makeString("p"), toSExpression(cipher.getP()));
		ListExpression g = new ListExpression(StringExpression.makeString("G"), toSExpression(cipher.getG()));
		ListExpression h = new ListExpression(StringExpression.makeString("H"), toSExpression(cipher.getH()));
		
		return new ListExpression(p, g, h);
	}
	
	/**
	 * Converts an Auditorium SExpression to an Adder ElgamalCiphertext
	 * 
	 * @param ase - an SExpression
	 * @return the ElgamalCiphertext equivalent
	 */
	public static ElgamalCiphertext toCiphertext(ASExpression ase){
		ListExpression exp = (ListExpression)ase;
		
		ListExpression p = (ListExpression)exp.get(0);
		ListExpression g = (ListExpression)exp.get(1);
		ListExpression h = (ListExpression)exp.get(2);
		
		return new ElgamalCiphertext(toAdderInteger(g.get(1)), toAdderInteger(h.get(1)), toAdderInteger(p.get(1)));
	}
	
	/**
	 * Converts a SExpression to an Adder MembershipProof
	 * 
	 * @param ase - SExpression
	 * @return a MembershipProof equivalent of ase
	 */
	public static MembershipProof toMembershipProof(ASExpression ase){
		ListExpression list = (ListExpression)ase;
		
		ListExpression p = (ListExpression)list.get(0);
		ListExpression yList = (ListExpression)list.get(1);
		ListExpression zList = (ListExpression)list.get(2);
		ListExpression sList = (ListExpression)list.get(3);
		ListExpression cList = (ListExpression)list.get(4);
		
		AdderInteger pI = Converter.toAdderInteger(p.get(1));
		
		List<AdderInteger> yListI = new ArrayList<AdderInteger>();
		for(int i = 1; i < yList.size(); i++)
			yListI.add(toAdderInteger(yList.get(i)));
		
		List<AdderInteger> zListI = new ArrayList<AdderInteger>();
		for(int i = 1; i < zList.size(); i++)
			zListI.add(toAdderInteger(zList.get(i)));
		
		List<AdderInteger> sListI = new ArrayList<AdderInteger>();
		for(int i = 1; i < sList.size(); i++)
			sListI.add(toAdderInteger(sList.get(i)));
		
		List<AdderInteger> cListI = new ArrayList<AdderInteger>();
		for(int i = 1; i < cList.size(); i++)
			cListI.add(toAdderInteger(cList.get(i)));
		
		StringBuffer sb = new StringBuffer(4096);

        sb.append("p");
        sb.append(pI);

        for (Iterator it = yListI.iterator(); it.hasNext();) {
            AdderInteger y = (AdderInteger) it.next();
            sb.append("y");
            sb.append(y);
        }

        for (Iterator it = zListI.iterator(); it.hasNext();) {
            AdderInteger z = (AdderInteger) it.next();
            sb.append("z");
            sb.append(z);
        }

        for (Iterator it = sListI.iterator(); it.hasNext();) {
            AdderInteger s = (AdderInteger) it.next();
            sb.append("s");
            sb.append(s);
        }

        for (Iterator it = cListI.iterator(); it.hasNext();) {
            AdderInteger c1 = (AdderInteger) it.next();
            sb.append("c");
            sb.append(c1);
        }

        return MembershipProof.fromString(sb.toString());
	}
	
	/**
	 * Converts an Adder MembershipProof to an SExpression
	 * 
	 * @param proof - a MembershipProof
	 * @return an ASExpression equivalent of the MembershipProof
	 */
	public static ASExpression toSExpression(MembershipProof proof){
		String str = proof.toString();

		ASExpression pExp = null;
		ASExpression yListExp = null;
		ASExpression zListExp = null;
		ASExpression sListExp = null;
		ASExpression cListExp = null;
		
		StringTokenizer st = new StringTokenizer(str, "pyzsc", true);
		int numTokens = st.countTokens() - 2;

		if ((numTokens % 8) != 0) {
			throw new
			InvalidMembershipProofException("number of tokens not divisible by 8");
		}

		int count = numTokens / 8;

		try {
			if (!st.nextToken().equals("p")) {
				throw new InvalidMembershipProofException("expected token: `p\'");
			}

			AdderInteger p = new AdderInteger(st.nextToken());

			AdderInteger q
			= p.subtract(AdderInteger.ONE).divide(AdderInteger.TWO);

			List<AdderInteger> yList
			= new ArrayList<AdderInteger>(count);

			for (int ySize = 0; ySize < count; ySize++) {
				if (!st.nextToken().equals("y")) {
					throw new
					InvalidMembershipProofException("expected token: `y\'");
				}

				yList.add(new AdderInteger(st.nextToken(), p));
			}

			List<AdderInteger> zList
			= new ArrayList<AdderInteger>(count);

			for (int zSize = 0; zSize < count; zSize++) {
				if (!st.nextToken().equals("z")) {
					throw new
					InvalidMembershipProofException("expected token: `z\'");
				}

				zList.add(new AdderInteger(st.nextToken(), p));
			}

			List<AdderInteger> sList
			= new ArrayList<AdderInteger>(count);

			for (int sSize = 0; sSize < count; sSize++) {
				if (!st.nextToken().equals("s")) {
					throw new
					InvalidMembershipProofException("expected token: `s\'");
				}

				sList.add(new AdderInteger(st.nextToken(), q));
			}

			List<AdderInteger> cList
			= new ArrayList<AdderInteger>(count);

			for (int cSize = 0; cSize < count; cSize++) {
				if (!st.nextToken().equals("c")) {
					throw new
					InvalidMembershipProofException("expected token: `c\'");
				}

				cList.add(new AdderInteger(st.nextToken(), q));
			}
			
			pExp = new ListExpression(StringExpression.makeString("p"), Converter.toSExpression(p));
			
			ArrayList<ASExpression> tempYList = new ArrayList<ASExpression>();
			tempYList.add(StringExpression.makeString("y"));
			
			for(AdderInteger y : yList)
				tempYList.add(toSExpression(y));
			
			yListExp = new ListExpression(tempYList);
			
			ArrayList<ASExpression> tempZList = new ArrayList<ASExpression>();
			tempZList.add(StringExpression.makeString("z"));
			
			for(AdderInteger z : zList)
				tempZList.add(toSExpression(z));
			
			zListExp = new ListExpression(tempZList);
			
			ArrayList<ASExpression> tempSList = new ArrayList<ASExpression>();
			tempSList.add(StringExpression.makeString("s"));
			
			for(AdderInteger s : sList)
				tempSList.add(toSExpression(s));
			
			sListExp = new ListExpression(tempSList);
			
			ArrayList<ASExpression> tempCList = new ArrayList<ASExpression>();
			tempCList.add(StringExpression.makeString("c"));
			
			for(AdderInteger c : cList)
				tempCList.add(toSExpression(c));
			
			cListExp = new ListExpression(tempCList);
		}catch(Exception e){
			return null;
		}
		
		return new ListExpression(pExp, yListExp, zListExp, sListExp, cListExp);
	}
}