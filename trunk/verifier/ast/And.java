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

import verifier.*;
import verifier.value.*;

import sexpression.*;

/**
 * This AST node represents the sentential connective "and."
 * 
 * @author kyle
 * 
 */
public class And extends ListArgConnective {

	public static final ListArgFactory FACTORY = new ListArgFactory(
			new IConstructor() {

				public AST make(ASExpression from, AST... args) {
					return new And(args);
				}
			}) {

		@Override
		public String getName() {
			return "and";
		}
	};

	private And(AST[] args) {
		super(args);
	}

	@Override
	public void forEvalFalse(Box<Boolean> box) {
		box.set(true);
	}

	@Override
	public void forEvalTrue(Box<Boolean> box) {
	}

	@Override
	public Value result(Box<Boolean> box, ArrayList<AST> unknowns) {
		if (box.get().booleanValue() == true)
			return False.SINGLETON;
		if (unknowns.size() > 0)
			return new Reduction(new And(unknowns.toArray(new AST[0])));
		return True.SINGLETON;
	}
}
