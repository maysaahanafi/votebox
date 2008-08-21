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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;

import javax.imageio.ImageIO;

/**
 * This is a wrapper for the AWT Image class, BufferedImage.
 * 
 * @author kyle
 * 
 */
public class AWTImage implements IViewImage {

    private final String _filename;
    private SoftReference<BufferedImage> _bufferedImage;

    /**
     * Construct a new AWT Image.
     * 
     * @param filename
     *            Construct an image that loads its bytes from this path.
     */
    public AWTImage(String filename) {
        _bufferedImage = new SoftReference<BufferedImage>( null );
        _filename = filename;
    }

    /**
     * @see votebox.middle.view.IViewImage#getImage()
     */
    public BufferedImage getImage() {
        if (_bufferedImage.get() == null)
            try {
                _bufferedImage = new SoftReference<BufferedImage>( ImageIO
                        .read( new File( _filename ) ) );

            }
            catch (IOException e) {
                throw new BallotBoxViewException( "The file " + _filename
                        + " could not be loaded to represent an image", e );
            }

        return _bufferedImage.get();
    }

    /**
     * @see votebox.middle.view.IViewImage#getHeight()
     */
    public int getHeight() {
        return getImage().getHeight();
    }

    /**
     * @see votebox.middle.view.IViewImage#getWidth()
     */
    public int getWidth() {
        return getImage().getWidth();
    }
}
