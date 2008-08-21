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
 * This exception is thrown when the strategy defined by a propery is unknown.
 * 
 * @author Kyle
 * 
 */
public class UnknownStrategyException extends Exception {

	/**
	 * This is the strategy that is unknown.
	 */
	private String _strategy;

	/**
	 * default serial version uid
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the public constructor for UnknownStrategyException
	 * 
	 * @param strategy
	 *            This is the strategy that is unknown.
	 */
	public UnknownStrategyException(String strategy) {
		super(strategy + " is an unknown strategy.");
	}

	/**
	 * This is the getter for _strategy.
	 * 
	 * @return _strategy
	 */
	public String getStrategy() {
		return _strategy;
	}
}
