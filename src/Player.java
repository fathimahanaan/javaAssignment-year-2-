import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void drawNewCard(Deck deck, Dealer computer) {
        Card card;
        do {
            card = deck.deal();
        } while (card != null && (hand.contains(card) || computer.getDealerHand().contains(card)));

        if (card != null) {
            hand.add(card);
        } else {
            System.out.println("Deck is empty. Cannot draw more cards.");
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String showHand() {
        StringBuilder result = new StringBuilder(name + "'s hand:\n");
        for (Card card : hand) {
            result.append(card).append("\n");
        }
        return result.toString();
    }

    public Card replaceCard(Deck deck, int index) {
        if (index >= 0 && index < hand.size()) {
            Card replacedCard = hand.remove(index);
            drawNewCard(deck, new Dealer("TempComputer")); // Draw a new card for the player
            return replacedCard;
        } else {
            System.out.println("Invalid index. No card replaced.");
            return null;
        }
    }

    public List<String> getHandStringList() {
        List<String> handStringList = new ArrayList<>();
        for (Card card : hand) {
            handStringList.add(card.toString());
        }
        return handStringList;
    }

    // In the Player class
    public String showHandAsString() {
        StringBuilder handString = new StringBuilder("Player's Hand: ");
        for (Card card : hand) {
            handString.append(card.toString()).append(" ");
        }
        return handString.toString();
    }


    public static void main(String[] args) {
        Deck deck = new Deck();
        Player player = new Player("Player");

        // Draw 5 unique cards for the player (not in computer's hand)
        for (int i = 0; i < 5; i++) {
            player.drawNewCard(deck, new Dealer("TempComputer"));
        }

        // Show the player's hand
        System.out.println(player.showHand());

        // Replace a card and show the updated hand
        Card replacedCard = player.replaceCard(deck, 3);
        if (replacedCard != null) {
            System.out.println("Replaced Card: " + replacedCard);
        }
        System.out.println("Updated Player's Hand:");
        System.out.println(player.showHand());
    }


}
