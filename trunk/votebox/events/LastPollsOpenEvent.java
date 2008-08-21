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
 * Event that represents the last-polls-open message:<br>
 * 
 * <pre>
 * (last - polls - open( polls - open ))
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class LastPollsOpenEvent implements IAnnounceEvent {

    private int serial;

    private PollsOpenEvent pollsOpenMsg;

    /**
     * Matcher for the LastPollsOpenEvent.
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "last-polls-open" ), new ListWildcard(
                Wildcard.SINGLETON ) );

        private VoteBoxEventMatcher pollsOpenMatcher = new VoteBoxEventMatcher(
                PollsOpenEvent.getMatcher() );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                IAnnounceEvent pollsOpenMsg = pollsOpenMatcher.match( 0,
                    ((ListExpression) res).get( 0 ) );
                if (pollsOpenMsg == null)
                    return null;
                return new LastPollsOpenEvent( serial,
                        (PollsOpenEvent) pollsOpenMsg );
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
     * Constructs a new LastPollsOpenEvent.
     * 
     * @param serial
     *            the serial number of the sender
     * @param pollsOpenMsg
     *            a PollsOpenEvent representing the last polls-open message seen
     */
    public LastPollsOpenEvent(int serial, PollsOpenEvent pollsOpenMsg) {
        super();
        this.serial = serial;
        this.pollsOpenMsg = pollsOpenMsg;
    }

    public int getSerial() {
        return serial;
    }

    /**
     * @return the polls open message as an event
     */
    public PollsOpenEvent getPollsOpenMsg() {
        return pollsOpenMsg;
    }

    public void fire(VoteBoxEventListener l) {
        l.lastPollsOpen( this );
    }

    public ASExpression toSExp() {
        return new ListExpression( StringExpression
                .makeString( "last-polls-open" ), pollsOpenMsg.toSExp() );
    }

}
