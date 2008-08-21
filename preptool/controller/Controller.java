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
package preptool.controller;

import preptool.model.*;
import preptool.view.*;

/**
 * The Controller is main entry point of the program that initializes the model,
 * view and creates the adapters necesarry for communication between the model
 * and view
 * 
 * @author cshaw
 */
public class Controller {

    /**
     * The model of the program
     */
    private Model model;

    /**
     * The view (or GUI) of the program
     */
    private View view;

    /**
     * Constructs a new Controller. Initializes the model, view, and links them
     * with the model adapter. Finally it sets the view to visible.
     */
    public Controller() {
        ThreadGroup exceptionThreadGroup = new ExceptionGroup();
        new Thread( exceptionThreadGroup, "Init thread" ) {
            public void run() {
                model = new Model();
                view = new View( model );
                view.setVisible( true );
            }
        }.start();
    }

    /**
     * The main method of the program, constructs a new Contoller
     * 
     * @param args
     *            not used
     */
    public static void main(String args[]) {
        new Controller();
    }

}
