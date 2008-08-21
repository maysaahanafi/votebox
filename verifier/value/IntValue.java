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
 * Instances of this class represent integer values in the verifier.
 * 
 * @author kyle
 * 
 */
public class IntValue extends Value {

	private final int _value;

	/**
	 * @param value
	 *            Construct the instance to represent this integer value.
	 */
	public IntValue(int value) {
		super(true);
		_value = value;
	}

	/**
	 * @return This method returns the integer value that this instance
	 *         represents.
	 */
	public int get() {
		return _value;
	}

	/**
	 * @see verifier.value.Value#execute(verifier.value.IValueVisitor)
	 */
	@Override
	public Value execute(IValueVisitor visitor) {
		return visitor.forInt(this);
	}

	/**
	 * @see verifier.value.Value#toASE()
	 */
	@Override
	public ASExpression toASE() {
		return StringExpression.make(toString());
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return (o instanceof IntValue) && ((IntValue) o)._value == _value;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(_value);
	}
}
