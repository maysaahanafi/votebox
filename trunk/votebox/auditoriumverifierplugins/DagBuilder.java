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
package votebox.auditoriumverifierplugins;

import java.util.ArrayList;
import java.util.HashMap;

import auditorium.*;

import sexpression.*;
import verifier.*;
import verifier.value.*;

/**
 * A DAG builder incrementally accepts auditorium messages and builds a DAG
 * which represents the timeline of them. At any point during this process, the
 * user can ask that a verifier.DAG be constructed to represent what has been
 * seen so far.
 * 
 * @author kyle
 * 
 */
public class DagBuilder {

    /*
     * Matching against this pattern should yield the following: [0]: cert [1]:
     * signer id [2]: sigdata [3]: list of pointers that preceed [4]: data
     */
    private static final ASExpression PATTERN = new ListExpression(
            StringExpression.makeString( "signed-message" ),
            Wildcard.SINGLETON, new ListExpression( StringExpression
                    .makeString( "signature" ), StringWildcard.SINGLETON,
                    StringWildcard.SINGLETON, new ListExpression(
                            StringExpression.makeString( "succeeds" ),
                            new ListWildcard( MessagePointer.PATTERN ),
                            Wildcard.SINGLETON ) ) );

    /// mapping of ptr-->(listof predecessor ptrs)  
    private HashMap<Expression, ArrayList<Expression>> _predecessors;
    /// mapping of ptr-->full-message
    private HashMap<Expression, Expression> _ptrToMsg;
    /// mapping of message-->its own ptr
    private HashMap<Expression, Expression> _msgToPtr;

    public DagBuilder() {
        _predecessors = new HashMap<Expression, ArrayList<Expression>>();
        _ptrToMsg = new HashMap<Expression, Expression>();
        _msgToPtr = new HashMap<Expression, Expression>();
    }

    /**
     * Add a message to the list of messages that this builder is holding
     * 
     * @param message
     *            Add this message to the list
     * @throws FormatException
     *             This mehtod throws if the given message's datum is not
     *             formatted as expected.
     */
    public void add(Message message) throws FormatException {
        try {
        	Expression ptr = new Expression(
        			new MessagePointer( message ).toASE());
        	Expression expr = new Expression( message.toASE() );
            
        	// store ptr-->message mapping in DAG
        	_ptrToMsg.put( ptr, expr );
            _msgToPtr.put( expr, ptr );

            ASExpression matchresult = PATTERN.match( message.getDatum() );
            if (matchresult == NoMatch.SINGLETON)
                throw new FormatException( message.getDatum(), new Exception(
                        "didn't match pattern for an Auditorium message: "
                		+ PATTERN ) );
            ListExpression matchlist = (ListExpression) matchresult;

            ArrayList<Expression> ptrlst = new ArrayList<Expression>();
            for (ASExpression ptrexp : (ListExpression) matchlist.get( 3 )) {
                ptrlst.add( new Expression( 
                		new MessagePointer( ptrexp ).toASE() ) );
            }
            
            _predecessors.put( ptr, ptrlst );
        }
        catch (IncorrectFormatException e) {
            throw new FormatException( message.getDatum(), e );
        }
    }

    /**
     * @return This method returns a dag which reflects the collection of
     *         messages that have been given so far.
     */
    public DAGValue toDAG() {
        return new ExplicitDAG( _ptrToMsg, _msgToPtr, _predecessors );
    }
}
