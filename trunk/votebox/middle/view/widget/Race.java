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
package votebox.middle.view.widget;

import votebox.middle.driver.DeselectionException;
import votebox.middle.driver.IAdapter;
import votebox.middle.driver.SelectionException;
import votebox.middle.driver.UnknownUIDException;
import votebox.middle.view.BallotBoxViewException;

/**
 * This strategy interprets toggle buttons as being candidates in a given race
 * in the model. When a toggle button asks to be selected or deselected, this
 * strategy will delegate this decision to the ballot, expecting that the UID of
 * the toggle button is also the uid of the corresponding ballot element. It is
 * important that these uids actually do align properly.
 * 
 * @author Kyle
 * 
 */
public class Race extends AToggleButtonGroupStrategy {

	/**
	 * Use this adapter to make changes to the ballot.
	 */
	private IAdapter _adapter;

	/**
	 * This is the public constructor for Race.
	 * 
	 * @param adapter
	 *            Use this adapter to get access to the
	 *            model.
	 */
	public Race(IAdapter adapter) {
		_adapter = adapter;
	}

	/**
	 * @see votebox.middle.view.widget.AToggleButtonGroupStrategy#select(votebox.middle.view.widget.ToggleButton)
	 */
	@Override
	public void select(ToggleButton context) {
		try {
			if (_adapter.select(context.getUniqueID()))
				context.makeSelected();
		} catch (UnknownUIDException e) {
			throw new BallotBoxViewException(
					"A ToggleButton whose UID is "
							+ context.getUniqueID()
							+ " belongs to a group whose strategy is Race, but there is no element in the ballot with the corresponding UID",
					e);
		} catch (SelectionException e) {
			throw new BallotBoxViewException(
					"There was a selection error in the ballot: "
							+ e.getMessage(), e);
		}
	}

	/**
	 * @see votebox.middle.view.widget.AToggleButtonGroupStrategy#deselect(votebox.middle.view.widget.ToggleButton)
	 */
	@Override
	public void deselect(ToggleButton context) {
		try {
			if (_adapter.deselect(context.getUniqueID()))
				context.makeDeselected();
		} catch (UnknownUIDException e) {
			throw new BallotBoxViewException(
					"A ToggleButton whose UID is "
							+ context.getUniqueID()
							+ " belongs to a group whose strategy is Race, but there is no element in the ballot with the corresponding UID",
					e);
		} catch (DeselectionException e) {
			throw new BallotBoxViewException(
					"There was a deselection error in the ballot: "
							+ e.getMessage(), e);
		}
	}

}
