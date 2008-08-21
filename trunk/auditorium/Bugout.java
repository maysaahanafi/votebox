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

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * This class contains debugging output messages (which might, someday, get
 * turned into logs).
 * 
 * @author kyle
 * 
 */
public class Bugout {

    /**
     * Clear this field to disable debugging messages.
     */
    public static volatile boolean MSG_OUTPUT_ON = true;

    /**
     * Clear this field to disable error output.
     */
    public static volatile boolean ERR_OUTPUT_ON = true;

    /**
     * Set where the debugging and error message go.
     * 
     * @param msg
     *            Normal debugging messages go here.
     * @param err
     *            Error messages go here.
     */
    public static void changeStreams(OutputStream msg, OutputStream err) {
        _msg = new PrintWriter( msg );
        _err = new PrintWriter( err );
    }

    private static PrintWriter _msg = new PrintWriter( System.out );
    private static PrintWriter _err = new PrintWriter( System.err );

    /**
     * Print a debugging message.
     * 
     * @param message
     *            Print this message.
     */
    public synchronized static void msg(String message) {
        if (MSG_OUTPUT_ON)
            _msg.println( "MESSAGE: " + message );
        _msg.flush();
    }

    /**
     * Print an error message.
     * 
     * @param err
     *            Print this message.
     */
    public synchronized static void err(String err) {
        if (ERR_OUTPUT_ON)
            _err.println( "ERROR: " + err );
        _err.flush();
    }

}
