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
package auditorium.test;

import java.math.BigInteger;

import org.junit.*;
import static org.junit.Assert.*;
import auditorium.*;
import sexpression.*;

public class KeyTest {

    // ** <init>(String, String, BigInteger, BigInteger) tests **
    @Test
    public void constructor1_1() {
        Key k = new Key( "id", "annotation", new BigInteger( "1" ),
                new BigInteger( "2" ) );
        assertEquals( "id", k.getId() );
        assertEquals( "annotation", k.getAnnotation() );
        assertEquals( 1, k.getMod().intValue() );
        assertEquals( 2, k.getKey().intValue() );
        assertEquals( new ListExpression( StringExpression.makeString( "key" ),
                StringExpression.makeString( "id" ), StringExpression
                        .makeString( "annotation" ), StringExpression
                        .makeString( new BigInteger( "1" ).toByteArray() ),
                StringExpression.makeString( new BigInteger( "2" )
                        .toByteArray() ) ), k.toASE() );
    }

    @Test
    public void constructor1_2() {
        Key k = new Key( "id2", "2annotation", new BigInteger( "58" ),
                new BigInteger( "59" ) );
        assertEquals( "id2", k.getId() );
        assertEquals( "2annotation", k.getAnnotation() );
        assertEquals( 58, k.getMod().intValue() );
        assertEquals( 59, k.getKey().intValue() );
        assertEquals( new ListExpression( StringExpression.makeString( "key" ),
                StringExpression.makeString( "id2" ), StringExpression
                        .makeString( "2annotation" ), StringExpression
                        .makeString( new BigInteger( "58" ).toByteArray() ),
                StringExpression.makeString( new BigInteger( "59" )
                        .toByteArray() ) ), k.toASE() );
    }

    // ** <init>(ASExpression) tests **
    // Junk
    @Test(expected = IncorrectFormatException.class)
    public void constructor2_1() throws Exception {
        new Key( NoMatch.SINGLETON );
    }

    @Test(expected = IncorrectFormatException.class)
    public void constructor2_2() throws Exception {
        new Key( Nothing.SINGLETON );
    }

    @Test(expected = IncorrectFormatException.class)
    public void constructor2_3() throws Exception {
        new Key( StringWildcard.SINGLETON );
    }

    @Test(expected = IncorrectFormatException.class)
    public void constructor2_4() throws Exception {
        new Key( new ListWildcard( Wildcard.SINGLETON ) );
    }

    // len < 5
    @Test(expected = IncorrectFormatException.class)
    public void constructor2_5() throws Exception {
        new Key( ListExpression.EMPTY );
    }

    @Test(expected = IncorrectFormatException.class)
    public void constructor2_6() throws Exception {
        new Key( new ListExpression( "key", "1", "2", "3" ) );
    }

    // len > 5
    @Test(expected = IncorrectFormatException.class)
    public void constructor2_7() throws Exception {
        new Key( new ListExpression( "key", "1", "2", "3", "4", "5" ) );
    }

    // [0] != 'key'
    @Test(expected = IncorrectFormatException.class)
    public void constructor2_8() throws Exception {
        new Key( new ListExpression( "notkey", "1", "2", "3", "4" ) );
    }

    // Good testing
    byte[][] numbers = {
            {
                24
            }, {
                34
            }, {
                127
            }, {
                -49
            }
    };

    @Test
    public void constructor2_9() throws Exception {
        Key k = new Key( new ListExpression( StringExpression
                .makeString( "key" ), StringExpression.makeString( "id" ),
                StringExpression.makeString( "annotation" ), StringExpression
                        .makeString( numbers[0] ), StringExpression
                        .makeString( numbers[1] ) ) );

        assertEquals( "id", k.getId() );
        assertEquals( "annotation", k.getAnnotation() );
        assertEquals( 24, k.getMod().intValue() );
        assertEquals( 34, k.getKey().intValue() );
    }

    @Test
    public void constructor2_10() throws Exception {
        Key k = new Key( new ListExpression( StringExpression
                .makeString( "key" ), StringExpression.makeString( "id2" ),
                StringExpression.makeString( "4nn0t4t10n" ), StringExpression
                        .makeString( numbers[2] ), StringExpression
                        .makeString( numbers[3] ) ) );

        assertEquals( "id2", k.getId() );
        assertEquals( "4nn0t4t10n", k.getAnnotation() );
        assertEquals( 127, k.getMod().intValue() );
        assertEquals( -49, k.getKey().intValue() );
    }
}
