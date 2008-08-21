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
import auditorium.AuditoriumHost;
import auditorium.HostPointer;

public class Incrementer {

    public static void main(String[] args) throws Exception {
        AuditoriumHost host = new AuditoriumHost( args[0],
                TestParams.Singleton );
        host.start();
        for (HostPointer hp : host.discover())
            host.join( hp );

        if (args.length == 2)
            host.announce( StringExpression.makeString( "1" ) );

        while (true) {
            int rec = Integer.parseInt( new String( ((StringExpression) host
                    .listen().message).getBytesCopy() ) );

            if (rec == 9)
                break;

            rec++;
            host.announce( StringExpression
                    .makeString( Integer.toString( rec ) ) );

            if (rec == 10)
                break;
        }

        host.stop();
    }
}
