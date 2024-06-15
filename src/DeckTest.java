import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class DeckTest {

    private Deck deck;

    @Before
    public void setUp() {
        deck = new Deck();
    }

    @Test
    public void deal() {
        Card card = deck.deal();
        assertNotNull(card);
        assertEquals(51, deck.getSize());
    }

    @Test
    public void isDeckEmpty() {
        assertFalse(deck.isDeckEmpty());
        for (int i = 0; i < 52; i++) {
            deck.deal();
        }
        assertTrue(deck.isDeckEmpty());
    }

    @Test
    public void testToString() {
        String deckString = deck.toString();
        assertNotNull(deckString);
    }




}

