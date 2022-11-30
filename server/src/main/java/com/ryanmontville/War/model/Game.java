package com.ryanmontville.War.model;

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
    private int userCardCount;
    private int computerCardCount;
    private int roundCount = 0;
    private int warCount = 0;
    private int shuffledCount = 0;
    private int totalTime;
    private boolean isGameOver;

    public Game() {
        this.startingDeck = new Deck(createNewDeck());
        this.cardsCurrentlyPlayed = new Deck(new ArrayList<>());
        this.userDiscard = new Deck(new ArrayList<>());
        this.computerDiscard = new Deck(new ArrayList<>());
        this.isGameOver = false;
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

    public int getUserCardCount() {
        return this.userDeck.getPlayingCardCount() + this.getUserDiscard().getPlayingCardCount();
    }

    public int getComputerCardCount() {
        return this.getComputerDeck().getPlayingCardCount() + this.getComputerDiscard().getPlayingCardCount();
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

    public int getRoundCount() { return roundCount; }

    public int getWarCount() { return warCount; }

    public int getTotalTime() {
        return (((this.warCount*10) + (this.roundCount*5) + (this.shuffledCount*60))/60);
    }

    public boolean isGameOver() { return isGameOver; }

    public void setGameOver(boolean isOver) { this.isGameOver = isOver; }

    public List<PlayingCard> createNewDeck() {
        char[] suits = new char[] { '\u2660', '\u2666', '\u2663', '\u2665' };
        List<PlayingCard> newDeck = new ArrayList<>();
        for (char suit : suits) {
            for(int i=1;i<14;i++){
                newDeck.add(new PlayingCard(i,suit));
            }
        }
        return newDeck;
    }

    public void giveCardsToPlayers() {
        startingDeck.splitDeck();
        this.userDeck = new Deck(startingDeck.getHandOne());
        this.computerDeck = new Deck(startingDeck.getHandTwo());
    }

    public PlayingCard userDrawsCard() {
        this.checkIfDeckIsEmpty(false,'U');
        PlayingCard card = userDeck.drawOneCard();
        this.cardsCurrentlyPlayed.addCardToDeck(card);
        this.roundCount++;
        return card;
    }

    public PlayingCard computerDrawsCard() {
        this.checkIfDeckIsEmpty(false, 'C');
        PlayingCard card = computerDeck.drawOneCard();
        this.cardsCurrentlyPlayed.addCardToDeck(card);
        return card;
    }

    public String compareCards(PlayingCard cardComputerDrew, PlayingCard cardUserDrew,boolean userInput) {
        int compRank = cardComputerDrew.getRank();
        int userRank = cardUserDrew.getRank();
        String outcome;
        if(compRank == 1) { compRank = 14; }
        if(userRank == 1) { userRank = 14; }
        if (compRank > userRank) {
            outcome = cardComputerDrew.getPlayingCard() + " is greater than " + cardUserDrew.getPlayingCard() + ". Computer gets the cards.";
            computerDiscard.addMultipleCardsToDeck(cardsCurrentlyPlayed.getListOfPlayingCards());
            cardsCurrentlyPlayed.getListOfPlayingCards().removeAll(cardsCurrentlyPlayed.getListOfPlayingCards());

        } else if (userRank > compRank) {
            outcome = cardUserDrew.getPlayingCard() + " is greater that " + cardComputerDrew.getPlayingCard() + ". You gets the cards.";
            userDiscard.addMultipleCardsToDeck(cardsCurrentlyPlayed.getListOfPlayingCards());
            cardsCurrentlyPlayed.getListOfPlayingCards().removeAll(cardsCurrentlyPlayed.getListOfPlayingCards());

        } else {
            outcome = cardComputerDrew.getPlayingCard() + " = " + cardUserDrew.getPlayingCard() + ". WAR! Each player draws 3 cards face down, then draws.";
            this.playWar(userInput);
        }
        return outcome;
    }

    public void playWar(boolean userInput) {
        this.checkIfDeckIsEmpty(true,'C');
        this.checkIfDeckIsEmpty(true,'U');
        if(this.userDeck.getListOfPlayingCards().size() == 0 ) {
        } else if (this.computerDeck.getListOfPlayingCards().size() == 0) {
        } else {
            this.cardsCurrentlyPlayed.addMultipleCardsToDeck(computerDeck.drawCardsForWar("computer"));
            this.cardsCurrentlyPlayed.addMultipleCardsToDeck(userDeck.drawCardsForWar("user"));
            PlayingCard computerWarCard = this.computerDrawsCard();
            PlayingCard userWarCard = this.userDrawsCard();
            this.compareCards(computerWarCard,userWarCard,userInput);
        }
        this.warCount++;
    }

    public void checkIfDeckIsEmpty(boolean war, char p) {
        if( p == 'C') { //computer
            if(!war) {
                if(this.computerDeck.getListOfPlayingCards().size() == 0) {
                    computerDeck.addMultipleCardsToDeck(computerDiscard.getListOfPlayingCards());
                    computerDiscard.getListOfPlayingCards().removeAll(computerDiscard.getListOfPlayingCards());
                    computerDeck.shuffleDeck();
                } else {
                    if(this.computerDeck.getListOfPlayingCards().size() < 5) {
                        computerDeck.addMultipleCardsToDeck(computerDiscard.getListOfPlayingCards());
                        computerDiscard.getListOfPlayingCards().removeAll(computerDiscard.getListOfPlayingCards());
                        computerDeck.shuffleDeck();
                    }
                }
            }
        } else { //user
            if (!war) {
                if (this.userDeck.getListOfPlayingCards().size() == 0) {
                    userDeck.addMultipleCardsToDeck(userDiscard.getListOfPlayingCards());
                    userDiscard.getListOfPlayingCards().removeAll(userDiscard.getListOfPlayingCards());
                    userDeck.shuffleDeck();
                }
            } else {
                if (this.userDeck.getListOfPlayingCards().size() < 5) {
                    userDeck.addMultipleCardsToDeck(userDiscard.getListOfPlayingCards());
                    userDiscard.getListOfPlayingCards().removeAll(userDiscard.getListOfPlayingCards());
                    userDeck.shuffleDeck();
                }
            }
        }
    }
}
