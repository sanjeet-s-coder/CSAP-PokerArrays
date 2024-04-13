public class Deck {
    private Card[] cards;
    private int currentCard; 
    
  public Deck() {
        cards = new Card[52];
        currentCard = 0;
        createDeck();
    }

    public void createDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] nums = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        int index = 0;
        for (String suit : suits) {
            for (String num : nums) {
                cards[index] = new Card(suit, num);
                index++;
            }
        }
    }

    public void shuffle() {
        for (int i = cards.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[rand];
            cards[rand] = temp;
        }
        currentCard = 0; 
    }

    public Card printCard() {
        if (currentCard >= cards.length) {
            return null;
        }
        return cards[currentCard++];
    }
}
