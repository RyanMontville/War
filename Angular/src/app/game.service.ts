import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { Deck, Card, RoundOutcome } from "./deck.model";

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

    //war
    private warCount: number = 0;
    private warTime:boolean = false;
    public inWar = new BehaviorSubject<boolean>(this.warTime);

    //Round Outcome
    private message: string = '';
    private roundNumber: number = 0;
    private playerShuffleCount: number = 0;
    private computerShuffleCount: number = 0;
    private isGameOver: boolean = true;
    private ROUND_OUTCOME_INITIAL_STATE = new RoundOutcome('Draw a card.', 26, 26, new Card(0, ''), new Card(0, ''), 1, 0, 0, 0, true);
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
        this.warTime = false;
        this.inWar.next(this.warTime);
        this.roundOutcome.next(this.ROUND_OUTCOME_INITIAL_STATE);
        this.deck.generateDeck();
        while (this.deck.getDeckSize() > 0) {
            this.playerHand.addCardToDeck(this.deck.drawCard());
            this.ComputerHand.addCardToDeck(this.deck.drawCard());
        }
    }

    runSimulation() {
        this.startGame();
        while(!this.isGameOver) {
            this.drawCard();
        }
    }

    drawCard() {
        if(this.isGameOver) {
            this.startGame();
        }
        if(!this.warTime){
            this.roundNumber++;
        }
        //draw cards and "place them on the table"
        let playerCard = this.playerHand.drawCard();
        let computerCard = this.ComputerHand.drawCard();
        this.cardsOnTable.addMultipleCardsToDeck([playerCard, computerCard]);
        //compare cards and give to the round winner, or start a war
        let result = this.compareCards(playerCard, computerCard);
        if(this.warTime){
            this.warCount++;
            this.war();
            this.warTime = false;
            this.inWar.next(this.warTime);
        }
        if (result === 'player') {
            this.playerDiscard.addMultipleCardsToDeck(this.cardsOnTable.returnDeck());
        } else if (result === 'computer') {
            this.computerDiscard.addMultipleCardsToDeck(this.cardsOnTable.returnDeck());
        } else {
            if (!this.checkIfGameOver('War, but')) {
                this.warTime = true;
                this.inWar.next(this.warTime);
                this.message = 'War! Draw a card.';
            }
        }
        //check if game has ended
        let isOver = this.checkIfGameOver('End of round, ');
        //shuffle decks if needed
        if (!isOver) {
            this.checkIfNeedForShuffle();
        }
        this.roundOutcome.next(new RoundOutcome(this.message, this.getCardCount('player'), this.getCardCount('computer'), playerCard, computerCard, this.roundNumber, this.playerShuffleCount, this.computerShuffleCount, this.warCount, this.isGameOver));
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
            this.message = `${playerCard.toString()} is greater than ${computerCard.toString()}. ${this.warTime ? 'You won this war!' : 'You get the cards.'}`
            return 'player';
        } else if (computerCard.rank > playerCard.rank) {
            this.message = `${computerCard.toString()} is greater than ${playerCard.toString()}. ${this.warTime ? 'The computer won this war!' : 'Computer gets the cards.'}`
            return 'computer';
        } else {
            return 'war';
        }
    }

    war() {
        this.checkIfNeedForShuffle();
        this.cardsOnTable.addMultipleCardsToDeck(this.playerHand.drawForWar());
        this.cardsOnTable.addMultipleCardsToDeck(this.ComputerHand.drawForWar());
        this.checkIfGameOver('Both players put down cards for war');
        this.checkIfNeedForShuffle();
        //let computer = this.ComputerHand.drawCard();
        //let player = this.playerHand.drawCard();
        //this.cardsOnTable.addMultipleCardsToDeck([computer,player]);
        //this.compareCards(player,computer);
    }

    checkIfGameOver(reason: string): boolean {
        let computer = this.getCardCount('computer');
        let player = this.getCardCount('player');
        if(computer===0){
            this.message = `${reason} the computer ran out of cards.`
            this.playerHand.addMultipleCardsToDeck(this.cardsOnTable.returnDeck());
        } else if(player===0){
            this.message = `${reason} you ran out of cards.`
            this.ComputerHand.addMultipleCardsToDeck(this.cardsOnTable.returnDeck());
        }
        if (computer === 0 || player === 0) {
            this.isGameOver = true;
            this.router.navigate(['/game-over']);
        }
        return false;
    }

    checkIfNeedForShuffle() {
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
    
    //use for debugging
    checkTotalCardCount() {
        let total = this.getCardCount('player') + this.getCardCount('computer') + this.cardsOnTable.getDeckSize();
            let playerTotal = this.getCardCount('player');
            let computerTotal = this.getCardCount('computer');

            let allPlayerCards: Deck = new Deck();
            allPlayerCards.addMultipleCardsToDeck(this.playerHand.getDeck());
            allPlayerCards.addMultipleCardsToDeck(this.playerDiscard.getDeck());
            let playerArray = allPlayerCards.getDeck();
            let playerStr = JSON.stringify(playerArray);

            let allComputerCards: Deck = new Deck();
            allComputerCards.addMultipleCardsToDeck(this.ComputerHand.getDeck());
            allComputerCards.addMultipleCardsToDeck(this.computerDiscard.getDeck());
            let computerArray = allComputerCards.getDeck();
            let computerStr = JSON.stringify(computerArray);
            let str = '52 cards in the deck'
            if(total != 52){
                str = playerStr + computerStr;
            }
            return str;
    }

    getEstimatedTime(roundNum: number,warNum: number,ComputerNum: number,PlayerNum: number): string {
        let rounds: number = roundNum * 5;
        let wars = warNum * 10;
        let playerShuffles = PlayerNum * 60;
        let computerShuffles = ComputerNum * 60;
        let totalSeconds = rounds + wars + playerShuffles + computerShuffles;
        let totalMinutes = Math.round(totalSeconds/60);
        return `If it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for ${totalMinutes} minutes.`;
    }
}