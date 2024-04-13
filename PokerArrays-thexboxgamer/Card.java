public class Card {
  private String suit;
  private String num;

  public Card(String suit, String num) {
    this.suit = suit;
    this.num = num;
  }

  public String getSuit() {
    return suit;
  }

  public String getNum() {
    return num;
  }

  public String toString() {
    return num + " of " + suit;
  }
}
