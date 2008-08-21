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
package votebox.middle.ballot;

import sexpression.ASExpression;
import votebox.middle.driver.UnknownUIDException;

/**
 * Use an instance of this interface to make queries on the ballot.
 * 
 * @author Kyle
 * 
 */
public interface IBallotLookupAdapter {

	/**
	 * Look up a UID in the ballot, and check if it is representative of a Card.
	 * 
	 * @param UID
	 *            Lookup this UID in the ballot.
	 * @return This method returns true if the given UID is a Card, false if it
	 *         isn't.
	 * @throws UnknownUIDException
	 */
	public boolean isCard(String UID) throws UnknownUIDException;

	/**
	 * Look up a card's selected element.
	 * 
	 * @param UID
	 *            Look up the card given by this UID
	 * @return This method returns the UID of the given card's currently
	 *         selected element, or returns the UID of the card if no element on
	 *         the card is selected.
	 * @throws NonCardException
	 *             This method throws if the UID given is not representative of
	 *             a card.
	 * @throws CardException
	 *             This method throws if the Card encounters a problem when it
	 *             tries to determine what the selected element is.
	 * @throws UnknownUIDException
	 *             This method throws if the UID given does not exist in the
	 *             ballot.
	 */
	public String selectedElement(String UID) throws NonCardException,
			CardException, UnknownUIDException;

	/**
	 * Call this method to check the state of an element.
	 * 
	 * @param uid
	 *            This is the uniqueid of the element whose state you wish to
	 *            check.
	 * @throws UnknownUIDException
	 *             This method throws an exception if the UID given as a
	 *             parameter does not exist.
	 * @return This method returns true if the element given is currently
	 *         selected, or false if it is not.
	 */
	public boolean isSelected(String uid) throws UnknownUIDException;

	/**
	 * Call this method to check that a particular UID exists.
	 * 
	 * @param uid
	 *            This is the UID you wish to check.
	 * @return This method returns true if the checked UID exists, or false if
	 *         it does not.
	 */
	public boolean exists(String uid);

	/**
	 * Call this method to get the s-expression representation of the ballot.
	 * 
	 * @return This method returns an s-expression representation of the ballot.
	 */
	public ASExpression getCastBallot();

	/**
	 * Call this method to get the number of selections that have currently been
	 * made in the target ballot. By "selections" here, we mean the number of
	 * card elements that belong to this ballot that have selections on them at
	 * the time of the method call (these selections cannot be "no selection").
	 * 
	 * @return This method returns the number of selections that have been made
	 *         on the ballot associated with this adapter.
	 */
	public int numSelections();
}
