public class Hand {
  public Card[] cards;
  public int cardsDealt;
  private String[] numsOrder = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };

  public Hand() {
    cards = new Card[5];
    cardsDealt = 0;
  }

  public void giveHand(Deck deck) {
    for (int i = 0; i < 5; i++) {
      Card dealtCard = deck.printCard();
      if (dealtCard != null) {
        cards[i] = dealtCard;
        cardsDealt++;
      } else {
        System.out.println("No cards left");
        break;
      }
    }
  }

  public void showHand() {
    System.out.println("You have: ");
    for (Card card : cards) {
      if (card != null) {
        System.out.println(card.toString());
      }
    }
  }

  public void sort() {
    for (int i = 0; i < cardsDealt - 1; i++) {
      int minIndex = i;
      for (int j = i + 1; j < cardsDealt; j++) {
        if (getNumVal(cards[j].getNum(), numsOrder) < getNumVal(cards[minIndex].getNum(), numsOrder)) {
          minIndex = j;
        }
      }
      Card temp = cards[minIndex];
      cards[minIndex] = cards[i];
      cards[i] = temp;
    }
  }

  private int getNumVal(String num, String[] numsOrder) {
    for (int i = 0; i < numsOrder.length; i++) {
      if (numsOrder[i].equals(num)) {
        return i;
      }
    }
    return -1;
  }

  public boolean HighCard() {
    if (cardsDealt == 0) {
      System.out.println("No cards dealt yet");
      return false;
    }

    int highestCardIndex = 0;
    for (int i = 1; i < cardsDealt; i++) {
      int currentCardValue = getNumVal(cards[i].getNum(), numsOrder);
      int highestCardValue = getNumVal(cards[highestCardIndex].getNum(), numsOrder);

      if (currentCardValue > highestCardValue) {
        highestCardIndex = i;
      }
    }
    System.out.println("High Card: " + cards[highestCardIndex].toString());

    return true;
  }

  public boolean flush() {
    String referenceSuit = cards[0].getSuit();
    for (int i = 1; i < cardsDealt; i++) {
      if (!cards[i].getSuit().equals(referenceSuit)) {
        return false;
      }
    }
    System.out.println("Flush in " + referenceSuit);
    return true;
  }

  public boolean straight() {
    int start = getNumVal(cards[0].getNum(), numsOrder);
    for (int i = 1; i < cardsDealt; i++) {
      int currentCardValue = getNumVal(cards[i].getNum(), numsOrder);
      if (currentCardValue != start + i) {
        return false;
      }
    }
    System.out.println("Straight: " + cards[0].getNum() + " to " + cards[4].getNum());
    return true;
  }
  public boolean checkOnePair() {
      if (cardsDealt < 2) {
          System.out.println("Not enough cards for a pair");
          return false;
      }

      for (int i = 0; i < cardsDealt - 1; i++) {
          for (int j = i + 1; j < cardsDealt; j++) {
              int val1 = getNumVal(cards[i].getNum(), numsOrder);
              int val2 = getNumVal(cards[j].getNum(), numsOrder);

              if (val1 == val2) {
                  System.out.println("One Pair: " + cards[i].toString() + " and " + cards[j].toString());
                  return true;
              }
          }
      }
      return false;
  }
  public boolean checkTwoPair() {
      int firstPairIndex = -1;
      for (int i = 0; i < cardsDealt - 1; i++) {
          for (int j = i + 1; j < cardsDealt; j++) {
              int val1 = getNumVal(cards[i].getNum(), numsOrder);
              int val2 = getNumVal(cards[j].getNum(), numsOrder);

              if (val1 == val2) {
                  if (firstPairIndex == -1) {
                      firstPairIndex = i;
                  } else if (val1 != getNumVal(cards[firstPairIndex].getNum(), numsOrder)) {
                      System.out.println("Two Pair: " + cards[firstPairIndex].toString() +
                              " and " + cards[i].toString() +
                              ", " + cards[j].toString());
                      return true;
                  }
              }
          }
      }
      return false;
  }
  public boolean checkThreeKind() {
      if (cardsDealt < 3) {
          System.out.println("Not enough cards for three of a kind");
          return false;
      }

      for (int i = 0; i < cardsDealt - 2; i++) {
          for (int j = i + 1; j < cardsDealt - 1; j++) {
              for (int k = j + 1; k < cardsDealt; k++) {
                  int val1 = getNumVal(cards[i].getNum(), numsOrder);
                  int val2 = getNumVal(cards[j].getNum(), numsOrder);
                  int val3 = getNumVal(cards[k].getNum(), numsOrder);

                  if (val1 == val2 && val2 == val3) {
                      System.out.println("Three of a Kind: " +
                              cards[i].toString() + ", " +
                              cards[j].toString() + ", " +
                              cards[k].toString());
                      return true;
                  }
              }
          }
      }
      return false;
  }
  public boolean checkfullHouse() {
      if (cardsDealt < 5) {
          System.out.println("Not enough cards for a full house");
          return false;
      }

      boolean hasThreeKind = false;
      int threeKindValue = -1;
      for (int i = 0; i < cardsDealt - 2; i++) {
          for (int j = i + 1; j < cardsDealt - 1; j++) {
              for (int k = j + 1; k < cardsDealt; k++) {
                  int val1 = getNumVal(cards[i].getNum(), numsOrder);
                  int val2 = getNumVal(cards[j].getNum(), numsOrder);
                  int val3 = getNumVal(cards[k].getNum(), numsOrder);

                  if (val1 == val2 && val2 == val3) {
                      hasThreeKind = true;
                      threeKindValue = val1;
                      break;
                  }
              }
              if (hasThreeKind) {
                  break;
              }
          }
          if (hasThreeKind) {
              break;
          }
      }

      if (!hasThreeKind) {
          return false;
      }

      boolean hasPair = false;

      for (int i = 0; i < cardsDealt - 1; i++) {
          for (int j = i + 1; j < cardsDealt; j++) {
              int val1 = getNumVal(cards[i].getNum(), numsOrder);
              int val2 = getNumVal(cards[j].getNum(), numsOrder);

              if (val1 == val2 && val1 != threeKindValue) {
                  hasPair = true;
                  break;
              }
          }
          if (hasPair) {
              break;
          }
      }

      if (hasThreeKind && hasPair) {
          System.out.println("Full House!");
          return true;
      } else {
        
          return false;
      }
  }
  public boolean checkfourkind() {
      if (cardsDealt < 4) {
          System.out.println("Not enough cards for four of a kind");
          return false;
      }

      for (int i = 0; i < cardsDealt - 3; i++) {
          for (int j = i + 1; j < cardsDealt - 2; j++) {
              for (int k = j + 1; k < cardsDealt - 1; k++) {
                  for (int l = k + 1; l < cardsDealt; l++) {
                      int val1 = getNumVal(cards[i].getNum(), numsOrder);
                      int val2 = getNumVal(cards[j].getNum(), numsOrder);
                      int val3 = getNumVal(cards[k].getNum(), numsOrder);
                      int val4 = getNumVal(cards[l].getNum(), numsOrder);

                      if (val1 == val2 && val2 == val3 && val3 == val4) {
                          System.out.println("Four of a Kind: " +
                                  cards[i].toString() + ", " +
                                  cards[j].toString() + ", " +
                                  cards[k].toString() + ", " +
                                  cards[l].toString());
                          return true;
                      }
                  }
              }
          }
      }

      return false;
  }
  public boolean checkstraightflush() {
      if (cardsDealt < 5) {
          System.out.println("Not enough cards for a straight flush");
          return false;
      }
      sort();

      for (int i = 0; i <= cardsDealt - 5; i++) {
          boolean isFlush = true;
          String referenceSuit = cards[i].getSuit();
          for (int j = i + 1; j < i + 5; j++) {
              if (!cards[j].getSuit().equals(referenceSuit)) {
                  isFlush = false;
                  break;
              }
          }

          if (isFlush) {
              int start = getNumVal(cards[i].getNum(), numsOrder);
              boolean isStraight = true;
              for (int k = i + 1; k < i + 5; k++) {
                  int currentCardValue = getNumVal(cards[k].getNum(), numsOrder);
                  if (currentCardValue != start + (k - i)) {
                      isStraight = false;
                      break;
                  }
              }

              if (isStraight) {
                  System.out.println("Straight Flush: " + cards[i].getNum() + " to " + cards[i + 4].getNum() + " in " + referenceSuit);
                  return true;
              }
          }
      }
      
      return false;
  }
  public boolean checkroyalflush() {
      if (cardsDealt < 5) {
          System.out.println("Not enough cards for a royal flush");
          return false;
      }
      sort();
      boolean isStraightFlush = false;
      for (int i = 0; i <= cardsDealt - 5; i++) {
          boolean isFlush = true;
          String referenceSuit = cards[i].getSuit();
          for (int j = i + 1; j < i + 5; j++) {
              if (!cards[j].getSuit().equals(referenceSuit)) {
                  isFlush = false;
                  break;
              }
          }

          if (isFlush) {
              int start = getNumVal("10", numsOrder);
              boolean isStraight = true;
              for (int k = i + 1; k < i + 5; k++) {
                  int currentCardValue = getNumVal(cards[k].getNum(), numsOrder);
                  if (currentCardValue != start + (k - i)) {
                      isStraight = false;
                      break;
                  }
              }

              if (isStraight) {
                  isStraightFlush = true;
                  break;
              }
          }
      }
      if (isStraightFlush) {
          System.out.println("Royal Flush in " + cards[0].getSuit());
          return true;
      } else {
        
          return false;
    }
  }
  public void findhandranking() {
      sort();
      String highestRank = "";

      if (checkroyalflush()) {
          highestRank = "Royal Flush";
      } else if (checkstraightflush()) {
          highestRank = "Straight Flush";
      } else if (checkfourkind()) {
          highestRank = "Four of a Kind";
      } else if (checkfullHouse()) {
          highestRank = "Full House";
      } else if (flush()) {
          highestRank = "Flush";
      } else if (straight()) {
          highestRank = "Straight";
      } else if (checkThreeKind()) {
          highestRank = "Three of a Kind";
      } else if (checkTwoPair()) {
          highestRank = "Two Pair";
      } else if (checkOnePair()) {
          highestRank = "One Pair";
      } else {
        highestRank = "High Card: " + cards[cardsDealt - 1].toString();
      }
      System.out.println("The highest rank you have is " + highestRank);
  }
  public void evaluate(Hand otherHand) {
      int thisHandRank = getHandRanking();
      int otherHandRank = otherHand.getHandRanking();

      if (thisHandRank > otherHandRank) {
          System.out.println("First hand wins with a higher rank");
      } else if (thisHandRank < otherHandRank) {
          System.out.println("Second hand wins with a higher rank");
      } else {
          int highestCardThisHand = getHighestCardValue();
          int highestCardOtherHand = otherHand.getHighestCardValue();

          if (highestCardThisHand > highestCardOtherHand) {
              System.out.println("First hand wins with a higher card value");
          } else if (highestCardThisHand < highestCardOtherHand) {
              System.out.println("Second hand wins with a higher card value");
          } else {
              System.out.println("It's a tie!");
          }
      }
  }
  private int getHighestCardValue() {
      if (cardsDealt == 0) {
          return -1;
      }

      int highestCardValue = getNumVal(cards[0].getNum(), numsOrder);
      for (int i = 1; i < cardsDealt; i++) {
          int currentCardValue = getNumVal(cards[i].getNum(), numsOrder);
          if (currentCardValue > highestCardValue) {
              highestCardValue = currentCardValue;
          }
      }
      return highestCardValue;
  }


  private int getHandRanking() {
      if (checkroyalflush()) {
          return 10;
      } else if (checkstraightflush()) {
          return 9;
      } else if (checkfourkind()) {
          return 8;
      } else if (checkfullHouse()) {
          return 7;
      } else if (flush()) {
          return 6;
      } else if (straight()) {
          return 5;
      } else if (checkThreeKind()) {
          return 4;
      } else if (checkTwoPair()) {
          return 3;
      } else if (checkOnePair()) {
          return 2;
      } else {
          return 1;
      }
  }

}
