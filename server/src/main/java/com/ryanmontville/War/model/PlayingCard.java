package com.ryanmontville.War.model;

public class PlayingCard {
    private char suit;
    private int rank;

    public PlayingCard(int rank, char suit){
        this.rank = rank;
        this.suit = suit;
    }

    public char getSuit() { return this.suit; }
    public int getRank() { return this.rank; }
    public String getPlayingCard() {
        String card = "";
        if (rank == 1) {
            card = "A";
        } else if (rank == 11) {
            card = "J";
        } else if (rank == 12) {
            card = "Q";
        } else if (rank == 13) {
            card = "K";
        } else {
            card = card + this.rank;
        }
        return card;
    }
}
