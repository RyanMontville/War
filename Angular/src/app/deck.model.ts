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

    returnDeck(): Card[] {
        let deckToReturn = this.deck;
        this.clearDeck();
        return deckToReturn;
    }

    getDeck(): Card[] {
        return this.deck;
    }

    drawForWar(): Card[] {
        let cards: Card[] = [];
        if(this.deck.length>=3){
            cards.push(this.drawCard());
            cards.push(this.drawCard());
            cards.push(this.drawCard());
        } else if(this.deck.length===2){
            cards.push(this.drawCard());
            cards.push(this.drawCard());
        } else {
            cards.push(this.drawCard());
        }
        return cards;
    }
}

export class Card {
    public rank: number;
    public suit: string;

    constructor( rank: number,suit: string) {
        this.rank = rank;
        this.suit = suit;
    }

    getRank(): string {
        if(this.rank===14){
            return 'A';
        } else if(this.rank===11) {
            return 'J';
        } else if(this.rank===12) {
            return 'Q';
        } else if(this.rank===13) {
            return 'K';
        } else {
            return this.rank.toString();
        }
    }

    toString(): string {
        return this.getRank() + this.suit;
    }
}

export class RoundOutcome {

    constructor(public message: string, public playHandCount: number, public computerHandCount: number, 
                public playerCard: Card, public computerCard: Card, public roundNumber: number, 
                public playerShuffleCount: number, public computerShuffleCount: number,
                public warCount: number, public isGameOver: boolean) { }
}