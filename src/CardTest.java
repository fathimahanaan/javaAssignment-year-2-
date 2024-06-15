
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class  CardTest {

    @Test
    void getRank() {

        Card card = new Card(2, 3);
        String rank = card.getRank();
        assertEquals("3", rank);
    }

    @Test
    void getSuit() {
        Card card = new Card(5, 3);
        assertEquals("❤", card.getSuit());
    }


    @Test
    void getValue() {
        Card card = new Card(10, 1);
        int value = card.getValue();
        assertEquals(10, value);
    }


}





