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

import preptool.model.language.Language;

/**
 * A factory for making a LayoutManager with a given language
 * @author cshaw
 */
public interface ILayoutManagerFactory {

	/**
	 * Constructs a new LayoutManager with the given language
	 * @param language the language
	 * @param numCardsPerReviewPage number of cards per review page
	 * @param fontSize size of font to use
	 * @param commitChallenge whether or not to use the challenge-commit model when generating the ballot layour.
	 * @return the LayoutManager
	 */
	public ILayoutManager makeLayoutManager(Language language, int numCardsPerReviewPage, int fontSize, boolean commitChallenge);

}
