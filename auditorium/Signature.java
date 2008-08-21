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
package auditorium;

import sexpression.*;

/**
 * This class represents the auditorium signature data structure. Its format is
 * (signature [signer-id] [sigdata] {payload}).
 * 
 * @author kyle
 * 
 */
public class Signature {

    public static final ASExpression PATTERN = new ListExpression(
            StringExpression.makeString( "signature" ),
            StringWildcard.SINGLETON, StringWildcard.SINGLETON,
            Wildcard.SINGLETON );

    private final String _id;
    private final StringExpression _sigdata;
    private final ASExpression _payload;

    /**
     * @param id
     *            This is the ID of the signer.
     * @param sigdata
     *            This is the actual digital signature bytes.
     * @param payload
     *            This is the thing that is signed.
     */
    public Signature(String id, StringExpression sigdata, ASExpression payload) {
        _id = id;
        _sigdata = sigdata;
        _payload = payload;
    }

    /**
     * Construct a signature structure based on its s-expression format.
     * 
     * @param expression
     *            This is the expression that will be converted.
     * @throws IncorrectFormatException
     *             This method throws if the given expression is not formatted
     *             as (signature [signer-id] [sigdata] {payload}).
     */
    public Signature(ASExpression expression) throws IncorrectFormatException {
        ASExpression matchresult = PATTERN.match( expression );
        if (matchresult == NoMatch.SINGLETON)
            throw new IncorrectFormatException( expression, new Exception(
                    "did not match the pattern for key" ) );
        ListExpression matchlist = (ListExpression) matchresult;

        _id = matchlist.get( 0 ).toString();
        _sigdata = (StringExpression) matchlist.get( 1 );
        _payload = matchlist.get( 2 );
    }

    /**
     * @return This method returns this signature in its s-expression form.
     */
    public ASExpression toASE() {
        return new ListExpression( StringExpression.makeString( "signature" ),
                StringExpression.makeString( _id ), _sigdata, _payload );
    }

    /**
     * @return This method returns the ID of the signer.
     */
    public String getId() {
        return _id;
    }

    /**
     * @return This method returns the signature data.
     */
    public StringExpression getSigData() {
        return _sigdata;
    }

    /**
     * @return This method returns the payload -- the expression that was
     *         signed.
     */
    public ASExpression getPayload() {
        return _payload;
    }
}
