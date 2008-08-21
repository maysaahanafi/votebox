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
 * Class used to indicate that the previously committed ballot (corresponding to a nonce) is not
 * being challeneged, and should instead be tallied.
 * 
 * @author Montrose
 *
 */
public class CastCommittedBallotEvent extends CastBallotEvent {

	private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = ASExpression
                .make("(cast-committed-ballot %nonce:#string)");

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            HashMap<String, ASExpression> result = pattern.namedMatch(sexp);
            if (result != NamedNoMatch.SINGLETON)
                return new CastCommittedBallotEvent(serial, result.get("nonce"));

            return null;
        };
    };
	
    /**
     * 
     * @return a MatcherRule for use in identifying and/or parsing this event.
     */
    public static MatcherRule getMatcher(){
    	return MATCHER;
    }
    
    /**
     * Creates a new CastCommittedBallotEvent
     * 
     * @param serial - Serial number of the machine sending the message
     * @param nonce - Nonce of the voting transaction in question.
     */
	public CastCommittedBallotEvent(int serial, ASExpression nonce) {
		super(serial, nonce, StringExpression.EMPTY);
	}
	
	/**
     * @see votebox.events.IAnnounceEvent#toSExp()
     */
	@Override
    public ASExpression toSExp() {
        return new ListExpression(StringExpression.makeString("cast-committed-ballot"),
                getNonce());
    }

}
