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
package votebox.middle;

import java.util.Observable;

/**
 * Because this code was originally designed for C#, there are a few places that
 * use the C# event primitive. This implementation of a java Observable provides
 * the same behavior. Call Event.addObserver(...) to add callbacks. Call
 * Event.notifyObservers() to invoke all of them.
 * 
 * @author Kyle
 * 
 */
public class Event extends Observable {

    /**
     * Call this method to immediately invoke the update(...) method on every
     * observer.
     * 
     * @see java.util.Observable#notifyObservers()
     */
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

}
