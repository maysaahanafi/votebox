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
package supervisor.model;

/**
 * The model of a supervisor machine on the network.
 * @author cshaw
 */
public class SupervisorMachine extends AMachine {

    /**
     * Represents a supervisor that is active
     */
    public static final int ACTIVE = 4;

    /**
     * Represents a supervisor that is inactive
     */
    public static final int INACTIVE = 5;

    private boolean currentMachine;

    /**
     * Constructs a new supervisor machine
     * @param serial the serial number of the supervisor
     * @param current whether the supervisor is the current machine
     */
    public SupervisorMachine(int serial, boolean current) {
        super(serial);
        this.currentMachine = current;
    }

    /**
     * @return whether this is the current machine
     */
    public boolean isCurrentMachine() {
        return currentMachine;
    }

}
