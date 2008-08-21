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

public class Greater extends Comparison {

	public static final ASTFactory FACTORY = new ComparisonFactory(
			new IConstructor() {

				public AST make(ASExpression from, AST... args) {
					return new Greater(from, args[0], args[1]);
				}
			}) {

		@Override
		public String getName() {
			return ">";
		}
	};

	protected Greater(ASExpression from, AST left, AST right) {
		super(from, left, right);
	}

	@Override
	public Value compare(int left, int right) {
		if (left > right)
			return True.SINGLETON;
		return False.SINGLETON;
	}

}
