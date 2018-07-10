package com.company;

/*
 * Card.java
 *
 * A blueprint class to represent an individual playing card.
 *
 * starter code by course staff, CS 111, Boston University
 *
 * completed by: Laura K. Gross, grossL@bu.edu
 *         date: April, 2009
 *
 */

public class Card {
    // Define class constants for the ranks of non-numeric cards.
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;

    // Define arrays of strings for the rank names and abbreviations.
    // The name of the rank r is given by RANK_NAMES[r].
    // The abbreviation of the rank r is given by RANK_ABBREVS[r].
    // Note the arrays are instance fields for the Card class.
    private static final String[] RANK_NAMES = {
            null, "Ace", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "Jack", "Queen", "King"
    };
    private static final String[] RANK_ABBREVS = {
            null, "A", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "J", "Q", "K"
    };

    // Define class constants for the suits.
    public static final int DIAMONDS = 0;
    public static final int HEARTS = 1;
    public static final int CLUBS = 2;
    public static final int SPADES = 3;

    // Define arrays of strings for the suit names and abbreviations.
    // The name of the suit s is given by SUIT_NAMES[s].
    // The abbreviation of the suit s is given by SUIT_ABBREVS[s].
    private static final String[] SUIT_NAMES = {
            "Diamonds", "Hearts", "Clubs", "Spades"
    };
    private static final String[] SUIT_ABBREVS = {
            "D", "H", "C", "S"
    };

    /*
     * Fields that capture the state of a Card object.
     * We make them private to prevent direct access from outside the class.
     */
    private int rank;
    private int suit;
    private int value;

    /*
     * A constructor that allows clients to specify initial values for all
     * of the fields.  It calls the mutator method setValue(), which
     * performs error-checking.
     */
    public Card(int initialRank, int initialSuit, int initialValue) {
        if (initialRank < 1 || initialRank > 13) {
            throw new IllegalArgumentException();
        }
        else {
            rank = initialRank;
        }
        if (initialSuit < 0 || initialSuit > 3) {
            throw new IllegalArgumentException();
        }
        else {
            suit = initialSuit;
        }
        setValue(initialValue);
    }

    /*
     * A constructor that allows clients to specify initial values for rank
     * and suit only.  It assumes that Aces and numeric cards have a value that is
     * equal to their rank, and that face cards have a value of 10.   It calls the
     * mutator methods, so that they can perform the necessary error-checking.
     */
    public Card(int initialRank, int initialSuit) {
        // Call the other constructor and overwrite value if rank > 10.
        this(initialRank, initialSuit, initialRank); //
        if (rank > 10) {
            setValue(10);
        }
    }

    /*
     * getRank - an accessor method for a Card's rank
     */
    public int getRank() {
        return rank;
    }

    /*
     * getSuit - an accessor method for a Card's suit
     */
    public int getSuit() {
        return suit;
    }

    /*
     * getValue - an accessor method for a Card's value
     */
    public int getValue() {
        return value;
    }

    /*
     * setValue - a mutator method that changes a Card's value
     *
     * precondition: value must be non-negative.
     */
    public void setValue(int newValue) {
        if (newValue < 0) {
            throw new IllegalArgumentException();
        }
        value = newValue;
    }

    /*
     * getAbbrev - an accessor method that returns a string representing the abbreviation
     * of the Card, which is formed by combining the abbreviations of its rank and suit.
     * For example, the abbreviation of a King of Hearts is the string "KH", and the
     * abbreviation of a 7 of Clubs is "7C".
     */
    public String getAbbrev() {
        return (RANK_ABBREVS[rank] + SUIT_ABBREVS[suit]);
    }

    /*
     * isAce - an accessor method that returns true if Card is an ace and false otherwise.
     */
    public boolean isAce() {
        return rank == 1;
    }

    /*
     * isFaceCard - an accessor method that returns true if Card is a  face card (Jack,
     * Queen, or King) and and false if it is not.
     */
    public boolean isFaceCard() {
        return rank > 10;
    }

    /*
     * toString - returns a string representation of the Card of the form "rank_name of
     * suit_name". For example, if a Card object represents a 10 of Diamonds, this method
     * should return "10 of Diamonds". If a Card object represents a Queen of Spades,
     * this method should return "Queen of Spades".
     */
    public String toString() {
        return RANK_NAMES[rank] + " of " + SUIT_NAMES[suit];
    }

    /*
     * equals - returns true if the Card specified by the parameter
     * is equivalent to the calling object, and false otherwise.
     *
     * Note: this method does *not* override the default equals method,
     * because its parameter is of type Card.
     */
    public boolean equals(Card other) {
        return (other != null
                && rank == other.rank
                && suit == other.suit);
    }

}