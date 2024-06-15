import java.util.Scanner;

public class Total11 {

    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player = new Player("Player");
        Dealer dealer = new Dealer("Dealer");

        for (int i = 0; i < 5; i++) {
            player.drawNewCard(deck, dealer);
        }
        System.out.println("\n[DEALER]");
        dealer.drawNewCard(deck, player);

        int playerPoints = total11(player, dealer, deck);

        System.out.println("\n[NEW CARDS]");
        System.out.println(player.showHand());
        System.out.println(dealer.showHand());
        System.out.println("Player's Score: " + playerPoints);

        TopFiveScore.saveScore("Player", playerPoints);
    }

    public static int total11(Player player, Dealer dealer, Deck deck) {
        Scanner scanner = new Scanner(System.in);
        int playerScore = 0;
        int roundNumber = 1;

        while (!deck.isDeckEmpty()) {
            System.out.println("Round " + roundNumber);

            System.out.println("\nYour hand:");
            for (int i = 0; i < player.getHand().size(); i++) {
                System.out.println((char) ('A' + i) + ": " + player.getHand().get(i));
            }

            System.out.println("\nChoose a card from your hand to make 11:");

            char playerCardChoice = Character.toUpperCase(scanner.next().charAt(0));

            int playerCardIndex = playerCardChoice - 'A' + 1;

            if (playerCardIndex < 1 || playerCardIndex > player.getHand().size()) {
                System.out.println("Invalid index. Please enter a valid index between A and " + (char) ('A' + player.getHand().size() - 1));
                continue;
            }
            System.out.println("--------------------------------------------");
            System.out.println("\n[RESULT]");
            Card playerCard = player.getHand().get(playerCardIndex - 1);
            Card playerUsedCards = null; // Initialize to some default value
            int point = 0; // Initialize to some default value

            if (isRoyalCard(playerCard)) {
                System.out.println("Face cards used the game is considered a draw. Continue match!!!");

                Card replacedPlayerCard = player.replaceCard(deck, playerCardIndex - 1);
                Card replacedComputerCard = dealer.newCard(deck);

                playerUsedCards = replacedPlayerCard; // Set the playerUsedCards value
                point = 0; // Set the point value

                System.out.println("\n[NEW CARDS]");
                System.out.println(dealer.showHand());
                System.out.println("Player's Score: " + playerScore);
            } else {
                System.out.println("Player chose: " + playerCard);

                boolean foundCombination = false;
                for (Card dealerCard : dealer.getDealerHand()) {
                    if (playerCard.getValue() + dealerCard.getValue() == 11) {
                        System.out.println("Dealer has: " + dealerCard);
                        Card replacedPlayerCard = player.replaceCard(deck, playerCardIndex - 1);
                        Card replacedComputerCard = dealer.newCard(deck);

                        System.out.println("\n[NEW CARDS]");
                        System.out.println("Player's New Card is: " + replacedPlayerCard);
                        System.out.println("Dealer's New Card is: " + replacedComputerCard);
                        System.out.println("\n--------------------------------------------");

                        System.out.println("\nPlayer gets 1 point!");
                        System.out.println("\nDealer's new card: ");
                        for (Card card : dealer.getDealerHand()) {
                            System.out.println(card);
                        }

                        playerUsedCards = replacedPlayerCard; // Set the playerUsedCards value
                        point = (replacedPlayerCard.getValue() + replacedComputerCard.getValue() == 11) ? 1 : 0; // Set the point value

                        foundCombination = true;
                        playerScore += point;
                    }
                }

                if (!foundCombination) {
                    System.out.println("Card total value is not 11.");
                    return playerScore;
                }
            }

            GameReview.saveData(roundNumber, player.getHand(), dealer.getDealerHand(), playerScore, playerUsedCards, point);

            roundNumber++;
        }

        System.out.println("Deck is empty. Ending game.");
        return playerScore;
    }

    private static boolean isRoyalCard(Card card) {
        return card.getRank().equals("K") || card.getRank().equals("Q") || card.getRank().equals("J");
    }
}
