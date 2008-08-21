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

import preptool.model.XMLTools;
import preptool.model.language.Language;

/**
 * A LanguageButton is a ToggleButton that specifies a language selection
 * on the ballot, thus it must have a Language.
 * @author cshaw
 */
public class LanguageButton extends ToggleButton {

    Language language;

    /**
     * @param uid
     * @param t
     * @param sizeVisitor
     */
    public LanguageButton(String uid, String t,
            ILayoutComponentVisitor<Object, Dimension> sizeVisitor) {
        super(uid, t, sizeVisitor);
    }

    /**
     * @param uid
     * @param t
     */
    public LanguageButton(String uid, String t) {
        super(uid, t);
    }

    /**
     * @return the language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(Language language) {
        this.language = language;
    }
    
    @Override
    public Element toXML(Document doc) {
        Element buttonElt = super.toXML(doc);
        XMLTools.addProperty(doc, buttonElt, "Language", "String", language.getShortName());
        return buttonElt;
    }

}
