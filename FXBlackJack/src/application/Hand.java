package application;

import java.util.ArrayList;

public class Hand {
    
    private ArrayList<Card> hand;
    private boolean didBust;
    private int betAmount;
    
    
    public Hand() {
        hand = new ArrayList<Card>();
        didBust = false;
        betAmount = 0;
    }
    
    /**
     * Returns the specific card of the hand
     * @param num the card to be selected
     * @return the num-th card of the hand
     */
    public Card getCard(int num) {
        return hand.get(num);
    }
    
    public Card getTopCard() {
        return hand.get(hand.size() - 1);
    }
    
    /**
     * Basic getter for betAmount field
     * @return the value of betAmount
     */
    public int getBetAmount() {
        return betAmount;
    }

    /**
     * Bassic setter for betAmount field
     * @param betAmount new value for betAmount
     */
    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }
    
    public void increaseBetAmount(int betAmount) {
        this.betAmount += betAmount;
    }

    public void decreaseBetAmount(int betAmount) {
        this.betAmount -= betAmount;
    }
    /**
     * adds to hand
     * @param card card to add
     */
    public void addToHand(Card card) {
        hand.add(card);
    }

    /**
     * Basic getter for hand
     * @return hand
     */
    public ArrayList<Card> getList() {
        return hand;
    }
    
    /**
     * Basic getter for didBust field
     * @return didBust field
     */
    public boolean getDidBust() {
        return didBust;
    }

    /**
     * Basic setter for didBust Field
     * @param didBust new value for field
     */
    public void setDidBust(boolean didBust) {
        this.didBust = didBust;
    }
       
}
