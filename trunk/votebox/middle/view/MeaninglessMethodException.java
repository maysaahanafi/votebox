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
 * This method is a built-in "class cast exception." Some methods on drawables
 * only have meaning on certain kinds of drawables. Implementations of drawable
 * which choose to not define an implementation for a particular method will
 * throw this exception. I do this rather than checking and casting because I
 * find it to be slightly more elegant.
 * 
 * @author Kyle
 * 
 */
public class MeaninglessMethodException extends Exception {

	private static final long serialVersionUID = 1L;

	public MeaninglessMethodException(String message) {
		super(message);
	}
}
