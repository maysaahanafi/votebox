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

import sexpression.ASExpression;

/**
 * Abstract superclass for all DAGs-of-time to be used by the verifier. Some
 * implementations (ExplicitDAG) model an arbitrary DAG explicitly, while others
 * (FastDAG) use optimizations that require certain constraints on the input.
 * Either way, the public interface consists (in essence) of the single
 * predicate precedes(x,y): does x precede y in time?
 */
public abstract class DAGValue extends Value {
	public DAGValue() {
		super(/* sealed */false);
	}

	public abstract boolean precedes(Expression leftMessage,
			Expression rightMessage);

	/**
	 * @see verifier.value.Value#execute(verifier.value.IValueVisitor)
	 */
	@Override
	public Value execute(IValueVisitor visitor) {
		return visitor.forDAG(this);
	}

	/**
	 * @see verifier.value.Value#toASE()
	 */
	@Override
	public ASExpression toASE() {
		throw new RuntimeException("Cannot serialize a DAG");
	}

	/**
	 * Lookup the number of nodes in this dag.
	 * 
	 * @return This method returns the number of nodes in this dag.
	 */
	public abstract int size();

	/** String representation. */
	public String toString() {
		return "<value.DAGValue: size=" + size() + ">";
	}

	/** Enable the internal cache, if available. */
	public abstract void enableCache();

	/** Disable the internal cache, if available. */
	public abstract void disableCache();

}
