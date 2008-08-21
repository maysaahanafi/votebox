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
package verifier;

import sexpression.*;

/// Sub-interface of IVerifierPlugin that allows incremental addition of log
/// entries and log closure.
public interface IIncrementalPlugin extends IVerifierPlugin {
	/// Add another record to the log.
	public abstract void addLogData(ASExpression entry)
		throws InvalidLogEntryException;
	/// Close (or "seal") the log data, preventing verification from returning
	/// any reductions. 
	public void closeLog();
}
