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

import verifier.*;
import verifier.value.*;

import sexpression.*;

/**
 * Instances which implement IAST represent concrete nodes in an abstract syntax
 * tree.
 * 
 * @author kyle
 * 
 */
public abstract class AST {

	/**
	 * Evaluate the target expression given the passed environment.
	 * 
	 * @param environment
	 *            Lookup non-primitive identifiers in this environment.
	 * @return This method returns the result of the evaluation.
	 */
	public abstract Value eval(ActivationRecord environment);

	private final ASExpression _ase;

	/**
	 * @param ase
	 *            Extending classes must provide the representative s-expression
	 *            for any newly constructed AST. This should simply be the
	 *            expression that the AST was parsed from.
	 */
	protected AST(ASExpression ase) {
		_ase = ase;
	}

	/**
	 * @return Convert this AST to s-expression format (This is the
	 *         representation it was originally parsed out of).
	 */
	public ASExpression toASE() {
		return _ase;
	}
}
