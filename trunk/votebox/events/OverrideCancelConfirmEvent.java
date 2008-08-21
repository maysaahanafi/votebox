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

import java.math.BigInteger;

import sexpression.*;

/**
 * Event that represents the override-cancel-confirm message:<br>
 * 
 * <pre>
 * (override-cancel-confirm nonce)
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class OverrideCancelConfirmEvent implements IAnnounceEvent {

    private int serial;

    private byte[] nonce;

    /**
     * Matcher for the OverrideCancelConfirmEvent
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "override-cancel-confirm" ),
                StringWildcard.SINGLETON );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                /*byte[] nonce = ((StringExpression) ((ListExpression) res)
                        .get( 0 )).getBytesCopy();*/
            	byte[] nonce = new BigInteger(((StringExpression) ((ListExpression) res)
                        .get( 0 )).toString()).toByteArray();
                return new OverrideCancelConfirmEvent( serial, nonce );
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
     * Constructs a new OverrideCancelConfirmEvent
     * 
     * @param serial
     *            the serial number of the sender
     * @param nonce
     *            the nonce
     */
    public OverrideCancelConfirmEvent(int serial, byte[] nonce) {
        this.serial = serial;
        this.nonce = nonce;
    }

    /**
     * @return the nonce
     */
    public byte[] getNonce() {
        return nonce;
    }

    public int getSerial() {
        return serial;
    }

    public void fire(VoteBoxEventListener l) {
        l.overrideCancelConfirm( this );
    }

    public ASExpression toSExp() {
        /*return new ListExpression( StringExpression
                .makeString( "override-cancel-confirm" ), StringExpression
                .makeString( nonce ) );*/
    	return new ListExpression( StringExpression
                .makeString( "override-cancel-confirm" ), StringExpression
                .makeString( new BigInteger(nonce).toString() ) );
    }

}
