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
 * This class wraps an exception that happens as a result of a crypto operation.
 * It's existence is due to the fact that there are so *many* freaking
 * exceptions that can happen in the crypto layer.
 * 
 * @author kyle
 * 
 */
public class AuditoriumCryptoException extends Exception {

    private static final long serialVersionUID = 1L;

    public AuditoriumCryptoException(String action, Exception e) {
        super( "There was a problem while performing the crypto action: "
                + action + ".", e );
    }
}
