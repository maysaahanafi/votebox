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
package auditorium.test;

import sexpression.StringExpression;
import auditorium.*;

public class StressTest {

    public static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
        Bugout.ERR_OUTPUT_ON = true;
        Bugout.MSG_OUTPUT_ON = true;
        AuditoriumHost host = new AuditoriumHost( args[0],
                TestParams.Singleton );
        host.start();
        listenthread( host );
        HostPointer[] ptrs = host.discover();
        System.err.println( "Discover complete...waiting" );
        Thread.sleep( 1000 );

        for (HostPointer ptr : ptrs)
            host.join( ptr );

        for (int lcv = 0; lcv < 100; lcv++) {
            Thread.sleep( 300 );
            host.announce( StringExpression.makeString( Integer.toString( lcv ) ) );
        }

        System.err.println( "Done, letting the queues empty" );
        Thread.sleep( 30000 );
        System.err.println( "Killing." );
        running = false;
        host.stop();
    }

    public static void listenthread(final AuditoriumHost host) throws Exception {
        new Thread( new Runnable() {

            public void run() {
                while (running)
                    try {
                        host.listen();
                    }
                    catch (ReleasedQueueException e) {
                        break;
                    }
            }
        } ).start();
    }
}
