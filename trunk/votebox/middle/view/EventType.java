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

public enum EventType {

    /**
     * The voter has pressed the cast ballot button.
     */
    CAST_BALLOT,

    /**
     * Kill the software.
     */
    KILL,

    /**
     * The voter has pressed the next page button
     */
    NEXT_PAGE,

    /**
     * The voter has pressed the previous page button.
     */
    PREV_PAGE,

    /**
     * The voter has pressed the select button
     */
    SELECT,

    /**
     * The boter has pressed the left arrow
     */
    LEFT,

    /**
     * The voter has pressed the right arrow.
     */
    RIGHT,

    /**
     * The voter has pressed the up arrow.
     */
    UP,

    /**
     * The voter has pressed the down arrow.
     */
    DOWN,

    /**
     * The voter has pressed the next arrow.
     */
    NEXT,

    /**
     * The voter has pressed the previous arrow.
     */
    PREVIOUS,

    /**
     * The voter has moved the mouse.
     */
    MOUSE_MOVE,

    /**
     * The voter has pressed the mouse buttong.
     */
    MOUSE_DOWN,
    
    /**
     * VoteBox is redrawing or drawing a page for the first time. 
     */
   BEGIN_PAGE_REDRAW,
   
   /**
    * VoteBox has finished drawing a complete page. 
    */
   END_PAGE_REDRAW
}