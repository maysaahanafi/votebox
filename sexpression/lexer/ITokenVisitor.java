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
 * <b>Visitor Design Pattern</b><br>
 * <br>
 * Instances of this interface can visit a Token. Implementers must define a
 * case for every concrete instance of Token.
 * 
 * @see sexpression.lexer.Token
 * @author kyle
 * 
 * @param <E>
 *            Specify the return type of the visitor execution here.
 */
public interface ITokenVisitor<E> {

    /**
     * Define the close case.
     * 
     * @see sexpression.lexer.Close
     */
    E forClose(Close c);

    /**
     * Define the open case.
     * 
     * @see sexpression.lexer.Open
     */
    E forOpen(Open o);

    /**
     * Define the colon case.
     * 
     * @see sexpression.lexer.Colon
     */
    E forColon(Colon c);

    /**
     * Define the word case.
     * 
     * @see sexpression.lexer.Word
     */
    E forWord(Word w);

    /**
     * Define the EOF case
     * 
     * @see sexpression.lexer.EOF
     */
    E forEOF(EOF e);

    /**
     * Define the Hash case
     * 
     * @see sexpression.lexer.Hash
     */
    E forHash(Hash hash);

    /**
     * Define the mod case
     * 
     * @see sexpression.lexer.Mod
     */
    E forMod(Mod mod);
}
