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

import sexpression.ASExpression;
import verifier.*;
import verifier.value.*;

public class Or extends ListArgConnective {

	public static final ASTFactory FACTORY = new ListArgFactory(
			new IConstructor() {

				public AST make(ASExpression from, AST... args) {
					return new Or(args);
				}
			}) {

		@Override
		public String getName() {
			return "or";
		}
	};

	protected Or(AST[] args) {
		super(args);
	}

	@Override
	public void forEvalFalse(Box<Boolean> box) {
	}

	@Override
	public void forEvalTrue(Box<Boolean> box) {
		box.set(true);
	}

	@Override
	public Value result(Box<Boolean> box, ArrayList<AST> unknowns) {
		if (box.get().booleanValue() == true)
			return True.SINGLETON;
		if (unknowns.size() > 0)
			return new Reduction(new Or(unknowns.toArray(new AST[0])));
		return False.SINGLETON;
	}

}
