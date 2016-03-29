![http://votebox.cs.rice.edu/images/supervisor-icon.png](http://votebox.cs.rice.edu/images/supervisor-icon.png)

# VoteBox Software Specification: Supervisor #

_Daniel Sandler, Corey Shaw_

_Written by: Corey Shaw_

_Edited by: Kevin Montrose_

## Usage ##

With the jar built by ant, you can simply
```
 $ java -jar supervisor.jar <serial>
```

## Introduction ##

![http://votebox.cs.rice.edu/images/supervisor-eg-small.png](http://votebox.cs.rice.edu/images/supervisor-eg-small.png)

The supervisor is designed to be the poll worker's access to the rest of the VoteBox network.  It shows a list of all known machines on the network and their statuses, along with controls to open or close the polls, and authorize voters.

When the supervisor initially starts up, it attempts to connect to an Auditorium network if one exists.  This is done using the discovery protocol, and requires all machines to be on the same subnet.  If it finds any machines, it then broadcasts its own status so that other machines on the network can figure out what this new machine is.  Once connected, a supervisor can then be [activated](#Activation.md), and then all of the above controls become available.  If the supervisor does not find any machines on the network, it simply waits until another machine attempts to connect to it.

The supervisor is designed in the classic MVC pattern, where the controller serves as the entry point into the program and simply instantiates the model and view.  See the respective subsections for more details.

## Supervisor Concepts ##

### Activation ###

The '''active''' supervisor is the supervisor that is currently in use by the poll worker, and can be used to perform actions in the election.  There can only ever be one active supervisor, and a supervisor must be activated before it can perform any actions.  This is for two reasons:
  * Activation ensures that the supervisor has a clear view of the entire network, by requiring all machines to respond with their status if it is not found and up-to-date
  * Allowing more than one supervisor means that those supervisors might try to perform the same action at the same time (such as both authorize for a booth), causing unexpected behavior

If at any time a supervisor is activated, all other supervisors must deactivate at that time.  In addition, we maintain that a supervisor must be on the network to remain active, if it loses connections to all other machines, it assumes it is offline and automatically deactivates.  This prevents two machines from being active and off the network, then coming on separately.  It also requires that supervisor to reactivate once back on the network, receiving status updates as necessary from the messages it missed.

When being activated, a supervisor may also query the other machines on the network to see if the polls are open, and/or label one or more of the booths.

### Labeling ###

In order to keep the system as simple as possible, we decided it would be best for the supervisor to label the booths automatically.  They are labeled as integers starting from 1, in the order that they are seen.  However, note that only the active supervisor can label the machines; this prevents labeling conflicts from more than one supervisor.  If a supervisor is activated and sees any unlabeled machines, it will label them in an indeterminate order, starting with the first available number.  As such, the standard setup procedure should be the following:
  * Turn on the main and backup supervisors, and once they see each other activate the main supervisor.
  * Turn on and connect the booths one at a time, in the order that they should be labeled.
  * This ensures that the booths are labeled in a sensible order, that allows for machines to be easily located by their numbers.  If the labels are out of order, it may be difficult for the poll worker and the voter.

A couple of other notes with the labeling procedure:
  * Supervisors remember the labels of all booths, so if a booth is rebooted, it will be reassigned the label that it had previously.
  * Booths remember their own labels, so if a fresh supervisor connects, it will receive the labels of all of the booths (in their status messages).
  * If a new booth is connected during the election, it will be assigned the next available number (so if there are booths 1-8, the new one will be labeled 9).
  * If for some reason all machines lose power simultaneously, the booths may be labeled differently the next time they are turned on.