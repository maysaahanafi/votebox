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
package preptool.model.language;

import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * A class that contains multiple translations of a String. It is a wrapper for
 * a map from language to string.
 * @author cshaw
 */
public class LocalizedString {

	/**
	 * The map containing the strings
	 */
	private HashMap<Language, String> map;

	/**
	 * Constructs a LocalizedString with no translations
	 */
	public LocalizedString() {
		map = new HashMap<Language, String>();
	}

	/**
	 * Checks all Strings in both maps, and makes sure there are the
	 * same number of localizations
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LocalizedString))
			return false;
		LocalizedString rhs = (LocalizedString)obj;
		if (map.size() != rhs.map.size())
			return false;
		for (Language lang: map.keySet())
			if (!map.get(lang).equals(rhs.map.get(lang)))
				return false;
		return true;
	}
	
	/**
	 * Returns the translation for the given language
	 * @param lang the language
	 * @return the translation
	 */
	public String get(Language lang) {
		String val = map.get(lang);
		if (val == null)
			return "";
		else
			return val;
	}

	/**
	 * Sets (or adds) the translation for the given language to the given String
	 * @param lang the language
	 * @param val the String
	 */
	public void set(Language lang, String val) {
		map.put(lang, val);
	}

	/**
	 * Converts this card to a savable XML representation, to be opened later
	 * @param doc the document
	 * @param name name to attach to the element
	 * 
	 * @return the element for this card
	 */
	public Element toSaveXML(Document doc, String name) {
		Element elt = doc.createElement("LocalizedString");
		elt.setAttribute("name", name);
		for (Language lang: map.keySet()) {
			Element stringElt = doc.createElement("String");
			stringElt.setAttribute("language", lang.getName());
			stringElt.setAttribute("text", map.get(lang));
			elt.appendChild(stringElt);
		}
		return elt;
	}

	/**
	 * Parses XML into a LocalizedString object
	 * @param elt the element
	 * @return the LocalizedString
	 */
	public static LocalizedString parseXML(Element elt) {
		assert elt.getTagName().equals("LocalizedString");
		LocalizedString ls = new LocalizedString();

		NodeList list = elt.getElementsByTagName("String");
		for (int i = 0; i < list.getLength(); i++) {
			Element child = (Element) list.item(i);
			ls.set(Language.getLanguageForName(child.getAttribute("language")),
					child.getAttribute("text"));
		}
		return ls;
	}

}
