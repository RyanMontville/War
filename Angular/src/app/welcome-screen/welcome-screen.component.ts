import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GameService } from '../game.service';

@Component({
  selector: 'app-welcome-screen',
  templateUrl: './welcome-screen.component.html',
  styleUrls: ['./welcome-screen.component.css']
})
export class WelcomeScreenComponent {
  constructor(private gameService: GameService, private router: Router) {}

  start() {
    this.gameService.startGame();
    this.router.navigate(['/play-game']);
  }
  
  simulation() {
    this.gameService.runSimulation();
  }
}
