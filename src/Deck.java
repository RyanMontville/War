import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deckOfCards = new ArrayList<Card>();
    public Deck() {
        this.deckOfCards = createNewDeck();
    }

    public List<Card> createNewDeck() {
        List<Card> newDeck = new ArrayList<>();
        char[] suits = new char[] { '\u2660', '\u2666', '\u2663', '\u2665' };
        for (char suit : suits) {
            for(int i=1;i<14;i++){
                newDeck.add(new Card(i,suit));
            }
        }
        return newDeck;
    }
    public void shuffleDeck() {
        Collections.shuffle(deckOfCards);
    }
    public List<Card> getDeckOfCards() {
        return this.deckOfCards;
    }
}
