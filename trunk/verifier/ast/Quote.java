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
package verifier.ast;

import sexpression.*;
import verifier.*;
import verifier.value.*;

/**
 * This AST node represents an application of the "quote" primitive.
 * 
 * @author kyle
 * 
 */
public class Quote extends AST {

	public static final ASTFactory FACTORY = new ASTFactory() {

		@Override
		public String getName() {
			return "quote";
		}

		@Override
		public String getPattern() {
			return "(quote #any)";
		}

		@Override
		public AST make(ASExpression from, ListExpression matchresult,
				ASTParser parser) {
			return new Quote(from, matchresult.get(0));
		}
	};

	private ASExpression _arg;

	private Quote(ASExpression from, ASExpression arg) {
		super(from);
		_arg = arg;
	}

	/**
	 * @see verifier.ast.AST#eval(verifier.ActivationRecord)
	 */
	@Override
	public Value eval(ActivationRecord environment) {
		return new Expression(_arg);
	}

}
