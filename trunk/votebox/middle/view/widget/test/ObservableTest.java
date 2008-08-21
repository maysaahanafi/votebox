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
package votebox.middle.view.widget.test;

import java.util.Observable;
import java.util.Observer;

import junit.framework.TestCase;

public class ObservableTest extends TestCase{

	int count = 0;
	
	public class TestObservable extends Observable{
		
		public void notifyObservers(){
			setChanged();
			super.notifyObservers();
		}
	}
	
	public class TestObserver implements Observer{

		public void update(Observable arg0, Object arg1) {
			count++;
		}
		
	}
	
	public void test_observer() {
		ObservableTest ot = new ObservableTest();
		ot.runTest();
	}
	
	public void runTest(){
		TestObservable to = new TestObservable();
		to.addObserver(new TestObserver());
		to.addObserver(new TestObserver());
		to.addObserver(new TestObserver());
			
		to.notifyObservers();
		
		assertEquals(3,count);
	}
}
