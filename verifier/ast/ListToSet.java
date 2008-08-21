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
 * This AST represents an application of the "convert" primitive.
 * 
 * @author kyle
 * 
 */
public class ListToSet extends AST {

	public static final ASTFactory FACTORY = new PrimFactory(1,
			new IConstructor() {

				public AST make(ASExpression from, AST... args) {
					return new ListToSet(from, args[0]);
				}
			}) {

		@Override
		public String getName() {
			return "list->set";
		}
	};

	private final AST _arg;

	private ListToSet(ASExpression from, AST arg) {
		super(from);
		_arg = arg;
	}

	/**
	 * @see verifier.ast.AST#eval(verifier.ActivationRecord)
	 */
	@Override
	public Value eval(ActivationRecord environment) {
		Value arg = _arg.eval(environment);

		return arg.execute(new AValueVisitor() {

			@Override
			public Value forExpression(Expression e) {
				ASExpression expression = e.getASE();
				if (!(expression instanceof ListExpression))
					throw new UnexpectedTypeException(e, "list");
				ListExpression list = (ListExpression) expression;

				Expression[] exps = new Expression[list.size()];
				for (int lcv = 0; lcv < exps.length; lcv++)
					exps[lcv] = new Expression(list.getArray()[lcv]);
				SetValue sv = new SetValue(exps);
				sv.seal();
				return sv;
			}

		});
	}

}
