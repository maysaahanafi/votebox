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
package votebox.middle.view.widget;

/**
 * Buttons delegate the behavior that is executed when they are clicked to an
 * instance of IButtonStrategy.
 * 
 * @author Kyle
 * 
 */
public interface IButtonStrategy {

	/**
	 * The behavior that is executed when the button is pressed is defined here.
	 * 
	 * @param context
	 *            This is the button that is delegating the behavior.
	 */
	public void execute(Button context) ;
}