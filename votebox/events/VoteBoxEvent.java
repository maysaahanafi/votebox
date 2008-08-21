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
 * Event that represents the votebox message:<br>
 * 
 * <pre>
 * (votebox label votebox-status battery-health protected-count public-count)
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class VoteBoxEvent implements IAnnounceEvent {

    private int serial;

    private int label;

    private String status;

    private int battery;

    private int protectedCount;

    private int publicCount;

    /**
     * Matcher for the VoteBoxEvent.
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "votebox" ), StringWildcard.SINGLETON,
                StringWildcard.SINGLETON, StringWildcard.SINGLETON,
                StringWildcard.SINGLETON, StringWildcard.SINGLETON );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                int label = Integer.parseInt( ((ListExpression) res).get( 0 )
                        .toString() );
                String status = ((ListExpression) res).get( 1 ).toString();
                int battery = Integer.parseInt( ((ListExpression) res).get( 2 )
                        .toString() );
                int protectedCount = Integer.parseInt( ((ListExpression) res)
                        .get( 3 ).toString() );
                int publicCount = Integer.parseInt( ((ListExpression) res).get(
                    4 ).toString() );
                return new VoteBoxEvent( serial, label, status, battery,
                        protectedCount, publicCount );
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
     * Constructs a new VoteBoxEvent
     * 
     * @param serial
     *            the serial number of the sender
     * @param label
     *            the label
     * @param status
     *            the votebox's status
     * @param battery
     *            the battery level
     * @param protectedCount
     *            the protected count
     * @param publicCount
     *            the public count
     */
    public VoteBoxEvent(int serial, int label, String status, int battery,
            int protectedCount, int publicCount) {
        this.serial = serial;
        this.label = label;
        this.status = status;
        this.battery = battery;
        this.protectedCount = protectedCount;
        this.publicCount = publicCount;
    }

    /**
     * @return the battery level
     */
    public int getBattery() {
        return battery;
    }

    /**
     * @return the label
     */
    public int getLabel() {
        return label;
    }

    /**
     * @return the protected count
     */
    public int getProtectedCount() {
        return protectedCount;
    }

    /**
     * @return the public count
     */
    public int getPublicCount() {
        return publicCount;
    }

    public int getSerial() {
        return serial;
    }

    /**
     * @return the status, either "ready" or "in-use"
     */
    public String getStatus() {
        return status;
    }

    public ASExpression toSExp() {
        return new ListExpression( StringExpression.makeString( "votebox" ),
                StringExpression.makeString( Integer.toString( label ) ),
                StringExpression.makeString( status ), StringExpression
                        .makeString( Integer.toString( battery ) ),
                StringExpression
                        .makeString( Integer.toString( protectedCount ) ),
                StringExpression.makeString( Integer.toString( publicCount ) ) );
    }

    public void fire(VoteBoxEventListener l) {
        l.votebox( this );
    }

}
