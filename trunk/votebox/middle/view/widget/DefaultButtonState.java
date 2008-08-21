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
 * This is the button state which is "default," or is the state that the button
 * is in when it is not currently being focused on.
 */

public class DefaultButtonState extends AButtonState {
    /**
     * Singleton Design Pattern.
     * 
     */
    public static DefaultButtonState Singleton = new DefaultButtonState();

    /**
     * Singleton Design Pattern
     */
    private DefaultButtonState() {}

    /**
     * When the Button wishes to be focused, switch to the focused state
     */
    public void focus(Button context) {
        context.setState( FocusedButtonState.Singleton );
        context.getFocusedEvent().notifyObservers();
    }

    /**
     * When the Button wishes to be unfocused, do nothing, it is already
     * unfocused.
     */
    public void unfocus(Button context) {
    // NO-OP
    }

    /**
     * @see votebox.middle.view.widget.AButtonState#getImage(votebox.middle.view.widget.Button)
     */
    @Override
    public IViewImage getImage(Button context) {
        return context.getDefaultImage();
    }
}
