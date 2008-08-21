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
package votebox.events;

/**
 * A listener that handles various VoteBox message events on an auditorium
 * network. Events for a machine joining and leaving are included as well.
 * 
 * @author cshaw
 */
public interface VoteBoxEventListener {

    /**
     * Fired when the activated message is received
     * 
     * @param e
     *            the event
     */
    public void activated(ActivatedEvent e);

    /**
     * Fired when the assign-label message is received
     * 
     * @param e
     *            the event
     */
    public void assignLabel(AssignLabelEvent e);

    /**
     * Fired when the authorized-to-cast message is received
     * 
     * @param e
     *            the event
     */
    public void authorizedToCast(AuthorizedToCastEvent e);

    /**
     * Fired when the ballot-received message is received
     * 
     * @param e
     *            the event
     */
    public void ballotReceived(BallotReceivedEvent e);

    /**
     * Fired when the cast-ballot message is received
     * 
     * @param e
     *            the event
     */
    public void castBallot(CastBallotEvent e);

    public void commitBallot(CommitBallotEvent e);

    public void challenge(ChallengeEvent e);
    
    public void challengeResponse(ChallengeResponseEvent e);

    /**
     * Fired when a connection is established with another machine on the
     * network (regardless of which machine initiated it)
     * 
     * @param e
     *            the event
     */
    public void joined(JoinEvent e);

    /**
     * Fired when the last-polls-open message is received
     * 
     * @param e
     *            the event
     */
    public void lastPollsOpen(LastPollsOpenEvent e);

    /**
     * Fired when a connection with another machine is lost for any reason
     * 
     * @param e
     *            the event
     */
    public void left(LeaveEvent e);

    /**
     * Fired when the override-cancel message is received
     * 
     * @param e
     *            the event
     */
    public void overrideCancel(OverrideCancelEvent e);

    /**
     * Fired when the override-cancel-confirm message is received
     * 
     * @param e
     *            the event
     */
    public void overrideCancelConfirm(OverrideCancelConfirmEvent e);

    /**
     * Fired when the override-cancel-deny message is received
     * 
     * @param e
     *            the event
     */
    public void overrideCancelDeny(OverrideCancelDenyEvent e);

    /**
     * Fired when the override-cast message is received
     * 
     * @param e
     *            the event
     */
    public void overrideCast(OverrideCastEvent e);

    /**
     * Fired when the override-cast-confirm message is received
     * 
     * @param e
     *            the event
     */
    public void overrideCastConfirm(OverrideCastConfirmEvent e);

    /**
     * Fired when the override-cast-deny message is received
     * 
     * @param e
     *            the event
     */
    public void overrideCastDeny(OverrideCastDenyEvent e);

    /**
     * Fired when the polls-closed message is received
     * 
     * @param e
     *            the event
     */
    public void pollsClosed(PollsClosedEvent e);

    /**
     * Fired when the polls-open message is received
     * 
     * @param e
     *            the event
     */
    public void pollsOpen(PollsOpenEvent e);

    /**
     * Fired when the polls-open? message is received
     * 
     * @param e
     *            the event
     */
    public void pollsOpenQ(PollsOpenQEvent e);

    /**
     * Fired when the supervisor message is received
     * 
     * @param e
     *            the event
     */
    public void supervisor(SupervisorEvent e);

    /**
     * Fired when the votebox message is received
     * 
     * @param e
     *            the event
     */
    public void votebox(VoteBoxEvent e);
    
    /**
     * Fired when the ballot counted message is received
     * 
     * @param e
     * 			  the event
     */
    public void ballotCounted(BallotCountedEvent e);

}
