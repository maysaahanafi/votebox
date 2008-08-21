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
package votebox;

/**
 * Will, sometime in the future, actually read battery status if able.
 * 
 * Currently, a better solution that leaving a Math.random() in votebox.Votebox proper.
 * @author Montrose
 *
 */
public class BatteryStatus {
	public static int read(){
		return (int)(Math.random() * 100.0);
	}
}