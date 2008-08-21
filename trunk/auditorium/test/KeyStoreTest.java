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

import java.io.File;

import org.junit.*;

import auditorium.*;
import sexpression.*;

public class KeyStoreTest {

    private final ASExpression _message = new ListExpression( "one", "two" );

    private File _file;
    private SimpleKeyStore _keystore;

    @Before
    public void build() throws Exception {
        _file = new File( "tmp/" );
        _file.mkdir();
        Generator.main( "5", "tmp/" );
        _keystore = new SimpleKeyStore( "tmp/" );
    }

    @After
    public void tear() throws Exception {
        for (File child : _file.listFiles())
            child.delete();
        _file.delete();
    }

    @Test
    public void load_key() throws Exception {
        for (int lcv = 0; lcv < 5; lcv++) {
            Cert cert = _keystore.loadCert( Integer.toString( lcv ) );
            Key key = _keystore.loadKey( Integer.toString( lcv ) );
            Signature sig = RSACrypto.SINGLETON.sign( _message, key );
            RSACrypto.SINGLETON.verify( sig, cert );
        }
    }

    @Test(expected = AuditoriumCryptoException.class)
    public void load_key_fail_1() throws Exception {
        _keystore.loadKey( "blah" );
    }

    @Test(expected = AuditoriumCryptoException.class)
    public void load_key_fail_2() throws Exception {
        _keystore.loadKey( "5" );
    }

    @Test(expected = AuditoriumCryptoException.class)
    public void load_cert_fail_1() throws Exception {
        _keystore.loadCert( "blah" );
    }

    @Test(expected = AuditoriumCryptoException.class)
    public void load_cert_fail_2() throws Exception {
        _keystore.loadCert( "5" );
    }
}
