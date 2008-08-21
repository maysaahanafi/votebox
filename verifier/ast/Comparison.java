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
import verifier.value.*;
import verifier.*;

public abstract class Comparison extends AST {
	public abstract Value compare(int left, int right);

	private final AST _left;

	private final AST _right;

	protected Comparison(ASExpression from, AST left, AST right) {
		super(from);
		_left = left;
		_right = right;
	}

	public Value eval(ActivationRecord environment) {
		final Value left = _left.eval(environment);
		final Value right = _right.eval(environment);
		return left.execute(new AValueVisitor() {

			@Override
			public Value forInt(final IntValue left) {
				return right.execute(new AValueVisitor() {

					@Override
					public Value forInt(IntValue right) {
						return compare(left.get(), right.get());
					}

				});
			}

		});
	}
}
