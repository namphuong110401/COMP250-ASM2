import java.util.Random;

public class Deck {
 public static String[] suitsInOrder = {"clubs", "diamonds", "hearts", "spades"};
 public static Random gen = new Random(809);

 public int numOfCards; // contains the total number of cards in the deck
 public Card head; // contains a pointer to the card on the top of the deck

 /*
  * TODO: Initializes a Deck object using the inputs provided
  */
 public Deck(int numOfCardsPerSuit, int numOfSuits) {
  /**** ADD CODE HERE ****/
  // check for valid input
  if(numOfCardsPerSuit>13 || numOfCardsPerSuit<1 || numOfSuits<1 || numOfSuits > suitsInOrder.length){
   throw new IllegalArgumentException();
  }
  // loop for numOfSuits
  for (int i = 0; i < numOfSuits; i++) {
   //loop for numOfCards
   for (int j = 1; j <= numOfCardsPerSuit; j++) {
    //create new card with suit and rank
    Card newCard = new PlayingCard(suitsInOrder[i], j);
    this.addCard(newCard);
   }
  }
  // create card for red joker and insert at end
  Card newRJ = new PlayingCard("RJ", 0);
  this.addCard(newRJ);
  // create card for black joker and insert at end
  Card newBJ = new PlayingCard("BJ", 0);
  this.addCard(newBJ);
 }

 /* 
  * TODO: Implements a copy constructor for Deck using Card.getCopy().
  * This method runs in O(n), where n is the number of cards in d.
  */
 public Deck(Deck d) {
  /**** ADD CODE HERE ****/
  Deck.Card tmpHead = d.head;
  for (int i = 0; i < d.numOfCards; i++) {
   Deck.Card card = tmpHead.getCopy();
   this.addCard(card);
   tmpHead = tmpHead.next;
  }
 }

 /*
  * For testing purposes we need a default constructor.
  */
 public Deck() {}

 public void printDeck()
 {
  if(numOfCards==1)
  {
   System.out.println("Card: " + head+". Value: " + head.getValue());
   return;
  }
  Card currentCard = head;
  System.out.println("Previous\tCurrent\t\t\tNext ");
  for(int cardIndex = 0; cardIndex < numOfCards; cardIndex++)
  {
   System.out.println(currentCard.prev + " <--------- " + currentCard + " ---------> " + currentCard.next + ", Values are: " +currentCard.prev.getValue() + " and " + currentCard.getValue() + " and " + currentCard.next.getValue());
   currentCard = currentCard.next;
  }

  System.out.println("Number of cards: " + numOfCards);
 }

 /* 
  * TODO: Adds the specified card at the bottom of the deck. This 
  * method runs in $O(1)$. 
  */
 public void addCard(Card c) {
  /**** ADD CODE HERE ****/
   // check if the list is empty, add a node
  if(head==null){
    head = c;
    c.next = c.prev = c;
  } else { //add new card at the end
    Card last = head.prev;
    last.next = c;
    c.prev = last;
    c.next = head;
    head.prev = c;
  }
  numOfCards++;
 }

 /*
  * TODO: Shuffles the deck using the algorithm described in the pdf. 
  * This method runs in O(n) and uses O(n) space, where n is the total 
  * number of cards in the deck.
  */
 public void shuffle() {
  /**** ADD CODE HERE ****/
  Card[] arrOfDeck = new Card[this.numOfCards];
  Card tmpHead = this.head;
  int curPos = 0;
  for (int i = 0; i < this.numOfCards; i++) {
    arrOfDeck[curPos++] = tmpHead;
    tmpHead = tmpHead.next;
   }

  //shuffle
  for (int i =this.numOfCards-1; i>=1; i--){
   int j = gen.nextInt(i+1);
   Card temp = arrOfDeck[i];
   arrOfDeck[i] = arrOfDeck[j];
   arrOfDeck[j] = temp;
  }
  //rebuild the shuffle deck
  int length = this.numOfCards;
  for (int i = 0; i < length; i++) {
   arrOfDeck[i].next = arrOfDeck[(i+1)%length];
   arrOfDeck[i].prev = arrOfDeck[(i-1+length)%length];
  }
  if (tmpHead != null) {
   this.head = arrOfDeck[0];
  }
 }

 /*
  * TODO: Returns a reference to the joker with the specified color in 
  * the deck. This method runs in O(n), where n is the total number of 
  * cards in the deck. 
  */
 public Joker locateJoker(String color) {
  /**** ADD CODE HERE ****/
  Card tmpHead = head;
  for (int i = 0; i < this.numOfCards; i++) {
   int valueOfCard = tmpHead.getValue();
   if(valueOfCard==numOfCards || valueOfCard==numOfCards-1){

    if(color.equals(((Joker)tmpHead).getColor()));
    return (Joker) tmpHead;
   }
   tmpHead = tmpHead.next;
  }
  return null;
 }

 /*
  * TODO: Moved the specified Card, p positions down the deck. You can 
  * assume that the input Card does belong to the deck (hence the deck is
  * not empty). This method runs in O(p).
  */
 public void moveCard(Card c, int p) {
  /**** ADD CODE HERE ****/
  Card tmpHead = c;
  while(p!=0){
   tmpHead = tmpHead.next;
   p--;
  }
  Card prevPointer = c.prev;
  prevPointer.next = c.next;
  c.next.prev = prevPointer;
  c.next = tmpHead.next;
  tmpHead.next = c;
  c.prev = tmpHead;
  c.next.prev = tmpHead.next;
 }

 /*
  * TODO: Performs a triple cut on the deck using the two input cards. You 
  * can assume that the input cards belong to the deck and the first one is 
  * nearest to the top of the deck. This method runs in O(1)
  */
 public void tripleCut(Card firstCard, Card secondCard) {
  /**** ADD CODE HERE ****/
  firstCard.next = head;
  head.prev = secondCard;
  secondCard.prev = firstCard;

 }

 /*
  * TODO: Performs a count cut on the deck. Note that if the value of the 
  * bottom card is equal to a multiple of the number of cards in the deck, 
  * then the method should not do anything. This method runs in O(n).
  */
 public void countCut() {
  /**** ADD CODE HERE ****/

 }

 /*
  * TODO: Returns the card that can be found by looking at the value of the 
  * card on the top of the deck, and counting down that many cards. If the 
  * card found is a Joker, then the method returns null, otherwise it returns
  * the Card found. This method runs in O(n).
  */
 public Card lookUpCard() {
  /**** ADD CODE HERE ****/
  return null;
 }

 /*
  * TODO: Uses the Solitaire algorithm to generate one value for the keystream 
  * using this deck. This method runs in O(n).
  */
 public int generateNextKeystreamValue() {
  /**** ADD CODE HERE ****/
  return 0;
 }


 public abstract class Card { 
  public Card next;
  public Card prev;

  public abstract Card getCopy();
  public abstract int getValue();

 }

 public class PlayingCard extends Card {
  public String suit;
  public int rank;

  public PlayingCard(String s, int r) {
   this.suit = s.toLowerCase();
   this.rank = r;
  }

  public String toString() {
   String info = "";
   if (this.rank == 1) {
    //info += "Ace";
    info += "A";
   } else if (this.rank > 10) {
    String[] cards = {"Jack", "Queen", "King"};
    //info += cards[this.rank - 11];
    info += cards[this.rank - 11].charAt(0);
   } else {
    info += this.rank;
   }
   //info += " of " + this.suit;
   info = (info + this.suit.charAt(0)).toUpperCase();
   return info;
  }

  public PlayingCard getCopy() {
   return new PlayingCard(this.suit, this.rank);   
  }

  public int getValue() {
   int i;
   for (i = 0; i < suitsInOrder.length; i++) {
    if (this.suit.equals(suitsInOrder[i]))
     break;
   }

   return this.rank + 13*i;
  }

 }

 public class Joker extends Card{
  public String redOrBlack;

  public Joker(String c) {
   if (!c.equalsIgnoreCase("red") && !c.equalsIgnoreCase("black")) 
    throw new IllegalArgumentException("Jokers can only be red or black"); 

   this.redOrBlack = c.toLowerCase();
  }

  public String toString() {
   //return this.redOrBlack + " Joker";
   return (this.redOrBlack.charAt(0) + "J").toUpperCase();
  }

  public Joker getCopy() {
   return new Joker(this.redOrBlack);
  }

  public int getValue() {
   return numOfCards - 1;
  }

  public String getColor() {
   return this.redOrBlack;
  }
 }

}
