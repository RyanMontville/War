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
        System.out.print("\nWould you like to (P)lay war, or have the (C)omputer run the entire game and tell you how long it would have taken in real life: ");
        char decision = scanner.next().charAt(0);
        //make it so if not C or C, if P or P, play else not C or P, still make them play
        //else if p or P - simulation
        if (decision == 'P' || decision == 'p') {
            while (game.getUserDeck().getListOfCards().size() > 0 || game.getUserDiscard().getListOfCards().size() > 0) {
                if (game.getUserDeck().getListOfCards().size() == 0 && game.getUserDiscard().getListOfCards().size() == 0) {
                    break;
                }
                if (game.getComputerDeck().getListOfCards().size() == 0 && game.getComputerDiscard().getListOfCards().size() == 0) {
                    break;
                }
                System.out.println("");
                System.out.print("You have " + (game.getUserDeck().getListOfCards().size() + game.getUserDiscard().getListOfCards().size()) + " cards total. ");
                System.out.println("The computer has " + (game.getComputerDeck().getListOfCards().size() + game.getComputerDiscard().getListOfCards().size()) + " cards total.");
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
                game.compareCards(cardComputerDrew, cardUserDrew,true);
                count++;
            }
        } else if (decision == 'C' || decision == 'c') {
            System.out.println("Running simulation ...");
            while (game.getUserDeck().getListOfCards().size() > 0 || game.getUserDiscard().getListOfCards().size() > 0) {
                if (game.getUserDeck().getListOfCards().size() == 0 && game.getUserDiscard().getListOfCards().size() == 0) {
                    break;
                }
                if (game.getComputerDeck().getListOfCards().size() == 0 && game.getComputerDiscard().getListOfCards().size() == 0) {
                    break;
                }
                System.out.print("\nYou have " + (game.getUserDeck().getListOfCards().size() + game.getUserDiscard().getListOfCards().size()) + " cards total. ");
                System.out.println("The computer has " + (game.getComputerDeck().getListOfCards().size() + game.getComputerDiscard().getListOfCards().size()) + " cards total.");
                Card cardUserDrew = game.userDrawsCard();
                Card cardComputerDrew = game.computerDrawsCard();
                game.compareCards(cardComputerDrew, cardUserDrew, false);
                count++;
            }
        } else {
            System.out.println("\nThat is mot a P or C!\nEnding game.");
        }
        if (game.getRoundCount() != 0) {
            System.out.print("\nGame over. You ");
            if (game.getUserDeck().getListOfCards().size() == 0) {
                System.out.println("lose.");
            } else {
                System.out.println("win!");
            }
        }
        System.out.println("");
        game.displayGameStats();
    }

}
