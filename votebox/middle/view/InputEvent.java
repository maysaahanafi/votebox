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
package votebox.middle.view;

/**
 * An event is our encapsulation of an input device event.
 * 
 * @author Kyle
 * 
 */
public class InputEvent {

    /**
     * Use this to refer to input events that don't involve a drawable.
     */
    public static final InputEvent NONE = new InputEvent( null );

    private final IDrawable _focused;

    /**
     * This is the public constructor for Event.
     * 
     * @param focused
     *            This is the drawable that the mouse is currently focused on.
     */
    public InputEvent(IDrawable focused) {
        _focused = focused;
    }
    
    /**
     * Call this method to get the drawable that was being pointed at by the
     * mouse at the time the event happened.
     * 
     * @return This method returns the drawable that was being pointed at by the
     *         mouse, or null if the mouse was not pointing to anything.
     */
    public IDrawable focusedDrawable() {
        return _focused;
    }
}
