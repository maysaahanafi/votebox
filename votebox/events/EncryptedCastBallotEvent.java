/**
  * This file is part of VoteBox.
  * 
  * VoteBox is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License as published by
  * the Free Software Foundation, either version 3 of the License, or
  * (at your option) any later version.
  * 
  * VoteBox is distributed in the hope that it will be useful,
  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  * GNU General Public License for more details.
  * 
  * You should have received a copy of the GNU General Public License
  * along with VoteBox.  If not, see <http://www.gnu.org/licenses/>.
 */
package votebox.events;

import java.util.HashMap;

import sexpression.ASExpression;
import sexpression.ListExpression;
import sexpression.NamedNoMatch;
import sexpression.StringExpression;

/**
 * An event resulting from the receipt of a encrypted-cast-ballot event.<BR>
 * Form:<BR>
 * (encrypted-cast-ballot [nonce] [ballot])<BR>
 * Where [ballot] is in the form ((cand-#1-vote-race1 cand-#2-vote-race1) (cand-#1-vote-race2 ...))
 * with a whole message of E(...) thrown in.
 * @author Montrose
 */
public class EncryptedCastBallotEvent extends CastBallotEvent{
	
    /**
     * Matcher for the EncryptedCastBallotEvent
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = ASExpression
                .make("(encrypted-cast-ballot %nonce:#string %ballot:#any)");

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            HashMap<String, ASExpression> result = pattern.namedMatch(sexp);
            if (result != NamedNoMatch.SINGLETON)
                return new EncryptedCastBallotEvent(serial, result.get("nonce"), result
                        .get("ballot"));

            return null;
        };
    };
	
    /**
     * 
     * @return a MatcherRule for parsing this event type.
     */
    public static MatcherRule getMatcher(){
    	return MATCHER;
    }//getMatcher
    
	/**
     * @param serial
     *            the serial number of the sender
     * @param nonce
     *            the nonce
     * @param ballot
     *            the encrypted ballot, as an array of bytes
     */
    public EncryptedCastBallotEvent(int serial, ASExpression nonce, ASExpression ballot) {
        super(serial, nonce, ballot);
    }

	/**
     * @see votebox.events.IAnnounceEvent#toSExp()
     */
	public ASExpression toSExp() {
		return new ListExpression(StringExpression.makeString("encrypted-cast-ballot"),
                getNonce(), getBallot());
	}
	
}
