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
import verifier.value.*;

public abstract class ListArgConnective extends AST {

	public abstract Value result(Box<Boolean> box, ArrayList<AST> unknowns);

	public abstract void forEvalTrue(Box<Boolean> box);

	public abstract void forEvalFalse(Box<Boolean> box);

	private final AST[] _args;

	private ASExpression _ase;

	protected ListArgConnective(AST[] args) {
		super(null);
		_args = args;
	}

	/**
	 * @see verifier.ast.AST#toASE()
	 */
	@Override
	public ASExpression toASE() {
		if (_ase == null) {
			ASExpression[] arr = new ASExpression[_args.length];
			for (int lcv = 0; lcv < arr.length; lcv++)
				arr[lcv] = _args[lcv].toASE();
			_ase = new ListExpression(arr);
		}
		return _ase;
	}

	/**
	 * @see verifier.ast.AST#eval(verifier.ActivationRecord)
	 */
	@Override
	public Value eval(ActivationRecord environment) {
		final Box<Boolean> box = new Box<Boolean>(false);
		final ArrayList<AST> unknowns = new ArrayList<AST>();

		for (final AST ast : _args) {
			ast.eval(environment).execute(new AValueVisitor() {

				@Override
				public Value forTrue(True t) {
					forEvalTrue(box);
					return null;
				}

				@Override
				public Value forFalse(False f) {
					forEvalFalse(box);
					return null;
				}

				@Override
				public Value forReduction(Reduction reduction) {
					unknowns.add(reduction.getAST());
					return null;
				}

			});
		}

		return result(box, unknowns);
	}
}
