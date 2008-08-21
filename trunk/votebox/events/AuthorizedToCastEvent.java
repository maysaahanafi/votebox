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
 * Event that represents the authorized-to-cast message:<br>
 * 
 * <pre>
 * (authorized-to-cast node-id nonce ballot)
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class AuthorizedToCastEvent implements IAnnounceEvent {

    private int serial;

    private int node;

    private byte[] nonce;

    private byte[] ballot;

    /**
     * The matcher for the AuthorizedToCastEvent.
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "authorized-to-cast" ), StringWildcard.SINGLETON,
                StringWildcard.SINGLETON, StringWildcard.SINGLETON );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                int node = Integer.parseInt( ((ListExpression) res).get( 0 )
                        .toString() );
                /*byte[] nonce = ((StringExpression) ((ListExpression) res)
                        .get( 1 )).getBytesCopy();*/
                byte[] nonce = new BigInteger(((StringExpression) ((ListExpression) res)
                        .get( 1 )).toString()).toByteArray();
                byte[] ballot = ((StringExpression) ((ListExpression) res)
                        .get( 2 )).getBytesCopy();
                return new AuthorizedToCastEvent( serial, node, nonce, ballot );
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
     * Constructs a new AuthorizedToCastEvent.
     * 
     * @param serial
     *            the serial number of the sender
     * @param node
     *            the node id
     * @param nonce
     *            the nonce (or authorization code), an array of bytes
     * @param ballot
     *            the ballot in zip format, stored as an array of bytes
     */
    public AuthorizedToCastEvent(int serial, int node, byte[] nonce,
            byte[] ballot) {
        this.serial = serial;
        this.node = node;
        this.nonce = nonce;
        this.ballot = ballot;
    }

    /**
     * @return the ballot
     */
    public byte[] getBallot() {
        return ballot;
    }

    /**
     * @return the node
     */
    public int getNode() {
        return node;
    }

    /**
     * @return the nonce, or authorization code
     */
    public byte[] getNonce() {
        return nonce;
    }

    public int getSerial() {
        return serial;
    }

    public void fire(VoteBoxEventListener l) {
        l.authorizedToCast( this );
    }

    public ASExpression toSExp() {
        /*return new ListExpression( StringExpression
                .makeString( "authorized-to-cast" ), StringExpression
                .makeString( Integer.toString( node ) ), StringExpression
                .makeString( nonce ), StringExpression.makeString( ballot ) );*/
    	return new ListExpression( StringExpression
                .makeString( "authorized-to-cast" ), StringExpression
                .makeString( Integer.toString( node ) ), StringExpression
                .makeString( new BigInteger(nonce).toString() ), StringExpression.makeString( ballot ) );
    }
    
}
