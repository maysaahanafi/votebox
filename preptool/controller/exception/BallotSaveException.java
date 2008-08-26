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
package preptool.controller.exception;

/**
 * Exception thrown when an error occurs during a save ballot operation.
 * 
 * @author cshaw
 */
public class BallotSaveException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BallotSaveException() {}

    public BallotSaveException(String message) {
        super( message );
    }

    public BallotSaveException(Throwable cause) {
        super( cause );
    }

    public BallotSaveException(String message, Throwable cause) {
        super( message, cause );
    }

}