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

import java.io.File;
import java.io.FileInputStream;

import auditorium.HostPointer;
import auditorium.MessagePointer;

import sexpression.*;
import sexpression.stream.*;

public class PointerExtractor {

    // 0: id
    // 1: ip
    // 2: port
    // 3: sequence
    // 4: cert
    // 5: signature
    // 6: list-of-pointers
    // 7: datum
    public static final ASExpression PATTERN = new ListExpression(
            StringExpression.makeString( "announce" ), HostPointer.PATTERN,
            new ListExpression( StringExpression.makeString( "sequence" ),
                    StringWildcard.SINGLETON, new ListExpression(
                            StringExpression.makeString( "signature" ),
                            StringWildcard.SINGLETON, StringWildcard.SINGLETON,
                            new ListExpression( StringExpression
                                    .makeString( "succeeds" ),
                                    new ListWildcard( MessagePointer.PATTERN ),
                                    Wildcard.SINGLETON ) ) ) );

    private final String _path;

    public PointerExtractor(String path) {
        _path = path;
    }

    public void extract() throws Exception {
        ASEInputStreamReader reader = new ASEInputStreamReader(
                new FileInputStream( new File( _path ) ) );

        ASExpression read;
        while ((read = reader.read()) != null) {
            try {
                ListExpression result = (ListExpression) PATTERN.match( read );
                System.out.println( "ID:" + result.get( 0 ) + " SEQUENCE:"
                        + result.get( 3 ) + " MESSAGE:" + result.get( 7 ) );
                System.err.println( "Pointers:" );
                for (ASExpression ase : (ListExpression) result.get( 6 )) {
                    ListExpression le = (ListExpression) ase;
                    System.err.println( "    " + le.get( 1 ) + " / "
                            + le.get( 2 ) );
                }
            }
            catch (ClassCastException e) {
                System.err.println( "Skipping malformed expression" );
            }
        }
    }

    public static void main(String[] args) {
        System.out.println( "Reading files" );
        for (String s : args)
            try {
                new PointerExtractor( s ).extract();
            }
            catch (Exception e) {
                System.err.println( e );
            }
    }
}
