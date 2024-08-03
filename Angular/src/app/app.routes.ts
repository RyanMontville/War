import { Routes } from '@angular/router';
import { StartScreenComponent } from './start-screen/start-screen.component';
import { GameScreenComponent } from './game-screen/game-screen.component';
import { GameOverComponent } from './game-over/game-over.component';
import { NotFoundComponent } from './not-found/not-found.component';

export const routes: Routes = [
    {path: '', component: StartScreenComponent},
    {path: 'game', component: GameScreenComponent},
    {path: 'game-over', component: GameOverComponent},
    {path: '**', component: NotFoundComponent}
];
