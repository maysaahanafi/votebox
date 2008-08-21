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
package sexpression.parser;

import sexpression.ASExpression;

/**
 * An s-expression parser must conform to this interface. Its job is to parse
 * instance of ASExpression from streams of tokens.
 * 
 * @see sexpression.ASExpression
 * @see sexpression.lexer.ILexer
 * @author kyle
 * 
 */
public interface IParser {

    /**
     * Parse the next s-expression that is on the stream.
     * 
     * @return This method returns an instance of ASExpression that represents
     *         the next sexp on the given stream.
     */
    public ASExpression read();
}
