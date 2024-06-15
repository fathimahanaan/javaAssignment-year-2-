import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopFiveScore {

    private static final String LEADERBOARD_FILE = "leaderboard.txt";

    private static final int TOP_FIVE = 5;

    public static void saveScore(String playerName, int score) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(new File(LEADERBOARD_FILE), true)))) {
            writer.println(playerName + "," + score);
        } catch (IOException e) {
            System.err.println("Error writing to the scores file: " + e.getMessage());
        }
    }

    public static void printTopFive() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LEADERBOARD_FILE));
            List<SaveScoreData> scoreEntries = new ArrayList<>();

            for (String line : lines) {
                String[] parts = line.split(",");
                String playerName = parts[0];
                int score = Integer.parseInt(parts[1]);
                scoreEntries.add(new SaveScoreData(playerName, score));
            }

            // Sort the entries in descending order based on score
            Collections.sort(scoreEntries, Comparator.comparingInt(SaveScoreData::getPoints).reversed());

            int displayCount = Math.min(TOP_FIVE, scoreEntries.size());
            System.out.println("\n==============[TOP " + displayCount + " HIGH SCORES]===================");

            for (int i = 0; i < displayCount; i++) {
                SaveScoreData entry = scoreEntries.get(i);
                System.out.println(entry.getPlayerName() + ": " + entry.getPoints() + " points");
            }

            System.out.println("=============================================");
        } catch (Exception e) {
            System.err.println("Error reading high scores: " + e.getMessage());
        }
    }

    private static class SaveScoreData {
        private final String playerName;
        private final int points;

        public SaveScoreData(String playerName, int points) {
            this.playerName = playerName;
            this.points = points;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getPoints() {
            return points;
        }
    }

}
