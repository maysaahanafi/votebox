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
 * Event that represents the assign-label message:<br>
 * 
 * <pre>
 * (assign-label node-id new-label)
 * </pre>
 * 
 * See <a href="https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages">
 * https://sys.cs.rice.edu/votebox/trac/wiki/VotingMessages</a> for a complete
 * list of messages.
 * 
 * @author cshaw
 */
public class AssignLabelEvent implements IAnnounceEvent {

    private int serial;

    private int node;

    private int label;

    /**
     * Matcher for the AssignLabelEvent.
     */
    private static MatcherRule MATCHER = new MatcherRule() {
        private ASExpression pattern = new ListExpression( StringExpression
                .makeString( "assign-label" ), StringWildcard.SINGLETON,
                StringWildcard.SINGLETON );

        public IAnnounceEvent match(int serial, ASExpression sexp) {
            ASExpression res = pattern.match( sexp );
            if (res != NoMatch.SINGLETON) {
                int node = Integer.parseInt( ((ListExpression) res).get( 0 )
                        .toString() );
                int label = Integer.parseInt( ((ListExpression) res).get( 1 )
                        .toString() );
                return new AssignLabelEvent( serial, node, label );
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
     * Constructs a new AssignLabelEvent.
     * 
     * @param serial
     *            the serial number of the sender
     * @param node
     *            the node id
     * @param label
     *            the new label
     */
    public AssignLabelEvent(int serial, int node, int label) {
        this.serial = serial;
        this.node = node;
        this.label = label;
    }

    /**
     * @return the new label
     */
    public int getLabel() {
        return label;
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

    public void fire(VoteBoxEventListener l) {
        l.assignLabel( this );
    }

    public ASExpression toSExp() {
        return new ListExpression(
                StringExpression.makeString( "assign-label" ), StringExpression
                        .makeString( Integer.toString( node ) ),
                StringExpression.makeString( Integer.toString( label ) ) );
    }

}
