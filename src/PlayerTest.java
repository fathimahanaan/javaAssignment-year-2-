import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Deck deck;
    private Player player;
    private Dealer computer;

    @BeforeEach
    void setUp() {
        deck = new Deck();
        player = new Player("TestPlayer");
        computer = new Dealer("TempComputer");
    }


    @Test
    void drawNewCard() {

        Deck mockDeck = new Deck();
        Dealer mockDealer = new Dealer("TempComputer");
        Player player = new Player("Player");
        player.drawNewCard(mockDeck, mockDealer);
        assertFalse(player.getHand().isEmpty());
    }


    @Test
    void showHand() {
        Player player = new Player("Player");
        player.getHand().add(new Card(1, 1));
        player.getHand().add(new Card(2, 2));
        String expectedOutput = "Player's hand:\n" + new Card(1, 1) + "\n" + new Card(2, 2) + "\n";
        assertEquals(expectedOutput, player.showHand());
    }

    @Test
    void replaceCard() {
        Player player = new Player("Player");
        Deck mockDeck = new Deck();
        player.getHand().add(new Card(1, 1));
        player.getHand().add(new Card(2, 2));
        Card replacedCard = player.replaceCard(mockDeck, 1);
        assertNotNull(replacedCard);
        assertEquals(2, player.getHand().size());
    }



    @Test
    void showHandAsString() {
        for (int i = 0; i < 3; i++) {
            player.drawNewCard(deck, computer);
        }

        String handAsString = player.showHandAsString();
        assertTrue(handAsString.contains("Player's Hand:"));
        for (String cardString : player.getHandStringList()) {
            assertTrue(handAsString.contains(cardString));
        }

    }}

