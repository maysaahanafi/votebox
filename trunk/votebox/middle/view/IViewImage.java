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
 * Abstract interface for an image. A view should cast this to what it expects.
 * 
 * @author kyle
 * 
 */
public interface IViewImage {

    /**
     * Get the image.
     * 
     * @return This method returns the image in the format that the current view
     *         should expect. The view must cast the object that is returned to
     *         what it expects. *Check the cast*
     */
    public Object getImage();

    /**
     * Get the width of this image.
     * 
     * @return This method returns the width of this image in pixels.
     */
    public int getWidth();

    /**
     * Get the height of this image.
     * 
     * @return This method returns the height of this image in pixels.
     */
    public int getHeight();
}
