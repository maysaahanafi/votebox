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
 * Event that represents the supervisor message:<br>
 * 
 * <pre>
 * (supervisor local-timestamp supervisor-status)
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class SupervisorEvent implements IAnnounceEvent {

    private int serial;

    private long timestamp;

    private String status;

    /**
     * Matcher for the supervisor message
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "supervisor" ), StringWildcard.SINGLETON,
                StringWildcard.SINGLETON );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                long timestamp = Long.parseLong( ((ListExpression) res).get( 0 )
                        .toString() );
                String status = ((ListExpression) res).get( 1 ).toString();
                return new SupervisorEvent( serial, timestamp, status );
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
     * Constructs a new SupervisorEvent
     * 
     * @param serial
     *            the serial number of the sender
     * @param timestamp
     *            the local timestamp
     * @param status
     *            the supervisor's status
     */
    public SupervisorEvent(int serial, long timestamp, String status) {
        this.serial = serial;
        this.timestamp = timestamp;
        this.status = status;
    }

    public int getSerial() {
        return serial;
    }

    /**
     * @return the status, either "active" or "inactive"
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    public void fire(VoteBoxEventListener l) {
        l.supervisor( this );
    }

    public ASExpression toSExp() {
        return new ListExpression( StringExpression.makeString( "supervisor" ),
                StringExpression.makeString( Long.toString( timestamp ) ),
                StringExpression.makeString( status ) );
    }

}
