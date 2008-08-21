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
package verifier;

/**
 * Thrown when a verifier plugin couldn't understand the s-expression handed
 * to it in addLogData().
 * 
 */
public class InvalidLogEntryException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidLogEntryException(Throwable exception) {
        super("Invalid log format for verifier plugin in use", exception );
    }
}
