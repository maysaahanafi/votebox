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
package preptool.view.dragndrop;

/**
 * Interface for a listener that is called when a ListTransferHandler moves an
 * item in a JList
 * @author cshaw
 */
public interface ListTransferListener {

	/**
	 * The method that is called when the item is moved
	 * @param from old index
	 * @param to new index
	 */
	public void listItemMoved(int from, int to);

}
