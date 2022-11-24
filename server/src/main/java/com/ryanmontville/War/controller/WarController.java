package com.ryanmontville.War.controller;

import com.ryanmontville.War.model.Game;
import com.ryanmontville.War.model.Output;
import com.ryanmontville.War.model.PlayingCard;
import org.springframework.web.bind.annotation.*;

@RestController
public class WarController {

    Game game;

    @RequestMapping(path = "/startGame/{gameType}", method = RequestMethod.GET)
    public String startGame(@PathVariable char gameType) {
        String message = "";
        String whoWon = "";
        if(gameType == 'p') {
            game = new Game();
            game.giveCardsToPlayers();
            message = "The game has started.";
        } else {
            game = new Game();
            game.giveCardsToPlayers();
            int computerTotal = game.getComputerDeck().getPlayingCardCount() + game.getComputerDiscard().getPlayingCardCount();
            int userTotal = game.getUserDeck().getPlayingCardCount() + game.getUserDiscard().getPlayingCardCount();

            while (computerTotal > 0 && userTotal > 0) {
                PlayingCard cardUserDrew = game.userDrawsCard();
                PlayingCard cardComputerDrew = game.computerDrawsCard();
                game.compareCards(cardComputerDrew, cardUserDrew, false);
                computerTotal = game.getComputerDeck().getPlayingCardCount() + game.getComputerDiscard().getPlayingCardCount();
                userTotal = game.getUserDeck().getPlayingCardCount() + game.getUserDiscard().getPlayingCardCount();
            }
            if(userTotal>0 && computerTotal==0){
                whoWon = "player ";
            } else if(computerTotal>0 && userTotal==0) {
                whoWon = "Computer ";
            } else {
                whoWon = "something went wrong.";
            }
            message = "Game over after " + game.getRoundCount() + " rounds. Winner: " + whoWon;
        }
        return message;
    }

    @RequestMapping(path = "/getUserHand", method = RequestMethod.GET)
    public String getUserHand() {
        return "You have " + game.getUserDeck().getPlayingCardCount();
    }

    @RequestMapping(path = "/draw", method = RequestMethod.GET)
    public Output  drawCard() {
        String outcome;
        PlayingCard userCard;
        PlayingCard computerCard;
        String userCardStr;
        String computerCardStr;
        if(game.getUserDeck().getPlayingCardCount()==0 && game.getUserDiscard().getPlayingCardCount()==0){
            outcome = "Game Over. You Lose. If it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for "
                    + game.getTotalTime() + " minutes.";
            userCardStr = "Game over";
            computerCardStr = "Game over";
        } else if(game.getComputerDeck().getPlayingCardCount()==0 && game.getComputerDiscard().getPlayingCardCount()==0){
            outcome = "Game Over. You Win! If it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for "
                    + game.getTotalTime() + " minutes.";
            userCardStr = "Game over";
            computerCardStr = "Game over";
        } else {
            userCard = game.userDrawsCard();
            computerCard = game.computerDrawsCard();
            outcome = game.compareCards(userCard,computerCard,true);
            userCardStr = "User drew a " + userCard.getPlayingCard();
            computerCardStr = "Computer drew a " + computerCard.getPlayingCard();
        }
        String userHandCount = "User hand: " + game.getUserDeck().getPlayingCardCount() + " cards";
        String  computerHandCount = "Computer hand: " + game.getComputerDeck().getPlayingCardCount() + " cards";
        String userDiscardCount = "User discard: " + game.getUserDiscard().getPlayingCardCount() + " cards";
        String computerDiscardCount = "Computer discard: " + game.getComputerDiscard().getPlayingCardCount() + " cards";
        String round = "Round: " + game.getRoundCount();
        String userShuffleCount = "User shuffled " + game.getUserDeck().getDeckShuffleCount() + " times";
        String computerShuffleCount = "Computer shuffled " + game.getComputerDeck().getDeckShuffleCount() + " times";
        String watCount = "Wars: " + game.getWarCount();
        Output output = new Output(outcome,userHandCount,computerHandCount,userDiscardCount,computerDiscardCount,userCardStr,computerCardStr,round,
                userShuffleCount,computerShuffleCount,watCount);

        return output;
    }

    @RequestMapping(path = "/giveUp/{gameType}", method = RequestMethod.GET)
    public Output giveUp(@PathVariable char gameType) {
        String outcome;
        if(gameType=='p'){
            outcome = "You gave up after " + game.getRoundCount() + " rounds. If it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for "
                    + game.getTotalTime() + " minutes.";
        } else {
            outcome = game.getRoundCount() + " rounds occured. If it takes 5 seconds per round, 10 seconds for a war, and a minute for a thorough shuffle, you would have played for "
                    + game.getTotalTime() + " minutes.";
        }
        String userHandCount = "User hand: " + game.getUserDeck().getPlayingCardCount() + " cards";
        String  computerHandCount = "Computer hand: " + game.getComputerDeck().getPlayingCardCount() + " cards";
        String userDiscardCount = "User discard: " + game.getUserDiscard().getPlayingCardCount() + " cards";
        String computerDiscardCount = "Computer discard: " + game.getComputerDiscard().getPlayingCardCount() + " cards";
        String round = "Round: " + game.getRoundCount();
        String userShuffleCount = "User shuffled " + game.getUserDeck().getDeckShuffleCount() + " times";
        String computerShuffleCount = "Computer shuffled " + game.getComputerDeck().getDeckShuffleCount() + " times";
        String watCount = "Wars: " + game.getWarCount();
        String userCardStr = "Game over";
        String computerCardStr = "Game over";

        Output output = new Output(outcome,userHandCount,computerHandCount,userDiscardCount,computerDiscardCount,userCardStr,computerCardStr,round,
                userShuffleCount,computerShuffleCount,watCount);

        return output;
    }

}
