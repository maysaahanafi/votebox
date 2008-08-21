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
 * This is the factory for the AWTView. It constructs AWTView and AWTImage
 * instances.
 * 
 * @author kyle
 * 
 */
public class AWTViewFactory implements IViewFactory {

    private final boolean _windowed;

    /**
     * @param windowed
     *            Set this to true if the constructed views should be windowed
     *            rather than fullscreened.
     */
    public AWTViewFactory(boolean windowed) {
        _windowed = windowed;
    }

    /**
     * @see votebox.middle.view.IViewFactory#makeImage(java.lang.String)
     */
    public IViewImage makeImage(String path) {
        return new AWTImage( path );
    }

    /**
     * @see votebox.middle.view.IViewFactory#makeView()
     */
    public IView makeView() {
        return new AWTView( _windowed );
    }
}
