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

import verifier.ast.*;
import sexpression.*;

/**
 * The verifier will evaluate an AST to a reduction if there isn't enough
 * information to evaluate what the rule asks for. The wrapped AST is a simpler
 * problem than was given (a reduction).
 * 
 * @author kyle
 * 
 */
public class Reduction extends Value {

	private final AST _ast;

	/**
	 * @param ast
	 *            This is the wrapped AST
	 */
	public Reduction(AST ast) {
		super(true);
		_ast = ast;
	}

	/**
	 * @return This method returns the AST that represents the reduction.
	 *         Evaluate this AST in an attempt to "try again" with the
	 *         verification.
	 */
	public AST getAST() {
		return _ast;
	}

	/**
	 * @see verifier.value.Value#execute(verifier.value.IValueVisitor)
	 */
	@Override
	public Value execute(IValueVisitor visitor) {
		return visitor.forReduction(this);
	}

	@Override
	public ASExpression toASE() {
		return new ListExpression(StringExpression.make("red"), _ast.toASE());
	}

	@Override
	public String toString() {
		return toASE().toString();
	}
}
