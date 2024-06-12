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
            "T_of_clubs", "T_of_spades", "T_of_hearts", "T_of_diamonds",
            "J_of_clubs", "J_of_spades", "J_of_hearts", "J_of_diamonds",
            "Q_of_clubs", "Q_of_spades", "Q_of_hearts", "Q_of_diamonds",
            "K_of_clubs", "K_of_spades", "K_of_hearts", "K_of_diamonds",
            "A_of_clubs", "A_of_spades", "A_of_hearts", "A_of_diamonds",
            };
        //loops through all the decks we gonna use and creates card with value
        //adds the card to the current working deck
        for (int decks = 0; decks < 6; decks++ ) {
            for (int x = 0; x < classicDeck.length; x++ ) {
                deck.add(new Card(classicDeck[x].substring(0, 1),classicDeck[x]));
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
