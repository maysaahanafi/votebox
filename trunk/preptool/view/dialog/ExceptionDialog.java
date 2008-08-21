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
package preptool.view.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ExceptionDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    /**
     * The title label on the dialog
     */
    private JLabel titleLabel;

    /**
     * The sub task label on the dialog
     */
    private JTextArea textArea;

    /**
     * The OK button on the dialog
     */
    private JButton okButton;

    /**
     * Constructs a new ProgressTask with the given parent frame and title
     * 
     * @param parent
     *            the parent frame
     * @param stackTrace
     *            the exception's stack trace
     */
    public ExceptionDialog(JFrame parent, String message,
            StackTraceElement[] stackTrace) {
        super( parent, "Unhandled Exception", true );
        this.setSize( 700, 500 );
        this.setLocationRelativeTo( parent );
        this.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();

        titleLabel = new JLabel( message );
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets( 15, 15, 15, 15 );
        c.weightx = 1;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.BOTH;
        this.add( titleLabel, c );

        String stackTraceString = "";
        for (StackTraceElement s : stackTrace)
            stackTraceString += s + "\n";

        textArea = new JTextArea();
        textArea.setText( stackTraceString );
        c.gridy = 1;
        c.insets = new Insets( 0, 15, 15, 15 );
        c.weighty = 1;
        JScrollPane textAreaScrollPane = new JScrollPane( textArea );
        textAreaScrollPane.setBorder( BorderFactory
                .createTitledBorder( "Stack Trace:" ) );
        this.add( textAreaScrollPane, c );

        JPanel buttonPanel = new JPanel();
        okButton = new JButton( "OK" );
        okButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible( false );
            }
        } );
        buttonPanel.add( okButton );

        c.gridy = 2;
        c.insets = new Insets( 0, 15, 15, 15 );
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.PAGE_END;
        this.add( buttonPanel, c );
    }

    /**
     * Shows the dialog.
     */
    public void showDialog() {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                setVisible( true );
            }
        } );
    }

}
