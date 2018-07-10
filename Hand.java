package com.company;

/*
 * Hand.java
 *
 * Starter code from Computer Science 111, Boston University
 *
 * modified by Laura K. Gross
 *
 */

/**
 * AlexanderZaurov
 * <p>
 * and
 * <p>
 * JuliaGillis
 */

public class Hand {
    // Constants for types of hands
    // The numbers used for the hand
    // types increase with the value of the hand type.
    // For example, four-of-a-kind is the highest-valued
    // hand type that we support, and it has the highest
    // numeric value.
    private static final int HIGH_CARD = 0;
    private static final int PAIR = 1;
    private static final int TWO_PAIRS = 2;
    private static final int THREE_OF_A_KIND = 3;
    private static final int FLUSH = 4;
    private static final int FOUR_OF_A_KIND = 5;

    // The fields for a hand.
    // The Card objects in the hand are stored in array called cards.
    private Card[] cards;
    // The number of cards in the hand is called numCards.
    private int numCards;


    /* Put a Hand constructor here to allow client code to specify
     the maximum size of the hand for a given type of card game.
     */
    public Hand(int max) {
        cards = new Card[max];
        numCards = 0;

    }

    /*
    Accessor constructor that get the number of cards in hand.  Named getNumCards
     */

    public int getNumCards() {

        return numCards;
    }

    /*
    Mutator that will add a card to the hand, which will change the cards in hand (DUMMY)
     */

    public void addCard(Card newCard) {


        if (numCards < cards.length) {

            cards[numCards] = newCard;
            numCards++;
        } else {
            throw new IllegalArgumentException();
        }


    }



    /*
    Accessor that retrieves the card at a given index
     */

    public Card getCard(int cardPostion) {

        if (cardPostion < 0 || cardPostion > cards.length) {
            throw new IllegalArgumentException();
        }
        return cards[cardPostion];
    }

    /*
    Accessor that retrieves the play card
     */

    public Card playCard(int playPosition) {

        Card temp = cards[playPosition];

        for (int i = playPosition; i < numCards - 1; i++) {
            cards[i] = cards[i + 1];
        }
        if (playPosition < 0 || playPosition > numCards) {
            throw new IllegalArgumentException();
        }
        numCards--;

        return temp;
    }

    /*
    Accessor that retrieves the highest card from the hand (DUMMY)
     */

    public Card highCard() {
        Card cardmax = cards[0];
        for (int i = 1; i < numCards; i++) {
            if (cards[i].getValue() > cardmax.getValue()) {
                cardmax = cards[i];
            }
        }
        return cardmax;
    }

    /*
    Finding the number of cards of rank in the hand
     */
    public int numCardsOfRank(int rank) {
        int numRank = 0;
        for (int i = 0; i < numCards; i++) {
            if (cards[i].getRank() == rank) {
                numRank++;
            }
        }
        return numRank;
    }

    /*
     * Logic operator that tests to see if the Hand has a pair and return true if found
     */
    public boolean hasPair() {
        for (int rank = 1; rank <= 13; rank++) {
            if (numCardsOfRank(rank) >= 2) {
                return true;
            }
        }
        return false;
    }

    /*
    Logic operator that tests to see if the Hand has three of a kind and return true if found
     */
    public boolean hasThreeOfAKind() {
        for (int rank = 1; rank <= 13; rank++) {
            if (numCardsOfRank(rank) >= 3) {
                return true;
            }
        }

        return false;
    }

    /*
    Logic operator that tests to see if the Hand has two pairs and returns true if found
     */
    public boolean hasTwoPairs() {
        int count = 0;
        for (int rank = 1; rank <= 13; rank++) {
            if (numCardsOfRank(rank) >= 2) {
                count++;
            }
            if (numCardsOfRank(rank) >= 4) {
                return true;
            }
        }
        if (count == 2) {
            return true;
        }
        return false;
    }

    /*
      Logic operator that tests to see if the Hand has a flush and returns true if found
     */
    public boolean hasFlush() {
        if (cards[0].getSuit() == cards[1].getSuit() && cards[0].getSuit() == cards[2].getSuit() && cards[0].getSuit() == cards[3].getSuit() && cards[0].getSuit() == cards[4].getSuit()) {
            return true;
        }
        return false;
    }

    /*
      Logic operator that tests to see if the Hand has four of a kind and returns true if found
     */
    public boolean hasFourOfAKind() {
        for (int rank = 1; rank <= 13; rank++) {
            if (numCardsOfRank(rank) >= 4) {
                return true;
            }
        }

        return false;
    }

    /*
    Method that scores the Hand based on the cards
     */
    public int handType() {


        int pair = 0;
        int twoOfKind = 0;
        int threeOfKind = 0;
        int flush = 0;
        int fourOfKind = 0;

        if (hasPair()) {
            pair = PAIR;
        }
        if (hasTwoPairs()) {
            twoOfKind = TWO_PAIRS;
        }
        if (hasThreeOfAKind()) {
            threeOfKind = THREE_OF_A_KIND;
        }
        if (hasFlush()) {
            flush = FLUSH;
        }
        if (hasFourOfAKind()) {
            fourOfKind = FOUR_OF_A_KIND;
        }

        if (fourOfKind > flush && fourOfKind > threeOfKind && fourOfKind > twoOfKind && fourOfKind > pair) {
            return FOUR_OF_A_KIND;
        }
        if (flush > fourOfKind && flush > threeOfKind && flush > twoOfKind && flush > pair) {
            return FLUSH;
        }
        if (threeOfKind > pair && threeOfKind > twoOfKind && threeOfKind > flush && threeOfKind > fourOfKind) {
            return THREE_OF_A_KIND;
        }
        if (twoOfKind > pair && twoOfKind > threeOfKind && twoOfKind > flush && twoOfKind > fourOfKind) {
            return TWO_PAIRS;
        }
        if (pair > twoOfKind && pair > threeOfKind && pair > flush && pair > fourOfKind) {
            return PAIR;
        } else {
            return HIGH_CARD;
        }


    }

    /*
    Method that compares one Hand to another Hand and return true if first hand is better than the other,
    or false if the other hand is better based on the handType
     */
    public boolean isBetterHandThan(Hand other) {

        return handType() > other.handType();

    }

    /*
    This is the toString method that outputs the Hand grabbing getAbbrev method which is the rank and suit of
    each card separated by commas
     */
    public String toString() {
        int i;
        String Abbrv = "";
        for (i = 0; i < numCards - 1; i++) {
            Abbrv += cards[i].getAbbrev() + ", ";

        }
        for (i = numCards - 1; i < numCards; i++) {
            Abbrv += cards[i].getAbbrev();
        }
        return "[" + Abbrv + "]";
    }

    /*
     * getTotalValue - computes and returns the total of the
     * values of the cards in the hand.
     */
    public int getTotalValue() {
        int value = 0;
        for (int i = 0; i < numCards; i++) {
            value += cards[i].getValue();
        }
        return value;
    }

}