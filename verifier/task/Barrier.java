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
package verifier.task;

public class Barrier {

	private final int _num;

	private int _count;

	public Barrier(int num) {
		_num = num;
		_count = 0;
	}

	public synchronized void hit() {
		_count++;
		if (_count == _num)
			notifyAll();
		else
			while (_count < _num)
				try {
					wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
	}
}
