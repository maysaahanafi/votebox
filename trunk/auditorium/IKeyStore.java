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

/**
 * The auditorium integrity layer intefaces with an instance of this type in
 * order to access keys that are stored somehow.
 * 
 * @author kyle
 * 
 */
public interface IKeyStore {

    /**
     * Load the private key associated with a given ID.
     * 
     * @return This method returns the private key of the ID that was asked for.
     * @throws AuditoriumCryptoException
     *             This method throws if the file can't be found or if it isn't
     *             in the correct format.
     */
    Key loadKey(String nodeid) throws AuditoriumCryptoException;

    /**
     * Load a PEM certificate from a file.
     * 
     * @param nodeid
     *            Load this node's certificate.
     * @return This method returns the certificate that wasa loaded from the
     *         given file.
     */
    Cert loadCert(String nodeid) throws AuditoriumCryptoException;
}
