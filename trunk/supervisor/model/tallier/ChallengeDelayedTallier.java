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

import java.util.HashMap;
import java.util.Map;

import auditorium.Bugout;

import sexpression.ASExpression;

/**
 * Tallier implementation that caches ballots until a second message (affirming the voter is NOT challenging)
 * is received.  At which point the cached ballot is added to the tally.
 * 
 * @author Montrose
 *
 */
public class ChallengeDelayedTallier extends EncryptedTallier {
	//Mapping of nonce values to pending ballots
	private Map<ASExpression, byte[]> _nonceToBallot = new HashMap<ASExpression, byte[]>();
	
	public ChallengeDelayedTallier(){}
	
	@Override
	public void recordVotes(byte[] message, ASExpression nonce){
		_nonceToBallot.put(nonce,message);
	}//recordVotes
	
	public void confirmed(ASExpression nonce){
		byte[] vote = _nonceToBallot.remove(nonce);
		
		if(vote == null){
			throw new RuntimeException("Attempted to confirm an unknown vote, nonce = "+nonce);
		}//if
		
		//Delegate actually tallying to the EncryptedTallier class
		super.recordVotes(vote, null);
	}//confirmed
	
	public void challenged(ASExpression nonce){
		
		//This could be innoculous (supervisor going on and offline)
		//Or it could be a sign of malicious tampering, so we'll report it.
		if(_nonceToBallot.remove(nonce) == null){
			Bugout.err("Detected a challenge on an UNKNOWN vote, nonce  = "+nonce);
		}//if
	}//challenge
}