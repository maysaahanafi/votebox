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
package preptool.model.layout;

import java.util.ArrayList;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import preptool.model.XMLTools;

/**
 * A ToggleButtonGroup is a set of ToggleButtons that follow a strategy when
 * selected. For instance, a standard strategy is to only allow one ToggleButton
 * to be selected at a time; when another is clicked, the others are deselected.
 * This is analagous to a Card in the ballot, but can be used for things that
 * aren't in the ballot as well (such as language selection).
 * @author cshaw
 */
public class ToggleButtonGroup extends ALayoutComponent {

	/**
	 * The strategy of this ToggleButtonGroup
	 */
	private String strategy;

	/**
	 * The array of ToggleButtons in this group
	 */
	private ArrayList<ToggleButton> buttons;

	/**
	 * Constructs a new ToggleButtonGroup with given strategy
	 * @param strat the strategy
	 */
	public ToggleButtonGroup(String strat) {
		super("");
		strategy = strat;
		buttons = new ArrayList<ToggleButton>();
	}

	/**
	 * Calls the forToggleButtonGroup method in visitor
	 * @param visitor the visitor
	 * @param param the parameters
	 * @return the result of the visitor
	 */
	@Override
	public <P,R> R execute(ILayoutComponentVisitor<P,R> visitor, P... param) {
		return visitor.forToggleButtonGroup(this, param);
	}

	/**
	 * @return the buttons
	 */
	public ArrayList<ToggleButton> getButtons() {
		return buttons;
	}

	/**
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * Converts this ToggleButtonGroup object to XML
	 * @param doc the document
	 * @return the element for this ToggleButtonGroup
	 */
	@Override
	public Element toXML(Document doc) {
		Element toggleButtonGroupElt = doc.createElement("ToggleButtonGroup");
		XMLTools.addProperty(doc, toggleButtonGroupElt, "ToggleButtonGroupStrategy",
				"String", strategy);
		for (ToggleButton b : buttons) {
			Element toggleButtonElt = b.toXML(doc);
			toggleButtonGroupElt.appendChild(toggleButtonElt);
		}
		return toggleButtonGroupElt;
	}

}
