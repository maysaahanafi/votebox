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
 * <b>State Design Pattern</b><br>
 * <br>
 * When the lexer is in this state, is in the middle of reading a word. This
 * state instance is not a singleton, because it must keep track of charcaters
 * read in the word that it is in the middle of reading.
 * 
 * @author kyle
 * 
 */
public class WordState implements ILexerState {

    private StringBuffer _buffer;

    public WordState() {
        _buffer = new StringBuffer();
    }

    /**
     * @see sexpression.lexer.ILexerState#readCharacter(sexpression.lexer.Lexer,
     *      char)
     */
    public void readCharacter(Lexer l, char c) {
        _buffer.append( c );
    }

    /**
     * @see sexpression.lexer.ILexerState#readSpecial(sexpression.lexer.Lexer,
     *      sexpression.lexer.Token)
     */
    public void readSpecial(Lexer l, Token t) {
        l.add( new Word( _buffer.toString() ) );
        l.add( t );
        l.setState( WhitespaceState.SINGLETON );
    }

    /**
     * @see sexpression.lexer.ILexerState#readWhitespace(sexpression.lexer.Lexer)
     */
    public void readWhitespace(Lexer l) {
        l.add( new Word( _buffer.toString() ) );
        l.setState( WhitespaceState.SINGLETON );
    }

    /**
     * @see sexpression.lexer.ILexerState#readEOF(sexpression.lexer.Lexer)
     */
    public void readEOF(Lexer l) {
        l.add( new Word( _buffer.toString() ) );
        l.add( EOF.SINGLETON );
        l.setState( WhitespaceState.SINGLETON );
    }

    /**
     * @see sexpression.lexer.ILexerState#readComment(sexpression.lexer.Lexer)
     */
    public void readComment(Lexer l) {
        l.add( new Word( _buffer.toString() ) );
        l.setState( CommentState.SINGLETON );
    }

    /**
     * @see sexpression.lexer.ILexerState#readEOL(sexpression.lexer.Lexer)
     */
    public void readEOL(Lexer l) {
        l.add( new Word( _buffer.toString() ) );
        l.setState( WhitespaceState.SINGLETON );
    }
}
