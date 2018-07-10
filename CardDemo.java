package com.company;

public class CardDemo {
    public static void main(String[] args) {
        // Use the Card constructor with two parameters.
        Card c1 = new Card(7, Card.HEARTS);          // 7 of Hearts
        Card c2 = new Card(Card.JACK, Card.SPADES);  // Jack of Spades
        Card c3 = new Card(Card.ACE, Card.CLUBS);    // Ace of Clubs

        // Use the Card constructor with three parameters
        // to create an Ace of Diamonds with a value of 11.
        Card c4 = new Card(Card.ACE, Card.DIAMONDS, 11);

        // Use the Card constructor with three parameters
        // to create a Jack of Spades with a value of 11.
        Card c5 = new Card(Card.JACK, Card.SPADES, 11);

        System.out.println("c1 = " + c1);    // toString() is called
        System.out.println("     rank: " + c1.getRank());
        System.out.println("     suit: " + c1.getSuit());
        System.out.println("    value: " + c1.getValue());
        System.out.println("   abbrev: " + c1.getAbbrev());
        System.out.println("      ace? " + c1.isAce());
        System.out.println("face card? " + c1.isFaceCard());
        System.out.println();

        System.out.println("c2 = " + c2);    // toString() is called
        System.out.println("     rank: " + c2.getRank());
        System.out.println("     suit: " + c2.getSuit());
        System.out.println("    value: " + c2.getValue());
        System.out.println("   abbrev: " + c2.getAbbrev());
        System.out.println("      ace? " + c2.isAce());
        System.out.println("face card? " + c2.isFaceCard());
        System.out.println();

        System.out.println("c3 = " + c3);    // toString() is called
        System.out.println("     rank: " + c3.getRank());
        System.out.println("     suit: " + c3.getSuit());
        System.out.println("    value: " + c3.getValue());
        System.out.println("   abbrev: " + c3.getAbbrev());
        System.out.println("      ace? " + c3.isAce());
        System.out.println("face card? " + c3.isFaceCard());
        System.out.println();

        System.out.println("c4 = " + c4);    // toString() is called
        System.out.println("     rank: " + c4.getRank());
        System.out.println("     suit: " + c4.getSuit());
        System.out.println("    value: " + c4.getValue());
        System.out.println("   abbrev: " + c4.getAbbrev());
        System.out.println("      ace? " + c4.isAce());
        System.out.println("face card? " + c4.isFaceCard());
        System.out.println();

        System.out.println("c5 = " + c5);    // toString() is called
        System.out.println("     rank: " + c5.getRank());
        System.out.println("     suit: " + c5.getSuit());
        System.out.println("    value: " + c5.getValue());
        System.out.println("   abbrev: " + c5.getAbbrev());
        System.out.println("      ace? " + c5.isAce());
        System.out.println("face card? " + c5.isFaceCard());
        System.out.println();

        System.out.println("c1.equals(c1) = " + c1.equals(c1));
        System.out.println("c1.equals(c2) = " + c1.equals(c2));
        System.out.println("c3.equals(c4) = " + c3.equals(c4));
        System.out.println("c2.equals(c5) = " + c2.equals(c5));
        System.out.println();

        System.out.println("changing the value of c5 to 10...");
        c5.setValue(10);
        System.out.println("c5's value is now " + c5.getValue());
    }
}