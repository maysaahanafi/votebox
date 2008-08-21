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
 * This class encapsulates the notion of the strategy which a toggle button
 * group uses to determine whether or not to accept a toggle button's request to
 * either select itself or deselect itself.
 * 
 * @author Kyle
 * 
 */
public abstract class AToggleButtonGroupStrategy {

    /**
     * A toggle button calls this method to ask to be selected. If an
     * implementing strategy allows the selection to be made, it should call the
     * button's makeSelected(...) method.
     * 
     * @param context
     *            This is the button that wishes to be selected.
     */
    public abstract void select(ToggleButton context);

    /**
     * A toggle button calls this method to ask to be deselected. If an
     * implementing strategy allows this deselection to be made, it should call
     * the button's makeDeselected(...) method.
     * 
     * @param context
     *            This is the button that wishes to be deselected.
     */
    public abstract void deselect(ToggleButton context);
}
