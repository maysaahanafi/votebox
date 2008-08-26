/**
  * This file is part of VoteBox.
  * 
  * VoteBox is free software: you can redistribute it and/or modify
  * it under the terms of the GNU General Public License version 3 as published by
  * the Free Software Foundation.
  * 
  * You should have received a copy of the GNU General Public License
  * along with VoteBox, found in the root of any distribution or
  * repository containing all or part of VoteBox.
  * 
  * THIS SOFTWARE IS PROVIDED BY WILLIAM MARSH RICE UNIVERSITY, HOUSTON,
  * TX AND IS PROVIDED 'AS IS' AND WITHOUT ANY EXPRESS, IMPLIED OR
  * STATUTORY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, WARRANTIES OF
  * ACCURACY, COMPLETENESS, AND NONINFRINGEMENT.  THE SOFTWARE USER SHALL
  * INDEMNIFY, DEFEND AND HOLD HARMLESS RICE UNIVERSITY AND ITS FACULTY,
  * STAFF AND STUDENTS FROM ANY AND ALL CLAIMS, ACTIONS, DAMAGES, LOSSES,
  * LIABILITIES, COSTS AND EXPENSES, INCLUDING ATTORNEYS' FEES AND COURT
  * COSTS, DIRECTLY OR INDIRECTLY ARISING OUR OF OR IN CONNECTION WITH
  * ACCESS OR USE OF THE SOFTWARE.
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
