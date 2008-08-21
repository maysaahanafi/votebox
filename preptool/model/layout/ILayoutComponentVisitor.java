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
package preptool.model.layout;

/**
 * A visitor interface over the ALayoutComponent composite.
 * @param <P> the parameter type
 * @param <R> the return type
 * @author cshaw
 */
public interface ILayoutComponentVisitor<P,R> {
	
	/**
	 * Background case
	 * @param bg the Background
	 * @param param parameters
	 * @return the result
	 */
	public R forBackground(Background bg, P... param);
	
	/**
	 * Button case
	 * @param b the Button
	 * @param param parameters
	 * @return the result
	 */
	public R forButton(Button b, P... param);
	
	/**
	 * Label case
	 * @param l the Label
	 * @param param parameters
	 * @return the result
	 */
	public R forLabel(Label l, P... param);
	
	/**
	 * ReviewButton case
	 * @param rb the ReviewButton
	 * @param param parameters
	 * @return the result
	 */
	public R forReviewButton(ReviewButton rb, P... param);
	
	/**
	 * ReviewLabel case
	 * @param rl the ReviewLabel
	 * @param param parameters
	 * @return the result
	 */
	public R forReviewLabel(ReviewLabel rl, P... param);
	
	/**
	 * ToggleButton case
	 * @param tb the ToggleButton
	 * @param param parameters
	 * @return the result
	 */
	public R forToggleButton(ToggleButton tb, P... param);
	
	/**
	 * ToggleButtonGroup case
	 * @param tbg the ToggleButtonGroup
	 * @param param parameters
	 * @return the result
	 */
	public R forToggleButtonGroup(ToggleButtonGroup tbg, P... param);

}
