package com.ryanmontville.War.model;

public class Output {

    private String outcome;
    private int  playerHandCount;
    private int computerHandCount;
    private String playerRank;
    private char playerSuit;
    private String computerRank;
    private char computerSuit;
    private int  round;
    private int  playerShuffleCount;
    private int  computerShuffleCount;
    private int warCount;
    private boolean isGameOver;

    public Output(String outcome, int playerHandCount, int computerHandCount, String playerRank, char playerSuit,
                  String computerRank, char computerSuit, int round, int playerShuffleCount, int computerShuffleCount,
                  int warCount, boolean isGameOver) {
        this.outcome = outcome;
        this.playerHandCount = playerHandCount;
        this.computerHandCount = computerHandCount;
        this.playerRank = playerRank;
        this.playerSuit = playerSuit;
        this.computerRank = computerRank;
        this.computerSuit = computerSuit;
        this.round = round;
        this.playerShuffleCount = playerShuffleCount;
        this.computerShuffleCount = computerShuffleCount;
        this.warCount = warCount;
        this.isGameOver = isGameOver;
    }

    public String getOutcome() { return outcome; }

    public void setOutcome(String outcome) { this.outcome = outcome; }

    public int getPlayerHandCount() { return playerHandCount; }

    public void setPlayerHandCount(int playerHandCount) { this.playerHandCount = playerHandCount; }

    public int getComputerHandCount() { return computerHandCount; }

    public void setComputerHandCount(int computerHandCount) { this.computerHandCount = computerHandCount; }

    public String getPlayerRank() { return playerRank; }

    public void setPlayerRank(String playerRank) { this.playerRank = playerRank; }

    public char getPlayerSuit() { return playerSuit; }

    public void setPlayerSuit(char playerSuit) { this.playerSuit = playerSuit; }

    public String getComputerRank() { return computerRank; }

    public void setComputerRank(String computerRank) { this.computerRank = computerRank; }

    public char getComputerSuit() { return computerSuit; }

    public void setComputerSuit(char computerSuit) { this.computerSuit = computerSuit; }

    public int getRound() { return round; }

    public void setRound(int round) { this.round = round; }

    public int getPlayerShuffleCount() { return playerShuffleCount; }

    public void setPlayerShuffleCount(int playerShuffleCount) { this.playerShuffleCount = playerShuffleCount; }

    public int getComputerShuffleCount() { return computerShuffleCount; }

    public void setComputerShuffleCount(int computerShuffleCount) { this.computerShuffleCount = computerShuffleCount; }

    public int getWarCount() { return warCount; }

    public void setWarCount(int warCount) { this.warCount = warCount; }

    public boolean isGameOver() { return isGameOver; }

    public void setGameOver(boolean gameOver) { isGameOver = gameOver; }
}
