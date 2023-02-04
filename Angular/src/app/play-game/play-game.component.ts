import { Component, OnInit } from '@angular/core';
import { Card } from '../deck/card.model';
import { Deck } from '../deck/deck.model';
import { GameService } from '../game.service';

@Component({
  selector: 'app-play-game',
  templateUrl: './play-game.component.html',
  styleUrls: ['./play-game.component.css']
})
export class PlayGameComponent implements OnInit {

  constructor(private gameService: GameService) {}

  ngOnInit() {

  }

  draw() {
    this.gameService.drawCard();
  }
}
