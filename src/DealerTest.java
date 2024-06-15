import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class  DealerTest {

    private Dealer dealer;
    private Deck deck;
    private Player player;

    @BeforeEach
    public void setUp() {
        dealer = new Dealer("TestDealer");
        deck = new Deck();
        player = new Player("TestPlayer");
    }



    @Test
    public void testNewCard() {

        dealer.drawNewCard(deck, player);
        Card replacedCard = dealer.newCard(deck);
        assertNotNull(replacedCard);
        assertEquals(1, dealer.getDealerHand().size());
    }




    @Test
    public void testShowHand() {
        dealer.drawNewCard(deck, player);
        String expected = "TestDealer's hand:\n";
        expected += dealer.getDealerHand().get(0).toString() + "\n";

        assertEquals(expected, dealer.showHand());
    }


}
