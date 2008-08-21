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

import org.junit.Test;

import auditorium.*;
import auditorium.Generator.Keys;

import sexpression.*;

/**
 * Unit test the primitives provided in the Crypto class. These test the output
 * of the generation scripts in /keys/. Make sure you have run them and
 * generated at least 10 key pairs, plus the CA, before you run this test.
 * 
 * @author kyle
 * 
 */
public class CryptoTest {

    private final Generator _gen = new Generator();
    private final ASExpression _message = new ListExpression( "One", "Two",
            "Three" );

    @Test
    public void test_sign_verify() throws Exception {
        for (int lcv = 0; lcv < 3; lcv++) {
            Keys keys = _gen.generateKey( "TEST", "TEST" );
            Cert cert = _gen.createCert( keys.getPrivate(), keys.getPublic() );
            Signature sig = RSACrypto.SINGLETON.sign( _message, keys.getPrivate() );
            RSACrypto.SINGLETON.verify( sig, cert );
        }
    }

    @Test(expected = AuditoriumCryptoException.class)
    public void test_sign_verify_fail() throws Exception {
        for (int lcv = 0; lcv < 3; lcv++) {
            Keys keys = _gen.generateKey( "TEST", "TEST" );
            Cert cert = _gen.createCert( keys.getPrivate(), keys.getPublic() );
            Signature sig = RSACrypto.SINGLETON.sign( _message, keys.getPrivate() );
            byte[] sigbytes = sig.getSigData().getBytesCopy();
            sigbytes[32] = 12;
            sigbytes[1] = -3;
            Signature notsig = new Signature( "TEST", StringExpression.makeString(
                    sigbytes ), keys.getPublic().toASE() );
            RSACrypto.SINGLETON.verify( notsig, cert );
        }
    }
}
