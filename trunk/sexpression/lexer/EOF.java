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
package sexpression.lexer;

/**
 * This class represents the special token "EOF." Receiving an EOF token from
 * the stream means that the given character stream has reached its end.
 * 
 * @author kyle
 * 
 */
public class EOF extends Token {

    public static final EOF SINGLETON = new EOF();

    private EOF() {}

    /**
     * @see sexpression.lexer.Token#execute(sexpression.lexer.ITokenVisitor)
     */
    @Override
    public Object execute(ITokenVisitor v) {
        return v.forEOF( this );
    }

}
