import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> deckOfCards = new ArrayList<>();
    private List<Card> handOne = new ArrayList<>();
    private List<Card> handTwo = new ArrayList<>();
    private int deckShuffleCount = 0;

    public Deck(List<Card> cards) {
        this.deckOfCards = cards;

    }

    public void shuffleDeck() {
        Collections.shuffle(deckOfCards);
        this.deckShuffleCount++;
    }

    public int getDeckShuffleCount() { return deckShuffleCount; }

    public List<Card> getListOfCards() {
        return this.deckOfCards;
    }

    public void splitDeck() {
        Collections.shuffle(deckOfCards);
        for (int i=0;i<this.deckOfCards.size();i+=2){
            Card card = this.deckOfCards.get(i);
            this.handOne.add(card);
        }
        for (int i=1;i<this.deckOfCards.size();i+=2){
            Card card = this.deckOfCards.get(i);
            this.handTwo.add(card);
        }
        this.deckOfCards.removeAll(this.deckOfCards);
    }

    public List<Card> getHandOne() {
        return handOne;
    }

    public List<Card> getHandTwo() {
        return handTwo;
    }

    public Card drawOneCard() {
        Card drawnCard = this.deckOfCards.get(0);
        this.deckOfCards.remove(drawnCard);
        return drawnCard;
    }

    public List<Card> drawCardsForWar(String player) {
        List<Card> cardsForWar = new ArrayList<>();
        if (deckOfCards.size() < 4) {
            System.out.println(player + " has less than 4 cards, so the only play " + (deckOfCards.size()-1 + " cards instead of 3."));
            for (int i=0;i<deckOfCards.size()-1;i++){
                Card card = this.deckOfCards.get(0);
                cardsForWar.add(card);
                this.deckOfCards.remove(card);
                System.out.println(player + " places 1 card face down.");
            }
        } else {
            for (int i=0;i<3;i++) {
                Card card = this.deckOfCards.get(0);
                cardsForWar.add(card);
                this.deckOfCards.remove(card);
                System.out.println(player + " places 1 card face down.");
            }
        }
        return cardsForWar;
    }

    public void addCardToDeck(Card card) {
        this.deckOfCards.add(card);
    }
    public void addMultipleCardsToDeck(List<Card> cards) { this.deckOfCards.addAll(cards); }
}
