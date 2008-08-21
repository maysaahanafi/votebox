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
package votebox.auditoriumverifierplugins;

import java.util.ArrayList;
import java.util.HashMap;

import verifier.*;
import verifier.value.*;
import auditorium.*;
import sexpression.*;

/**
 * This verifier plugin maintains all-set and all-dag based on incremental log
 * data given via this API (rather than read from a file). It also registers the
 * signature-verify primitive.
 * 
 * Uses the fast BFS implementation [10/04/2007 00:07 dsandler]
 * 
 * @author kyle
 * 
 */
public class IncrementalAuditoriumLogFast implements IIncrementalPlugin {

	private Verifier _verifier;
	private ArrayList<Expression> _allset;
	private SetValue _allsetValue;
	private DagBuilder _alldag;
	private DAGValue _alldagValue;

	/**
	 * @see verifier.IVerifierPlugin#init(verifier.Verifier)
	 */
	public void init(Verifier verifier) {
		_allset = new ArrayList<Expression>();
		_alldag = new FastDAGBuilder();
		_verifier = verifier;

		registerHandlers();
		registerGlobals();
	}

	/**
	 * Add incremental log data.
	 * 
	 * @param entry
	 *            Message to append to the log.
	 */
	public void addLogData(Message entry) {
		_allset.add(new Expression(entry.toASE()));
		_alldag.add(entry);
		registerGlobals();
	}

	/**
	 * Add incremental log data.
	 * 
	 * @param entry
	 *            S-expression representing a message to append to the log.
	 */
	public void addLogData(ASExpression entry) throws InvalidLogEntryException {
		try {
			_allset.add(new Expression(entry));
			_alldag.add(new Message(entry));
			registerGlobals();
		} catch (IncorrectFormatException e) {
			throw new InvalidLogEntryException(e);
		}
	}

	/**
	 * Add incremental log data.
	 * 
	 * @param entry
	 *            Expression value representing a message to append to the log.
	 */
	public void addLogData(Expression entry) throws InvalidLogEntryException {
		try {
			_allset.add(entry);
			_alldag.add(new Message(entry.getASE()));
			registerGlobals();
		} catch (IncorrectFormatException e) {
			throw new InvalidLogEntryException(e);
		}
	}

	/**
	 * Seal the logs -- the verifier will no longer return a reduction now. If
	 * something isn't in the log the verifier will expect that it never will
	 * be.
	 */
	public void closeLog() {
		_allsetValue.seal();
		_alldagValue.seal();
	}

	private void registerGlobals() {
		HashMap<String, Value> bindings = new HashMap<String, Value>();

		_allsetValue = new SetValue(_allset.toArray(new Expression[0]));
		bindings.put("all-set", _allsetValue);
		_alldagValue = _alldag.toDAG();

		// XXX: see note in IncrementalAuditoriumLog.java at this point
		if (_verifier.getArgs().containsKey("dagcache")) {
			if (new Boolean(_verifier.getArgs().get("dagcache")).booleanValue())
				_alldagValue.enableCache();
			else
				_alldagValue.disableCache();
		}

		bindings.put("all-dag", _alldagValue);

		ActivationRecord.END.setBindings(bindings);
	}

	private void registerHandlers() {
		_verifier.getPrimitiveFactories().put("signature-verify",
				SignatureVerify.FACTORY);
	}
}
