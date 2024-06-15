import java.util.ArrayList;

public class Dealer {

    private String playerName;
    private ArrayList<Card> dealerHand;

    public Dealer(String name) {
        this.playerName = name;
        this.dealerHand = new ArrayList<>();
    }

    public void drawNewCard(Deck deck, Player player) {
        Card card;
        do {
            card = deck.deal();
        } while (card != null && (dealerHand.contains(card) || player.getHand().contains(card)));

        if (card != null) {
            dealerHand.add(card);
            System.out.println(playerName + " drew a card: " + card);
        } else {
            System.out.println("Deck is empty. Cannot draw more cards.");
        }
    }

    public Card newCard(Deck deck) {
        if (!dealerHand.isEmpty()) {
            int index = (int) (Math.random() * dealerHand.size()); // Randomly choose a card to replace
            Card replacedCard = dealerHand.get(index);
            Card newCard = deck.deal();
            if (newCard != null) {
                System.out.println(playerName + " replaced a card: " + replacedCard + " with " + newCard);
                dealerHand.set(index, newCard);
                return replacedCard;
            } else {
                System.out.println("Deck is empty. Cannot replace cards.");
            }
        }
        return null;
    }

    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public String showHand() {
        StringBuilder result = new StringBuilder(playerName + "'s hand:\n");
        for (Card card : dealerHand) {
            result.append(card).append("\n");
        }
        return result.toString();
    }
}
