package com.ryanmontville.War.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<PlayingCard> deckOfPlayingCards = new ArrayList<>();
    private List<PlayingCard> handOne = new ArrayList<>();
    private List<PlayingCard> handTwo = new ArrayList<>();
    private int deckShuffleCount;

    public Deck(List<PlayingCard> cards) {
        this.deckOfPlayingCards = cards;
        this.deckShuffleCount = 0;
    }

    public void shuffleDeck() {
        Collections.shuffle(deckOfPlayingCards);
        this.deckShuffleCount++;
    }

    public int getDeckShuffleCount() { return deckShuffleCount; }

    public int getPlayingCardCount() { return this.deckOfPlayingCards.size(); }

    public List<PlayingCard> getListOfPlayingCards() { return this.deckOfPlayingCards; }

    public void splitDeck() {
        Collections.shuffle(deckOfPlayingCards);
        for (int i=0;i<this.deckOfPlayingCards.size();i+=2){
            PlayingCard card = this.deckOfPlayingCards.get(i);
            this.handOne.add(card);
        }
        for (int i=1;i<this.deckOfPlayingCards.size();i+=2){
            PlayingCard card = this.deckOfPlayingCards.get(i);
            this.handTwo.add(card);
        }
        this.deckOfPlayingCards.removeAll(this.deckOfPlayingCards);
    }

    public List<PlayingCard> getHandOne() {
        return handOne;
    }

    public List<PlayingCard> getHandTwo() {
        return handTwo;
    }

    public PlayingCard drawOneCard() {
        PlayingCard drawnCard = this.deckOfPlayingCards.get(0);
        this.deckOfPlayingCards.remove(drawnCard);
        return drawnCard;
    }

    public List<PlayingCard> drawCardsForWar(String player) {
        List<PlayingCard> cardsForWar = new ArrayList<>();
        if (deckOfPlayingCards.size() < 4) {
            for (int i=0;i<deckOfPlayingCards.size()-1;i++){
                PlayingCard card = this.deckOfPlayingCards.get(0);
                cardsForWar.add(card);
                this.deckOfPlayingCards.remove(card);
            }
        } else {
            for (int i=0;i<3;i++) {
                PlayingCard card = this.deckOfPlayingCards.get(0);
                cardsForWar.add(card);
                this.deckOfPlayingCards.remove(card);
            }
        }
        return cardsForWar;
    }

    public void addCardToDeck(PlayingCard card) {
        this.deckOfPlayingCards.add(card);
    }
    public void addMultipleCardsToDeck(List<PlayingCard> cards) { this.deckOfPlayingCards.addAll(cards); }

}
