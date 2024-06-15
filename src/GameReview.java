import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GameReview {

    private static final String REVIEW_FILE = "review.txt";

    public static void resetReview() {
        try {
            Files.deleteIfExists(Paths.get(REVIEW_FILE));
        } catch (IOException e) {
            System.err.println("Error deleting old review file: " + e.getMessage());
        }
    }

    public static void saveData(int roundNumber, List<Card> playerHand, List<Card> computerHand,
                                int playerScore, Card playerCardUsed, int points) {
        try {
            Path path = Paths.get(REVIEW_FILE);
            StringBuilder matchData = new StringBuilder();

            matchData.append("Round ").append(roundNumber).append("\n");
            matchData.append("Player deck: ").append(getListOfCards(playerHand)).append("\n");
            matchData.append("Dealer card: ").append(getListOfCards(computerHand)).append("\n");
            matchData.append("Player played: ").append(playerCardUsed != null ? playerCardUsed : "None").append("\n");
            matchData.append("Points earned: ").append(points).append("\n");
            matchData.append("").append("\n");
            Files.writeString(path, matchData.toString(), Files.exists(path) ? java.nio.file.StandardOpenOption.APPEND : java.nio.file.StandardOpenOption.CREATE);

        } catch (IOException e) {
            System.err.println("Error saving round data to replay file: " + e.getMessage());
        }
    }

    private static String getListOfCards(List<Card> cards) {
        StringBuilder result = new StringBuilder();
        for (Card card : cards) {
            result.append(card).append(" ");
        }
        return result.toString().trim();
    }

    public static void printReview() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(REVIEW_FILE));
            System.out.println("\nGAME REPLAY");
            System.out.println("--------------------");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("END REPLAY");
            System.out.println("--------------------");
        } catch (IOException e) {
            System.err.println("No file was found as you haven't played or you lost Round 1.");
        }
    }
}
