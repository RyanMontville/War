import { Component, OnInit } from '@angular/core';
import { Card } from '../deck/card.model';
import { GameService } from '../game.service';
import { RoundOutcome } from '../round-outcome.model';

@Component({
  selector: 'app-play-game',
  templateUrl: './play-game.component.html',
  styleUrls: ['./play-game.component.css']
})
export class PlayGameComponent implements OnInit {
  roundOutcome: RoundOutcome = new RoundOutcome('',0,0,new Card(0,''),new Card(0,''),0,0,0,0,false);
  inWar: boolean = false;
  cardsDrawnForWar: number = 0;
  roundOfWar = 0;

  constructor(private gameService: GameService) {}

  ngOnInit() {
    this.gameService.roundOutcome.subscribe((outcome: RoundOutcome)=>{
      this.roundOutcome = outcome;
    });
    this.gameService.inWar.subscribe((inWar: boolean)=>{
      this.inWar = inWar;
    })
  }

  draw() {
    this.gameService.drawCard();
    this.cardsDrawnForWar = 0;
  }

  checkSuit(suit: string):boolean {
    if(suit==='♥' || suit==='♦') {
      return true;
    }
    return false;
  }

  drawForWar() {
    if(this.cardsDrawnForWar===3){
      this.gameService.drawCard();
      this.cardsDrawnForWar++;
    } else {
      this.cardsDrawnForWar++;
    }
  }
}
