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

import sexpression.ASExpression;
import sexpression.ListExpression;

/**
 * Instances of this class are factories for primitives that have one argument
 * which is a list ('and' and 'or').
 * 
 * @author kyle
 * 
 */
public abstract class ListArgFactory extends ASTFactory {

	private IConstructor _constructor;

	protected ListArgFactory(IConstructor constructor) {
		_constructor = constructor;
	}

	/**
	 * @see verifier.ast.ASTFactory#make(sexpression.ListExpression,
	 *      verifier.ast.ASTParser)
	 */
	@Override
	public AST make(ASExpression from, ListExpression matchresult,
			ASTParser parser) {
		ArrayList<AST> args = new ArrayList<AST>();
		for (ASExpression exp : (ListExpression) matchresult.get(0))
			args.add(parser.parse(exp));
		return _constructor.make(from, args.toArray(new AST[0]));
	}

	/**
	 * @see verifier.ast.ASTFactory#getPattern()
	 */
	@Override
	public String getPattern() {
		return "(" + getName() + " #list:#any)";
	}
}
