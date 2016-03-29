# Frequently Asked Questions #

## Understanding the VoteBox Security Model ##

Q: Can you explain the VoteBox security model?  How is VoteBox different from conventional electronic voting machines?

A: VoteBox is built around a family of "end to end" cryptographic techniques.  In normal usage, a voter's interaction with VoteBox is almost exactly the same as their interaction would be with a normal electronic voting system, with the exception that the voter is asked, after the summary screen, whether he or she would like to "cast" or "challenge" the ballot.  By this time, the VoteBox has already transmitted the voter's encrypted vote to every other VoteBox system in the local precinct, and has thus "committed" to it.  When a vote is "cast," another message is broadcast saying as much and the voter is done.  When a vote is "challenged," then the VoteBox reveals a key that can be used to decrypt the vote.  (These "challenged" votes are not included in the final election tally.)  Because a VoteBox has no idea, in advance, whether it will be challenged, it must behave correctly on the chance it might be caught misbehaving.

VoteBox uses a number of other cryptographic techniques, such as homomorphic tallying, to add the votes together.  This allows any observer to tally the votes themselves, based only on seeing the encrypted vote records, and verify that the official decryption of them is correct.  Likewise, VoteBox is engineered to make lots of copies.  There are copies of every vote on every VoteBox machine in the precinct, and it's even safe to allow third-parties to connect to the network and listen in.  This makes VoteBox  resilient in the face of a variety of different failure modes.

We describe these issues in much greater detail in our [USENIX Security '08 paper](http://www.usenix.org/events/sec08/tech/sandler.html).

Q: How do I explain this to my (unsophisticated) grandparents?  Why should they believe it?

A: To some extent, you'd have plenty of trouble trying to explain why they should trust traditional voting technologies.  The cryptographic and data replication mechanisms within VoteBox provide excellent integrity mechanisms without compromising voter privacy.  The auditing procedure can be performed by anybody, using an independent implementation of the VoteBox protocol.  That means your grandparents can rely on an organization that they do trust, such as their favorite political party or candidate, the League of Women Voters, and so forth.  These organizations can now perform much more sophisticated audits than they could ever conduct in the past as "poll watchers."

Q: If somebody wants to audit a voting machine properly, they will want to videotape it, have observers and witnesses, and so forth.  How can this integrate with a busy election precinct on election day?

A: Clearly, the volume of auditors who wish to conduct challenges need to be limited in cases when too many auditors could have a measurable impact on throughput for normal voters.  Otherwise, they're welcome to bring their video cameras and everything else.  One useful trick, for a commercial implementation of VoteBox, would be to have the final "cast ballot" button be a hardware button that's not used for any other purpose.  With that, poll workers could have a procedure to "lock" that button (perhaps with a plastic hinged cover of some kind).  At that point, the auditors are free to press and poke any way they'd like.  The machine, of course, would have no idea it was being audited and would have no idea that the "cast ballot" button was locked away.

Q: What kind of damage could corrupt software in the voting booth accomplish?

A: In a traditional DRE voting system, corrupt / tampered software can do two bad things: it can reveal how you voted to somebody else (violating your privacy) and it can change your vote (violating your vote's integrity).  With VoteBox, our challenge mechanism guarantees that any voting machine which is attacking vote integrity will be detected with high probability.

(Consider: if three percent of the ballots are challenged, then the odds of a malicious VoteBox getting away with tampering with one single vote during the election day is 97%.  Sounds high, but one vote isn't likely to change the election outcome.  Now, if the machine tampers with two votes, that drops to 94%.  If the machine tampers with twenty votes, the odds of getting away with it drop to 54%.  If a machine tampered with 100 votes, the odds of that going undetected are less than 5%.  In effect, the more malicious a machine is, the more likely it will get caught.)

Now, privacy attacks are a different can of worms.  If the machine is evil, it can simply remember every vote, internally, in the order cast.  This same issue occurs with the "toilet paper roll" printers in present DRE vendors' VVPAT solution.  This same issue could even occur in precinct-based ballot optical scanners.  Privacy attacks are a difficult problem with any voting system.

Q: Would VoteBox systems be vulnerable to "sleepover" issues (i.e., tampering by corrupt poll workers, the night before the election, if the voting systems were left at their houses)?

A: If every single voting machine in the precinct is corrupted, this could definitely be used to compromise voters' anonymity (as previously described) but not their integrity.  In a scenario like this, corrupt poll workers could then inject bogus votes into every voting machine in a consistent fashion.  Assuming these corrupt poll workers could similarly forge the contents of their poll books, this would be a big problem.  In VoteBox, however, a "launch code" can be distributed by election officials on the day of the election, which then goes into the machines' logs.  That would allow "prehistoric" votes to be distinguished from real ones, on the logs.  If the software was tampered to inject these bogus votes during election day, rather than in advance, this could cause a more serious problem.  At that point, the question is whether we can distinguish a falsely injected vote from a legitimate vote.

Every legitimate vote has two basic components: an authorization message, broadcast by the supervisor console, and the encrypted vote, broadcast by the voting machine.  By splitting the overnight "sleepover" VoteBox equipment across different poll workers (who hopefully aren't colluding with one another), we can control this threat, since the automatic injection of a fraudulent vote would require the collusion of the supervisor console and a voting machine.

Q: VoteBox lets the voter either challenge or cast any given ballot.  If you challenge a ballot, then you can verify it, but that ballot won't be cast.  If you cast a ballot, you can't challenge and verify it.  Why should I believe that the unchallenged ballots are accurate?

A: VoteBox's cryptographic mechanism relies on the concept of committing to an encrypted vote before the voter/auditor tells the machine that it's being audited.  Since the voting machine has absolutely no idea if a given vote is going to be challenged, it has to behave correctly, just on the off chance it that might be challenged.  All of these encrypted votes are digitally signed, which means that if the machine is caught cheating, the auditor has what amounts to a signed confession of the cheating.  You can take that to court.

Q: What if a voter accidentally challenges their vote when they wanted to cast it?

A: No problem.  The supervisor console will indicate that a challenge occurred, and poll workers would be instructed to approach these voters and offer them the chance to cast their vote again.

Q: What if malicious poll workers manage to convince gullible voters that they're supposed to hit the challenge button, meaning that their votes don't actually get cast?

A: A post-election audit would see all of these challenged ballots.  This would provide a lot of evidence to bring to a court, to perhaps argue that the poll workers were misbehaving and send them to jail.  You could even tally the challenge votes, independently, and argue for the court to include them in the official tally.

Q: What about booth capture?

A: This doesn't happen much in the U.S., but it's a real concern internationally.  Armed thugs bust into the polling place and cast as many votes as they can until the police arrive.  With VoteBox's logging infrastructure, encrypted votes are recorded in order.  A court could find that all the encrypted votes from the start to end of the booth capture attack should be thrown out of the tally.  If there was a concern that the thugs tampered with the software, those VoteBox machines could be retired and fresh ones brought in to replace them.  If the thugs destroy all of the records on every machine in the precinct, a copy would have still been safely transmitted outside of the precinct, before the thugs arrived.

Q: I still don't trust computers.  I want hand-counted paper ballots.

A: We're not asking you to trust computers.  We're asking you to trust mathematics.  It's a significant difference.  And, you do realize that hand-counted paper ballots have plenty of their own failure modes.  Also, there's no reason you can't include a printer as part of a VoteBox precinct deployment.  That printer can print every (encrypted) VoteBox ballot as it's broadcast on the wire.  Voters can happily take these things home.  For cast ballots, voters can look at the official "bulletin board", published by election officials, and verify that their ballot is part of the official list of (encrypted) ballots.  For challenged ballots, they can have a computer verify that the encryption was done properly.  If it wasn't, that piece of paper is a signed confession of misbehavior.  You can take that to court.

(We can't let you take home a proof of some sort that can verify who you actually voted for.  That would effectively let you sell your vote or would enable somebody to coerce you to vote their way.  VoteBox is carefully engineered to defeat voter bribery and coercion.)


## Features Present and Absent in VoteBox ##

Q: When I download VoteBox, what am I getting?

A: The VoteBox distribution includes four applications: the ballot preparation tool, the VoteBox voting booth, the VoteBox supervisor console, and a web-based vote validation tool.  The web-based vote validation tool is itself two executables, a "tap" on the VoteBox network and a seperate web server.

Q: What major features might I want in a real election that are missing from VoteBox?

A: VoteBox does not yet have support for write-in votes.  Similarly, there is no support for straight ticket voting or ballot rotation.  VoteBox is built for American-style winner-takes-all elections where a voter may only cast one vote in a race.  VoteBox does support approval voting (where voters may vote for any subset of the candidates in an election) and could be easily extended for range voting, but VoteBox does not support schemes where the voter is asked to rank preferences (e.g., single-transferable vote, instant runoff, Condorcet elections, etc.).

Adding straight ticket voting or ballot rotation would be relatively straightforward.  The mathematics for doing write-in votes alongside homomorphic vote encryption, as used in VoteBox, would similarly not be much trouble to integrate.  Moving to a ranked preference voting system would require a significant overhaul.

Q: Are there any other advanced cryptographic techniques that VoteBox is missing that we might want to add?

A: A production VoteBox system would also want to include non-interactive zero-knowledge (NIZK) proofs alongside the encrypted ballots.  These provide important "sanity-checking" assurance alongside the existing challenge mechanism.  A NIZK proof can demonstrate to any observer (not just a challenger) that a ballot is well-formed, i.e., that it has zero or one in any of the counters and that the sum of all the counters in a given race are either zero or one.  Also, a production VoteBox system would want to use threshold cryptographic techniques as part of the decryption process -- requiring a set of trustees to collaborate in order to decrypt the final vote totals, and thus being able to prevent a malicious election official from decrypting any individual ballots.

Q: Is VoteBox ready for multilingual ballots?  What about accessibility features?

A: The VoteBox ballot preparation tool supports a variety of languages, including Chinese (Simplified), Japanese, and Korean.  Basically, if Java supports your language, making the VoteBox prep tool support it is going to be straightforward.  VoteBox does not yet support alternative input devices (e.g., sip and puff devices), although those are really just a variation on keyboard navigation, which is supported by VoteBox.  VoteBox does not yet have audio support.  A straightforward extension to the ballot prep tool could include audio recordings, and the voting booths could then include audio playback code.

Q: In Election Centers, there may be hundreds of different ballot styles that might be presented to a voter.  How would VoteBox support this?

A: This would require a significant development effort.  The present ballot preparation tool would need to be extended to deal with these issues and generate all the different ballot styles.  The supervisor console would need to present an appropriate menu to the poll workers to then select the proper ballot.  The vote tallying system would need to identify which ballots should be included in any given tally.  The VoteBox voting booth, itself, would not require any changes.

## Licensing and Future Plans ##

Q: What are the license terms for VoteBox?

A: VoteBox is licensed under the [GPL version 3](http://gplv3.fsf.org/).

Q: Why did you choose this license instead of some other license?

A: There is no perfect license model.  GPL-style licenses encourage sharing and reuse of code, which is the essence of our mission as researchers.  We considered BSD or Apache-style licenses, which would allow companies to reuse our code without being required to share the results, and decided against such a license at this time.

Q: Are you planning to commercialize VoteBox?

A: No.

Q: Are you aware of anybody else who is planning to commercialize VoteBox?

A: No, but we'd love to help them.

Q: What are your future plans for VoteBox?

A: At Rice, we do research, and VoteBox is a great platform for studying security issues in electronic voting.  VoteBox is also useful for human factors studies of electronic voting systems.  We look at questions in both of these domains at Rice.

Q: Where does VoteBox fit into the regulatory world for electronic voting systems?

A: VoteBox fits roughly into what the newest [VVSG](http://vvsg.org/) (voluntary voting system guidelines) documents call the "Innovation Class."  If/when a VoteBox-like system is going through the certification process, it will require a different sort of scrutiny than is normally applied as part of voting systems certification.  For traditional voting systems, not subject to the innovation class, standard certification is still necessary (and it's still not very reassuring).

In other words, the existence of VoteBox and the fact that it doesn't strictly need a voter-verified paper trail shouldn't be taken as any kind of endorsement or statement as to whether current voting machines can similarly get away with it.  (They can't.)

If a voting machine vendor wants to argue that they fit into the innovation class, they're going to have a substantial burden to convince us that their systems are secure.  Systems like VoteBox rely on strong mathematics for their security.  The "bad guys" are welcome to know everything there is to know about how VoteBox works, and it won't help them attack an election built around VoteBox.  Any vendor wishing to sell such a system would need to convincingly prove that their systems are "software independent", which means that it is impossible for somebody to undetectably tamper with the software of the system in such a way that they can undetectably tamper with the tallies at the end of the election.