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

import java.awt.Dimension;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A ToggleButton is similar to a button, but it holds state in that it can
 * either be selected or deselected. It is usually contained within a
 * ToggleButtonGroup, which specifies a strategy for selection of these buttons.
 * @author cshaw
 */
public class ToggleButton extends ALayoutComponent {

	/**
	 * The text of this ToggleButton
	 */
	private String text;

	/**
	 * The second line of this ToggleButton (used in Presidential Races)
	 */
	private String secondLine = "";
	
	/**
	 * The party text of this ToggleButton (used for candidates)
	 */
	private String party = "";

	/**
	 * Whether this ToggleButton has bold text
	 */
	private boolean bold;

	/**
	 * Whether this ToggleButton has increased font size
	 */
	private boolean increasedFontSize;

	/**
	 * Constructs a new ToggleButton with given unique ID and text
	 * @param uid the unique ID
	 * @param t the text
	 */
	public ToggleButton(String uid, String t) {
		super(uid);
		text = t;
	}

	/**
	 * Constructs a new ToggleButton with given unique ID, text, and size
	 * visitor, which determines and sets the size.
	 * @param uid the uniqueID
	 * @param t the text
	 * @param sizeVisitor the size visitor
	 */
	public ToggleButton(String uid, String t,
			ILayoutComponentVisitor<Object,Dimension> sizeVisitor) {
		this(uid, t);
		setSize(execute(sizeVisitor));
	}

	/**
	 * Calls the forToggleButton method in visitor
	 * @param visitor the visitor
	 * @param param the parameters
	 * @return the result of the visitor
	 */
	@Override
	public <P,R> R execute(ILayoutComponentVisitor<P,R> visitor, P... param) {
		return visitor.forToggleButton(this, param);
	}

	/**
	 * Returns two lines separated by a newline if necessary
	 */
	public String getBothLines() {
		if (secondLine.equals(""))
			return text;
		else
			return text + " \n " + secondLine;
	}

	/**
	 * @return the party
	 */
	public String getParty() {
		return party;
	}

	/**
	 * @return the secondLine
	 */
	public String getSecondLine() {
		return secondLine;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return if the toggle button is bold
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * @return if the toggle button has increasedFontSize
	 */
	public boolean isIncreasedFontSize() {
		return increasedFontSize;
	}

	/**
	 * @param bold the bold to set
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}

	/**
	 * @param increasedFontSize the increasedFontSize to set
	 */
	public void setIncreasedFontSize(boolean increasedFontSize) {
		this.increasedFontSize = increasedFontSize;
	}

	/**
	 * @param party the party to set
	 */
	public void setParty(String party) {
		this.party = party;
	}

	/**
	 * @param secondLine the secondLine to set
	 */
	public void setSecondLine(String secondLine) {
		this.secondLine = secondLine;
	}

	/**
	 * @return the String representation of this ToggleButton
	 */
	@Override
    public String toString() {
		return "ToggleButton[text=" + text + ",x=" + xPos + ",y=" + yPos
				+ ",width=" + width + ",height=" + height + "]";
	}

	/**
	 * Converts this ToggleButton object to XML
	 * @param doc the document
	 * @return the element for this ToggleButton
	 */
	@Override
	public Element toXML(Document doc) {
		Element toggleButtonElt = doc.createElement("ToggleButton");
		addCommonAttributes(doc, toggleButtonElt);
		return toggleButtonElt;
	}

}
