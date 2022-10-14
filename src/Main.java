import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private List<Card> userHand = new ArrayList<>();
    private List<Card> computerHand = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Starting new game");
        Game game = new Game();
        Deck testDeck1 = game.getStartingDeck();
        System.out.println("Starting deck has " + testDeck1.getListOfCards().size());
        System.out.println("spliting the deck");
        game.giveCardsToPlayers();
        Deck testDeck2 = game.getUserHand();
        Deck testDeck3 = game.getComputerDeck();
        System.out.println("starting deck now has " + testDeck1.getListOfCards().size());
        System.out.println("User deck now has " + testDeck2.getListOfCards().size());
        System.out.println("Computer deck now has " + testDeck3.getListOfCards().size());

    }

}
