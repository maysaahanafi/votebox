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
 * This class deifnes the abstract state for a ToggleButton (used in state
 * pattern). ToggleButtons delegate certain state-dependent behaviors to an
 * instance of this class.
 */
public abstract class AToggleButtonState {

    /**
     * Because the image that is used to represent a toggle button is
     * state-speciffic, getImage is delegated here.
     * 
     * @param context
     *            This is the button that is delegating.
     * @return This method returns the state-specific image that represents the
     *         delegating button.
     */
    public abstract IViewImage getImage(ToggleButton context);

    /**
     * The SelectableCardElement delegates to this method when it is told that
     * the user has chosen it to be toggled.
     * 
     * @param context
     *            This is the button that is delegating.
     */
    public abstract void select(ToggleButton context);

    /**
     * Explicitly selects the element who delegates to this state object. This
     * will happen regardless of the current state.
     * 
     * @param context
     *            This is the button that is delegating.
     */
    public abstract void makeSelected(ToggleButton context);

    /**
     * Explicitly deselects the element who delegates to this state object. Thi
     * swill happen regardless of the current state.
     * 
     * @param context
     *            This is the button that is delegating.
     */
    public abstract void makeDeselected(ToggleButton context);

    /**
     * The SelectableCardElement delegates to this method when it is told that
     * the user is currently focused on it.
     * 
     * @param context
     *            This is the button that is delegating.
     */
    public abstract void focus(ToggleButton context);

    /**
     * The SelectableCardElement delegates to this method when it is told that
     * the user is no longer focused on it.
     * 
     * @param context
     *            This is the button that is delegating.
     */
    public abstract void unfocus(ToggleButton context);

}
