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
 * Each state instance must define the following, state-dependent behavior.
 * 
 * @author kyle
 * 
 */
public interface ILexerState {

    /**
     * Notify that a character has been read from the stream.
     * 
     * @param l
     *            This is the delegating instance.
     * @param b
     *            This is the character that was read.
     */
    public void readCharacter(Lexer l, char b);

    /**
     * Notify that whitespace has been read from the stream.
     * 
     * @param l
     *            This is the delegating instance.
     */
    public void readWhitespace(Lexer l);

    /**
     * Notify that an end-of-line has been read from the strem.
     * 
     * @param l
     *            This is the delegating instance.
     */
    public void readEOL(Lexer l);

    /**
     * Notify that a start-comment character has been read from the stream.
     * 
     * @param l
     *            This is the delegating instance.
     */
    public void readComment(Lexer l);

    /**
     * Notify that a special character (one that has a special token
     * correlation) has been read from the stream.
     * 
     * @param l
     *            This is the delegating instance.
     * @param t
     *            This is the token that represents the character that was read.
     */
    public void readSpecial(Lexer l, Token t);

    /**
     * Notify that the end of the stream has been reached.
     * 
     * @param l
     *            This is the delegating instance.
     */
    public void readEOF(Lexer l);
}
