import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Card } from '../deck/card.model';
import { GameService } from '../game.service';
import { RoundOutcome } from '../round-outcome.model';

@Component({
  selector: 'app-game-over',
  templateUrl: './game-over.component.html',
  styleUrls: ['./game-over.component.css']
})
export class GameOverComponent implements OnInit {
  roundOutcome = new RoundOutcome('',0,0,new Card(0,''),new Card(0,''),0,0,0,0,false);
  constructor(private gameService: GameService, private router:Router) {}

  ngOnInit() {
    this.gameService.roundOutcome.subscribe((outcome: RoundOutcome)=>{
      this.roundOutcome = outcome;
    });
  }

  start() {
    this.gameService.startGame();
    this.router.navigate(['/play-game']);
  }

  simulation() {
    this.gameService.runSimulation();
  }

}
