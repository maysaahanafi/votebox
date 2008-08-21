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
 * This exception is thrown by Properties if the format given for a certain type
 * cannot be decoded to mean anything reasonable.
 * 
 * @author Kyle
 * 
 */
public class UnknownFormatException extends Exception {

	/**
	 * This is the default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the type for which the value could not be decoded.
	 */
	private String _type;

	/**
	 * This is the value that could not be decoded.
	 */
	private String _value;

	/**
	 * This is the public constructor for UnknownFormatException
	 * 
	 * @param type
	 *            This is the type for which the value cannot be determined.
	 * @param value
	 *            This is the value which cannot be properly decoded.
	 */
	public UnknownFormatException(String type, String value) {
		super("Could not decode " + value + " to mean anything for type "
				+ type);
		_type = type;
		_value = value;
	}

	/**
	 * This is the getter for _type
	 * 
	 * @return _type
	 */
	public String getType() {
		return _type;
	}

	/**
	 * This is the getter for _value
	 * 
	 * @return _value
	 */
	public String getValue() {
		return _value;
	}
}
