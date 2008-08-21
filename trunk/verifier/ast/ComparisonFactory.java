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

public abstract class ComparisonFactory extends ASTFactory {
	private IConstructor _constructor;

	protected ComparisonFactory(IConstructor constructor) {
		_constructor = constructor;
	}

	/**
	 * @see verifier.ast.ASTFactory#make(sexpression.ListExpression,
	 *      verifier.ast.ASTParser)
	 */
	@Override
	public AST make(ASExpression from, ListExpression matchresult,
			ASTParser parser) {
		return _constructor.make(from, parser.parse(matchresult.get(0)), parser
				.parse(matchresult.get(1)));
	}

	/**
	 * @see verifier.ast.ASTFactory#getPattern()
	 */
	@Override
	public String getPattern() {
		return "(" + getName() + " #any #any)";
	}
}
