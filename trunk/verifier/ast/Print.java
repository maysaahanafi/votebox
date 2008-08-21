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
import verifier.*;
import verifier.value.*;

public class Print extends AST {

	public static final ASTFactory FACTORY = new ListArgFactory(
			new IConstructor() {

				public AST make(ASExpression from, AST... args) {
					return new Print(from, args);
				}
			}) {

		@Override
		public String getName() {
			return "print";
		}
	};

	private final AST[] _args;

	private Print(ASExpression from, AST[] args) {
		super(from);
		_args = args;
	}

	public Value eval(ActivationRecord environment) {
		StringBuffer buf = new StringBuffer();

		boolean first = true;
		for (AST a : _args) {
			if (first)
				first = false;
			else
				buf.append(" ");

			Value evaluated = a.eval(environment);
			Value result = evaluated.execute(new AValueVisitor() {
				public Value forExpression(Expression ase) {
					return ase;
				}

				public Value forFalse(False f) {
					return f;
				}

				public Value forInt(IntValue i) {
					return i;
				}

				public Value forSet(SetValue s) {
					return s;
				}

				public Value forTrue(True t) {
					return t;
				}

				public Value forDAG(DAGValue d) {
					return d;
				}

				public Value forReduction(Reduction ast) {
					return ast;
				}
			});
			buf.append(result.toString());
		}
		System.out.println(buf.toString());
		return True.SINGLETON;
	}
}
