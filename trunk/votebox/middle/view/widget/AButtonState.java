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
 * Buttons delegate to an instance of this state class. These methods are
 * state-dependent.
 */

public abstract class AButtonState {

    /**
     * This method is called by the Button when it has been asked to focus
     * itself.
     * 
     * @param context
     *            This is the button that is delegating the behavior for focus.
     */
    public abstract void focus(Button context);

    /**
     * This method is called by the Button when it has been asked to unfocus
     * itself.
     * 
     * @param context
     *            This is the button that is delegating the behavior for unfocus
     */
    public abstract void unfocus(Button context);

    /**
     * This method is called by the Button when it has been asked to give its
     * current image.
     * 
     * @param context
     *            This is the button that is delegating the behavior for
     *            getImage.
     */
    public abstract IViewImage getImage(Button context);
}
