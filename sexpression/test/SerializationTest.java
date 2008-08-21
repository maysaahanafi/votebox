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
package sexpression.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import sexpression.*;

public class SerializationTest {

    private boolean eq(ASExpression l, String r) throws Exception {
        return Arrays.equals( l.toVerbatim(), r.getBytes() );
    }

    // OLD
    @Test
    public void verbatim() throws Exception {
        assertTrue( eq( StringExpression.makeString( "foo" ), "3:foo" ) );
        assertTrue( eq( StringExpression.makeString( "" ), "0:" ) );
        assertTrue( eq( StringExpression.makeString( "::" ), "2:::" ) );
        assertTrue( eq( ListExpression.EMPTY, "()" ) );
        assertTrue( eq( new ListExpression( ListExpression.EMPTY ), "(())" ) );
        assertTrue( eq(
            new ListExpression( StringExpression.makeString( "foo" ) ),
            "(3:foo)" ) );
        assertTrue( eq(
            new ListExpression( StringExpression.makeString( "" ) ), "(0:)" ) );
        assertTrue( eq(
            new ListExpression( StringExpression.makeString( "(" ) ), "(1:()" ) );
        assertTrue( eq(
            new ListExpression( StringExpression.makeString( ")" ) ), "(1:))" ) );

        assertTrue( eq( new ListExpression(
                StringExpression.makeString( "foo" ), StringExpression
                        .makeString( "bar" ) ), "(3:foo3:bar)" ) );

        assertTrue( eq( new ListExpression(
                StringExpression.makeString( "foo" ), ListExpression.EMPTY,
                StringExpression.makeString( "bar" ) ), "(3:foo()3:bar)" ) );

        assertTrue( eq( new ListExpression( new ListExpression(
                StringExpression.makeString( "Hello" ) ), new ListExpression(
                ListExpression.EMPTY, ListExpression.EMPTY ),
                new ListExpression( StringExpression.makeString( "World" ) ) ),
            "((5:Hello)(()())(5:World))" ) );
    }

    // ADDED 9/18/2007
    private void eq2(String pretty, String verbatim) throws Exception {
        ASExpression parsed = ASExpression.make( pretty );
        assertTrue( Arrays.equals( parsed.toVerbatim(), verbatim.getBytes() ) );
        assertEquals( ASExpression.makeVerbatim( verbatim.getBytes() ), parsed );
    }

    @Test
    public void wildcards() throws Exception {
        eq2( "#nomatch", "#f" );
        eq2( "#string", "#s" );
        eq2( "#wildcard", "#w" );
        eq2( "#any", "#a" );
        eq2( "#list:#any", "#l#a" );
        eq2( "#list:#list:#any", "#l#l#a" );
        eq2( "#list:#string", "#l#s" );
        eq2( "(#string #string)", "(#s#s)" );
        eq2( "(#list:#list:#string #list:#list:#string)", "(#l#l#s#l#l#s)" );
    }

    @Test
    public void names() throws Exception {
        eq2( "%blah:#nomatch", "%4:blah#f" );
        eq2( "%blah2:#string", "%5:blah2#s" );
        eq2( "%blah3:#wildcard", "%5:blah3#w" );
        eq2( "%foo:#any", "%3:foo#a" );
        eq2( "%foo2:#list:#any", "%4:foo2#l#a" );
        eq2( "%whoa:#list:#list:#any", "%4:whoa#l#l#a" );
        eq2( "%hi:#list:#string", "%2:hi#l#s" );
        eq2( "%list:(#string #string)", "%4:list(#s#s)" );
        eq2( "%huge:(#list:#list:#string #list:#list:#string)",
            "%4:huge(#l#l#s#l#l#s)" );
    }
}
