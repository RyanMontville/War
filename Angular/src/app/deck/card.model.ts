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