import { Component } from '@angular/core';
import { HeaderComponent } from "../header/header.component";
import { Router } from '@angular/router';
import { RoundOutcome, Card } from '../deck.model';
import { GameService } from '../game.service';

@Component({
  selector: 'app-game-over',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './game-over.component.html',
  styleUrl: './game-over.component.css'
})
export class GameOverComponent {
  roundOutcome = new RoundOutcome('',0,0,new Card(0,''),new Card(0,''),0,0,0,0,false);
  estimatedTime: string = '';
  constructor(private gameService: GameService, private router:Router) {}

  ngOnInit() {
    this.gameService.roundOutcome.subscribe((outcome: RoundOutcome)=>{
      this.roundOutcome = outcome;
      this.estimatedTime = this.gameService.getEstimatedTime(this.roundOutcome.roundNumber,this.roundOutcome.warCount,this.roundOutcome.playerShuffleCount,this.roundOutcome.computerShuffleCount);
    });

    
  }

  start() {
    this.gameService.startGame();
    this.router.navigate(['/game']);
  }

  simulation() {
    this.gameService.runSimulation();
  }

}
