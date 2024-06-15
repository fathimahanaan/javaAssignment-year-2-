import java.util.Random;

public class  Card {

    private int rank;
    private int suit;
    private int value;
    private static String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static String[] suits = { "♣", "♠", "♦", "❤"};
    private static int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    public Card(){
        Random random = new Random();
        this.rank = random.nextInt(Card.ranks.length);
        this.suit = random.nextInt(Card.suits.length);
        this.value = values[rank];
    }

    public Card(int rank, int suit){
        this.rank = rank;
        this.suit = suit;
        this.value = values[rank];
    }

    public String getRank(){
        return Card.ranks[this.rank];
    }
    public String getSuit(){
        return Card.suits[this.suit];
    }
    public int getValue() { return this.value;}

    public String toString(){
        return getRank() + getSuit();
    }

}
