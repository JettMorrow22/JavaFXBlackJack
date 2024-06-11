package application;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck;
    
    
    public Deck() {
        deck = new ArrayList<Card>();
        initalizeDeck();
        shuffleDeck();
    }
    
    /**
     * Method that creates the arrayList deck based off how many decks they are
     * playing with
     */
    public void initalizeDeck() {
        //each deck has 4 of each number 2-A
        //13 total numbers * 4 so 52 cards * numOfDecks
        String[] classicDeck = {"2_of_clubs", "2_of_spades", "2_of_hearts", "2_of_diamonds", 
            "3_of_clubs", "3_of_spades", "3_of_hearts", "3_of_diamonds",
            "4_of_clubs", "4_of_spades", "4_of_hearts", "4_of_diamonds",
            "5_of_clubs", "5_of_spades", "5_of_hearts", "5_of_diamonds",
            "6_of_clubs", "6_of_spades", "6_of_hearts", "6_of_diamonds",
            "7_of_clubs", "7_of_spades", "7_of_hearts", "7_of_diamonds",
            "8_of_clubs", "8_of_spades", "8_of_hearts", "8_of_diamonds",
            "9_of_clubs", "9_of_spades", "9_of_hearts", "9_of_diamonds",
            "10_of_clubs", "10_of_spades", "10_of_hearts", "10_of_diamonds",
            "jack_of_clubs", "jack_of_spades", "jack_of_hearts", "jack_of_diamonds",
            "queen_of_clubs", "queen_of_spades", "queen_of_hearts", "queen_of_diamonds",
            "king_of_clubs", "king_of_spades", "king_of_hearts", "king_of_diamonds",
            "ace_of_clubs", "ace_of_spades", "ace_of_hearts", "ace_of_diamonds",
            };
        //loops through all the decks we gonna use and creates card with value
        //adds the card to the current working deck
        for (int decks = 0; decks < 6; decks++ ) {
            for (int x = 0; x < classicDeck.length; x++ ) {
                deck.add(new Card(classicDeck[x]));
            }
        }
    }
    
    /**
     * shuffles the deck using collections method
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    /**
     * removes and returns the top card to deal 
     * @return the top card of the deck
     */
    public Card deal() {
        if ( deck.size() < ((6 * 52)/4) ) {
            deck.clear();
            initalizeDeck();
            shuffleDeck();
        }
        return deck.remove(0);
    }
}
