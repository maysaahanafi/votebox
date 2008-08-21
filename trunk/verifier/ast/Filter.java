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

import java.util.ArrayList;

import sexpression.*;
import verifier.*;
import verifier.value.AValueVisitor;
import verifier.value.Expression;
import verifier.value.Value;
import verifier.value.SetValue;

public class Filter extends AST {

	public static final ASTFactory FACTORY = new PrimFactory(2,
			new IConstructor() {

				public AST make(ASExpression from, AST... args) {
					return new Filter(from, args[0], args[1]);
				}
			}) {

		@Override
		public String getName() {
			return "filter";
		}
	};

	private final AST _set;

	private final AST _pattern;

	private Filter(ASExpression from, AST set, AST pattern) {
		super(from);
		_set = set;
		_pattern = pattern;
	}

	@Override
	public Value eval(ActivationRecord environment) {
		final Value set = _set.eval(environment);
		final Value pattern = _pattern.eval(environment);

		final ArrayList<Expression> lst = new ArrayList<Expression>();
		set.execute(new AValueVisitor() {

			@Override
			public Value forSet(SetValue s) {
				for (int lcv = 0; lcv < s.size(); lcv++) {
					final Expression exp = s.get(lcv);
					pattern.execute(new AValueVisitor() {

						@Override
						public Value forExpression(Expression pattern) {
							ASExpression patternase = pattern.getASE();
							ASExpression ase = exp.getASE();
							if (patternase.match(ase) != NoMatch.SINGLETON)
								lst.add(exp);
							return null;
						}

					});
				}
				return null;

			}

		});

		return new SetValue(lst.toArray(new Expression[0]));
	}

}
