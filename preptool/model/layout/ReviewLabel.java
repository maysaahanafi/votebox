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

/**
 * A special type of Label that is seen on review screens.
 * @author cshaw
 */
public class ReviewLabel extends Label {

	/**
	 * Constructs a ReviewLabel with the given unique ID, text, and strategy.
	 * @param uid the unique ID
	 * @param text the text
	 */
	public ReviewLabel(String uid, String text) {
		super(uid, text);
	}

	/**
	 * Constructs a ReviewButton with the given unique ID, text, strategy, and
	 * size visitor that determines and sets the size.
	 * @param uid the unique ID
	 * @param text the text
	 * @param sizeVisitor the size visitor
	 */
	public ReviewLabel(String uid, String text,
			ILayoutComponentVisitor<Object,Dimension> sizeVisitor) {
		this(uid, text);
		setSize(execute(sizeVisitor));
	}

	/**
	 * Calls the forReviewButton method in visitor
	 * @param visitor the visitor
	 * @param param the parameters
	 * @return the result of the visitor
	 */
	@Override
	public <P,R> R execute(ILayoutComponentVisitor<P,R> visitor, P... param) {
		return visitor.forReviewLabel(this, param);
	}

}
