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
package votebox.middle.test;

import java.util.ArrayList;
import java.util.List;

import votebox.middle.IncorrectTypeException;
import votebox.middle.Properties;
import votebox.middle.UnknownFormatException;
import votebox.middle.UnknownTypeException;

import junit.framework.TestCase;

/**
 * This class tests the behaviors that are defined in Properties.
 * 
 * @author Kyle
 * 
 */
public class TestProperties extends TestCase {

    /**
     * This method tests the simple add, get, and contains functions. If this
     * method throws an exception, something went wrong.
     */
    public void test_addGetContains() throws UnknownTypeException,
            IncorrectTypeException, UnknownFormatException {
        Properties p = new Properties();

        p.add( "int", "3", "Integer" );
        p.add( "string", "three", "String" );
        p.add( "boolean", "true", "Boolean" );

        assertTrue( p.contains( "int" ) );
        assertTrue( p.contains( "string" ) );
        assertTrue( p.contains( "boolean" ) );

        assertEquals( new Integer( 3 ), p.getInteger( "int" ) );
        assertEquals( "three", p.getString( "string" ) );
        assertEquals( new Boolean( true ), p.getBoolean( "boolean" ) );
    }

    /**
     * This method tests to make sure that UnknownTypeException is getting
     * thrown when it needs to be. All the exceptions that are tested for are
     * caught in the method, if this method throws an exception, something went
     * wrong.
     */
    public void test_UnknownTypeException() throws UnknownFormatException {
        Properties p = new Properties();

        try {
            p.add( "key", "value", "randomtype" );
            assertFalse( true );
        }
        catch (UnknownTypeException e) {
            assertEquals( "randomtype", e.getType() );
        }

    }

    /**
     * This method tests to make sure that UnknownFormatException is getting
     * thrown when it needs to be. All the exceptions that are tested for are
     * caught in the method, if this method throws an exception, something went
     * wrong.
     */
    public void test_UnknownFormatException() throws UnknownTypeException {
        Properties p = new Properties();

        try {
            p.add( "key", "value", "Integer" );
            assertTrue( false );
        }
        catch (UnknownFormatException e) {
            assertEquals( "value", e.getValue() );
            assertEquals( "Integer", e.getType() );
        }

        try {
            p.add( "key", "value", "Boolean" );
            assertTrue( false );
        }
        catch (UnknownFormatException e) {
            assertEquals( "value", e.getValue() );
            assertEquals( "Boolean", e.getType() );
        }
    }

    /**
     * This method tests to make sure that UnknownTypeException is getting
     * thrown when it needs to be. All the exceptions that are tested for are
     * caught in the method, if this method throws an exception, something went
     * wrong.
     */
    public void test_IncorrectTypeException() throws UnknownTypeException,
            UnknownFormatException {
        Properties p = new Properties();

        p.add( "int", "3", "Integer" );
        p.add( "string", "three", "String" );
        p.add( "boolean", "true", "Boolean" );

        try {
            p.getString( "int" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "String", e.getExpected() );
            assertEquals( "Integer", e.getActual() );
        }

        try {
            p.getString( "boolean" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "String", e.getExpected() );
            assertEquals( "Boolean", e.getActual() );
        }

        try {
            p.getInteger( "string" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "Integer", e.getExpected() );
            assertEquals( "String", e.getActual() );
        }

        try {
            p.getInteger( "boolean" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "Integer", e.getExpected() );
            assertEquals( "Boolean", e.getActual() );
        }

        try {
            p.getBoolean( "string" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "Boolean", e.getExpected() );
            assertEquals( "String", e.getActual() );
        }

        try {
            p.getBoolean( "int" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "Boolean", e.getExpected() );
            assertEquals( "Integer", e.getActual() );
        }
    }

    public void test_lists() throws Exception {
        Properties p = new Properties();
        ArrayList<String> stringlist = new ArrayList<String>();
        ArrayList<String> intlist = new ArrayList<String>();

        stringlist.add( "Hello" );
        stringlist.add( "World" );

        intlist.add( "1" );
        intlist.add( "2" );

        p.add( "strings", stringlist, "String" );
        p.add( "ints", intlist, "Integer" );
        
        List<String> getbackstring = p.getStringList( "strings" );
        List<Integer> getbackint = p.getIntegerList( "ints" );
        
        assertEquals(stringlist, getbackstring);
        assertEquals(new Integer(1), getbackint.get(0));
        assertEquals(new Integer(2), getbackint.get(1));
        
        try {
            p.getStringList( "ints" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "List<String>", e.getExpected() );
            assertEquals( "List<Integer>", e.getActual() );
        }
        
        try {
            p.getIntegerList( "strings" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "List<Integer>", e.getExpected() );
            assertEquals( "List<String>", e.getActual() );
        }
        
        try {
            p.getString( "strings" );
            assertTrue( false );
        }
        catch (IncorrectTypeException e) {
            assertEquals( "String", e.getExpected() );
            assertEquals( "ArrayList", e.getActual() );
        }
    }
}
