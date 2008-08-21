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
package preptool.view.dragndrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 * <p>
 * Abstract handler for drag and drop events that uses Strings as its
 * Transferable representation. This class also checks to see if the drag and
 * drop was on the same component, only then does it complete.
 * </p>
 * Inspired by Copied from
 * http://java.sun.com/docs/books/tutorial/uiswing/examples/dnd/ExtendedDnDDemoProject/src/dnd/StringTransferHandler.java
 * <br>
 * Modifications by cshaw
 */
public abstract class StringTransferHandler extends TransferHandler {

	/**
	 * Component that originated the drag
	 */
	protected JComponent fromComponent;

	/**
	 * Whether the data was dragged to the same component
	 */
	protected boolean sameComponent;

	/**
	 * Abstract method to export a string from the component
	 * @param c the component
	 * @return a String
	 */
	protected abstract String exportString(JComponent c);

	/**
	 * Imports a String into a component
	 * @param c the component
	 * @param str the String
	 */
	protected abstract void importString(JComponent c, String str);

	/**
	 * Finishes a successful drag and drop operation
	 * @param c the component
	 * @param remove whether to move the data
	 */
	protected abstract void cleanup(JComponent c, boolean remove);

	/**
	 * Creates a StringSelection from the return value of exportString
	 */
	@Override
    protected Transferable createTransferable(JComponent c) {
		fromComponent = c;
		sameComponent = false;
		return new StringSelection(exportString(c));
	}

	/**
	 * @return MOVE
	 */
	@Override
    public int getSourceActions(JComponent c) {
		return MOVE;
	}

	/**
	 * Imports the data from a transferable to a component
	 */
	@Override
    public boolean importData(JComponent c, Transferable t) {
		if (c == fromComponent && canImport(c, t.getTransferDataFlavors())) {
			try {
				String str = (String) t
						.getTransferData(DataFlavor.stringFlavor);
				importString(c, str);
				sameComponent = true;
				return true;
			} catch (UnsupportedFlavorException ufe) {
			} catch (IOException ioe) {
			}
		}

		return false;
	}

	/**
	 * Called when the drag and drop operation is complete
	 */
	@Override
    protected void exportDone(JComponent c, Transferable data, int action) {
		if (sameComponent) cleanup(c, action == MOVE);
		fromComponent = null;
		sameComponent = false;
	}

	/**
	 * Checks if the component can accept data from a drag
	 */
	@Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
		if (c != fromComponent) return false;
		for (int i = 0; i < flavors.length; i++) {
			if (DataFlavor.stringFlavor.equals(flavors[i])) {
				return true;
			}
		}
		return false;
	}
}
