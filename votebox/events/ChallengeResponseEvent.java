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
 * This is the event that happens as a response to a voter's challenge. It
 * essentially reveals the randomness values used in elgamal encryption.<br>
 * <br>
 * 
 * format: (challenge-response [nonce] [list-of-r-values])
 * 
 * @author kyle
 * 
 */
public class ChallengeResponseEvent implements IAnnounceEvent {

    private final int _serial;
    private final int _node;
    private final ASExpression _nonce;

    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "challenge-response" ), StringWildcard.SINGLETON,
                StringWildcard.SINGLETON);

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
            	int node = Integer.parseInt( ((ListExpression) res).get( 0 )
                        .toString() );
                StringExpression nonce = ((StringExpression) ((ListExpression) res)
                        .get( 1 ));
                return new ChallengeResponseEvent( serial, node, nonce);
            }
            return null;
        };
    };
    
    /**
     * 
     * @return a MatcherRule for parsing this type of event.
     */
    public static MatcherRule getMatcher(){
    	return MATCHER;
    }//getMatcher
    
    public ChallengeResponseEvent(int serial, int node, ASExpression nonce) {
        _serial = serial;
        _node = node;
        _nonce = nonce;
    }

    public void fire(VoteBoxEventListener l) {
        l.challengeResponse(this);
    }

    public int getSerial() {
        return _serial;
    }
    
    public int getNode() {
    	return _node;
    }

    public ASExpression getNonce(){
    	return _nonce;
    }//getNonce
    
    public ASExpression toSExp() {
        return new ListExpression(StringExpression
                .makeString("challenge-response"), StringExpression
                .makeString( Integer.toString( _node ) ), _nonce);
    }

}
