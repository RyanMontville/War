import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { Deck } from "./deck/deck.model";
import { Card } from "./deck/card.model";
import { RoundOutcome } from "./round-outcome.model";

@Injectable()
export class GameService {
    private deck: Deck = new Deck;
    private playerHand: Deck = new Deck;
    private ComputerHand: Deck = new Deck;
    private playerDiscard: Deck = new Deck;
    private computerDiscard: Deck = new Deck;
    private cardsOnTable: Deck = new Deck;
    private ROUND_OUTCOME_INITIAL_STATE = new RoundOutcome('',0,0,new Card(0,''),new Card(0,''),0,0,0,0,false);
    public roundOutcome = new BehaviorSubject<RoundOutcome>(this.ROUND_OUTCOME_INITIAL_STATE);


    startGame() {
        this.deck.clearDeck();
        this.ComputerHand.clearDeck();
        this.playerHand.clearDeck();
        this.playerDiscard.clearDeck();
        this.computerDiscard.clearDeck();
        this.cardsOnTable.clearDeck();
        this.deck.generateDeck();
        while(this.deck.getDeckSize()>0){
            this.playerHand.addCardToDeck(this.deck.drawCard());
            this.ComputerHand.addCardToDeck(this.deck.drawCard());
        }
    }

    drawCard() {
        let playerCard = this.playerHand.drawCard();
        let computerCard = this.ComputerHand.drawCard();
        this.cardsOnTable.addMultipleCardsToDeck([playerCard,computerCard]);
        alert(playerCard.getRank() + "," + computerCard.getRank() + "," + this.cardsOnTable.getDeckSize());
    }
}