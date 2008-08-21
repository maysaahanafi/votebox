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
 * Values emitted by the lexer are all of this type. Construct instances of
 * ITokenVisitor to work with them.
 * 
 * @see sexpression.lexer.ITokenVisitor
 * @author kyle
 * 
 */
public abstract class Token {

    /**
     * This is the visitor pattern hook method. Call this method to execute a
     * visitor on this token.
     * 
     * @param v
     *            Exevute this visitor.
     * @return This method returns the result of the visitor execution.
     */
    public abstract Object execute(ITokenVisitor v);
}
