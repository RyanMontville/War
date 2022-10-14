import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Deck testDeck = new Deck();
        testDeck.shuffleDeck();
        List<Card> deck = testDeck.getDeckOfCards();
        for (Card card : deck) {
            System.out.println(card.getCard());
        }
    }
}
