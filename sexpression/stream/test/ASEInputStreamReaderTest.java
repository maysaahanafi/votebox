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
package sexpression.stream.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import sexpression.ASExpression;
import sexpression.stream.ASEInputStreamReader;
import sexpression.stream.InvalidVerbatimStreamException;

import junit.framework.TestCase;

/**
 * This class tests the parsing capability included in ASEInputStreamReader.
 * Note: This class depends on the toString method in ASExpression (which is
 * tested by sexpression.algo.test.ToDisplayStringAlgoTest.
 * 
 * @author Kyle
 * 
 */
public class ASEInputStreamReaderTest extends TestCase {

    /**
     * This method tests a simple s-expression string (no lists).
     * 
     * @throws InvalidVerbatimStreamException
     * @throws IOException
     * 
     */
    public void test_simple() throws IOException,
            InvalidVerbatimStreamException {
        String test = "3:abc";
        ASEInputStreamReader stream = new ASEInputStreamReader(
                new ByteArrayInputStream( test.getBytes( "us-ascii" ) ) );

        assertEquals( "abc", stream.read().toString() );
    }

    /**
     * this method tests a simple list of two simple s-expression strings.
     * 
     * @throws IOException
     * @throws InvalidVerbatimStreamException
     */
    public void test_simpeList() throws IOException,
            InvalidVerbatimStreamException {
        String test = "(3:abc2:ab)";
        ASEInputStreamReader stream = null;
        stream = new ASEInputStreamReader( new ByteArrayInputStream( test
                .getBytes( "us-ascii" ) ) );

        assertEquals( "(abc ab)", ((ASExpression) stream.read()).toString() );
    }

    /**
     * This method tests a slightly more complicated list of two lists inside a
     * list, one of which is empty.
     * 
     * @throws InvalidVerbatimStreamException
     * @throws IOException
     * 
     */
    public void test_recursiveLists() throws IOException,
            InvalidVerbatimStreamException {
        String test = "(()(3:abc2:ab))";
        ASEInputStreamReader stream = null;
        stream = new ASEInputStreamReader( new ByteArrayInputStream( test
                .getBytes( "us-ascii" ) ) );

        assertEquals( "(() (abc ab))", ((ASExpression) stream.read())
                .toString() );
    }

    /**
     * This method is a complex test which includes testing the support of
     * base64.
     * 
     * @throws IOException
     * @throws InvalidVerbatimStreamException
     */
    public void test_complexBase64() throws IOException,
            InvalidVerbatimStreamException {
        String test = "{KDEwOkRlYXIgS3lsZTooNTpzLWV4cCgxOmYxOnQxOncpMTohKCkpKQ==}";
        ASEInputStreamReader stream = null;
        stream = new ASEInputStreamReader( new ByteArrayInputStream( test
                .getBytes( "us-ascii" ) ) );

        assertEquals( "(Dear Kyle: (s-exp (f t w) ! ()))",
            ((ASExpression) stream.read()).toString() );
    }
}
