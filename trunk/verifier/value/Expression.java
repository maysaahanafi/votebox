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
 * This class represents the s-expression verifier value. Any arbitrary
 * s-expression can be represented.
 * 
 * @author kyle
 * 
 */
public class Expression extends Value implements Comparable {

	private final ASExpression _ase;

	/**
	 * @param ase
	 *            The SExpression represented by this Expression object.
	 */
	public Expression(ASExpression ase) {
		super(true);
		_ase = ase;
	}

	/**
	 * @see verifier.value.Value#execute(verifier.value.IValueVisitor)
	 */
	public Value execute(IValueVisitor visitor) {
		return visitor.forExpression(this);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Expression))
			return false;
		return _ase.equals(((Expression) o)._ase);
	}

	/**
	 * @return This method returns the s-expression that this value represents.
	 */
	public ASExpression getASE() {
		return _ase;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return _ase.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return _ase.toString();
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0) {
		return toString().compareTo(arg0.toString());
	}

	@Override
	public ASExpression toASE() {
		return new ListExpression(StringExpression.make("quote"), _ase);
	}
}
