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
package preptool.model.layout.manager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import preptool.model.ballot.ACard;
import preptool.model.ballot.Ballot;
import preptool.model.layout.ILayoutComponentVisitor;
import preptool.model.layout.Layout;
import preptool.view.ProgressInfo;


/**
 * Interface specifying a LayoutManager. On the highest level, the purpose of a
 * LayoutManager is to take a Ballot and produce a corresponding Layout.
 * @author cshaw
 */
public interface ILayoutManager {

	/**
	 * Creates a Layout using the information from the given Ballot.
	 * @param ballot the ballot
	 * @return the layout
	 */
	public Layout makeLayout(Ballot ballot);

	/**
	 * Renders all images in a layout to disk.
	 * @param layout the layout
	 * @param location disk location
     * @param progressInfo a ProgressInfo object to send progress updates to
	 */
	public void renderAllImagesToDisk(Layout layout, String location,
			ProgressInfo progressInfo);

	/**
	 * Sets the unique IDs of the entire ballot
	 * @param ballot the ballot
	 */
	public void assignUIDsToBallot(Ballot ballot);

	/**
	 * Executes this as a visitor to get a JPanel for the card's type
	 * @param card the card
	 * @return a JPanel with all elements laid out on it
	 */
	public ArrayList<JPanel> makeCardPage(ACard card);

	/**
	 * Returns an image visitor that renders an image of a component specific to
	 * this layout configuration
	 * @return the visitor
	 */
	public abstract ILayoutComponentVisitor<Boolean, BufferedImage> getImageVisitor();

}
