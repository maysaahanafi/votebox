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
package votebox.events;

/**
 * An event that is fired whenever a connection to another machine is lost. This
 * event is not an IAnnounceEvent and has no sexpression form.
 * @author cshaw
 */
public class LeaveEvent {

    private int serial;

    /**
     * Constructs a new LeaveEvent
     * @param serial the serial number of the machine that left
     */
    public LeaveEvent(int serial) {
        super();
        this.serial = serial;
    }

    /**
     * @return the serial number of the machine that left
     */
    public int getSerial() {
        return serial;
    }

}
