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
package auditorium;

import sexpression.ASExpression;

/**
 * This exception is thrown when Auditorium expects an s-expression to be
 * formatted a particular way, but it isn't.
 * 
 * @author kyle
 * 
 */
public class IncorrectFormatException extends Exception {
    private static final long serialVersionUID = 1L;

    private final ASExpression _ase;

    public IncorrectFormatException(ASExpression message, Throwable exception) {
        super( message.toString() + " incorrectly formatted: "
                + exception.getMessage(), exception );
        _ase = message;
    }

    public ASExpression getOffender() {
        return _ase;
    }
}
