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
package supervisor;

import javax.swing.JOptionPane;

import supervisor.model.Model;
import supervisor.view.View;
import auditorium.Bugout;
import votebox.AuditoriumParams;

/**
 * This is the main entry point of the Supervisor. It is the "Controller" of the
 * MVC pattern.
 * @author cshaw
 */
public class Supervisor {

    public static final String FONTNAME = "Sans";

    /**
     * Runs the supervisor. If an argument is given, it will be the serial
     * number, otherwise, a random number between 1 and 9 is chosen.
     * @param args
     */
    public static void main(String[] args) {
		int i = 0;


		if (args.length > i && args[i].equals("-q")) {
			Bugout.MSG_OUTPUT_ON = false;
			i++;
		}

        if (args.length > i)
            new Supervisor(Integer.parseInt(args[i]));
        else
            new Supervisor(-1);
    }

    private Model model;

    private View view;

    /**
     * Constructs (and starts) a new instance of a supervisor
     * @param serial
     */
    private Supervisor(int serial) {
    	if(serial != -1)
    		model = new Model(serial, new AuditoriumParams("supervisor.conf"));
    	else
    		model = new Model(new AuditoriumParams("supervisor.conf"));
    	
        view = new View(model);
        view.setVisible(true);

        String keyword = "";
        while (keyword == null || keyword.equals(""))
            keyword = JOptionPane.showInputDialog(view,
                    "Please enter today's election keyword:", "Keyword",
                    JOptionPane.QUESTION_MESSAGE);
        model.setKeyword(keyword);

        view.display();
        model.start();
    }
}
