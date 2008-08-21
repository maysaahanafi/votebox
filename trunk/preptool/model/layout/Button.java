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

import java.awt.Color;
import java.awt.Dimension;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import preptool.model.XMLTools;

/**
 * A Button is a component that executes an action in VoteBox when clicked.
 * @author cshaw
 */
public class Button extends ALayoutComponent {

	/**
	 * The text on this button
	 */
	private String text;

	/**
	 * The strategy of this button when clicked
	 */
	private String strategy;

	/**
	 * Page to go to when clicked
	 */
	private int pageNum;

	/**
	 * Whether this button is bold
	 */
	private boolean bold;

	/**
	 * Whether this button is boxed
	 */
	private boolean boxed = true;

	/**
	 * Whether this button has an increased font size
	 */
	private boolean increasedFontSize;

	/**
	 * The background color of this button
	 */
	private Color backgroundColor = new Color(225, 227, 235);

	/**
	 * Constructs a new Button with given unique ID, text, and strategy
	 * @param uid the uniqueID
	 * @param text the text
	 * @param strat the ButtonStrategy
	 */
	public Button(String uid, String text, String strat) {
		super(uid);
		this.text = text;
		strategy = strat;
	}

	/**
	 * Constructs a new Button with given unique ID, text, strategy, and size
	 * visitor, which determines and sets the size.
	 * @param uid the unique ID
	 * @param text the text
	 * @param strat the ButtonStrategy
	 * @param sizeVisitor the size visitor
	 */
	public Button(String uid, String text, String strat,
			ILayoutComponentVisitor<Object,Dimension> sizeVisitor) {
		this(uid, text, strat);
		setSize(execute(sizeVisitor));
	}

	/**
	 * Calls the forButton method in visitor
	 * @param visitor the visitor
	 * @param param the parameters
	 * @return the result of the visitor
	 */
	@Override
	public <P,R> R execute(ILayoutComponentVisitor<P,R> visitor, P... param) {
		return visitor.forButton(this, param);
	}

	/**
	 * @return the backgroundColor
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @return the strategy
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return if this button is bold
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * @return the boxed
	 */
	public boolean isBoxed() {
		return boxed;
	}

	/**
	 * @return if this button has increasedFontSize
	 */
	public boolean isIncreasedFontSize() {
		return increasedFontSize;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @param bold the bold to set
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
	}

	/**
	 * @param boxed the boxed to set
	 */
	public void setBoxed(boolean boxed) {
		this.boxed = boxed;
	}

	/**
	 * @param increasedFontSize the increasedFontSize to set
	 */
	public void setIncreasedFontSize(boolean increasedFontSize) {
		this.increasedFontSize = increasedFontSize;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @param strategy the strategy to set
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * Converts this Button object to XML
	 * @param doc the document
	 * @return the element for this Button
	 */
	@Override
    public Element toXML(Document doc) {
		Element buttonElt = doc.createElement("Button");
		addCommonAttributes(doc, buttonElt);
		XMLTools.addProperty(doc, buttonElt, "ButtonStrategy", "String",
				strategy);

		if (strategy.equals("GoToPage"))
			XMLTools.addProperty(doc, buttonElt, "PageNumber", "Integer", Integer
					.toString(pageNum));
		return buttonElt;
	}

}
