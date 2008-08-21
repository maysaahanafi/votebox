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

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AView implements IView{

    protected final HashMap<IDrawable, Rectangle> _hitboxMap;
    protected final LinkedList<IDrawable> _currentDrawables;
    protected final HashMap<EventType, IEventHandler> _handlers;
    protected int _yoffset;
    protected IDrawable _background;
    
    private ExecutorService _executor = Executors.newCachedThreadPool();

    protected AView() {
        _hitboxMap = new HashMap<IDrawable, Rectangle>();
        _currentDrawables = new LinkedList<IDrawable>();
        _handlers = new HashMap<EventType, IEventHandler>();
    }

    /**
     * @see votebox.middle.view.IView#draw(votebox.middle.view.IDrawable)
     */
    public void draw(IDrawable element) {
        _currentDrawables.add( element );
        _hitboxMap.put( element, makeHitBox( element ) );
        invalidate( element );
    }

    /**
     * @see votebox.middle.view.IView#setBackground(votebox.middle.view.IDrawable)
     */
    public synchronized void setBackground(IDrawable element) {
        _background = element;
    }

    /**
     * @see votebox.middle.view.IView#register(votebox.middle.view.EventType,
     *      votebox.middle.view.IEventHandler)
     */
    public synchronized void register(EventType eventType, IEventHandler lambda) {
        _handlers.put( eventType, lambda );
    }

    /**
     * Call this method to create a rectangle that appropriately represents the
     * hit box of a given drawable.
     * 
     * @param drawable
     *            This is the given drawable.
     * @return This method returns the rectangle that represents the hitbox of
     *         drawable.
     * @throws Exception
     *             This method throws an exception if the drawable does not know
     *             its image and has a problem finding it.
     */
    protected Rectangle makeHitBox(IDrawable drawable) {
        return new Rectangle( drawable.getX(), drawable.getY() + _yoffset,
                drawable.getImage().getWidth(), drawable.getImage().getHeight() );
    }

    /**
     * Get the focusable which covers a given (x,y) coordinate.
     * 
     * @param x
     *            This is the given x-coordinate.
     * @param y
     *            This is the given y-coordinate.
     * @return This method returns the focusable which contains the given
     *         coordinate pair, or null if there is no focusable associated with
     *         the pair.
     */
    protected IDrawable getFocusableFromHitbox(int x, int y) {
        for (IDrawable d : _hitboxMap.keySet())
            if (d instanceof IFocusable && _hitboxMap.get( d ).contains( x, y ))
                return d;
        return null;
    }

    /**
     * If a given event type has been registered for, execute its handler.  Returns immediately.
     * 
     * @param type
     *            If this type of event has been registered for, execute the
     *            handler.
     * @param event
     *            This structure wraps information about the event.
     */
    protected void deliver(final EventType type, final InputEvent event) {
    	_executor.submit(new Runnable(){
    		public void run(){
    			if (_handlers.containsKey( type ))
    				_handlers.get( type ).handle( event );
    		}//run
    	});
    }
    
    /**
     * @see votebox.middle.view.IEventPump#pump(EventType, InputEvent)
     */
    public void pump(EventType type, InputEvent event){
    	deliver(type, event);
    }//pumpEvent
}
