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

import java.util.HashMap;

/**
 * A named pattern represents any expression whose intended use is as a pattern
 * for a match operation. They are especially useful in ASExpression.namedMatch.
 * A call to namedMatch on a pattern that contains named patterns as
 * subexpressions will result in a mapping for each named pattern to the
 * subexpression which matches it. Note that named patterns can contain wildcard
 * expressions.
 * 
 * @author kyle
 * 
 */
public class NamedPattern extends ASExpression {

    private final String _name;
    private final ASExpression _pattern;

    /**
     * @param name
     *            This is the name of the pattern
     * @param pattern
     *            This is the actual pattern.
     */
    public NamedPattern(String name, ASExpression pattern) {
        _name = name;
        _pattern = pattern;
    }

    /**
     * @see sexpression.ASExpression#match(sexpression.ASExpression)
     */
    @Override
    public ASExpression match(ASExpression target) {
        return _pattern.match( target );
    }

    /**
     * @see sexpression.ASExpression#namedMatch(sexpression.ASExpression)
     */
    @Override
    public HashMap<String, ASExpression> namedMatch(ASExpression target) {
        HashMap<String, ASExpression> map = new HashMap<String, ASExpression>();
        if (_pattern.match( target ) == NoMatch.SINGLETON)
            return NamedNoMatch.SINGLETON;

        map.put( _name, target );
        return map;
    }

    /**
     * @see sexpression.ASExpression#size()
     */
    @Override
    public int size() {
        return _pattern.size();
    }

    /**
     * @see sexpression.ASExpression#toStringHelp()
     */
    @Override
    public StringBuffer toStringHelp() {
        StringBuffer sb = new StringBuffer();
        sb.append( '%' );
        sb.append( _name );
        sb.append( ':' );
        sb.append( _pattern.toStringHelp() );
        return sb;
    }

    /**
     * @see sexpression.ASExpression#toVerbatimHelp()
     */
    @Override
    public ByteArrayBuffer toVerbatimHelp() {
        ByteArrayBuffer ba = new ByteArrayBuffer();
        ba.append( (byte) '%' );
        ba.append( StringExpression.make( _name ).toVerbatimHelp() );
        ba.append( _pattern.toVerbatimHelp() );
        return ba;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof NamedPattern))
            return false;
        NamedPattern other = (NamedPattern) o;

        return _name.equals( other._name ) && _pattern.equals( other._pattern );
    }
}
