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

/**
 * Instances of this class represent AST nodes that represent applications of
 * simple primitives. A simple primitive is one that is of the form ([name] arg1
 * arg2 ... argn) for some n number of arguments.
 * 
 * @author kyle
 * 
 */
public abstract class PrimFactory extends ASTFactory {

	private final IConstructor _constructor;

	private final int _args;

	protected PrimFactory(int args, IConstructor constructor) {
		_args = args;
		_constructor = constructor;
	}

	/**
	 * @see verifier.ast.ASTFactory#make(sexpression.ListExpression,
	 *      verifier.ast.ASTParser)
	 */
	@Override
	public AST make(ASExpression from, ListExpression matchresult,
			ASTParser parser) {
		AST[] args = new AST[_args];
		for (int lcv = 0; lcv < _args; lcv++)
			args[lcv] = parser.parse(matchresult.get(lcv));
		return _constructor.make(from, args);
	}

	/**
	 * @see verifier.ast.ASTFactory#getPattern()
	 */
	@Override
	public String getPattern() {
		StringBuffer buff = new StringBuffer();
		buff.append('(');
		buff.append(getName());
		for (int lcv = 0; lcv < _args; lcv++) {
			buff.append(" ");
			buff.append("#any");
		}
		buff.append(')');
		return buff.toString();
	}

}
