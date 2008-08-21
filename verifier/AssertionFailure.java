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

import verifier.value.Value;

public class AssertionFailure {

    public final String _name;
    public final ActivationRecord _snapshot;
    public final Value _expected;
    public final Value _actual;

    public AssertionFailure(String name, ActivationRecord snapshot,
            Value expected, Value actual) {
        _name = name;
        _snapshot = snapshot;
        _expected = expected;
        _actual = actual;
    }

    @Override
    public String toString() {
        return _name + "\n" + _snapshot + "\n" + "EXPECTED: " + _expected
                + "\n" + "ACTUAL: " + _actual + "\n\n";
    }
}
