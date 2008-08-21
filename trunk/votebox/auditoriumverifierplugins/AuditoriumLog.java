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

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import auditorium.*;

import verifier.*;
import verifier.value.*;
import sexpression.stream.*;

/**
 * Read log data in from the argument "log" and construct all-set and all-dag
 * based on this.
 * 
 * @author kyle
 * 
 */
public class AuditoriumLog implements IVerifierPlugin {

	/**
	 * @see verifier.IVerifierPlugin#init(verifier.Verifier)
	 */
	public void init(Verifier verifier) throws PluginException {
		registerFactories(verifier);
		registerData(verifier);
	}

	/**
	 * @param verifier
	 *            This method registers the signature-verify primitive on this
	 *            verifier.
	 */
	private void registerFactories(Verifier verifier) {
		verifier.getPrimitiveFactories().put("signature-verify",
				SignatureVerify.FACTORY);
	}

	/**
	 * Load log data from a file and register all-set and all-dag to represent
	 * this data in the given verifier.
	 * 
	 * @param verifier
	 *            Load the log data from the location specified in the "log"
	 *            argument given to this verifier and register all-dat and
	 *            all-set to represent the log data in this verifier.
	 */
	private void registerData(Verifier verifier) {
		DagBuilder dag = new FastDAGBuilder(); // DagBuilder();
		ArrayList<Expression> set = new ArrayList<Expression>();

		try {
			ASEInputStreamReader in = new ASEInputStreamReader(
					new FileInputStream(new File(verifier.getArgs().get("log"))));

			while (true) {
				Message msg = new Message(in.read());
				dag.add(msg);
				set.add(new Expression(msg.toASE()));
			}
		} catch (EOFException e) {
		} catch (IOException e) {
			throw new PluginException("auditorium", e);
		} catch (IncorrectFormatException e) {
			throw new PluginException("auditorium", e);
		} catch (InvalidVerbatimStreamException e) {
			throw new PluginException("auditorium", e);
		}

		HashMap<String, Value> bindings = new HashMap<String, Value>();
		SetValue sv = new SetValue(set.toArray(new Expression[0]));
		DAGValue dv = dag.toDAG();
		sv.seal();
		dv.seal();
		bindings.put("all-set", sv);
		bindings.put("all-dag", dv);
		ActivationRecord.END.setBindings(bindings);
	}
}
