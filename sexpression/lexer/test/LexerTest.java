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
package sexpression.lexer.test;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Test;

import sexpression.lexer.*;

/**
 * This classes contains JUnit tests for the Lexer class.
 * 
 * @author kyle
 * 
 */
public class LexerTest {

    private Lexer _lex;

    /**
     * Set up the lexer to read from the stream of characters given.
     * 
     * @param inbuffer
     *            Interpret this string as a stream of characters and read from
     *            it.
     */
    private void setup(String inbuffer) throws Exception {
        _lex = new Lexer( new CharArrayReader( inbuffer.toCharArray() ) );
    }

    @Test
    public void empty() throws Exception {
        setup( "" );
        assertEquals( EOF.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
    }

    @Test
    public void whitespace() throws Exception {
        setup( "      \n\n  \n" );
        assertEquals( EOF.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
    }

    @Test
    public void close() throws Exception {
        setup( ")" );
        assertEquals( Close.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
    }

    @Test
    public void colon() throws Exception {
        setup( ":" );
        assertEquals( Colon.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
    }

    @Test
    public void words() throws Exception {
        setup( "one two" );
        assertEquals( new Word( "one" ), _lex.read() );
        assertEquals( new Word( "two" ), _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
    }

    @Test
    public void mixed() throws Exception {
        setup( "        one (((  ( : )::)two!? :" );
        assertEquals( new Word( "one" ), _lex.read() );
        assertEquals( Open.SINGLETON, _lex.read() );
        assertEquals( Open.SINGLETON, _lex.peek() );
        assertEquals( Open.SINGLETON, _lex.read() );
        assertEquals( Open.SINGLETON, _lex.read() );
        assertEquals( Open.SINGLETON, _lex.peek() );
        assertEquals( Open.SINGLETON, _lex.read() );
        assertEquals( Colon.SINGLETON, _lex.read() );
        assertEquals( Close.SINGLETON, _lex.read() );
        assertEquals( Colon.SINGLETON, _lex.read() );
        assertEquals( Colon.SINGLETON, _lex.peek() );
        assertEquals( Colon.SINGLETON, _lex.read() );
        assertEquals( Close.SINGLETON, _lex.read() );
        assertEquals( new Word( "two!?" ), _lex.read() );
        assertEquals( Colon.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.read() );
        assertEquals( EOF.SINGLETON, _lex.peek() );
        assertEquals( EOF.SINGLETON, _lex.read() );
    }
}
