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
package verifier.task;

import sexpression.*;
import verifier.*;
import verifier.ast.*;
import verifier.value.*;

/**
 * A local task performs the computation on the local machine, never requiring
 * serialization.
 * 
 * @author kyle
 * 
 */
public class LocalTask extends Task {

	private final Future _future;

	/**
	 * @param future
	 *            Realize this future with the result of the task.
	 * @param ast
	 *            Evaluate this ast.
	 * @param environment
	 *            Evaluate the ast in this environment.
	 */
	public LocalTask(Future future, AST ast, ActivationRecord environment) {
		super(null, ast, environment);
		_future = future;
	}

	/**
	 * @see verifier.task.Task#run()
	 */
	@Override
	public void run() {
		_future.realize(_ast.eval(_environment));
	}

	/**
	 * @see verifier.task.Task#toASE()
	 */
	@Override
	public ASExpression toASE() {
		throw new RuntimeException("Cannot serialize a local task");
	}

}
