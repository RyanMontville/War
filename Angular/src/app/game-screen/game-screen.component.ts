import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from "../header/header.component";
import { RoundOutcome, Card } from '../deck.model';
import { GameService } from '../game.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-game-screen',
  standalone: true,
  imports: [HeaderComponent, CommonModule],
  templateUrl: './game-screen.component.html',
  styleUrl: './game-screen.component.css'
})
export class GameScreenComponent implements OnInit {
  roundOutcome: RoundOutcome = new RoundOutcome('', 0, 0, new Card(0, ''), new Card(0, ''), 0, 0, 0, 0, false);
  inWar: boolean = false;
  cardsDrawnForWar: number = 0;
  roundOfWar = 0;

  constructor(private gameService: GameService) { }

  ngOnInit() {
    this.gameService.roundOutcome.subscribe((outcome: RoundOutcome) => {
      this.roundOutcome = outcome;
    });
    this.gameService.inWar.subscribe((inWar: boolean) => {
      this.inWar = inWar;
    })
  }

  draw() {
    this.gameService.drawCard();
    this.cardsDrawnForWar = 0;
  }

  checkSuit(suit: string): boolean {
    if (suit === '♥' || suit === '♦') {
      return true;
    }
    return false;
  }

  drawForWar() {
    if (this.cardsDrawnForWar === 4) {
      this.cardsDrawnForWar = 0;
    }
    if (this.cardsDrawnForWar === 3) {
      this.gameService.drawCard();
      this.cardsDrawnForWar++;
    } else {
      this.cardsDrawnForWar++;
    }
  }
}
