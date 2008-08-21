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
package votebox.middle.ballot;

/**
 * Exceptions that are of this type represent errors that occur while parsing
 * the ballot XML file. These errors originate from BallotParser.
 * 
 * @author derrley
 * 
 */
public class BallotParserException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Public constructor.
	 * 
	 * @param message
	 *            This is a message that is descriptive of the problem.
	 * @param cause
	 *            This is the exception that caused the problem.
	 */
	public BallotParserException(String message, Exception cause) {
		super(message, cause);
	}
}
