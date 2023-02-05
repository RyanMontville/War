import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GameService } from './game.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showMenu: boolean = false;

  constructor(private router: Router, private gameService: GameService) {}

  goHome() {
    this.showMenu = false;
    this.router.navigate(['/'])
  }

  goToGame() {
    this.showMenu = false;
    this.router.navigate(['/play-game'])
  }

  runSimulation() {
    this.showMenu = false;
    this.gameService.runSimulation();
  }
}
