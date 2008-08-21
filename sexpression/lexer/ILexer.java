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
 * This defines the public interface of a lexer.
 * 
 * @author kyle
 * 
 */
public interface ILexer {

    /**
     * Peek at the next token that the next call to read will return. Calling
     * this method does not consume a token from the stream.
     * 
     * @return This method returns the token that the next call to read will
     *         return.
     */
    public Token peek();

    /**
     * Consume and return the next token from the stream.
     * 
     * @return This method returns the next token that is available on the
     *         stream. If no more tokens are available, this method returns
     *         EOF.SINGLETON.
     */
    public Token read();

}
