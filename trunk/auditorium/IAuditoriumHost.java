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
package auditorium;

import sexpression.ASExpression;

/**
 * Abstraction of the interface between a layer and its AuditoriumHost. This is
 * done so that testing is easier.
 * 
 * @author kyle
 * 
 */
public interface IAuditoriumHost {

    /**
     * Get the node ID of this auditorium host. This uniquely identifies this
     * host from all other hosts that could possibly be on the network.
     * 
     * @return This method returns the unique host identifier.
     */
    String getNodeId();

    /**
     * Get the Log instance. This is the interface that is used to both
     * serialize messages to the log and keep track of what messages have been
     * logged in the past.
     * 
     * @return This method returns the host's log instance.
     */
    Log getLog();

    /**
     * Get a pointer to the host.
     * 
     * @return This method returns a HostPointer instance that refers to this
     *         host.
     */
    HostPointer getMe();

    /**
     * Get all the addresses that are in this network.
     * 
     * @return This method returns an s-expression representation of all
     *         addresses that are a member of this network.
     */
    ASExpression getAddresses();

    /**
     * Notify the host that message was received on a link. Links should call
     * this method.
     * 
     * @param message
     *            Notify the host that this message was received on the link.
     */
    void receiveAnnouncement(Message message);

    /**
     * @return This method returns the incremented sequence number counter's
     *         value.Call this method when making messages to put on the wire,
     *         to determine what the sequence number should be.
     */
    String nextSequence();

    /**
     * Remove this host's link
     * 
     * @param l
     *            Remove this link
     */
    void removeLink(Link l);

}
