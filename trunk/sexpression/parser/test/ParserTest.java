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
package sexpression.parser.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sexpression.*;

/**
 * Test pretty-parsing of s-expressions. The top level method call to
 * pretty-parse an s-expression string is ASExpression.make();
 * 
 * @author kyle
 * 
 */
public class ParserTest {

    private ASExpression str(String s) {
        return StringExpression.make( s );
    }

    private ASExpression lst(ASExpression... s) {
        return new ListExpression( s );
    }

    private ASExpression parse(String s) {
        return ASExpression.make( s );
    }

    @Test
    public void comments() {
        assertEquals(
            str( "test" ),
            parse( ";This is comment1  \n    ;This is comment2\n     test;this is comment3" ) );
        assertEquals(
            str( "test" ),
            parse( ";This is comment1\n;This is comment2\ntest;this is comment3" ) );
        assertEquals( lst( str( "test1" ), str( "test2" ) ),
            parse( "     \n ;comment (blah blah blah)  \n (test1 test2)" ) );
    }

    @Test
    public void strings() {
        assertEquals( str( "test" ), parse( "test" ) );
        assertEquals( str( "test" ), parse( "            test" ) );
        assertEquals( str( "test" ), parse( "     \n       test        \n\n" ) );
        assertEquals( str( "test2" ), parse( " \n \n \n test2" ) );
    }

    @Test
    public void lists() {
        assertEquals( lst(), parse( "()" ) );
        assertEquals( lst( str( "one" ) ), parse( "(one)" ) );
        assertEquals( lst( str( "one" ), str( "two" ) ),
            parse( "(;weird\none \n\ntwo)" ) );
        assertEquals( lst( lst( str( "one" ) ), str( "two" ) ),
            parse( "(  (one  ) two)" ) );
    }

    @Test
    public void specials() {
        assertEquals( Nothing.SINGLETON, parse( "#nothing" ) );
        assertEquals( Nothing.SINGLETON, parse( "      \n\n   #nothing" ) );
        assertEquals( NoMatch.SINGLETON, parse( "#nomatch" ) );
    }

    @Test
    public void wildcards() {
        assertEquals( Wildcard.SINGLETON, parse( "#any" ) );
        assertEquals( StringWildcard.SINGLETON, parse( "#string" ) );
        assertEquals( WildcardWildcard.SINGLETON, parse( "#wildcard" ) );
        assertEquals( new ListWildcard( Wildcard.SINGLETON ),
            parse( "#list : #any" ) );
        assertEquals(
            new ListWildcard( new ListExpression( Wildcard.SINGLETON ) ),
            parse( "#list : (#any)" ) );
        assertEquals( new ListWildcard( new ListExpression( "one", "two" ) ),
            parse( "#list : (one two)" ) );
    }

    @Test
    public void names() {
        assertEquals( new NamedPattern( "kyle", str( "test" ) ),
            parse( "%kyle : test" ) );
        assertEquals( new NamedPattern( "kyle's_pattern",
                parse( "(asdf asdf asdf)" ) ),
            parse( "%kyle's_pattern  \n : (asdf asdf asdf)" ) );
    }
}
