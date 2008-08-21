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

import sexpression.*;
import verifier.*;
import verifier.value.*;

/**
 * This AST represents any identifier or number.
 * 
 * @author kyle
 * 
 */
public class Constant extends AST {

	public static final ASTFactory FACTORY = new ASTFactory() {

		@Override
		public String getName() {
			throw new RuntimeException(
					"the constant factory should be in the primitive table");
		}

		@Override
		public String getPattern() {
			return "#string";
		}

		@Override
		public AST make(ASExpression from, ListExpression matchresult,
				ASTParser parser) {
			return new Constant(from, matchresult.get(0).toString());
		}
	};

	private final String _value;

	private Constant(ASExpression from, String string) {
		super(from);
		_value = string;
	}

	/**
	 * @see verifier.ast.AST#eval(verifier.ActivationRecord)
	 */
	@Override
	public Value eval(ActivationRecord environment) {
		try {
			return new IntValue(Integer.parseInt(_value));
		} catch (NumberFormatException e) {
			if (_value.equals("true"))
				return True.SINGLETON;
			else if (_value.equals("false"))
				return False.SINGLETON;
			else
				return environment.lookup(_value);
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append(_value);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return (o instanceof Constant) && ((Constant) o)._value.equals(_value);
	}
}
