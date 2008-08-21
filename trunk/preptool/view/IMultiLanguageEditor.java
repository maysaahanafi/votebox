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
package preptool.view;

import preptool.model.language.Language;

/**
 * An interface that links with a LanguageBar.  The LanguageBar uses this interface
 * to inform the editor that the language has been changed or to check if all
 * translation data exists.
 * @author cshaw
 */
public interface IMultiLanguageEditor {
	
	/**
	 * Method that is called by a LanguageBar when a language has been selected
	 * @param lang the language
	 */
	public void languageSelected(Language lang);
	
	/**
	 * Checks if the editor has all translation information filled in for the
	 * given language
	 * @param lang the language
	 * @return whether all translation info is entered
	 */
	public boolean needsTranslation(Language lang);
	
	/**
	 * Called by a LanguageBar when the primary language has changed
	 * @param lang the language
	 */
	public void updatePrimaryLanguage(Language lang);
	
}
