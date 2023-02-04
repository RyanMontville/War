import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeScreenComponent } from './welcome-screen/welcome-screen.component';
import { PlayGameComponent } from './play-game/play-game.component';
import { GameOverComponent } from './game-over/game-over.component';
import { GameService } from './game.service';

@NgModule({
  declarations: [
    AppComponent,
    WelcomeScreenComponent,
    PlayGameComponent,
    GameOverComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
