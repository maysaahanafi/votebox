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

import votebox.middle.Properties;

/**
 * This interface defines a general adapter that the
 * 
 * @author Kyle
 * 
 */
public interface IAdapter {

	/**
	 * Call this method to ask that a specific element be selected.
	 * 
	 * @param uid
	 *            Ask to select a card element that has this UID.
	 * @return This method returns true if the selection was a success (if it
	 *         was allowed), and false otherwise.
	 * @throws UnknownUIDException
	 *             This method throws when the UID that is given as a parameter
	 *             is not defined.
	 */
	public boolean select(String uid) throws UnknownUIDException, SelectionException;

	/**
	 * Call this method to ask that a specific element be deselected.
	 * 
	 * @return This method returns true if the deselection was a success (if it
	 *         was allowed), and false otherwise.
	 * @param uid
	 *            Ask to deselect a card element that has this UID.
	 * @throws UnknownUIDException
	 *             This method throws when the UID that is given as a parameter
	 *             is not defined.
	 */
	public boolean deselect(String uid) throws UnknownUIDException, DeselectionException;

	/**
	 * Call this method to get properties.
	 * 
	 * @return This method returns properties.
	 */
	public Properties getProperties();
}
