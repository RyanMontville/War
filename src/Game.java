import java.util.ArrayList;
import java.util.List;

public class Game {
    private Deck startingDeck;
    private Deck userHand;
    private Deck computerDeck;

    public Game() {
        this.startingDeck = new Deck(createNewDeck());
    }

    public Deck getStartingDeck() {
        return startingDeck;
    }

    public Deck getComputerDeck() {
        return computerDeck;
    }

    public Deck getUserHand() {
        return userHand;
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
        this.userHand = new Deck(startingDeck.getHandOne());
        this.computerDeck = new Deck(startingDeck.getHandTwo());

    }

}
