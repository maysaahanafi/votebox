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
 * Each type of AST node must define a corresponding factory. The factory is
 * used by the parser to construct instances of the given AST type. AST
 * factories are the means by which primitives are pluggable -- plugins provide
 * the parser with factories in order to add support for new primitive types.
 * 
 * @author kyle
 * 
 */
public abstract class ASTFactory {

	/**
	 * Make an AST instance.
	 * 
	 * @param from
	 *            This is the s-expression that was encountered (matchresult is
	 *            the result of getPattern().match(this)
	 * @param matchresult
	 *            This is the result of the match operation of this factory's
	 *            pattern on the expression being parsed.
	 * @param parser
	 *            This is the parser doing the parsing.
	 * @return This method returns the newly constructed .
	 */
	public abstract AST make(ASExpression from, ListExpression matchresult,
			ASTParser parser);

	/**
	 * @return Get the pattern that defines which AST types this factory knows
	 *         how to make.
	 */
	public abstract String getPattern();

	/**
	 * @return This method returns the name of the target binary operation.
	 */
	public abstract String getName();

	private ASExpression _pattern;

	/**
	 * Get the ASE representation of the pattern string (the result of
	 * this.getPattern()). This value is lazily computed and cached.
	 * 
	 * @return This method returns the ASE representation of the pattern string.
	 */
	public ASExpression getPatternASE() {
		if (_pattern == null)
			_pattern = ASExpression.make(getPattern());
		return _pattern;
	}
}
