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
 * This is the
 */

public class FocusedButtonState extends AButtonState {
	/**
	 * Singleton Design Pattern
	 */
	public static FocusedButtonState Singleton = new FocusedButtonState();

	/**
	 * Singleton Design Pattern
	 */
	private FocusedButtonState() {
	}

	/**
	 * When the button asks to be focused, do nothing, it already is.
	 */
	public void focus(Button context) {
		// NO-OP
	}

	/**
	 * When the button asks to be unfocused, change the button's state to
	 * default.
	 */
	public void unfocus(Button context) {
		context.setState(DefaultButtonState.Singleton);
		context.getUnfocusedEvent().notifyObservers();
	}

	/**
	 * @see votebox.middle.view.widget.AButtonState#getImage(votebox.middle.view.widget.Button)
	 */
	@Override
	public IViewImage getImage(Button context) {
		return context.getFocusedImage();
	}
}