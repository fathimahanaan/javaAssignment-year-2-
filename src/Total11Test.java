import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Total11Test {

    @Test
    void main() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String input = "A\nA\nA\nA\nA\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Total11.main(null);
        assertTrue(outContent.toString().contains("[DEALER]"));
        assertTrue(outContent.toString().contains("[NEW CARDS]"));
        assertTrue(outContent.toString().contains("Player's Score:"));
        assertFalse(outContent.toString().contains("Deck is empty. Ending game."));
        System.setOut(System.out);
    }


}
