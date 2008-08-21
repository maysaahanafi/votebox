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
package tap;

import java.util.regex.*;
import java.net.*;

public abstract class Access {
	@SuppressWarnings("serial")
	public static class InvalidHostPatternException extends Exception { }
	public static interface SocketRule {
		boolean permitted(Socket connection);
	}
	public static class Allow implements SocketRule {
		protected Pattern addressPattern = null;
		protected Pattern namePattern = null;
		public Allow(String patternStr) 
				throws InvalidHostPatternException
		{
			if (patternStr.matches("^[0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+$")) {
				String[] bytes = patternStr.split("\\.");
				for (int i=0; i<4; i++) {
					if (bytes[i].equals("0") || bytes[i].equals("*")) {
						bytes[i] = "\\d+";
					}
				}
				addressPattern = Pattern.compile(
					"^" +
					bytes[0] + "\\." +
					bytes[1] + "\\." +
					bytes[2] + "\\." +
					bytes[3] +
					"$",
					Pattern.CASE_INSENSITIVE
				);
			} else if (patternStr.matches("^[-_a-zA-Z0-9.*]+$")) {
				namePattern = Pattern.compile(
					"^" +
					patternStr
						.replaceAll("\\.", "\\\\.")
						.replaceAll("\\*", ".*") +
					"$",
					Pattern.CASE_INSENSITIVE
				);
			} else {
				throw new InvalidHostPatternException();
			}
		}
		public boolean permitted(Socket connection) {
			InetAddress addr = connection.getInetAddress();
			if (addressPattern != null) {
				return addressPattern.matcher(
					addr.getHostAddress()).matches();
			} else {
				return namePattern.matcher(
					addr.getCanonicalHostName()).matches();
			}
		}
		public String toString() {
			return "Allow(" + (
				(addressPattern != null) 
					? ("ip=" + addressPattern.pattern())
					: ("host=" + namePattern.pattern())
			) + ")";
		}
	}
	public static class Deny extends Allow {
		public Deny(String patternStr) 
				throws InvalidHostPatternException
		{
			super(patternStr);
		}
		public boolean permitted(Socket connection) {
			return ! super.permitted(connection);
		}
		public String toString() {
			return "Deny(" + (
				(addressPattern != null) ? ("ip=" + addressPattern)
										 : ("host=" + namePattern)
			) + ")";
		}
	}
}

