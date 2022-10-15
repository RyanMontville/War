import java.util.ArrayList;
import java.util.List;

public class Game {
    private Deck startingDeck;
    private Deck userDeck;
    private Deck computerDeck;
    private Deck cardsCurrentlyPlayed;
    private Deck userDiscard;
    private Deck computerDiscard;

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
        if(userDeck.getListOfCards().size() == 0) {
            userDeck.addMultipleCardsToDeck(userDiscard.getListOfCards());
            userDiscard.getListOfCards().removeAll(userDiscard.getListOfCards());
            userDeck.shuffleDeck();
        }
        Card card = userDeck.drawOneCard();
        System.out.println("user drew a " + card.getCard());
        this.cardsCurrentlyPlayed.addCardToDeck(card);
        return card;
    }

    public Card computerDrawsCard() {
        if(computerDeck.getListOfCards().size() == 0) {
            computerDeck.addMultipleCardsToDeck(computerDiscard.getListOfCards());
            computerDiscard.getListOfCards().removeAll(computerDiscard.getListOfCards());
            computerDeck.shuffleDeck();
        }
        Card card = computerDeck.drawOneCard();
        System.out.println("computer drew a " + card.getCard());
        this.cardsCurrentlyPlayed.addCardToDeck(card);
        return card;
    }

    public void compareCards(Card cardComputerDrew, Card cardUserDrew) {
        int compRank = cardComputerDrew.getRank();
        int userRank = cardUserDrew.getRank();
        if (compRank > userRank) {
            System.out.println(cardComputerDrew.getCard() + " is greater than " + cardUserDrew.getCard() + ". Computer gets both cards.");
            computerDiscard.addMultipleCardsToDeck(cardsCurrentlyPlayed.getListOfCards());
            cardsCurrentlyPlayed.getListOfCards().removeAll(cardsCurrentlyPlayed.getListOfCards());

        } else if (userRank > compRank) {
            System.out.println(cardUserDrew.getCard() + " is greater that " + cardComputerDrew.getCard() + ". User gets both cards.");
            userDiscard.addMultipleCardsToDeck(cardsCurrentlyPlayed.getListOfCards());
            cardsCurrentlyPlayed.getListOfCards().removeAll(cardsCurrentlyPlayed.getListOfCards());

        } else {
            System.out.println(cardComputerDrew.getCard() + " = " + cardUserDrew.getCard() + ". WAR!******************************");
        }
    }

}
