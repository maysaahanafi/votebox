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
 * 
 * The IView is an interface that allows for the drawing of a Drawable element
 * to the dispaly. We have purposefully drawn an abstraction boundary here, at
 * the interface between the ViewManager (better seen as the model's interface
 * with the display) and the View, because we know that the view implementation
 * will change several times before this project comes to completion. It is
 * important to speak in abstract terms to the display to avoid being tied to
 * one particular implementation.
 * 
 */

public interface IView {

    /**
     * Call this method to draw a Drawable element to the display.
     * 
     * @param element
     *            This is the drawable that wishes to be drawn.
     */
    void draw(IDrawable element);

    /**
     * This method invalidates a given element. Calling this method tells the
     * view to reload the texture for a given element, then rerender the display
     * 
     * @param element
     *            This is the element which is no longer valid. Reload this
     *            element's image and rerender it to the display
     */
    void invalidate(IDrawable element);

    /**
     * Call this method to identify a drawable to use as the background image.
     * This should not be a drawable that is used as an argument to "draw"
     * 
     * @param element
     *            This is the element which will be identified as the background
     *            image.
     */
    void setBackground(IDrawable element);

    /**
     * This method is the means that an outsider can use to register for an
     * input event with the view. In our system, "registering for an event"
     * involves asking a view to dispatch events of a certain abstract type to a
     * queue that the requestor provides.
     * 
     * @param eventType
     *            This is the abstract event type that is being registered on.
     * @param lambda
     *            Execute this handler when the event happens.
     */
    void register(EventType eventType, IEventHandler lambda);

    /**
     * This method should clear the display of all rendered ADrawable elements.
     */
    void clearDisplay();

    /**
     * This method initializes and launches the view, putting the view into its
     * event loop. Before the loop starts, and after the initialization, a given
     * lambda is executed.
     * 
     * @param lambda
     *            Run this lambda before the loop.
     */
    void run(Runnable lambda);

    /**
     * This method destroys the view.
     */
    void dispose();
}
