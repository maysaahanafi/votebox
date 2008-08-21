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
package verifier.value;

import sexpression.*;

/**
 * An IValue represents an internal verifier value. These are values which can
 * be represented in the rules language, and include integers, truth, falsity,
 * sets, DAGs, closures (functions as values), expressions (these are arbitrary
 * s-expressions), or suspensions.
 * 
 * @see verifier.value.IntValue
 * @see verifier.value.TruthValue
 * @see verifier.value.SetValue
 * @see verifier.value.DAGValue
 * @see verifier.value.Closure
 * @see verifier.value.Expression
 * @see verifier.ISuspension
 * 
 * @author derrley
 * 
 */
public abstract class Value {

	/**
	 * Each concrete value class must implement this visitor hook method.
	 * 
	 * @param visitor
	 *            Execute this visitor on the target.
	 * @return This method will relay the return value of the visitor.
	 */
	public abstract Value execute(IValueVisitor visitor);

	/**
	 * Convert this value to a serializable s-expression format.
	 * 
	 * @return This method returns the s-expression format for the target value.
	 */
	public abstract ASExpression toASE();

	private boolean _sealed;

	/**
	 * @param sealed
	 *            This is the initial sealed state of the value.
	 */
	protected Value(boolean sealed) {
		_sealed = sealed;
	}

	/**
	 * Seal the value, denoting that no new information will ever arrive.
	 */
	public void seal() {
		_sealed = true;
	}

	/**
	 * @return This method returns true if the value has been sealed or false if
	 *         it has not been.
	 */
	public boolean isSealed() {
		return _sealed;
	}
}
