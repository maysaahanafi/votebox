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

/**
 * This strategy encapsulates the behavior expected with radio buttons. When any
 * button wants to be selected, it is allowed, but all other buttons in its
 * group are deselected first.
 * 
 * @author Kyle
 * 
 */
public class RadioButton extends AToggleButtonGroupStrategy {

	/**
	 * Singleton Design Pattern
	 */
	public static final RadioButton Singleton = new RadioButton();
	
	/**
	 * Singleton Design Pattern
	 */
	private RadioButton(){
	}
	
	/**
	 * @see votebox.middle.view.widget.AToggleButtonGroupStrategy#select(votebox.middle.view.widget.ToggleButton)
	 */
	public void select(ToggleButton element) {
		// Deselect everyone else
		for (ToggleButton button : element.getGroup().getButtons()) {
			if (!button.equals(element)) {
				button.makeDeselected();
			}
		}
		// Select the new guy.
		element.makeSelected();
	}

	/**
	 * @see votebox.middle.view.widget.AToggleButtonGroupStrategy#deselect(votebox.middle.view.widget.ToggleButton)
	 */
	public void deselect(ToggleButton element) {
		// NO-OP
	}
}