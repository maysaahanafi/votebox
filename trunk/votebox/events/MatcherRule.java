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
package votebox.events;

import sexpression.ASExpression;

/**
 * A MatcherRule checks to see if the given sexpression is a match, and then
 * builds a corresponding event and returns it.
 * @author cshaw
 */
public interface MatcherRule {

    /**
     * Checks to see if the sexpression is a match, and returns an corresponding
     * event if so
     * @param serial the serial to use in the event constructor
     * @param sexp the sexpression to match
     * @return an event if there was a match, null otherwise
     */
    public IAnnounceEvent match(int serial, ASExpression sexp);

}
