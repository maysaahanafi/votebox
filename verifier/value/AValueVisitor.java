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
package verifier.value;

import verifier.*;

public abstract class AValueVisitor implements IValueVisitor {

    public Value forExpression(Expression ase) {
        throw new UnexpectedTypeException( ase, "expression" );
    }

    public Value forFalse(False f) {
        throw new UnexpectedTypeException( f, "false value" );
    }

    public Value forInt(IntValue i) {
        throw new UnexpectedTypeException( i, "integer" );
    }

    public Value forSet(SetValue s) {
        throw new UnexpectedTypeException( s, "set" );
    }

    public Value forTrue(True t) {
        throw new UnexpectedTypeException( t, "true value" );
    }

    public Value forDAG(DAGValue d) {
        throw new UnexpectedTypeException( d, "dag value" );
    }

    public Value forReduction(Reduction ast) {
        throw new UnexpectedTypeException( ast, "ast value" );
    }
}
