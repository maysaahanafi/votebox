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

import java.io.IOException;

/**
 * This class represents a single link in the group of outgoing links from a
 * given host. Each link has a listen thread associated with it. It's job is to
 * simply listen for incoming traffic on said link, and relay said traffic to
 * the host. The host will then, of course, decide what to do with it. Because
 * each link is a keeper of this listening thread, you must call start() and
 * stop() on it before it will behave in the expected way. The link's thread
 * will operate at a priority one less than the calling thread. This is to aid
 * auditorium in being able to keep up with many links flooding messages onto
 * its queues.
 * 
 * @author Kyle
 * 
 */
public class Link {

    private final IAuditoriumHost _host;
    private final MessageSocket _socket;
    private final HostPointer _address;
    private volatile boolean _running;

    /**
     * Construct a new auditorium link structure to wrap a socket that has
     * already been established with another auditorium host.
     * 
     * @param host
     *            This is the AuditoriumHost that is using this link.
     * @param socket
     *            This is the socket to the other auditorium host.
     * @param address
     *            This is the address that the socket is connected to.
     */
    public Link(IAuditoriumHost host, MessageSocket socket, HostPointer address) {
        _host = host;
        _socket = socket;
        _address = address;
        _running = false;
    }

    /**
     * Start the thread.
     */
    public void start() {
        Bugout.msg( "Link " + _address + ": STARTING" );

        _running = true; // listenThread will stop immediately if not set here

        Thread t = new Thread( new Runnable() {

            public void run() {
                listenThread();
            }
        } );
        t.setPriority( Thread.currentThread().getPriority() + 1 );
        t.start();

    }

    /**
     * Stop the thread.
     */
    public void stop() {
        Bugout.err( "Link " + _address + ": STOPPING" );
        _running = false;
        try {
            _socket.close();
        }
        catch (IOException e) {
            Bugout.err( "Link " + _address + ": while stopping: "
                    + e.getMessage() );
        }
    }

    /**
     * Get the address of the other end of this link.
     * 
     * @return This method returns the address of who this link is with.
     */
    public HostPointer getAddress() {
        return _address;
    }

    /**
     * Get the message socket that is established for this link.
     * 
     * @return This method returns the message socket that is established for
     *         this link.
     */
    public MessageSocket getSocket() {
        return _socket;
    }

    /**
     * Check if this link is currently running
     * 
     * @return This method returns true if the link is running or false if it
     *         isn't.
     */
    public boolean running() {
        return _running;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Link))
            return false;

        return this._address.equals( ((Link) o)._address );
    }

    private void listenThread() {
        Bugout.msg( "Link " + _address + ": THREAD START" );
        try {
            while (_running) {
                try {
                    Message message = _socket.receive();
                    Bugout.msg( "Link " + _address + ": received: "
                            + new MessagePointer( message ) );
                    _host.receiveAnnouncement( message );
                }
                catch (IncorrectFormatException e) {
                    Bugout
                            .err( "Link "
                                    + _address
                                    + ": received a message that is incorrectly formatted:"
                                    + e.getMessage() );
                }
            }
        }
        catch (NetworkException e) {
            Bugout.err( "Link " + _address + ": " + e.getMessage() );
        }
        _host.removeLink( this );
        Bugout.msg( "Link " + _address + ": THREAD END" );
        stop();
    }
}
