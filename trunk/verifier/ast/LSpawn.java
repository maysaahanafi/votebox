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

import sexpression.ASExpression;
import sexpression.ListExpression;
import verifier.*;
import verifier.task.*;
import verifier.value.*;

public class LSpawn extends AST {

	public static final Pool POOL = new Pool(3);

	public static final ASTFactory FACTORY = new ASTFactory() {

		@Override
		public String getName() {
			return "lspawn";
		}

		@Override
		public String getPattern() {
			return "(lspawn #any)";
		}

		@Override
		public AST make(ASExpression from, ListExpression matchresult,
				ASTParser parser) {
			return new LSpawn(parser.parse(matchresult.get(0)));
		}
	};

	private final AST _body;

	private LSpawn(AST body) {
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

		if (POOL.running() == false)
			POOL.start();

		POOL.run(new LocalTask(f, _body, environment));

		return f;
	}

}
