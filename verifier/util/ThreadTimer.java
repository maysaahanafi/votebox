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
package verifier.util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class ThreadTimer {
	// helpful
	//
	public static final long NS_PER_MS = 1000000;

	public static long getThreadTime(long tid) {
		ThreadMXBean tb = ManagementFactory.getThreadMXBean();
		return tb.getThreadCpuTime(tid);
	}
	public static long getThreadTimeMilli(long tid) {
		return getThreadTime(tid) / NS_PER_MS;
	}
	public static long getCurrentThreadTime() {
		ThreadMXBean tb = ManagementFactory.getThreadMXBean();
		return tb.getCurrentThreadCpuTime();
	}
	public static long getCurrentThreadTimeMilli() {
		return getCurrentThreadTime() / NS_PER_MS;
	}
	
	// members
	//
	long _tid; /// thread ID
	long _start_ns; 
	long _stop_ns;

	// ctors
	//
	public ThreadTimer(long tid) {
		_tid = tid;
		_start_ns = _stop_ns = 0;
	}
	public static ThreadTimer timerForThread(Thread t) {
		return new ThreadTimer(t.getId());
	}
	public static ThreadTimer timerForCurrentThread() {
		return new ThreadTimer(Thread.currentThread().getId());
	}

	// methods
	//
	public long getTimeMilli() {
		return getThreadTimeMilli(_tid);
	}
	public long getTime() {
		return getThreadTime(_tid);
	}

	public void start() {
		_stop_ns = 0;
		_start_ns = getTime();
	}
	public long look() {
		if (_stop_ns > 0L)
			return _stop_ns - _start_ns;
		if (_start_ns > 0L)
			return getTime() - _start_ns;
		return 0L;
	}
	public long lookMilli() { return look() / NS_PER_MS; }
	public long stop() {
		if (_start_ns > 0)
			_stop_ns = getTime();
		return _stop_ns - _start_ns;
	}
	public void resume() {
		_stop_ns = 0;
	}
}
