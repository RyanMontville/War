import { Card } from "./deck/card.model";

export class RoundOutcome {

    constructor(public message: string, public playHandCount: number, public computerHandCount: number, 
                public playerCard: Card, public computerCard: Card, public roundNumber: number, 
                public playerShuffleCount: number, public computerShuffleCount: number,
                public warCount: number, public isGameOver: boolean) { }
}