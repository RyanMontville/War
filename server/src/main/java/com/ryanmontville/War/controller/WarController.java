package com.ryanmontville.War.controller;

import com.ryanmontville.War.model.Game;
import com.ryanmontville.War.model.Output;
import com.ryanmontville.War.model.PlayingCard;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class WarController {

    Game game;

    @RequestMapping(path = "/startGame/{gameType}", method = RequestMethod.GET)
    public Output startGame(@PathVariable char gameType) {
        String message = "";
        String whoWon = "";
        if(gameType == 'p') {
            game = new Game();
            game.giveCardsToPlayers();
            message = "The game has started.";
        } else {
            game = new Game();
            game.giveCardsToPlayers();

            while (game.getComputerCardCount() > 0 && game.getUserCardCount() > 0) {
                PlayingCard cardUserDrew = game.userDrawsCard();
                PlayingCard cardComputerDrew = game.computerDrawsCard();
                game.compareCards(cardComputerDrew, cardUserDrew, false);
            }
            if(game.getUserCardCount()>0 && game.getComputerCardCount()==0){
                whoWon = "player ";
            } else if(game.getComputerCardCount()>0 && game.getUserCardCount()==0) {
                whoWon = "Computer ";
            } else {
                whoWon = "something went wrong.";
            }
            message = "Game over after " + game.getRoundCount() + " rounds. Winner: " + whoWon + ". If you were playing this game IRL and it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for "
                    + game.getTotalTime() + " minutes.";

            game.setGameOver(true);
        }
        Output output = new Output(message,game.getUserCardCount(),game.getComputerCardCount(),"0",' ', "0",
                ' ', game.getRoundCount(), game.getUserDeck().getDeckShuffleCount(), game.getComputerDeck().getDeckShuffleCount(),
                game.getWarCount(), game.isGameOver());
        return output;
    }

    @RequestMapping(path = "/getUserHand", method = RequestMethod.GET)
    public String getUserHand() {
        return "You have " + game.getUserCardCount() + " cards";
    }

    @RequestMapping(path = "/getComputerHand", method = RequestMethod.GET)
    public String getComputerHand() {return "The computer has " + game.getComputerCardCount() + " cards."; }

    @RequestMapping(path = "/draw", method = RequestMethod.GET)
    public Output  drawCard() {
        String outcome;
        PlayingCard userCard;
        PlayingCard computerCard;
        String playerRank = "0";
        char playerSuit = 'O';
        String computerRank = "0";
        char computerSuit = 'O';
        if(game.getUserCardCount()==0){
            outcome = "Game Over. You Lose. If it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for "
                    + game.getTotalTime() + " minutes.";
            game.setGameOver(true);
        } else if(game.getComputerCardCount()==0){
            outcome = "Game Over. You Win! If it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for "
                    + game.getTotalTime() + " minutes.";
            game.setGameOver(true);
        } else {
            if(!game.isGameOver()){
                userCard = game.userDrawsCard();
                computerCard = game.computerDrawsCard();
                outcome = game.compareCards(computerCard,userCard,true);
                playerSuit = userCard.getSuit();
                playerRank = userCard.getPlayingCard();
                computerSuit = computerCard.getSuit();
                computerRank = computerCard.getPlayingCard();
            } else {
                outcome = "Game Over";
            }

        }

        Output output = new Output(outcome,game.getUserCardCount(),game.getComputerCardCount(),playerRank,playerSuit, computerRank,
                computerSuit, game.getRoundCount(), game.getUserDeck().getDeckShuffleCount(), game.getComputerDeck().getDeckShuffleCount(),
                game.getWarCount(), game.isGameOver());

        return output;
    }

    @RequestMapping(path = "/giveUp", method = RequestMethod.GET)
    public Output giveUp() {
        String outcome;
        game.setGameOver(true);
        if(game.getComputerCardCount()==0){
            outcome = "You Win! If you were playing this game IRL and it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for " + game.getTotalTime() + " minutes.";
        } else if (game.getUserCardCount()==0){
            outcome = "You Lose. If you were playing this game IRL and it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for " + game.getTotalTime() + " minutes.";
        } else {
            outcome = "You gave up after " + game.getRoundCount() + " rounds. If you were playing this game IRL and it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for "
                    + game.getTotalTime() + " minutes.";
        }

        String playerRank = "0";
        char playerSuit = 'O';
        String computerRank = "0";
        char computerSuit = 'O';

        Output output = new Output(outcome,game.getUserCardCount(),game.getComputerCardCount(),playerRank,playerSuit, computerRank,
                computerSuit, game.getRoundCount(), game.getUserDeck().getDeckShuffleCount(), game.getComputerDeck().getDeckShuffleCount(),
                game.getWarCount(), game.isGameOver());

        return output;
    }

}
