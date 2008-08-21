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

import votebox.middle.view.IViewImage;

/**
 * This class represents the focused state of a ToggleButton.
 * 
 * @author Kyle
 * 
 */
public class FocusedToggleButtonState extends AToggleButtonState {

	/**
	 * Singleton Design Pattern
	 */
	public static final FocusedToggleButtonState Singleton = new FocusedToggleButtonState();
	
	/**
	 * Singleton Design Pattern
	 */
	private FocusedToggleButtonState(){
	}
	
	/**
	 * @see votebox.middle.view.widget.AToggleButtonState#getImage(votebox.middle.view.widget.ToggleButton)
	 */
	@Override
	public IViewImage getImage(ToggleButton context){
		return context.getFocusedImage();
	}

	/**
	 * @see votebox.middle.view.widget.AToggleButtonState#toggle(votebox.middle.view.widget.ToggleButton)
	 */
	@Override
	public void select(ToggleButton context) {
		context.getGroup().select(context);
	}

	/**
	 * @see votebox.middle.view.widget.AToggleButtonState#select(votebox.middle.view.widget.ToggleButton)
	 */
	@Override
	public void makeSelected(ToggleButton context) {
		context.setState(FocusedSelectedToggleButtonState.Singleton);
		context.getSelectedEvent().notifyObservers();
	}

	/**
	 * @see votebox.middle.view.widget.AToggleButtonState#deselect(votebox.middle.view.widget.ToggleButton)
	 */
	@Override
	public void makeDeselected(ToggleButton context) {
		// NO-OP
	}

	/**
	 * @see votebox.middle.view.widget.AToggleButtonState#focus(votebox.middle.view.widget.ToggleButton)
	 */
	@Override
	public void focus(ToggleButton context) {
		// NO-OP
	}

	/**
	 * @see votebox.middle.view.widget.AToggleButtonState#unfocus(votebox.middle.view.widget.ToggleButton)
	 */
	@Override
	public void unfocus(ToggleButton context) {
		context.setState(DefaultToggleButtonState.Singleton);
		context.getUnfocusedEvent().notifyObservers();
	}

}
