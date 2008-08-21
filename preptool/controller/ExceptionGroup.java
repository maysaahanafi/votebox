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

import java.awt.Frame;

import javax.swing.JFrame;

import preptool.view.dialog.*;

/**
 * Copied from http://www.javaspecialists.co.za/archive/Issue081.html
 */
public class ExceptionGroup extends ThreadGroup {
    public ExceptionGroup() {
        super( "ExceptionGroup" );
    }

    public void uncaughtException(Thread t, Throwable e) {
        new ExceptionDialog( findActiveFrame(), e.toString(), e.getStackTrace() )
                .showDialog();
        e.printStackTrace();
    }

    /**
     * I hate ownerless dialogs. With this method, we can find the currently
     * visible frame and attach the dialog to that, instead of always attaching
     * it to null.
     */
    private JFrame findActiveFrame() {
        Frame[] frames = JFrame.getFrames();
        for (int i = 0; i < frames.length; i++) {
            Frame frame = frames[i];
            if (frame.isVisible()) {
                return (JFrame) frame;
            }
        }
        return null;
    }
}
