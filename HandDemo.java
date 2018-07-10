package com.company;

public class HandDemo {
    public static void main(String[] args) {
        // Create a Hand object capable of holding 5 cards.
        System.out.println("Creating hand h1.");
        Hand h1 = new Hand(5);

        // Print the number of cards in the hand h1 (initially 0).
        System.out.print("Number of cards in hand h1: ");
        System.out.println(h1.getNumCards());
        System.out.println();

        // Start by adding three cards to it.
        System.out.println("Add three Cards to hand h1.");
        h1.addCard(new Card(7, Card.HEARTS));
        h1.addCard(new Card(Card.JACK, Card.SPADES));
        h1.addCard(new Card(Card.ACE, Card.CLUBS));

        System.out.println("h1 = " + h1);    // toString() is called
        System.out.println("    # cards: " + h1.getNumCards());
        System.out.println("     card 0: " + h1.getCard(0));
        System.out.println("     card 1: " + h1.getCard(1));
        System.out.println("     card 2: " + h1.getCard(2));
        System.out.println("total value: " + h1.getTotalValue());
        System.out.println("  high card: " + h1.highCard());
        System.out.println("  # of aces: " + h1.numCardsOfRank(Card.ACE));
        System.out.println("       pair? " + h1.hasPair());
        System.out.println("  two pairs? " + h1.hasTwoPairs());
        System.out.println("3-of-a-kind? " + h1.hasThreeOfAKind());
        System.out.println("      flush? " + h1.hasFlush());
        System.out.println("4-of-a-kind? " + h1.hasFourOfAKind());
        System.out.println("  hand type: " + h1.handType());
        System.out.println();

        // Add two more Aces, one of which has a value of 11.
        System.out.println("adding two more cards...");
        h1.addCard(new Card(Card.ACE, Card.DIAMONDS));
        h1.addCard(new Card(Card.ACE, Card.HEARTS, 11));

        System.out.println("h1 = " + h1);    // toString() is called
        System.out.println("    # cards: " + h1.getNumCards());
        System.out.println("     card 3: " + h1.getCard(3));
        System.out.println("     card 4: " + h1.getCard(4));
        System.out.println("  4's value: " + h1.getCard(4).getValue());
        System.out.println("total value: " + h1.getTotalValue());
        System.out.println("  high card: " + h1.highCard());
        System.out.println("  # of aces: " + h1.numCardsOfRank(Card.ACE));
        System.out.println("       pair? " + h1.hasPair());
        System.out.println("  two pairs? " + h1.hasTwoPairs());
        System.out.println("3-of-a-kind? " + h1.hasThreeOfAKind());
        System.out.println("      flush? " + h1.hasFlush());
        System.out.println("4-of-a-kind? " + h1.hasFourOfAKind());
        System.out.println("  hand type: " + h1.handType());
        System.out.println();

        // Play the Jack.
        System.out.println("playing card 1...");
        Card c = h1.playCard(1);
        System.out.println("card played = " + c);
        System.out.println("h1 = " + h1);    // toString() is called
        System.out.println();

        // Add a fourth Ace.
        System.out.println("adding another card...");
        h1.addCard(new Card(Card.ACE, Card.SPADES));
        System.out.println("h1 = " + h1);    // toString() is called
        System.out.println("    # cards: " + h1.getNumCards());
        System.out.println("     card 3: " + h1.getCard(3));
        System.out.println("     card 4: " + h1.getCard(4));
        System.out.println("  # of aces: " + h1.numCardsOfRank(Card.ACE));
        System.out.println("       pair? " + h1.hasPair());
        System.out.println("  two pairs? " + h1.hasTwoPairs());
        System.out.println("3-of-a-kind? " + h1.hasThreeOfAKind());
        System.out.println("      flush? " + h1.hasFlush());
        System.out.println("4-of-a-kind? " + h1.hasFourOfAKind());
        System.out.println("  hand type: " + h1.handType());
        System.out.println();

        // Create a Hand that is a flush.
        Hand h2 = new Hand(5);
        h2.addCard(new Card(10, Card.HEARTS));
        h2.addCard(new Card(Card.QUEEN, Card.HEARTS));
        h2.addCard(new Card(Card.KING, Card.HEARTS));
        h2.addCard(new Card(2, Card.HEARTS));
        h2.addCard(new Card(6, Card.HEARTS));

        System.out.println("creating a second hand...");
        System.out.println("h2 = " + h2);    // toString() is called
        System.out.println("    # cards: " + h2.getNumCards());
        System.out.println("total value: " + h2.getTotalValue());
        System.out.println("  high card: " + h2.highCard());
        System.out.println("  # of aces: " + h2.numCardsOfRank(Card.ACE));
        System.out.println("       pair? " + h2.hasPair());
        System.out.println("  two pairs? " + h2.hasTwoPairs());
        System.out.println("3-of-a-kind? " + h2.hasThreeOfAKind());
        System.out.println("      flush? " + h2.hasFlush());
        System.out.println("4-of-a-kind? " + h2.hasFourOfAKind());
        System.out.println("  hand type: " + h2.handType());
        System.out.println();

        System.out.println("h1.isBetterHandThan(h2) = " + h1.isBetterHandThan(h2));
        System.out.println("h2.isBetterHandThan(h1) = " + h2.isBetterHandThan(h1));
        System.out.println();

        System.out.println("removing one Ace from h1...");
        h1.playCard(3);
        System.out.println("h1 = " + h1);
        System.out.println("hand type is now " + h1.handType());
        System.out.println();

        System.out.println("h1.isBetterHandThan(h2) = " + h1.isBetterHandThan(h2));
        System.out.println("h2.isBetterHandThan(h1) = " + h2.isBetterHandThan(h1));
        System.out.println();

        // Try to make invalid changes.
        System.out.println("Trying to add a card to a full hand...");
        try {
            h2.addCard(new Card(7, Card.CLUBS));
        } catch(IllegalArgumentException e) {
            System.out.println("Caught an IllegalArgumentException...");
        }
        System.out.println("Trying to get a nonexistent card...");
        try {
            h2.getCard(7);
        } catch(IllegalArgumentException e) {
            System.out.println("Caught an IllegalArgumentException...");
        }

    }
}