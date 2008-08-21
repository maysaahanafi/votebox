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
 * Event that represents the status message:<br>
 * 
 * <pre>
 * (status node-id (supervisor|votebox))
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class StatusEvent implements IAnnounceEvent {

    private int serial;

    private int node;

    private IAnnounceEvent status;

    /**
     * Matcher for the StatusEvent.
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "status" ), StringWildcard.SINGLETON,
                new ListWildcard( Wildcard.SINGLETON ) );

        private VoteBoxEventMatcher statusMatcher = new VoteBoxEventMatcher(
                SupervisorEvent.getMatcher(), VoteBoxEvent.getMatcher() );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                int node = Integer.parseInt( ((ListExpression) res).get( 0 )
                        .toString() );
                IAnnounceEvent status = statusMatcher.match( 0,
                    ((ListExpression) res).get( 1 ) );
                if (status == null)
                    return null;
                return new StatusEvent( serial, node, status );
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
     * Constructs a new StatusEvent with given serial number, node, and status
     * 
     * @param serial
     *            the serial number
     * @param node
     *            the node
     * @param status
     *            the status
     */
    public StatusEvent(int serial, int node, IAnnounceEvent status) {
        this.serial = serial;
        this.node = node;
        this.status = status;
    }

    /**
     * @return the node
     */
    public int getNode() {
        return node;
    }

    public int getSerial() {
        return serial;
    }

    /**
     * @return the statuses
     */
    public IAnnounceEvent getStatus() {
        return status;
    }

    public void fire(VoteBoxEventListener l) {
        throw new UnsupportedOperationException();
    }

    public ASExpression toSExp() {
        return new ListExpression( StringExpression.makeString( "status" ),
                StringExpression.makeString( Integer.toString( node ) ), status
                        .toSExp() );
    }

}
