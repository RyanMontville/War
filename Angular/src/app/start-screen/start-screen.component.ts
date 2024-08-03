import { Component } from '@angular/core';
import { HeaderComponent } from "../header/header.component";
import { Router } from '@angular/router';
import { GameService } from '../game.service';

@Component({
  selector: 'app-start-screen',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './start-screen.component.html',
  styleUrl: './start-screen.component.css'
})
export class StartScreenComponent {

  constructor(
    private gameService: GameService, 
    private router: Router) {}

  start() {
    this.gameService.startGame();
    this.router.navigate(['/game']);
  }
  
  simulation() {
    this.gameService.runSimulation();
  }
}
