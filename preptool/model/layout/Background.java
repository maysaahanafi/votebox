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

import java.awt.image.BufferedImage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A component that represents the background image of a page
 * @author cshaw
 */
public class Background extends ALayoutComponent {

	/**
	 * The image
	 */
	private BufferedImage image;

	/**
	 * Constructs a new Background with given unique ID and image
	 * @param uid the unique ID
	 * @param img the image
	 */
	public Background(String uid, BufferedImage img) {
		super(uid);
		image = img;
		width = img.getWidth();
		height = img.getHeight();
	}

	/**
	 * Calls the forBackground method in visitor
	 * @param visitor the visitor
	 * @param param list of parameters to pass to the appropriate visitor method
	 * @return the result of the visitor
	 */
	@Override
	public <P,R> R execute(ILayoutComponentVisitor<P,R> visitor, P... param) {
		return visitor.forBackground(this, param);
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * Converts this Background object to XML (same as a Label)
	 * @param doc the document
	 * @return the element for this Background
	 */
	@Override
	public Element toXML(Document doc) {
		Element labelElt = doc.createElement("Label");
		addCommonAttributes(doc, labelElt);
		return labelElt;
	}

}
