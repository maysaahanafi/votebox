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

public class CommentState implements ILexerState {

    public static final CommentState SINGLETON = new CommentState();

    private CommentState() {}

    /**
     * @see sexpression.lexer.ILexerState#readCharacter(sexpression.lexer.Lexer,
     *      char)
     */
    public void readCharacter(Lexer l, char c) {}

    /**
     * @see sexpression.lexer.ILexerState#readSpecial(sexpression.lexer.Lexer,
     *      sexpression.lexer.Token)
     */
    public void readSpecial(Lexer l, Token t) {}

    /**
     * @see sexpression.lexer.ILexerState#readWhitespace(sexpression.lexer.Lexer)
     */
    public void readWhitespace(Lexer l) {}

    /**
     * @see sexpression.lexer.ILexerState#readEOF(sexpression.lexer.Lexer)
     */
    public void readEOF(Lexer l) {
        l.add( EOF.SINGLETON );
        l.setState( WhitespaceState.SINGLETON );
    }

    /**
     * @see sexpression.lexer.ILexerState#readComment(sexpression.lexer.Lexer)
     */
    public void readComment(Lexer l) {}

    /**
     * @see sexpression.lexer.ILexerState#readEOL(sexpression.lexer.Lexer)
     */
    public void readEOL(Lexer l) {
        l.setState( WhitespaceState.SINGLETON );
    }
}
