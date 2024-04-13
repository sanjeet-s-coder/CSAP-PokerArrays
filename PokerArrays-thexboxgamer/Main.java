public class Main {
  public static void main(String[] args) {
    Deck d = new Deck();
    for (int i = 0; i < 52; i++) {
      Card c = d.printCard();
      d.shuffle();
      System.out.println(c);
    }
    System.out.println("------------------------------------------");
    Hand hand = new Hand();
    hand.giveHand(d);
    System.out.println("----Sorting Cards for the 1st Hand now---");
    hand.sort();
    hand.showHand();
    hand.findhandranking();
    Hand hand2 = new Hand();
    hand2.giveHand(d);
    System.out.println("---Sorting Cards for the 2nd Hand now---");
    hand2.sort();
    hand2.showHand();
    hand2.findhandranking();
    System.out.println("---Checking which hand won---");
    hand.evaluate(hand2);
  }
}