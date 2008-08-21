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
package votebox.middle;

/**
 * An object that implements IGlobalVars knows all the neccesary path and
 * filename information for a single ballot.
 * 
 * @author Kyle
 * 
 */
public interface IBallotVars {

    /**
     * Invoke this method to get the root path to all ballot related
     * information.
     * 
     * @return This method returns the ballot path.
     */
    String getBallotPath();

    /**
     * Invoke this method to get the fully qualified path+filename of the ballot
     * XML file.
     * 
     * @return This method returns the ballot file path.
     */
    String getBallotFile();

    /**
     * Invoke this method to get the fully qualified path+filename of the ballot
     * schema XML file.
     * 
     * @return This method returns the ballot schema file path.
     */
    java.net.URL getBallotSchema();

    /**
     * Invoke this method to get the fully qualified path+filename of the layout
     * XML file.
     * 
     * @return This method returns the layout file path.
     */
    String getLayoutFile();

    /**
     * Invoke this method to get the fully qualified path+filename of the layout
     * schema XML file.
     * 
     * @return This method returns the layout schema path.
     */
    java.net.URL getLayoutSchema();
}
