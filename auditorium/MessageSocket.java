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
import java.net.InetSocketAddress;
import java.net.Socket;

import sexpression.stream.*;

/**
 * This class wraps a socket that interfaces with the outside world in the form
 * of Message instances. This type of socket can only send and receive entire
 * auditorium messages.
 * 
 * @author kyle
 * 
 */
public class MessageSocket {

    private final ASEWriter _out;
    private final ASEInputStreamReader _in;
    private final Socket _socket;

    /**
     * Construct a new message socket and connect it to the given host, but
     * timeout the connection after a given period of time.
     * 
     * @param host
     *            Connect to this host.
     * @param timeout
     *            Only wait this long for the connection to succeed.
     * @throws NetworkException
     *             This method throws if there is a problem connecting.
     */
    public MessageSocket(HostPointer host, int timeout) throws NetworkException {
        _socket = new Socket();
        try {
            _socket.connect( new InetSocketAddress( host.getIP(), host
                    .getPort() ), timeout );
            _out = new ASEWriter( _socket.getOutputStream() );
            _in = new ASEInputStreamReader( _socket.getInputStream() );
        }
        catch (IOException e) {
            throw new NetworkException( "couldn't create socket", e );
        }
    }

    /**
     * Construct a new message socket with an already connected socket.
     * 
     * @param socket
     *            wrap this socket.
     * @throws NetworkException
     *             This method throws if the socket given isn't already
     *             connected.
     */
    public MessageSocket(Socket socket) throws NetworkException {
        _socket = socket;
        try {
            _out = new ASEWriter( socket.getOutputStream() );
            _in = new ASEInputStreamReader( socket.getInputStream() );
        }
        catch (IOException e) {
            throw new NetworkException( "couldn't create socket", e );
        }
    }

    /**
     * Send a message.
     * 
     * @param msg
     *            Send this message.
     * @throws NetworkException
     *             This method throws if the message can't be sent.
     */
    public void send(Message msg) throws NetworkException {
        try {
            _out.writeASE( msg.toASE() );
        }
        catch (IOException e) {
            throw new NetworkException( "Couldn't send " + msg, e );
        }
    }

    /**
     * Receive a message.
     * 
     * @return This method returns the message that is received.
     * @throws IncorrectFormatException
     *             This method throws if the incoming s-exp isn't formatted as a
     *             message.
     * @throws InvalidVerbatimStreamException
     *             This method throws if the incoming stream isn't
     *             s-expressions.
     */
    public Message receive() throws NetworkException, IncorrectFormatException {
        try {
            return new Message( _in.read() );
        }
        catch (IOException e) {
            throw new NetworkException( "while receiving:" + e.getMessage(), e );
        }
        catch (InvalidVerbatimStreamException e) {
            throw new NetworkException( "while receiving:" + e.getMessage(), e );
        }
    }

    /**
     * Close the socket.
     * 
     * @throws IOException
     *             This method throws if the decorated call to close throws.
     */
    public void close() throws IOException {
        _socket.close();
    }
}
