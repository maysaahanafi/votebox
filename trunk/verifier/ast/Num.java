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

import sexpression.ASExpression;
import verifier.*;
import verifier.value.*;

/**
 * Convert a string to its number equivalent.
 * 
 * @author kyle
 * 
 */
public class Num extends AST {

	public static final ASTFactory FACTORY = new PrimFactory(1,
			new IConstructor() {

				public AST make(ASExpression from, AST... args) {
					return new Num(from, args[0]);
				}
			}) {

		@Override
		public String getName() {
			return "string->num";
		}
	};

	private final AST _arg;

	private Num(ASExpression from, AST arg) {
		super(from);
		_arg = arg;
	}

	@Override
	public Value eval(ActivationRecord environment) {
		Value arg = _arg.eval(environment);

		return arg.execute(new AValueVisitor() {

			@Override
			public Value forExpression(Expression e) {
				try {
					return new IntValue(Integer.parseInt(e.getASE().toString()));
				} catch (NumberFormatException err) {
					throw new FormatException(e.getASE(), err);
				}
			}

		});
	}
}
