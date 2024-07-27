<a id='top'></a>

[Version Notes](#notes) | [Screenshots](#screenshots)

# War
One of the first side projects I started working on during the Tech Elevator bootcamp was a CLI of the card game War. The application evolved as we were taught the different parts of full stack development. I knew I wanted to make a web interface for the game, so I created an API. After the bootcamp concluded, I wanted to learn React.js, so I used React to build the front end of the app. 

I have created a second version of the app in Angular that doesn't rely on the API or Java, so that I could deploy it to GitHub pages. The Angular version of the app handles all of the game logic in Typescript.

The game is now live on GitHub pages. [Click here](https://ryanmontville.github.io/War/) to load the app.

<a id='notes'></a>

## Version Notes 
#### V 0.1 
* Initial code upload. Created the classes for Card and Deck.

#### V 0.2
* Trying to figure out which classes should handle the different functions of the game like splitting the deck into 2 for the user and computer.
* Created the Game class, moved some of the methods around, created a user and a computer hand within the game method.

#### V 0.3
* Still figuring out which classes should handle different parts of the game.
* Created methods to draw 1 card and to take the cards after a player wins the turn.
* Coded some test code in the Main method to have the program play the game on its own, but since war can be super long, I limited it to 500 turns
or else it seems like it goes into an almost infinite loop. When I ran the test without the limit, I let the program run for 30 seconds, the number 
of cards in each hand didn't change much, and my laptop's fan started revving up.
* Things to do:
    * code a method to draw extra cards when a war happens.
    * I want to add user input to draw a card or give up before the game ends. 
    * I also would like to get it working in a webpage to demo on my website.

#### V 0.4
* Added user input, asks user if they wan to draw a card or give up and end the game.
* Added methods to remove cards from players deck, add it to a deck to serve as the cards played on the table.
* Added method to check to see who played the higher cards and takes all cards played from play deck, adds to winners discard deck
* When a player runs out of cards to play, it takes the cards from their discard pile, adds them back to their deck, and shuffles their deck.
* Still need to code the methods to handle when a war takes place.

#### V 1.0
The program is a mostly fully functional game of war.
* Added the method to call when a war takes place.
* Cleaned up what is printed to the console.
* Current bug were the total number of cards goes over 52 during a war. Some list should be getting smaller, but is not. Trying to figure it out.
* Things to still add: 
    * web interface
    * mode were the computer plays out the entire game without user input adn reports how many round it took.

#### V 1.0.1
* I have fixed the bug were there would be over 52 cards total after a war, I was calling 2 different methods to draw cards, so it was adding twice as many cards as it should have. 
* I have also fixed cases were a player would not have enough cards for a war, which would break the game when it tried drawing a card from a deck of 0.

#### V 1.2
* I added an option to have the computer run the entire game and report how long it would have taken to play in real life.

#### V 1.3
* I have turned the CLI into an API so that I can have it run in a browser.
* I have created a mock-up of what the game might look like in a browser. There is no functionality to anything yet.

#### V 2.0
* I created the front end using React.js. The game is now playable in the browser.

#### V 3.0
* I want to have a live demo of the game, so I rebuilt the app to run entirely on the front end. There are now 2 versions of the game within this repo.
    * The first app runs on React.js and uses the API and Java backend.
    * The second app runs on Angular and everything is done within the Angular app using Typescript for the game logic. It does not use the API or Java backend.

[Back to Top](#top)

<a id='screenshots'></a>

## Screenshots
<div>
    <img src="https://raw.githubusercontent.com/RyanMontville/War/main/screenshots/homepage.png" alt="homepage" title="Homepage" style="width: 20%; display: inline-block;"></img>
<img src="https://raw.githubusercontent.com/RyanMontville/War/main/Angular/Screenshots/game-play.png" alt="game" title="Playing the game" style="width: 20%; display: inline-block;"></img>
<img src="https://raw.githubusercontent.com/RyanMontville/War/main/Angular/Screenshots/war-screen.png" alt="war" title="Screen when a war occurs" style="width: 20%; display: inline-block;"></img>
<img src="https://raw.githubusercontent.com/RyanMontville/War/main/screenshots/game-over.png" alt="gave-over" title="Game Over Screen" style="width: 20%; display: inline-block;"></img>
</div>

[Back to Top](#top)
