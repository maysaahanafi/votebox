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

import verifier.*;

import sexpression.*;

public abstract class PQuantifierFactory extends ASTFactory {
	private IQuantifierConstructor _constructor;

	protected PQuantifierFactory(IQuantifierConstructor constructor) {
		_constructor = constructor;
	}

	/**
	 * @see verifier.ast.ASTFactory#make(sexpression.ListExpression,
	 *      verifier.ast.ASTParser)
	 */
	@Override
	public AST make(ASExpression from, ListExpression matchresult,
			ASTParser parser) {
		ArrayList<Binding<AST, ActivationRecord>> bindings = new ArrayList<Binding<AST, ActivationRecord>>();
		ListExpression list = (ListExpression) matchresult.get(4);
		for (ASExpression ase : list) {
			ListExpression binding = (ListExpression) ase;
			bindings.add(new Binding<AST, ActivationRecord>(parser
					.parse(binding.get(0)),
					new ActivationRecord(binding.get(1))));
		}

		return _constructor.make(matchresult.get(0).toString(), parser
				.parse(matchresult.get(1)), parser.parse(matchresult.get(2)),
				Integer.parseInt(matchresult.get(3).toString()), bindings);
	}

	/**
	 * @see verifier.ast.ASTFactory#getPattern()
	 */
	@Override
	public String getPattern() {
		return "(" + getName() + " #string #any #any #string #list:(#any #list:(#string #any)))";
	}
}
