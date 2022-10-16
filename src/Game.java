import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Deck startingDeck;
    private Deck userDeck;
    private Deck computerDeck;
    private Deck cardsCurrentlyPlayed;
    private Deck userDiscard;
    private Deck computerDiscard;
    private int warCount = 0;

    public Game() {
        this.startingDeck = new Deck(createNewDeck());
        this.cardsCurrentlyPlayed = new Deck(new ArrayList<>());
        this.userDiscard = new Deck(new ArrayList<>());
        this.computerDiscard = new Deck(new ArrayList<>());
    }

    public Deck getStartingDeck() {
        return startingDeck;
    }

    public Deck getComputerDeck() {
        return computerDeck;
    }

    public Deck getUserDeck() {
        return userDeck;
    }

    public Deck getCardsCurrentlyPlayed() {
        return cardsCurrentlyPlayed;
    }

    public Deck getUserDiscard() {
        return userDiscard;
    }

    public Deck getComputerDiscard() {
        return computerDiscard;
    }

    public int getWarCount() { return warCount; }

    public List<Card> createNewDeck() {
        char[] suits = new char[] { '\u2660', '\u2666', '\u2663', '\u2665' };
        List<Card> newDeck = new ArrayList<>();
        for (char suit : suits) {
            for(int i=1;i<14;i++){
                newDeck.add(new Card(i,suit));
            }
        }
        return newDeck;
    }

    public void giveCardsToPlayers() {
        startingDeck.splitDeck();
        this.userDeck = new Deck(startingDeck.getHandOne());
        this.computerDeck = new Deck(startingDeck.getHandTwo());
    }

    public Card userDrawsCard() {
        this.checkIfDeckIsEmpty(false,'U');
        Card card = userDeck.drawOneCard();
        System.out.println("You drew a " + card.getCard() +". ");
        this.cardsCurrentlyPlayed.addCardToDeck(card);
        return card;
    }

    public Card computerDrawsCard() {
        this.checkIfDeckIsEmpty(false, 'C');
        Card card = computerDeck.drawOneCard();
        System.out.println("The computer drew a " + card.getCard() + ".");
        this.cardsCurrentlyPlayed.addCardToDeck(card);
        return card;
    }

    public void compareCards(Card cardComputerDrew, Card cardUserDrew) {
        int compRank = cardComputerDrew.getRank();
        int userRank = cardUserDrew.getRank();
        if(compRank == 1) { compRank = 14; }
        if(userRank == 1) { userRank = 14; }
        if (compRank > userRank) {
            System.out.println(cardComputerDrew.getCard() + " is greater than " + cardUserDrew.getCard() + ". Computer gets the cards.");
            computerDiscard.addMultipleCardsToDeck(cardsCurrentlyPlayed.getListOfCards());
            cardsCurrentlyPlayed.getListOfCards().removeAll(cardsCurrentlyPlayed.getListOfCards());

        } else if (userRank > compRank) {
            System.out.println(cardUserDrew.getCard() + " is greater that " + cardComputerDrew.getCard() + ". You gets the cards.");
            userDiscard.addMultipleCardsToDeck(cardsCurrentlyPlayed.getListOfCards());
            cardsCurrentlyPlayed.getListOfCards().removeAll(cardsCurrentlyPlayed.getListOfCards());

        } else {
            System.out.println(cardComputerDrew.getCard() + " = " + cardUserDrew.getCard() + ". WAR! Each player draws 3 cards face down, then draws.");
            this.playWar();
            warCount++;
        }
    }

    public void playWar() {
        this.checkIfDeckIsEmpty(true,'C');
        this.checkIfDeckIsEmpty(true,'U');
        if(this.userDeck.getListOfCards().size() == 0 ) {
            System.out.println("You don't have any cards left for the war.");
        } else if (this.computerDeck.getListOfCards().size() == 0) {
            System.out.println("The computer does not have any cards left for the war.");
        } else {
            this.cardsCurrentlyPlayed.addMultipleCardsToDeck(computerDeck.drawCardsForWar("computer"));
            this.cardsCurrentlyPlayed.addMultipleCardsToDeck(userDeck.drawCardsForWar("user"));
            Card computerWarCard = this.computerDrawsCard();
            Scanner scanner = new Scanner(System.in);
            System.out.print("(D)raw your card: ");
            char warResponse = scanner.next().charAt(0);
            if (warResponse != 'D') {
                if(warResponse != 'd') {
                    System.out.println("There is no escaping war! Drawing a card for you.");
                }
            }
            Card userWarCard = this.userDrawsCard();
            this.compareCards(computerWarCard,userWarCard);
        }
    }

    public void checkIfDeckIsEmpty(boolean war, char p) {
        if( p == 'C') { //computer
            if(!war) {
                if(this.computerDeck.getListOfCards().size() == 0) {
                    computerDeck.addMultipleCardsToDeck(computerDiscard.getListOfCards());
                    computerDiscard.getListOfCards().removeAll(computerDiscard.getListOfCards());
                    computerDeck.shuffleDeck();
                } else {
                    if(this.computerDeck.getListOfCards().size() < 5) {
                        computerDeck.addMultipleCardsToDeck(computerDiscard.getListOfCards());
                        computerDiscard.getListOfCards().removeAll(computerDiscard.getListOfCards());
                        computerDeck.shuffleDeck();
                    }
                }
            }
        } else { //user
            if (!war) {
                if (this.userDeck.getListOfCards().size() == 0) {
                    userDeck.addMultipleCardsToDeck(userDiscard.getListOfCards());
                    userDiscard.getListOfCards().removeAll(userDiscard.getListOfCards());
                    userDeck.shuffleDeck();
                }
            } else {
                if (this.userDeck.getListOfCards().size() < 5) {
                    userDeck.addMultipleCardsToDeck(userDiscard.getListOfCards());
                    userDiscard.getListOfCards().removeAll(userDiscard.getListOfCards());
                    userDeck.shuffleDeck();
                }
            }
        }

    }

    public void totalNumberOfCards() {
        int count = 0;
        count += this.startingDeck.getListOfCards().size();
        System.out.print("Starting deck: " + this.startingDeck.getListOfCards().size());
        count += this.cardsCurrentlyPlayed.getListOfCards().size();
        System.out.print(" Current play pile: " + this.cardsCurrentlyPlayed.getListOfCards().size());
        count += this.userDeck.getListOfCards().size();
        System.out.print(" User Deck: " + this.userDeck.getListOfCards().size());
        count += this.computerDeck.getListOfCards().size();
        System.out.print(" Computer Deck: " + this.computerDeck.getListOfCards().size());
        count += this.userDiscard.getListOfCards().size();
        System.out.print(" User Discard: " + this.userDiscard.getListOfCards().size());
        count += this.computerDiscard.getListOfCards().size();
        System.out.print(" Computer Discard: " + this.computerDiscard.getListOfCards().size());
        System.out.println(" TOTAL: " + count);
    }

}
