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
package sexpression.stream;

/**
 * This exception is thrown by S-Expression parse methods if the verbatim string
 * which is offered for parsing is an invalid format.
 * 
 * @author Kyle
 * 
 */
public class InvalidVerbatimStreamException extends Exception {

	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the constructor for InvalidVerbatimStringExpression. It simply
	 * invokes super.
	 * 
	 * @param message
	 */
	public InvalidVerbatimStreamException(String message) {
		super(message);
	}
}
