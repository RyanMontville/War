package com.ryanmontville.War.model;

public class Output {

    private String outcome;
    private String  userHandCount;
    private String computerHandCount;
    private String userDiscardCount;
    private String computerDiscardCount;
    private String userCard;
    private String computerCard;
    private String  round;
    private String  userShuffleCount;
    private String  computerShuffleCount;
    private String warCount;

    public Output(String outcome, String userHandCount, String computerHandCount, String userDiscardCount, String computerDiscardCount, String userCard,
                  String computerCard, String round, String userShuffleCount, String computerShuffleCount, String warCount) {
        this.outcome = outcome;
        this.userHandCount = userHandCount;
        this.computerHandCount = computerHandCount;
        this.userDiscardCount = userDiscardCount;
        this.computerDiscardCount = computerDiscardCount;
        this.userCard = userCard;
        this.computerCard = computerCard;
        this.round = round;
        this.userShuffleCount = userShuffleCount;
        this.computerShuffleCount = computerShuffleCount;
        this.warCount = warCount;
    }

    public String getOutcome() {
        return outcome;
    }

    public String getUserHandCount() {
        return userHandCount;
    }

    public String getComputerHandCount() {
        return computerHandCount;
    }

    public String getUserDiscardCount() {
        return userDiscardCount;
    }

    public String getComputerDiscardCount() {
        return computerDiscardCount;
    }

    public String getUserCard() {
        return userCard;
    }

    public String getComputerCard() {
        return computerCard;
    }

    public String getRound() {
        return round;
    }

    public String getUserShuffleCount() {
        return userShuffleCount;
    }

    public String getComputerShuffleCount() {
        return computerShuffleCount;
    }

    public String getWarCount() {
        return warCount;
    }
}
