import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<Card> userHand = new ArrayList<>();
    private List<Card> computerHand = new ArrayList<>();

    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        game.giveCardsToPlayers();
        int count = 0;
        int wars = 0;
        System.out.println(game.getCardsCurrentlyPlayed().getListOfCards().size());
        while (game.getUserDeck().getListOfCards().size() > 0 || game.getUserDiscard().getListOfCards().size() > 0) {
            if (game.getUserDeck().getListOfCards().size() == 0 && game.getUserDiscard().getListOfCards().size() == 0) {
                break;
            }
            if (game.getComputerDeck().getListOfCards().size() == 0 && game.getComputerDiscard().getListOfCards().size() == 0) {
                break;
            }
            System.out.println("");
            System.out.println("User deck has " + game.getUserDeck().getListOfCards().size() + ". Computer deck has " + game.getComputerDeck().getListOfCards().size());
            System.out.println("User hand has " + game.getUserDiscard().getListOfCards().size() + ". Computer hand has " + game.getComputerDiscard().getListOfCards().size());
            System.out.print("(D)raw a card or (G)ive up and end the game: ");
            char response = scanner.next().charAt(0);
            if (response =='G' || response == 'g') {
                break;
            } else if (response != 'D') {
                if (response != 'd') {
                    System.out.println("Invalid response. Interpreting as giving up.");
                    break;
                }
            }
            Card cardUserDrew = game.userDrawsCard();
            Card cardComputerDrew = game.computerDrawsCard();
            game.compareCards(cardComputerDrew, cardUserDrew);


            count++;
        }
        System.out.println("Game over. You gave up after " + count + " rounds. " + wars + " wars happened.");
    }

}
