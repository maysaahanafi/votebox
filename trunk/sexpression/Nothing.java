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
package sexpression;

/**
 * Use this as a placeholder, or a signal that "nothing" should go here.
 */
public class Nothing extends ASExpression {

    public static final Nothing SINGLETON = new Nothing();

    private Nothing() {}

    /**
     * @see sexpression.ASExpression#match(sexpression.ASExpression)
     */
    @Override
    public ASExpression match(ASExpression target) {
        if (this == target)
            return new ListExpression( target );
        return NoMatch.SINGLETON;
    }

    /**
     * @see sexpression.ASExpression#toStringHelp()
     */
    @Override
    public StringBuffer toStringHelp() {
        return new StringBuffer( "#nothing" );
    }

    /**
     * @see sexpression.ASpecialExpression#toVerbatimHelp()
     */
    @Override
    public ByteArrayBuffer toVerbatimHelp() {
        return new ByteArrayBuffer();
    }

    /**
     * @see sexpression.ASExpression#size()
     */
    @Override
    public int size() {
        return 0;
    }
}
