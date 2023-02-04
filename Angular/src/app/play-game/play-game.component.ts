import { Component, OnInit } from '@angular/core';
import { Card } from '../deck/card.model';
import { Deck } from '../deck/deck.model';
import { GameService } from '../game.service';
import { RoundOutcome } from '../round-outcome.model';

@Component({
  selector: 'app-play-game',
  templateUrl: './play-game.component.html',
  styleUrls: ['./play-game.component.css']
})
export class PlayGameComponent implements OnInit {
  roundOutcome: RoundOutcome = new RoundOutcome('',0,0,new Card(0,''),new Card(0,''),0,0,0,0,false);

  constructor(private gameService: GameService) {}

  ngOnInit() {
    this.gameService.roundOutcome.subscribe((outcome: RoundOutcome)=>{
      this.roundOutcome = outcome;
    })
  }

  draw() {
    this.gameService.drawCard();
  }
}
