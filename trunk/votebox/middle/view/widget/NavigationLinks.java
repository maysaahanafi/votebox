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

import votebox.middle.view.IFocusable;

/**
 * This class encapsulates the links that a focusable uses to determine who gets
 * the focus when it is pushed away from a focusable in any given direction.
 */
public class NavigationLinks {

	/**
	 * This is the element that is "above" this one.
	 */
	public IFocusable Up = null;

	/**
	 * This is the element that is "below" this one.
	 */
	public IFocusable Down = null;

	/**
	 * This is the element that is "left of" this one.
	 */
	public IFocusable Left = null;

	/**
	 * This is the element that is "right of" this one.
	 */
	public IFocusable Right = null;

	/**
	 * This is the element that is "next after" this one.
	 */
	public IFocusable Next = null;

	/**
	 * This is the element that is "before" this one.
	 */
	public IFocusable Previous = null;
}