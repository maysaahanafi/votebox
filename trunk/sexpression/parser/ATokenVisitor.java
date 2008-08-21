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

import sexpression.lexer.*;

/**
 * This visitor defines default behavior -- throw an exception in every case.
 * Selectively override these methods to denote acceptable cases in your
 * visitors.
 * 
 * @author kyle
 * 
 * @param <E>
 *            Set the return type of the visitor here.
 */
public class ATokenVisitor<E> implements ITokenVisitor<E> {

    /**
     * @see sexpression.lexer.ITokenVisitor#forClose(sexpression.lexer.Close)
     */
    public E forClose(Close c) {
        throw new UnexpectedTokenException( c.toString() );
    }

    /**
     * @see sexpression.lexer.ITokenVisitor#forColon(sexpression.lexer.Colon)
     */
    public E forColon(Colon c) {
        throw new UnexpectedTokenException( c.toString() );
    }

    /**
     * @see sexpression.lexer.ITokenVisitor#forOpen(sexpression.lexer.Open)
     */
    public E forOpen(Open o) {
        throw new UnexpectedTokenException( o.toString() );
    }

    /**
     * @see sexpression.lexer.ITokenVisitor#forWord(sexpression.lexer.Word)
     */
    public E forWord(Word w) {
        throw new UnexpectedTokenException( w.toString() );
    }

    /**
     * @see sexpression.lexer.ITokenVisitor#forEOF(sexpression.lexer.EOF)
     */
    public E forEOF(EOF e) {
        throw new UnexpectedTokenException( e.toString() );
    }

    /**
     * @see sexpression.lexer.ITokenVisitor#forHash(sexpression.lexer.Hash)
     */
    public E forHash(Hash hash) {
        throw new UnexpectedTokenException( hash.toString() );
    }

    /**
     * @see sexpression.lexer.ITokenVisitor#forMod(sexpression.lexer.Mod)
     */
    public E forMod(Mod mod) {
        throw new UnexpectedTokenException( mod.toString() );
    }

}
