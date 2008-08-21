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
package supervisor.model.tallier;

import sexpression.ASExpression;
import auditorium.Key;

import java.math.BigInteger;
import java.util.Map;

/**
 * Interface for all Tallier implementations.
 * 
 * @see supervisor.model.tallier.EncryptedTallier
 * @see supervisor.model.tallier.Tallier
 * @author Montrose
 *
 */
public interface ITallier {
	
	/**
	 * Gets the report of the total tally
	 * 
	 * @param key - Any necessary key for this operation.
	 * 
	 * @return the report as a map of Candidiate to votes received.
	 */
	public Map<String, BigInteger> getReport(Key key);
	
	/**
	 * Records the votes from the given ballot, as an S-Expression in byte array
	 * format
	 * 
	 * @param ballot - Verbatim version of the ballot to totall
	 * @param nonce - Nonce of this voting transaction
	 */
	public void recordVotes(byte[] ballot, ASExpression nonce);
	
	/**
	 * Called to indicate that the voting transactioned indicated by the nonce is complete, and should be counted
	 * in the final tally.
	 * 
	 * @param nonce - Nonce of this voting transaction
	 */
	public void confirmed(ASExpression nonce);
	
	/**
	 * Called to indicate that a voting transaction is being challenged.
	 * This should result in the destruction of the ballot in the tallier.
	 * 
	 * @param nonce - Nonce of this voting transaction
	 */
	public void challenged(ASExpression nonce);
}