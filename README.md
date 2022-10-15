# War


## V 0.1 
-Initial code upload. Created the classes for Card and Deck.

## V 0.2
-Trying to figure out which classes should handle the different functions of the game like splitting the deck into 2 for the user and computer.
<br>-Created the Game class, moved some of the methods around, created a user and a computer hand within the game method.

## V 0.3
-Still figuring out which classes should handle different parts of the game.
<br>-Created methods to draw 1 card and to take the cards after a player wins the turn.
<br>-Coded some test code in the Main method to have the program play the game on its own, but since war can be super long, I limited it to 500 turns
or else it seems like it goes into an almost infinite loop. When I ran the test without the limit, I let the program run for 30 seconds, the number 
of cards in each hand didn't change much, and my laptop's fan started revving up.
<br>-Still need to code a method to draw extra cards when a war happens.
<br>-I want to add user input to draw a card or give up before the game ends. I also would like to get it working in a webpage to demo on my website.

## V 0.4
-Added user input, asks user if they wan to draw a card or give up and end the game.
<br>-Added methods to remove cards from players deck, add it to a deck to serve as the cards played on the table.
<br>-Added method to check to see who played the higher cards and takes all cards played from play deck, adds to winners discard deck
<br>-When a player runs out of cards to play, it takes the cards from their discard pile, adds them back to their deck, and shuffles their deck.
<br>-Still need to code the methods to handle when a war takes place.

## V 1.0
The program is a mostly fully functional game of war.
-Added the method to call when a war takes place.
-Cleaned up what is printed to the console.
-Things to still add: web interface, mode were the computer plays out the entire game without user input adn reports how many round it took.
