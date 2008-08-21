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
 * 
 * The Card class is the model's representation for a piece of the ballot.
 * Because, in the
 * 
 * Card delegates to an implementation of this interface for its behavior
 * regarding what to do when a SelectableCardElement decides that it would like
 * to be selected. A SelectableCardElement delegates to a Card on what to do
 * when its Toggle method is called, and the Card then delegates to these
 * methods.
 * 
 * Class converted from .NET code
 */

public abstract class ACardStrategy {

	/**
	 * 
	 * When a SelectableCardElement has decided that it would like to be
	 * selected, it delegates to this method.
	 * 
	 * @param element
	 *            This element has chosen to be selected.
	 */
	public abstract boolean select(SelectableCardElement element) throws CardStrategyException;

	/**
	 * 
	 * When a SelectableCardElement decides that it would like to be deselected,
	 * it delegates to this method.
	 * 
	 * @param element
	 *            This element has chosen to be deselected.< param>
	 */
	public abstract boolean deselect(SelectableCardElement element) throws CardStrategyException;
}