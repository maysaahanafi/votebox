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

import sexpression.ASExpression;
import sexpression.ListExpression;
import sexpression.NoMatch;
import sexpression.StringExpression;
import sexpression.StringWildcard;

public class BallotCountedEvent extends BallotReceivedEvent{

	public BallotCountedEvent(int serial, int node, byte[] nonce) {
		super(serial, node, nonce);
	}
	
	/**
     * The matcher for the BallotReceivedEvent.
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "ballot-counted" ), StringWildcard.SINGLETON,
                StringWildcard.SINGLETON );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                int node = Integer.parseInt( ((ListExpression) res).get( 0 )
                        .toString() );
                /*byte[] nonce = ((StringExpression) ((ListExpression) res)
                        .get( 1 )).getBytesCopy();*/
                byte[] nonce = new BigInteger(((StringExpression) ((ListExpression) res)
                        .get( 1 )).toString()).toByteArray();
                return new BallotCountedEvent( serial, node, nonce );
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
    
    @Override
    public void fire(VoteBoxEventListener l) {
        l.ballotCounted( this );
    }

    public ASExpression toSExp() {
        /*return new ListExpression( StringExpression
                .makeString( "ballot-counted" ), StringExpression
                .makeString( Integer.toString( getNode() ) ), StringExpression
                .makeString( getNonce() ) );*/
    	
    	return new ListExpression( StringExpression
                .makeString( "ballot-counted" ), StringExpression
                .makeString( Integer.toString( getNode() ) ), StringExpression
                .makeString( new BigInteger(getNonce()).toString() ) );
    }
    
}
