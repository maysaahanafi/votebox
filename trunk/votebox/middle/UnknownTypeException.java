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
 * This method is thrown by Properties when it encounters a type it doesn't know
 * about.
 * 
 * @author Kyle
 * 
 */
public class UnknownTypeException extends Exception {

	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the type that was unknown.
	 */
	private String _type;

	/**
	 * This is the public constructor for UnknownTypeException
	 * 
	 * @param type
	 *            This is the string representation of the type that is unknown
	 */
	public UnknownTypeException(String type) {
		super(type + " is an unknown type. Properties must be declared as "
				+ "Integer, String, or Boolean");
		_type = type;
	}

	/**
	 * This is the getter for _type.
	 * 
	 * @return _type.
	 */
	public String getType() {
		return _type;
	}
}
