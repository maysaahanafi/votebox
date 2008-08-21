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

import sexpression.*;

/**
 * Event that represents the polls-open? message:<br>
 * 
 * <pre>
 * (polls-open? keyword)
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class PollsOpenQEvent implements IAnnounceEvent {

    private int serial;

    private String keyword;

    /**
     * Matcher for the PollsOpenEvent.
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "polls-open?" ), StringWildcard.SINGLETON );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                String keyword = ((ListExpression) res).get( 0 ).toString();
                return new PollsOpenQEvent( serial, keyword );
            }

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
     * Constructs a new PollsOpenEvent
     * 
     * @param serial
     *            the serial number
     * @param keyword
     *            the keyword
     */
    public PollsOpenQEvent(int serial, String keyword) {
        this.serial = serial;
        this.keyword = keyword;
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    public int getSerial() {
        return serial;
    }

    public void fire(VoteBoxEventListener l) {
        l.pollsOpenQ( this );
    }

    public ASExpression toSExp() {
        return new ListExpression(
                StringExpression.makeString( "polls-open?" ), StringExpression
                        .makeString( keyword ) );
    }

}
