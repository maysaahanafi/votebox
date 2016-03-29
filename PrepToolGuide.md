# VoteBox Software Specification: Ballot Creator User Guide #

_Written by: Corey Shaw, Kevin Montrose_

## Introduction ##

The **VoteBox Ballot Creator**, or more informally the "ballot prep tool", is a
graphical Java program used to [pre-render](PRUI.md) ballots for use with VoteBox.

http://votebox.googlecode.com/files/Preptool%20-%20Manual%20Example.PNG

## Overview and Layout ##

To launch the program, double-click on its icon:

  * `VoteBox Ballot Creator.app` (on Mac OS X)
  * `VoteBox Ballot Creator.exe` (on Windows)
  * `preptool.jar` (other platforms)

The main ballot creator window (see above screenshot) will appear.  On the top is a toolbar with some buttons to manipulate the entire ballot.  On the left side of the window is a list of all the cards in the ballot, and some buttons to manipulate those cards.  And on the right side of the window there is a panel for editing the current card (it starts out blank but changes when you select a card), and below it, a panel to preview the current card.

### Manipulating Ballots ###
On the toolbar there are five buttons for manipulating ballots: New Ballot, Open Ballot, Save Ballot, Export to VoteBox, and Preview in VoteBox.  The New Ballot button starts a blank ballot, that has no cards or parties, and defaults to only having English selected.  (See the [Languages](#Languages.md) and [Parties](#Parties.md) subsections for how to add more of these.)  Save Ballot will save your ballot to disk as a .bal file, which you can then load later with the Open Ballot button.  Export to VoteBox will take the ballot and export it in a special format so that it can be loaded into VoteBox.  Preview in VoteBox will export the entire ballot to a temporary directory, and then launch it in VoteBox so you can see what the finished product will be.

_Note: You can only have one ballot open at a time, so if you start a new ballot or open one from disk, you must save the ballot you are currently working on or it will be lost._

_Note: Be careful not to confuse the Save and Export features.  The Save Ballot button saves the ballot to disk so you can continue working on it or export it again at a later date.  The Export to VoteBox button exports the ballot as a format that only VoteBox can understand.  You can only open ballot files that were saved with the Save Ballot button, not the Export to VoteBox button._

### Manipulating Cards ###
A ballot consists of any number of **cards**.  A card is some part of the ballot on which the user needs to make a choice.  Currently, the three types of cards are a Race, a Presidential Race, and a Proposition.  A Race consists of candidates and their parties - this is the most common type of card.  A Presidential Race is just like a Race, except candidates also have running mates (there is also a "Top of Ticket" and "Bottom of Ticket" entry field; this is similar to "President" and "Vice President").  A Proposition has an area to enter text, and automatically adds the Yes and No options to the ballot.

Manipulating cards is all done on the left side of the window.  Click the **+** button in the bottom left corner to add a new card, and a popup menu will ask you for what type (Race, Presidential, or Proposition).  After choosing the type, the card shows up in the left panel at the bottom of the list, and it's selected so it also appears on the right side so you can edit the card (See: [Editing Cards](#EditingCards.md)).  Once you have multiple cards in your ballot, you can easily switch the card you are working on.  Just click on a card's name on the left, and it will highlight and show on the right side of the screen.  If you want to rearrange the order the cards appear in the ballot, you can select a card and then use the move up and move down buttons (the up and down arrows), or you can click and drag a card to move it within the ballot.  And if you decide you want to delete a card, press the **-** button in the bottom left.

### Editing Cards ###
Once you add a new card, you need to add its data (such as the candidates for a race).  Click on a card on the left, and the editor panel will switch to that card.  Here you will see fields that are specific to each card type.  Most cards have a '''Title'''.  This is usually the name of the race or proposition, and is also what the card is listed as on the left.  Changing this will immediately affect the name on the left: Try it!  Also, both race types have a large table to add the candidates.  This table has an add, delete, move up, and move down button just as the list of cards on the left.  You can also drag and drop candidates to reorder them.  When you want to edit candidates' names, double-click on the cell of the table that you want to edit, type the text, and then hit enter.  To select candidates' parties, see the [Parties](#Parties.md) subsection.

You can also preview the card that you are currently editing.  Click the Refresh button in the Preview Panel, and the images for that card will be rendered and displayed here.  Note that these images don't update automatically as you type; you have to hit the refresh button again to get the latest updates.  If you switch to a different card or change the language, the Preview Panel is cleared to inform you of this.  Also, the Preview Panel only shows the part of the page that is unique to that card, leaving off the main title, sidebar, and navigation.  To view the entire ballot with the navigation and review screen, click the Preview in VoteBox button at the top of the screen.

### Languages ###
Ballots by default start out with only English enabled.  However, if you are creating multi-lingual ballots, you will need to add those languages to the ballot and provide translations for all of the text.  You may have noticed a bar below the Preview Panel with an English button on it: this is called the **Language Bar**.  It shows up here and on the [Parties Dialog](#Parties.md), and will be used anywhere else multiple-language editing is necessary.  To change the languages used in the ballot, click on the button labeled English and choose the `Edit...` option.  This will bring up a dialog with several languages and checkboxes: simply check the boxes next to the language that you want included in the ballot.  The topmost language that is checked will be the "primary language", which is shown in the list of cards and is the language that text can be automatically copied from.  English will always be the primary language, unless it is deselected.  When you are done making your selections, press OK to continue.

_Note: If you remove a language from the ballot, its translations are not lost!  Simply add it back to the ballot and all of the translations will still be there.  This works even after a ballot has been saved and re-opened._

All data that is entered in the card editor panel is saved to the current language, the language shown in the Language Bar.  To change the current language, click the button again, and if there is more than one language in the ballot, you can select a different language.  All of the fields in the card editor panel switch to that language's translations (if you just added that language, everything should show up as blank).  Make sure and always fill in all of the fields on the card for every language, otherwise the information will not show up on that language's ballot.  The Language Bar displays a warning in red when some translations are missing - when you see this, you should check each language for any fields that you left blank.  The Export to VoteBox button will also warn you if any of the cards are missing translations.

### Parties ###
To edit the parties available in the ballot, first switch to a race or presidential race.  Then select a candidate and click on the drop-down in the Party column.  If there are no parties in the ballot yet, there should be a blank row (for a candidate without a party), and an `Edit...` option.  Click the `Edit...` option to bring up the Parties dialog.  Here you can add, delete, and move parties (with the buttons or with drag and drop), just as candidates or cards.  Click the OK button when you're done.  Now when you click the drop-down again, you should see the parties that you just added.  Simply click the party's name to select that party.  You only need to select this once; when you change languages, the party should update to the party's name for the newly selected language.

_Note: Don't forget to add the translations of the parties' names and abbreviations if you are working on a [multi-language ballot](#Languages.md)!  Otherwise, the parties will show up as blank on some languages._

_Note: It's okay for more than one candidate to have the same party (such as Independent)_

### Challenge/Fontsize/Etc. ###
http://votebox.googlecode.com/files/Preptool%20-%20Preferences.PNG

To generate a ballot in the challenge-commit model - that is, to include a cast/challenge button pair page at the end of the ballot - use the menu option under preferences named "Commit-Challenge".  The base font size and the number of races to include on each review screen may be configured with the remaining options in the preferences menu.

_Note: These settings do not persist across invocations and are not stored in .bal files.  As such, they must be set each time you wish to export a ballot._