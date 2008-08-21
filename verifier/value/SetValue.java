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

import java.util.Arrays;
import java.util.HashSet;

import sexpression.*;

/**
 * Instances of this class represent internal set values.
 * 
 * @author kyle
 * 
 */
public class SetValue extends Value {

	private final Expression[] _list;

	private final HashSet<Expression> _set;

	/**
	 * @param elts
	 *            Construct a new set value that has these elements.
	 */
	public SetValue(Expression[] elts) {
		super(false);
		_list = elts;
		_set = new HashSet<Expression>();

		for (Expression e : elts)
			_set.add(e);
	}

	/**
	 * @param exp
	 *            Test if this expression is a member of the target set.
	 * @return This method returns true if the given expression is a member of
	 *         the target set.
	 */
	public boolean isMember(Expression exp) {
		return _set.contains(exp);
	}

	/**
	 * @return This method returns the number of elements in this set.
	 */
	public int size() {
		return _list.length;
	}

	/**
	 * Get the set member at a given index.
	 * 
	 * @param index
	 *            Get the set member at this index.
	 * @return This method returns the set member at the given index.
	 */
	public Expression get(int index) {
		return _list[index];
	}

	/**
	 * @see verifier.value.Value#execute(verifier.value.IValueVisitor)
	 */
	public Value execute(IValueVisitor visitor) {
		return visitor.forSet(this);
	}

	/**
	 * @return Return an s-expression list which represents this set. The order
	 *         of the resulting list will be such that smaller indicies
	 *         represent entries added to the set earlier in time.
	 */
	public ListExpression toList() {
		ASExpression[] lst = new ASExpression[_list.length];
		for (int lcv = 0; lcv < _list.length; lcv++)
			lst[lcv] = _list[lcv].getASE();
		return new ListExpression(lst);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof SetValue))
			return false;
		SetValue ov = (SetValue) o;

		if (ov._list.length != _list.length)
			return false;

		Arrays.sort(ov._list);
		Arrays.sort(_list);
		return Arrays.equals(_list, ((SetValue) o)._list);
	}

	/**
	 * Most costly serialization operation.
	 * 
	 * @see verifier.value.Value#toASE()
	 */
	@Override
	public ASExpression toASE() {
		ASExpression[] lst = new ASExpression[_list.length];
		for (int lcv = 0; lcv < lst.length; lcv++)
			lst[lcv] = _list[lcv].toASE();
		return new ListExpression(StringExpression.make("list->set"),
				new ListExpression(StringExpression.make("quote"),
						new ListExpression(lst)));
	}

	@Override
	public String toString() {
		return "<value.SetValue: " + toList().toString() + ">";
	}
}
