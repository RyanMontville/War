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
        String[] suits = new String[] {"Clubs","Spades","Hearts","Diamonds"};
        for (String suit : suits) {
            for(int i=0;i<13;i++){
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
