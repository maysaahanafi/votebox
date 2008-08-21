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
package actionparser;

import java.io.PrintStream;
import java.util.*;

public class PageTranscript {
	
	private int _pageNum;
	private int _pageViewNum;
	private ArrayList<UIAction> _actions=new ArrayList<UIAction>();
	
	public PageTranscript(int pageNum, int pageViewNum){
		_pageNum=pageNum;
		_pageViewNum=pageViewNum;
	}
	
	public PageTranscript(ArrayList<UIAction> actions,int pageNum, int pageViewNum){
		_actions=actions;
		_pageNum=pageNum;
		_pageViewNum=pageViewNum;
	}
	
	public long getTotalTime(){
		return _actions.get(_actions.size()-1).get_time()-_actions.get(0).get_time();
	}

	/**
	 * @return Returns the _actions.
	 */
	public ArrayList<UIAction> getActions() {
		return _actions;
	}

	/**
	 * @param _actions The _actions to set.
	 */
	public void setActions(ArrayList<UIAction> _actions) {
		this._actions = _actions;
	}
	
	public void addAction(UIAction action){
		_actions.add(action);
	}

	/**
	 * @return Returns the _pageNum.
	 */
	public int getPageNum() {
		return _pageNum;
	}

	/**
	 * @param num The _pageNum to set.
	 */
	public void setPageNum(int num) {
		_pageNum = num;
	}

	/**
	 * @return Returns the _pageViewNum.
	 */
	public int getPageViewNum() {
		return _pageViewNum;
	}

	/**
	 * @param viewNum The _pageViewNum to set.
	 */
	public void setPageViewNum(int viewNum) {
		_pageViewNum = viewNum;
	}
	
	/**
	 * the time this page view began
	 */
	public long getStartTime() {
		return _actions.get(0).get_time();
	}
	
	/**
	 * the time this page view ended
	 */
	public long getEndTime() {
		return _actions.get(_actions.size()-1).get_time();
	}
	
	public void printPageInfo(PrintStream out){
		out.println(getPageViewNum()+","+getPageNum()+","+getTotalTime());
	}
	
}
