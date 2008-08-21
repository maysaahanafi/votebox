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
 * 
 * Card delegates to an implementation of this interface for its behavior
 * regarding what to do
 * 
 * This is the strategy implementation for KofN voting. KofN is the style of
 * voting that is done in a race where more than one candidate can be selected
 * as being preferred in the same race. The behavior of the gui components in
 * these types of races is synonymous to a group of checkboxes in a windows
 * environment, where only "k out of n" checkboxes can be selected at one time.
 * 
 */

public class KofN extends ACardStrategy {
	/**
	 * This field is the maximum number of elements who can be selected in this
	 * race.
	 * 
	 */
	private int _maxNumber;

	/**
	 * This field is the number of elements who are currently selected in this
	 * race.
	 * 
	 */
	private int _count = 0;

	public KofN(int max) {
		_maxNumber = max;
	}

	/**
	 * If this race has room for another selected candidate, then tell
	 * the candidate to Select itself.
	 * @param element This element wants to be selected.
	 */
	public boolean select(SelectableCardElement element) {
		if (_count + 1 <= _maxNumber) {
			_count++;
			element.setSelected(true);
			return true;
		}
		return false;
	}

	/**
	 * Decrement the count and tell the element to Select itself.
	 * @param element selectable element to deselect
	 */
	public boolean deselect(SelectableCardElement element) {
		if (_count - 1 >= 0) {
			_count--;
			element.setSelected(false);
			return true;
		}
		return false;
	}
}