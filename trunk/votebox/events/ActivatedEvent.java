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

import java.util.ArrayList;
import java.util.List;

import sexpression.*;

/**
 * Event that represents the activated message:<br>
 * 
 * <pre>
 * (activated ((status)*))
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class ActivatedEvent implements IAnnounceEvent {

    private int serial;

    private List<StatusEvent> statuses;

    /**
     * Matcher for the ActivatedEvent.
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "activated" ), new ListWildcard( new ListWildcard(
                Wildcard.SINGLETON ) ) );

        private VoteBoxEventMatcher statusMatcher = new VoteBoxEventMatcher(
                StatusEvent.getMatcher() );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                ArrayList<StatusEvent> statuses = new ArrayList<StatusEvent>();
                for (ASExpression s : (ListExpression) ((ListExpression) res)
                        .get( 0 )) {
                    StatusEvent status = (StatusEvent) statusMatcher.match( 0,
                        s );
                    if (status == null)
                        return null;
                    statuses.add( status );
                }
                return new ActivatedEvent( serial, statuses );
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
     * Constructs a new ActivatedEvent with given serial number and list of
     * known statuses
     * 
     * @param serial
     *            the serial number
     * @param statuses
     *            the list of known statuses
     */
    public ActivatedEvent(int serial, List<StatusEvent> statuses) {
        this.serial = serial;
        this.statuses = statuses;
    }

    public int getSerial() {
        return serial;
    }

    /**
     * @return the list of known statuses
     */
    public List<StatusEvent> getStatuses() {
        return statuses;
    }

    public void fire(VoteBoxEventListener l) {
        l.activated( this );
    }

    public ASExpression toSExp() {
        ArrayList<ASExpression> statusList = new ArrayList<ASExpression>();
        for (IAnnounceEvent s : statuses)
            statusList.add( s.toSExp() );
        return new ListExpression( StringExpression.makeString( "activated" ),
                new ListExpression( statusList ) );
    }

}
