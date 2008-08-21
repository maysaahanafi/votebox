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
package votebox.middle.view;

/**
 * All exceptions which eminate from the view will be thrown in the form of a
 * BallotBoxViewException. It will have a type and a message.
 * 
 * @author Kyle
 * 
 */
public class BallotBoxViewException extends RuntimeException {

	/**
	 * Default serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the BallotBoxViewException constructor.
	 * 
	 * @param cause
	 *            If an SDLException caused this exception to be thrown, pass it
	 *            to here.
	 * @param message
	 *            This is the exception's message.
	 */
	public BallotBoxViewException(String message, Exception cause) {
		super(message, cause);
	}
}
