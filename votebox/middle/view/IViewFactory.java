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
package votebox.middle.view;

/**
 * Use an instance of this class to construct a view and images that work with
 * the constructed view.
 * 
 * @author kyle
 * 
 */
public interface IViewFactory {

    /**
     * Construct a new view.
     * 
     * @return This method returns a newly constructed view.
     */
    public IView makeView();

    /**
     * Construct an image object that can be drawn in views that are constructed
     * with this factory.
     * 
     * @param path
     *            Load the image's bytes from this path
     * @return This method returns an image object which can be used in views
     *         that are constructed with this factory.
     */
    public IViewImage makeImage(String path);
}
