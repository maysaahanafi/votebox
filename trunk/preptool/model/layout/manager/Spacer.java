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

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JLabel;

import preptool.model.layout.ALayoutComponent;


/**
 * A Spacer is a holder for an ALayoutComponent, but it also subclasses JLabel
 * so that it can be placed onto a JPanel. This allows Swing to layout our
 * components for us.
 * @author cshaw, ttorous
 */
@SuppressWarnings("serial")
public class Spacer extends JLabel {

	/**
	 * The component held by this Spacer
	 */
	private ALayoutComponent comp;

	/**
	 * The container holding this Spacer
	 */
	private Container par;

	/**
	 * Creates an Spacer with the given component and container, and sets its
	 * size to the size of the component.<br>
	 * Warning: The size of the component must be set properly before
	 * constructing the Spacer, or it will be initialized improperly!
	 * @param lc the LayoutComponent this corresponds to
	 * @param container the Container that holds this Spacer
	 */
	public Spacer(ALayoutComponent lc, Container container) {
		setMinimumSize(new Dimension(lc.getWidth(), lc.getHeight()));
		setPreferredSize(new Dimension(lc.getWidth(), lc.getHeight()));
		setMaximumSize(new Dimension(lc.getWidth(), lc.getHeight()));
		setSize(new Dimension(lc.getWidth(), lc.getHeight()));

		comp = lc;
		par = container;
		validate();
	}

	/**
	 * Returns the component
	 * @return the component
	 */
	public ALayoutComponent getComponent() {
		return comp;
	}

	/**
	 * Returns the absolute X coordinate. Simply calling getX will not work
	 * because this returns the relative x coordinate of the label to its
	 * parent. This implementation assumes that the parent only goes one layer
	 * up
	 * @return the absolute X coordintate
	 */
	public int getXCoordinate() {
		return par.getX() + getX();
	}

	/**
	 * See getXCoordinate
	 * @return the absolute Y coordinte
	 */
	public int getYCoordinate() {
		return par.getY() + getY();
	}

	/**
	 * Updates the x and y coordinates of the component's position based on the
	 * spacer's position
	 */
	public void updatePosition() {
		comp.setXPos(getXCoordinate());
		comp.setYPos(getYCoordinate());
	}

}
