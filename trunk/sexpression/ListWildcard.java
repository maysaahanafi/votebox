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
 * An instance of this class represents a list wildcard that constrains slightly
 * more the contents of the list. Each element in the list much match a
 * particular pattern.
 * 
 * @author kyle
 * 
 */
public class ListWildcard extends AWildcard {

    private final ASExpression _pattern;

    public ListWildcard(ASExpression pattern) {
        _pattern = pattern;
    }

    /**
     * @see sexpression.ASExpression#match(sexpression.ASExpression)
     */
    @Override
    public ASExpression match(ASExpression target) {
        if (!(target instanceof ListExpression))
            return NoMatch.SINGLETON;

        ListExpression lst = (ListExpression) target;
        for (ASExpression ase : lst)
            if (_pattern.match( ase ) == NoMatch.SINGLETON)
                return NoMatch.SINGLETON;

        return new ListExpression( target );
    }

    /**
     * @see sexpression.ASExpression#toStringHelp()
     */
    @Override
    public StringBuffer toStringHelp() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "#list:" );
        buffer.append( _pattern.toStringHelp() );
        buffer.append( "" );
        return buffer;
    }

    /**
     * @see sexpression.ASExpression#size()
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * @see sexpression.ASExpression#toVerbatimHelp()
     */
    @Override
    public ByteArrayBuffer toVerbatimHelp() {
        ByteArrayBuffer ba = new ByteArrayBuffer();
        ba.append( (byte) '#' );
        ba.append( (byte) 'l' );
        ba.append( _pattern.toVerbatimHelp() );
        return ba;
    }

    /**
     * @see sexpression.ASpecialExpression#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ListWildcard))
            return false;

        return ((ListWildcard) o)._pattern.equals( _pattern );
    }
}
