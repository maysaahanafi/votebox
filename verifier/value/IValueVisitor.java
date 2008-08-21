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
package verifier.value;

/**
 * Concrete implementations of this class are visitors to value types. To
 * execute a visitor on a value, pass the visitor instance to the value's
 * "execute" hook method.
 * 
 * @author derrley
 * 
 */
public interface IValueVisitor {

    /**
     * Define the visitor's behavior in the case where the target is False.
     * 
     * @see verifier.value.False
     * @param f
     *            This is the target value.
     * @return This method returns the result of the algorithm.
     */
    public Value forFalse(False f);

    /**
     * Define the visitor's behavior in the case where the target is True.
     * 
     * @see verifier.value.True
     * @param t
     *            This is the target value.
     * @return This method returns the result of the algorithm.
     */
    public Value forTrue(True t);

    /**
     * Define the visitor's behavior in the case where the target is an integer.
     * 
     * @see verifier.value.IntValue
     * @param i
     *            This is the target value.
     * @return This method returns the result of the algorithm.
     */
    public Value forInt(IntValue i);

    /**
     * Define the visitor's behavior in the case where the target is False.
     * 
     * @see verifier.value.False
     * @param s
     *            This is the target value.
     * @return This method returns the result of the algorithm.
     */
    public Value forSet(SetValue s);

    /**
     * Define the visitor's behavior in the case where the target is an
     * expression.
     * 
     * @see verifier.value.Expression
     * @param ase
     *            This is the target value.
     * @return This method returns the result of the algorithm.
     */
    public Value forExpression(Expression ase);

    /**
     * Define the visitor's behavior in the case where the target is a DAG.
     * 
     * @see verifier.value.DAGValue
     * @param dag
     *            This is the target value.
     * @return This method returns the result of the algorithm.
     */
    public Value forDAG(DAGValue dag);

    /**
     * Define the visitor's behavior in the case where the target is a
     * reduction.
     * 
     * @see verifier.value.Reduction
     * @param reduction
     *            This is the target value.
     * @return This method returns the result of the algorithm.
     */
    public Value forReduction(Reduction reduction);
}
