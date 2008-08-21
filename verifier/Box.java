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
package verifier;

/**
 * An instance of this class represents a special boxed boolean -- one that
 * somewhat represents a fuse that can only be blown once.
 * 
 * @author kyle
 * 
 */
public class Box<E> {
	private E _value;

	public Box(E value) {
		_value = value;
	}

	public void set(E value) {
		_value = value;
	}

	public E get() {
		return _value;
	}
}
