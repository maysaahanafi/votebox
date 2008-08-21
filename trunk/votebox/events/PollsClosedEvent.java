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
 * Event that represents the polls-closed message:<br>
 * 
 * <pre>
 * (polls-closed local-timestamp)
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class PollsClosedEvent implements IAnnounceEvent {

    private int serial;

    private long timestamp;

    /**
     * Matcher for the PollsClosedEvent
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "polls-closed" ), StringWildcard.SINGLETON );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                long timestamp = Long.parseLong( ((ListExpression) res).get( 0 )
                        .toString() );
                return new PollsClosedEvent( serial, timestamp );
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
     * Constructs a new PollsClosedEvent
     * 
     * @param serial
     *            the serial number of the sender
     * @param timestamp
     *            the local timestamp
     */
    public PollsClosedEvent(int serial, long timestamp) {
        this.serial = serial;
        this.timestamp = timestamp;
    }

    public int getSerial() {
        return serial;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    public void fire(VoteBoxEventListener l) {
        l.pollsClosed( this );
    }

    public ASExpression toSExp() {
        return new ListExpression(
                StringExpression.makeString( "polls-closed" ), StringExpression
                        .makeString( Long.toString( timestamp ) ) );
    }
    
}
