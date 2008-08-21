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

/**
 * Denotes an error or inconsistency in a Card's state (like a property that
 * doesn't make any sense.)
 * 
 * @author Kyle
 * 
 */
public class CardException extends Exception {

	private static final long serialVersionUID = 1L;

	private Card instance;

	CardException(Card card, String message) {
		super("Card " + card.getUniqueID() + " had an error: " + message);
		instance = card;
	}

	public Card getInstance() {
		return instance;
	}
}