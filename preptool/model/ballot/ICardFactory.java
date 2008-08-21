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
package preptool.model.ballot;

/**
 * Factory interface for creating ACards.
 * @author Corey Shaw
 */
public interface ICardFactory {

	/**
	 * Makes a new card
	 * @return the card
	 */
	public ACard makeCard();

	/**
	 * Returns the String to display in the view's add card menu.
	 */
	public String getMenuString();

}
