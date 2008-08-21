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

/**
 * This AST node represents the "exists" quantifier.
 * 
 * @author kyle
 * 
 */
public class Exists extends Quantifier {

	public static final ASTFactory FACTORY = new QuantifierFactory(
			new IQuantifierConstructor() {

				public AST make(String name, AST set, AST body, int index,
						ArrayList<Binding<AST, ActivationRecord>> map) {
					return new Exists(name, set, body, index, map);
				}
			}) {

		@Override
		public String getName() {
			return "exists";
		}
	};

	public static final ASTFactory PFACTORY = new PQuantifierFactory(
			new IQuantifierConstructor() {

				public AST make(String name, AST set, AST body, int index,
						ArrayList<Binding<AST, ActivationRecord>> map) {
					return new Exists(name, set, body, index, map);
				}
			}) {

		@Override
		public String getName() {
			return "pexists";
		}
	};

	public Exists(String name, AST set, AST body, int index,
			ArrayList<Binding<AST, ActivationRecord>> unknowns) {
		super(name, set, body, index, unknowns);
	}

	@Override
	public void forEvalFalse(Box<Boolean> box) {
	}

	@Override
	public void forEvalTrue(Box<Boolean> box) {
		box.set(true);
	}

	@Override
	public Value result(Box<Boolean> box, boolean sealed,
			ArrayList<Binding<AST, ActivationRecord>> newUnknowns,
			Box<Integer> newIndex) {
		if (box.get().booleanValue() == true)
			return True.SINGLETON;
		if (sealed && newUnknowns.size() == 0)
			return False.SINGLETON;
		return new Reduction(new Exists(_name, _set, _body, newIndex.get(),
				newUnknowns));
	}

	@Override
	public String getPName() {
		return "pexists";
	}
}
