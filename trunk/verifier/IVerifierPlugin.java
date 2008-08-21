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
package verifier;

/**
 * Plugins to the verifier must implement this interface.<br>
 * <br>
 * When the verifier is run as a standalone application, each plugin's
 * initialize method will be called only once near the start of the verifier's
 * running.When the verifier is run as a module, the initialize method will be
 * called once (in a similar fashion) but the application will gain a reference
 * to the plugin after it is loaded, and is free to make specific API calls to
 * the plugin as it sees fit.
 * 
 * @see verifier.Verifier
 * @author kyle
 * 
 */
public interface IVerifierPlugin {

    /**
     * This method will be called from the verifier once after the plugin is
     * constructed.
     * 
     * @param verifier
     *            This is the verifier instance that is constructing this
     *            plugin.
     * @throws PluginException
     *             This method throws if the plugin encountered problems.
     */
    public abstract void init(Verifier verifier) throws PluginException;
}
