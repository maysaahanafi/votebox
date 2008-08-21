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

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;

/**
 * <p>
 * Handler for drag and drop events in a JList. This handler is specific in that
 * it does not package any information in the Transferable, rather it requires
 * the drag be only within the list (by checking if the components are the
 * same), and moves the item from the old index to the new index.
 * </p>
 * Inspired by
 * http://java.sun.com/docs/books/tutorial/uiswing/examples/dnd/ExtendedDnDDemoProject/src/dnd/ListTransferHandler.java
 * <br>
 * Modifications by cshaw
 */
public class ListTransferHandler extends StringTransferHandler {

    private static final long serialVersionUID = 1L;

    /**
     * Index the drag originated from
     */
    private int remIndex;

    /**
     * Index to insert the element at
     */
    private int addIndex;

    /**
     * Array of listeners that are called whenever a successful drag occurs
     */
    private ArrayList<ListTransferListener> listeners = new ArrayList<ListTransferListener>();

    /**
     * Records the current selected index
     */
    @Override
    protected String exportString(JComponent c) {
        JList list = (JList) c;
        remIndex = list.getSelectedIndex();
        addIndex = -1;
        return "";
    }

    /**
     * Records the target selected index. Fails if the indices are the same
     */
    @Override
    protected void importString(JComponent c, String str) {
        JList target = (JList) c;
        DefaultListModel listModel = (DefaultListModel) target.getModel();
        addIndex = target.getSelectedIndex();

        if (remIndex == addIndex) {
            remIndex = -1;
            addIndex = -1;
            return;
        }

        int max = listModel.getSize();
        if (addIndex < 0 || addIndex > max - 1)
            addIndex = max - 1;
    }

    /**
     * Performs the move operation by moving the list item from its old index to
     * the new one. Also calls all listeners.
     */
    @Override
    protected void cleanup(JComponent c, boolean remove) {
        if (remove && remIndex != -1) {
            JList source = (JList) c;
            DefaultListModel model = (DefaultListModel) source.getModel();
            Object item = model.remove( remIndex );
            model.add( addIndex, item );
            for (ListTransferListener l : listeners)
                l.listItemMoved( remIndex, addIndex );
            source.setSelectedIndex( addIndex );
            c.validate();
        }
        remIndex = -1;
        addIndex = -1;
    }

    /**
     * Adds a transfer listener to the list
     * 
     * @param l
     *            the listener
     */
    public void addListTransferListener(ListTransferListener l) {
        listeners.add( l );
    }

}
