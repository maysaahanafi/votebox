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
package votebox.middle;

/**
 * This exception is thrown when an assumption is made about a type that is
 * false. This exception is notably used in Properties. If one invokes get*(...)
 * where * is any type other than object, if the actual type doesn't agree with *,
 * the method will throw an instance of this exception.
 * 
 * @author Kyle
 * 
 */
public class IncorrectTypeException extends Exception {

	/**
	 * This is the default serial version uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the string representation for the type that was expected.
	 */
	private String _expected;

	/**
	 * This is the string representation for the actual type.
	 */
	private String _actual;

	/**
	 * This is the public constructor for IncorrectTypeException.
	 * 
	 * @param expected
	 *            This is the string representation for the type that was
	 *            expected.
	 * @param actual
	 *            This is the string representation for the actual type.
	 */
	public IncorrectTypeException(String expected, String actual) {
		super("Expected type " + expected + " but was type " + actual);
		_expected = expected;
		_actual = actual;
	}

	/**
	 * This is the getter for _expected.
	 * 
	 * @return _expected
	 */
	public String getExpected() {
		return _expected;
	}

	/**
	 * This is the getter for _actual
	 * 
	 * @return _actual
	 */
	public String getActual() {
		return _actual;
	}
}
