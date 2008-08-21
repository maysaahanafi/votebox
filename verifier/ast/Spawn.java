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
package verifier.ast;

import java.io.IOException;
import java.io.OutputStream;

import sexpression.ASExpression;
import sexpression.ListExpression;
import verifier.*;
import verifier.task.*;
import verifier.value.*;

public class Spawn extends AST {

	public static final ASTFactory FACTORY = new ASTFactory() {

		@Override
		public String getName() {
			return "spawn";
		}

		@Override
		public String getPattern() {
			return "(spawn #any)";
		}

		@Override
		public AST make(ASExpression from, ListExpression matchresult,
				ASTParser parser) {
			return new Spawn(parser.parse(matchresult.get(0)));
		}
	};

	private final AST _body;

	private Spawn(AST body) {
		super(null);
		_body = body;
	}

	@Override
	public ASExpression toASE() {
		throw new RuntimeException("Nested spawn not supported");
	}

	/**
	 * @see verifier.ast.AST#eval(verifier.ActivationRecord)
	 */
	@Override
	public Value eval(ActivationRecord environment) {
		Future f = new Future();
		Future.registerFuture(f);
		try {
			OutputStream os = Controller.SINGLETON.getOutbound();
			os.write(new Task(f, _body, environment).toASE().toVerbatim());
			os.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return f;
	}

}
