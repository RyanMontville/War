import { Card } from "./card.model";

export class Deck {
    private deck: Card[];

    constructor() {
        this.deck = [];
    }

    generateDeck() {
        let suits: string[] = ['♥','♠','♣','♦'];
        for(let suit of suits){
            for(let i=2;i<15;i++){
                let card: Card = new Card(i,suit);
                this.deck.push(card);
            }
        }
        this.shuffleDeck();
    }

    shuffleDeck() {
        for (var i = this.deck.length - 1; i > 0; i--) {
            var j = Math.floor(Math.random() * (i + 1));
            var temp = this.deck[i];
            this.deck[i] = this.deck[j];
            this.deck[j] = temp;
        }
    }

    drawCard(): Card {
        let card: Card = this.deck[this.deck.length-1];
        this.deck.pop();
        return card;
    }

    addCardToDeck(card: Card) {
        this.deck.push(card);
    }

    addMultipleCardsToDeck(cards: Card[]) {
        for(let card of cards) {
            this.deck.push(card);
        }
    }

    getDeckSize(): number {
        return this.deck.length;
    }

    clearDeck() {
        this.deck = [];
    }
}