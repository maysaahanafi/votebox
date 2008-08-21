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
 * Special value returned by match() when the target expression does not match
 * the pattern.
 */
public class NoMatch extends ASExpression {

    public static final NoMatch SINGLETON = new NoMatch();

    private NoMatch() {}

    /**
     * @see sexpression.ASExpression#match(sexpression.ASExpression)
     */
    @Override
    public ASExpression match(ASExpression target) {
        if (target == SINGLETON)
            return new ListExpression( SINGLETON );
        return NoMatch.SINGLETON;
    }

    /**
     * @see sexpression.ASExpression#size()
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * @see sexpression.ASExpression#toStringHelp()
     */
    @Override
    public StringBuffer toStringHelp() {
        return new StringBuffer( "#nomatch" );
    }

    /**
     * @see sexpression.ASExpression#toVerbatimHelp()
     */
    @Override
    public ByteArrayBuffer toVerbatimHelp() {
        ByteArrayBuffer ba = new ByteArrayBuffer();
        ba.append( (byte) '#' );
        ba.append( (byte) 'f' );
        return ba;
    }
}
