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
 * The model of a VoteBox booth on the network
 * @author cshaw
 */
public class VoteBoxBooth extends AMachine {

    /**
     * Represents a VoteBox that is currently in use
     */
    public static final int IN_USE = 1;

    /**
     * Represents a VoteBox that is ready to be authorized
     */
    public static final int READY = 2;

    /**
     * Represents a VoteBox whose battery is low
     */
    @Deprecated
    public static final int BATTERY_LOW = 3;

    private int battery;

    private int protectedCount;

    private int publicCount;

    private byte[] currentNonce;

    /**
     * Constructs a new VoteBoxBooth
     * @param serial the serial number of the booth
     */
    public VoteBoxBooth(int serial) {
        super(serial);
    }

    /**
     * @return the battery level, as an integer [0..100]
     */
    public int getBattery() {
        return battery;
    }

    /**
     * @return the current nonce (if this machine is voting)
     */
    public byte[] getNonce() {
        return currentNonce;
    }

    /**
     * @return the protected count
     */
    public int getProtectedCount() {
        return protectedCount;
    }

    /**
     * @return the public count
     */
    public int getPublicCount() {
        return publicCount;
    }

    /**
     * Sets the battery level
     * @param battery the battery to set
     */
    public void setBattery(int battery) {
        this.battery = battery;
        obs.notifyObservers();
    }

    /**
     * Sets the machine's current nonce
     * @param nonce the currentNonce to set
     */
    public void setNonce(byte[] nonce) {
        this.currentNonce = nonce;
    }

    /**
     * Sets the machine's protected count
     * @param protectedCount the protectedCount to set
     */
    public void setProtectedCount(int protectedCount) {
        this.protectedCount = protectedCount;
        obs.notifyObservers();
    }

    /**
     * Sets the machine's public count
     * @param publicCount the publicCount to set
     */
    public void setPublicCount(int publicCount) {
        this.publicCount = publicCount;
        obs.notifyObservers();
    }

}
