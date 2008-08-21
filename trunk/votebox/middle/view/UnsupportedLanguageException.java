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
package votebox.middle.view;

/**
 * This exception is thrown when a language is required that is not supported.
 * 
 * @author Kyle
 * 
 */
public class UnsupportedLanguageException extends Exception {

	/**
	 * default serial version uid.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the language that isn't supported.
	 */
	private String _language;
	
	/**
	 * This is the public constructor for UnsupportedLanguageException
	 * 
	 * @param language
	 *            This is the language that isn't supported.
	 */
	public UnsupportedLanguageException(String language) {
		super(language + "is not supported in this ballot.");
		_language = language;
	}
	
	/**
	 * This is the getter for _language.
	 * @return _language.
	 */
	public String getLanguage(){
		return _language;
	}
}