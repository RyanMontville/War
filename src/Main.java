import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private List<Card> userHand = new ArrayList<>();
    private List<Card> computerHand = new ArrayList<>();

    public static void main(String[] args) {
        Game game = new Game();
        Deck testDeck1 = game.getStartingDeck();
        System.out.println("Starting deck has " + testDeck1.getListOfCards().size());
        System.out.println("splitting the deck");
        game.giveCardsToPlayers();
        Deck testDeck2 = game.getUserHand();
        Deck testDeck3 = game.getComputerDeck();
        int count = 0;
        int wars = 0;
        while (count < 500) {
            if (testDeck2.getListOfCards().size() == 0 || testDeck3.getListOfCards().size() == 0) {
                break;
            }
            Card currentUserCard = testDeck2.drawOneCard();
            System.out.println("User just drew the " + currentUserCard.getCard() + ". User hand now has " + testDeck2.getListOfCards().size());
            Card currentCompCard = testDeck3.drawOneCard();
            System.out.println("Computer just drew the " + currentCompCard.getCard() + ". Computer now has " + testDeck3.getListOfCards().size());

            int compRank = currentCompCard.getRank();
            int userRank = currentUserCard.getRank();
            if (compRank > userRank) {
                System.out.println(currentCompCard.getCard() + " is greater than " + currentUserCard.getCard() + ". Computer gets both cards.");
                testDeck3.addCardToDeck(currentCompCard);
                testDeck3.addCardToDeck(currentUserCard);
            } else if (userRank > compRank) {
                System.out.println(currentUserCard.getCard() + " is greater that " + currentCompCard.getCard() + ". User gets both cards.");
                testDeck2.addCardToDeck(currentCompCard);
                testDeck2.addCardToDeck(currentUserCard);
            } else {
                System.out.println(currentCompCard.getCard() + " = " + currentUserCard + ". WAR!******************************");
                wars++;
            }
            count++;
        }
        System.out.println("Game over. Each player flipped " + count + " cards. " + wars + " wars happened.");
    }

}
