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
 * A Page represents what a user would see on the screen at any point during
 * voting. Each Page contains a list of ALayoutComponents, which keep track of
 * their size and location, and can be rendered on the screen.
 * @author cshaw
 */
public class Page {

	/**
	 * The array of components on this Page
	 */
	private ArrayList<ALayoutComponent> components;

	/**
	 * Unique ID of the background label (if any)
	 */
	private String backgroundLabel = "";

	/**
	 * Constructs a blank page with an empty list of components.
	 */
	public Page() {
		components = new ArrayList<ALayoutComponent>();
	}

	/**
	 * @return the backgroundLabel
	 */
	public String getBackgroundLabel() {
		return backgroundLabel;
	}

	/**
	 * @return the list of components
	 */
	public ArrayList<ALayoutComponent> getComponents() {
		return components;
	}

	/**
	 * @param backgroundLabel the backgroundLabel to set
	 */
	public void setBackgroundLabel(String backgroundLabel) {
		this.backgroundLabel = backgroundLabel;
	}

	/**
	 * @return a String for this Page
	 */
	@Override
	public String toString() {
		return super.toString() + "[ " + components + "]";
	}

	/**
	 * Converts this Page object to XML
	 * @param doc the document
	 * @return the element for this Page
	 */
	public Element toXML(Document doc) {
		Element pageElt = doc.createElement("Page");
		if (!backgroundLabel.equals(""))
			XMLTools.addProperty(doc, pageElt, "BackgroundLabel", "String",
					backgroundLabel);
		for (ALayoutComponent comp : components) {
			Element compElt = comp.toXML(doc);
			pageElt.appendChild(compElt);
		}
		return pageElt;
	}
}
