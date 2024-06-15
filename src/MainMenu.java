import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n[MENU]---------------------------------");
            System.out.println("A. New Game");
            System.out.println("B. LeaderBoard");
            System.out.println("C. Game Review");
            System.out.println("D. Leave Game");
            System.out.println("----------------------------------------\n");

            System.out.print("Choose an option (A-D): ");
            String choice = scanner.next().toUpperCase();

            if ("A".equals(choice)) {
                startNewGame(scanner);
            } else if ("B".equals(choice)) {
                TopFiveScore.printTopFive();
            } else if ("C".equals(choice)) {
                GameReview.printReview();
            } else if ("D".equals(choice)) {
                System.out.println("Bye. See you again!!!.");
                System.exit(0);
            } else {
                System.out.println("Wrong choice. Please enter a valid option (A-D).");
            }
        }
    }

    private static void startNewGame(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter name: ");
        String playerName = scanner.nextLine();

        // Reset the replay file for a new game
        GameReview.resetReview();

        Deck deck = new Deck();
        Player player = new Player(playerName);
        Dealer dealer = new Dealer("Dealer");

        for (int i = 0; i < 5; i++) {
            player.drawNewCard(deck, dealer);
        }
        System.out.println("\n[DEALER]");
        dealer.drawNewCard(deck, player);
        System.out.println("\n");

        int playerScore = Total11.total11(player, dealer, deck);

        System.out.println("\n[NEW CARDS]");
        System.out.println(player.showHand());
        System.out.println(dealer.showHand());
        System.out.println("Player's Score: " + playerScore);

        TopFiveScore.saveScore(playerName, playerScore);
    }
}
