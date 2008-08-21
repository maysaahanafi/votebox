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

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

/**
 * An extension of the DefaultTableModel that provides a moveRow method that
 * moves a single row within the table, and fires a special move event that
 * distinguishes from simply updating the objects in each row
 * 
 * @author cshaw
 */
public class MovableTableModel extends DefaultTableModel implements
        IMovableTableModel {

    private static final long serialVersionUID = 1L;

    public MovableTableModel() {
        super();
    }

    public MovableTableModel(int rowCount, int columnCount) {
        super( rowCount, columnCount );
    }

    public MovableTableModel(Object[] columnNames, int rowCount) {
        super( columnNames, rowCount );
    }

    public MovableTableModel(Object[][] data, Object[] columnNames) {
        super( data, columnNames );
    }

    public MovableTableModel(Vector columnNames, int rowCount) {
        super( columnNames, rowCount );
    }

    public MovableTableModel(Vector data, Vector columnNames) {
        super( data, columnNames );
    }

    /**
     * Moves the row from index 'from' to index 'to', and fires a table move
     * event
     * 
     * @param from
     *            the from index
     * @param to
     *            the to index
     */
    @SuppressWarnings("unchecked")
    public void moveRow(int from, int to) {
        if (from != to) {
            Vector row = (Vector) dataVector.get( from );
            if (to > from) {
                for (int i = from; i < to; i++)
                    dataVector.set( i, dataVector.get( i + 1 ) );
            }
            else {
                for (int i = from; i > to; i--)
                    dataVector.set( i, dataVector.get( i - 1 ) );
            }
            dataVector.set( to, row );
        }
        fireTableChanged( new TableModelEvent( this, from, to,
                TableModelEvent.ALL_COLUMNS, MOVE ) );
    }

}
