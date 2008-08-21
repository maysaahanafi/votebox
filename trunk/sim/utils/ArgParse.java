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
package sim.utils;

import java.util.*;

public class ArgParse {
	@SuppressWarnings("serial")
	public static class FormatException extends RuntimeException {
		String _arg;
		public FormatException(String arg) {
			_arg = arg;
		}
		public String toString() { 
			return "incorrect parameter format: " + _arg;
		}
	}
	public static void addArgsToMap(String[] args, Map<String, Object> opts) {
		for (String a : args) {
			String[] parts = a.split("=", 2);
			if (parts.length > 1) {
				opts.put(parts[0], parts[1]);
			} else {
				throw new FormatException(a);
			}
		}
	}
	public static SortedMap<String, Object> parseArgs(String[] args) {
		SortedMap<String, Object> opts = new TreeMap<String, Object>();
		addArgsToMap(args, opts);
		return opts;
	}
}
