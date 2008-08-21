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
package votebox.crypto;

import auditorium.Key;

/**
 * This is a temporary placeholder for a key pair. It just generates a new one
 * every time the JVM starts. This is mostly used for functionality testing.
 * 
 * @author kyle
 * 
 */
public class STATICKEY {

    public static final STATICKEY SINGLETON = new STATICKEY();

    public final Key PRIVATE_KEY;
    public final Key PUBLIC_KEY;

    private STATICKEY() {
        Pair<Key> pair = ElGamalCrypto.SINGLETON.generate("blah");
        PUBLIC_KEY = pair.get1();
        PRIVATE_KEY = pair.get2();
    }
}
