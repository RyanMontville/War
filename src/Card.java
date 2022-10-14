public class Card {
    private String suit;
    private int rank;
    public Card(int rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public String getSuit() { return this.suit; }
    public int getRank() { return this.rank; }
    public String getCard() {
        String card;
        if (rank == 0) {
            card = "Ace of " + this.suit;
        } else if (rank == 11) {
            card = "Jack of " + this.suit;
        } else if (rank == 12) {
            card = "Queen of" + this.suit;
        } else if (rank == 13) {
            card = "King of" + this.suit;
        } else {
            card = this.rank + " of " + this.suit;
        }
        return card;
    }
}
