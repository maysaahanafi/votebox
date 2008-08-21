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
package supervisor.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import supervisor.model.Model;

/**
 * The Supervisor's view. The common denominator is simply the frame, and the
 * observer that switches views between the active and inactive UI.
 * @author cshaw
 */
@SuppressWarnings("serial")
public class View extends JFrame {

    InactiveUI inactiveUI;

    ActiveUI activeUI;

    /**
     * Constructs a new View
     * @param model the model
     */
    public View(final Model model) {
        super("Supervisor Console");

        setSize(1024, 768);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // setUndecorated(true);

        activeUI = new ActiveUI(model);
        inactiveUI = new InactiveUI(model);

        model.registerForActivated(new Observer() {
            public void update(Observable o, Object arg) {
                if (model.isActivated())
                    setContentPane(activeUI);
                else
                    setContentPane(inactiveUI);
                validate();
                repaint();
            }
        });
    }

    /**
     * Shows the inactive UI (called after the keyword has been entered).<br>
     * The view is blank until this is called.
     */
    public void display() {
        setContentPane(inactiveUI);
        validate();
        repaint();
    }

}
