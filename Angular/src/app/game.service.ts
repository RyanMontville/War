import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { Deck } from "./deck/deck.model";
import { Card } from "./deck/card.model";
import { RoundOutcome } from "./round-outcome.model";
import { Router } from "@angular/router";

@Injectable()
export class GameService {
    constructor(private router: Router) { }

    //decks
    private deck: Deck = new Deck;
    private playerHand: Deck = new Deck;
    private ComputerHand: Deck = new Deck;
    private playerDiscard: Deck = new Deck;
    private computerDiscard: Deck = new Deck;
    private cardsOnTable: Deck = new Deck;

    //Round Outcome
    private message: string = '';
    private roundNumber: number = 0;
    private playerShuffleCount: number = 0;
    private computerShuffleCount: number = 0;
    private warCount: number = 0;
    private isGameOver: boolean = false;
    private ROUND_OUTCOME_INITIAL_STATE = new RoundOutcome('', 26, 26, new Card(0, ''), new Card(0, ''), 1, 0, 0, 0, false);
    public roundOutcome = new BehaviorSubject<RoundOutcome>(this.ROUND_OUTCOME_INITIAL_STATE);


    startGame() {
        this.deck.clearDeck();
        this.ComputerHand.clearDeck();
        this.playerHand.clearDeck();
        this.playerDiscard.clearDeck();
        this.computerDiscard.clearDeck();
        this.cardsOnTable.clearDeck();
        this.roundNumber = 0;
        this.playerShuffleCount = 0;
        this.computerShuffleCount = 0;
        this.warCount = 0;
        this.isGameOver = false;
        this.roundOutcome.next(this.ROUND_OUTCOME_INITIAL_STATE);
        this.deck.generateDeck();
        while (this.deck.getDeckSize() > 0) {
            this.playerHand.addCardToDeck(this.deck.drawCard());
            this.ComputerHand.addCardToDeck(this.deck.drawCard());
        }
    }

    drawCard() {
        this.roundNumber++;
        //draw cards and "place them on the table"
        let playerCard = this.playerHand.drawCard();
        let computerCard = this.ComputerHand.drawCard();
        this.cardsOnTable.addMultipleCardsToDeck([playerCard, computerCard]);
        //compare cards and give to the round winner, or start a war
        let result = this.compareCards(playerCard, computerCard);
        if (result === 'player') {
            this.playerDiscard.addMultipleCardsToDeck(this.cardsOnTable.returnDeck());
        } else if (result === 'computer') {
            this.computerDiscard.addMultipleCardsToDeck(this.cardsOnTable.returnDeck());
        } else {
            this.war();
        }
        //check if game has ended
        let computer = this.getCardCount('computer');
        let player = this.getCardCount('player');
        if (computer === 0 || player === 0) {
            this.isGameOver = true;
            this.router.navigate(['/game-over']);
        }

        //shuffle decks if needed
        if (!this.isGameOver) {
            if (this.playerHand.getDeckSize() === 0) {
                this.playerHand.addMultipleCardsToDeck(this.playerDiscard.returnDeck());
                this.playerHand.shuffleDeck();
                this.playerShuffleCount++;
            }
            if (this.ComputerHand.getDeckSize() === 0) {
                this.ComputerHand.addMultipleCardsToDeck(this.computerDiscard.returnDeck());
                this.ComputerHand.shuffleDeck();
                this.computerShuffleCount++;
            }
        }
        this.roundOutcome.next(new RoundOutcome(`You drew ${playerCard.toString()}, the computer drew ${computerCard.toString()}`, this.getCardCount('player'), this.getCardCount('computer'), playerCard, computerCard, this.roundNumber, this.playerShuffleCount, this.computerShuffleCount, this.warCount, this.isGameOver));
    }

    getCardCount(whose: string): number {
        if (whose === 'player') {
            return this.playerHand.getDeckSize() + this.playerDiscard.getDeckSize();
        } else {
            return this.ComputerHand.getDeckSize() + this.computerDiscard.getDeckSize();
        }
    }

    compareCards(playerCard: Card, computerCard: Card): string {
        if (playerCard.rank > computerCard.rank) {
            return 'player';
        } else if (computerCard.rank > playerCard.rank) {
            return 'computer';
        } else {
            return 'war';
        }
    }

    war() {
        this.warCount++;
        this.cardsOnTable.clearDeck();
    }
}