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

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JLabel;

import supervisor.Supervisor;

/**
 * A decorator for a JLabel that uses a standardized font, and turns on
 * antialiasing.
 * @author cshaw
 */
@SuppressWarnings("serial")
public class MyJLabel extends JLabel {

    public MyJLabel() {
        super();
        setFont(new Font(Supervisor.FONTNAME, Font.PLAIN, 12));
    }

    public MyJLabel(Icon arg0, int arg1) {
        super(arg0, arg1);
        setFont(new Font(Supervisor.FONTNAME, Font.PLAIN, 12));
    }

    public MyJLabel(Icon arg0) {
        super(arg0);
        setFont(new Font(Supervisor.FONTNAME, Font.PLAIN, 12));
    }

    public MyJLabel(String arg0, Icon arg1, int arg2) {
        super(arg0, arg1, arg2);
        setFont(new Font(Supervisor.FONTNAME, Font.PLAIN, 12));
    }

    public MyJLabel(String arg0, int arg1) {
        super(arg0, arg1);
        setFont(new Font(Supervisor.FONTNAME, Font.PLAIN, 12));
    }

    public MyJLabel(String arg0) {
        super(arg0);
        setFont(new Font(Supervisor.FONTNAME, Font.PLAIN, 12));
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paint(g);
    }

}
