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

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import preptool.model.ballot.module.AModule;
import preptool.model.language.Language;


/**
 * A CardView is a panel that is shown in the card editor pane. It contains a
 * list of module views, displaying them in rows on the panel.
 * 
 * @author cshaw
 */
public class CardView extends JPanel implements IMultiLanguageEditor {

    private static final long serialVersionUID = 1L;
    private ArrayList<AModuleView> views;

    /**
     * Constructs a new CardView
     * 
     * @param view
     *            the main view
     * @param type
     *            the type name of the card
     * @param modules
     *            list of the modules on the card
     */
    public CardView(View view, String type, ArrayList<AModule> modules) {
        views = new ArrayList<AModuleView>();
        setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();

        JLabel typeLabel = new JLabel( type );
        typeLabel.setFont( new Font( "Lucida Sans", Font.BOLD, 16 ) );
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets( 10, 10, 0, 0 );
        add( typeLabel, c );

        c.insets = new Insets( 10, 0, 0, 0 );

        for (int i = 0; i < modules.size(); i++) {
            if (modules.get( i ).hasView()) {
                AModuleView v = modules.get( i ).generateView( view );
                views.add( v );
            }
        }
        for (int i = 0; i < views.size(); i++) {
            c.gridy = i + 1;
            if (i == views.size() - 1)
                c.weighty = 1;
            add( views.get( i ), c );
        }
    }

    /**
     * Forwards the call onto all modules in this view that the language was
     * updated
     */
    public void languageSelected(Language lang) {
        for (AModuleView mod : views)
            mod.languageSelected( lang );
    }

    /**
     * Checks all modules on this view and reports if any need translation
     * information in the given language
     * 
     * @param lang
     *            the language
     */
    public boolean needsTranslation(Language lang) {
        boolean res = false;
        for (AModuleView mod : views)
            res |= mod.needsTranslation( lang );
        return res;
    }

    /**
     * Forwards the call onto all modules in this view that the primary language
     * was updated
     */
    public void updatePrimaryLanguage(Language lang) {
        for (AModuleView mod : views)
            mod.updatePrimaryLanguage( lang );
    }

}
