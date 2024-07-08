package application;

import java.util.ArrayList;
import java.util.HashMap;

public class Game {

    private Player player;
    private Dealer dealer;
    private Deck deck;
    
    private static HashMap<String, Integer> cardValues;

    
    public Game() {
        player = new Player();
        dealer = new Dealer();
        deck = new Deck();
        cardValues = createCardValuesMap();
    }

    public void startGame() {
        //must start betting
    }
    
    
    public void bettingCycle() {
        //firstly add card to dealer and player hands
        player.addToHand(0, deck.deal());
        dealer.addToHand(deck.deal());
        player.addToHand(0, deck.deal());
        dealer.addToHand(deck.deal());
        
        //now must show these on the screen
        //create image views 
    }
    
    /**
     * simple method to determine if the hand busted
     * added functionality of soft aces, if hand is busted, check if it has an
     * ace, if so change it to soft and return false
     * @param hand hand in questoin
     * @return true if busted false if not
     */
    public Boolean didBust(ArrayList<Card> hand) {
        
        if (calculateHand(hand) > 21) {
            if (hasAce(hand)) {
                changeAceToSoft(hand);
                return calculateHand(hand) > 21;
            }
            return true;
        }
        return false;
    }
    
    
    /**
     * calculates the total of the hand using cardValues hashMap
     * @param hand arraylist of hand
     * @return total of hand
     */
    public int calculateHand(ArrayList<Card> hand) {
        int sum = 0; 
        for (int x = 0; x < hand.size(); x++) {
            sum += cardValues.get(hand.get(x).getValue());
        }
        return sum;
    }
    
    /**
     * Function to determine if a hand contains an ace
     * @param hand hand in question
     * @return true if ace, false if not
     */
    public boolean hasAce(ArrayList<Card> hand) {
        for (int x = 0; x < hand.size(); x++) {
            if (hand.get(x).getValue().equals("A")) {
                return true;
            }
        }
        return false;
 
    }
    
    /**
     * Function to change ace in hand to soft ace == 11 -> 1
     * @param hand hand that has the ace
     */
    public void changeAceToSoft(ArrayList<Card> hand) {
        //find the ACE and change to ace
        int num = 0;
       
        while (!hand.get(num).getValue().equals("A")) { 
            num++;
        }
        hand.get(num).setValue("a");
    }
    
    public int getCardValue(String s) {
        return cardValues.get(s);
    }
    
    /**
     * creates hashMap of card values strings to ints
     * @return hashMap
     */
    public HashMap<String, Integer> createCardValuesMap() {
        HashMap<String, Integer> temp = new HashMap<String, Integer>();
        for (int x = 2; x <= 9; x++) {
            temp.put("" + x, x);
        }
        temp.put("T", 10);
        temp.put("J", 10);
        temp.put("Q", 10);
        temp.put("K", 10);
        temp.put("A", 11);
        temp.put("a", 1);
        return temp;
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }
}
