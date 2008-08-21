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
package votebox.middle.driver;

/**
 * This is thrown by someone who is asked to do something with a UID that he
 * doesn't believe to be valid.
 * 
 * @author derrley
 * 
 */
public class UnknownUIDException extends Exception {

	private static final long serialVersionUID = 1L;

	private String _uid;

	public UnknownUIDException(String uid) {
		super(uid + " is an unknown UID.");
		_uid = uid;
	}

	public String getUid() {
		return _uid;
	}

}
