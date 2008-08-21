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

import java.util.ArrayList;

import sexpression.*;
import verifier.*;
import verifier.value.*;

/**
 * (assert name expected actual)
 * 
 * @author kyle
 */
public class Assert extends AST {

	/**
	 * This maps assertion names to variable bindings which caused the failure.
	 */
	public static final ArrayList<AssertionFailure> FAILED_ASSERTIONS = new ArrayList<AssertionFailure>();

	public static final ASTFactory FACTORY = new ASTFactory() {

		@Override
		public String getName() {
			return "assert";
		}

		@Override
		public String getPattern() {
			return "(assert #string #any #any)";
		}

		@Override
		public AST make(ASExpression from, ListExpression matchresult,
				ASTParser parser) {
			return new Assert(from, matchresult.get(0).toString(), parser
					.parse(matchresult.get(1)), parser
					.parse(matchresult.get(2)));
		}
	};

	private final String _name;

	private final AST _expected;

	private final AST _actual;

	private Assert(ASExpression from, String name, AST expected, AST body) {
		super(from);
		_name = name;
		_expected = expected;
		_actual = body;
	}

	@Override
	public Value eval(ActivationRecord environment) {
		final Value expected = _expected.eval(environment);
		final Value actual = _actual.eval(environment);
		if (expected.equals(actual))
			return actual;

		FAILED_ASSERTIONS.add(new AssertionFailure(_name, environment,
				expected, actual));
		return actual;
	}

}
