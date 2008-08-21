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
package auditorium;

/**
 * These are configuration constants that auditorium needs. Pass an instance of
 * this to an {@link AuditoriumHost} when you construct it.
 * 
 * @author kyle
 * @see auditorium.AuditoriumHost
 */
public interface IAuditoriumParams {

    /**
     * @return Timeout after this many miliseconds when listening for discover
     *         responses.
     */
    public int getDiscoverTimeout();

    /**
     * @return Discover requests should be UDP broadcast on this port.
     */

    public int getDiscoverPort();

    /**
     * @return Timeout after this many miliseconds when trying to reply to a
     *         discover request.
     */

    public int getDiscoverReplyTimeout();

    /**
     * @return Discover replies should be sent over a TCP socket established at
     *         this port.
     */
    public int getDiscoverReplyPort();

    /**
     * @return Hosts should listen on this port for incoming join requests.
     */
    public int getListenPort();

    /**
     * @return If you don't get a reply back from the join in this amount of
     *         time, give up.
     */

    public int getJoinTimeout();

    /**
     * @return Send UDP packets to this address as "broadcast"
     */
    public String getBroadcastAddress();

    /**
     * @return Create the log data here.
     */
    public String getLogLocation();

    /**
	 * @return Return an {@link auditorium.IKeyStore} to be used when looking for
	 *         certificates of other participants or signing authorities.
	 */
    public IKeyStore getKeyStore();

    /**
	 * @return This method returns the location of the file that defines the
	 *         rule that should be given to an instance of {@link verifier.Verifier}.
	 *         Return null to denote that the verifier should not be used.
	 */
    public String getRuleFile();
    
    /**
     * @return Returns true if encryption for cast ballots is enabled.  This is used to determine the
     *         Tallier to use in the Supervisor, and the cast-ballot message to send from
     *         VoteBox.
     */
    public boolean getCastBallotEncryptionEnabled();

    /**
     * 
     * @return Returns true if VoteBox should make use of the "commit-challenge" model for voting.
     * 		   If false, a single event is used for casting ballots in place of the two stage
     * 	 	   commit & cast.
     */
    public boolean getUseCommitChallengeModel();
    
    /**
     * @return Returns true if VoteBox should use SDL to connect toe an Elo touchscreen as an input device.
     *         Should only be true on linux, SDL view, framebuffer driven machines.  All other configurations
     *         are likely to fail, and ungracefully at that.
     */
    public boolean getUseEloTouchScreen();
    
    /**
     * 
     * @return Return the path to the Elo touch screen.  Used by SDL to connect to that device for mouse input.
     */
    public String getEloTouchScreenDevice();
    
    /**
     * 
     * @return the amount of time (in milliseconds) the view should wait before restarting after
     *         a successful vote.
     */
    public int getViewRestartTimeout();
    
    /**
     * @return the key to use (if not specified on the command line).
     */
    public int getDefaultSerialNumber();
    
    /**
     * @return the report address for Tap to use (if not specified on the command line).
     */
    public String getReportAddress();
    
    /**
     * @return the port for the challenge web server and tap to use to communicate (if not specified on the command line).
     */
    public int getChallengePort();
    
    /**
     * @return the port for the challenge web server to server http requests on.
     */
    public int getHttpPort();
    
    /**
     * @return the ballot file to be used for images by the challenge web server (if not specified on the command line).
     */
    public String getChallengeBallotFile();
}
