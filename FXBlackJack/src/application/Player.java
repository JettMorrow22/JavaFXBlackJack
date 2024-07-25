package application;

import java.util.ArrayList;

public class Player {

    private ArrayList<Hand> hands;
    private int balance = 2500;
    
    public Player() {
        hands = new ArrayList<Hand>();
    }
    
    /**
     * Method to determine if all of the player's hands busted
     * @return true if all the hands busted
     */
    public boolean allHandsBusted() {
        boolean status = true;
        for (int x = 0; x < hands.size(); x++) {
            status = status && hands.get(x).getDidBust();
        }
        return status;
    }
    
    /**
     * Adds hand to the player Hands
     */
    public void createHand() {
        hands.add(new Hand());
    }
    
    /**
     * Adds newly dealt card to the players hand
     * 
     * @param card card in players hand
     */
    public void addToHand(int hand,Card card) {
        hands.get(hand).addToHand(card);
    }
    
    /**
     * getter for hand in hands
     * @return return hand
     */
    public Hand getHand(int hand) {
        return hands.get(hand);
    }
    
    /**
     * basic getter for field hands    
     * @return
     */
    public ArrayList<Hand> getHands() {
        return hands;
    }

    /**
     * Simple getter for balance field
     * @return balance
     */
    public int getBalance() {
        return balance;
    }
    
    /**
     * Player won their bet
     * @param num new balance value
     */
    public void increaseBalance(int num) {
        balance += num;
    }
    
    /**
     * Player lost their bet
     * @param num new balance value
     */
    public void decreaseBalance(int num) {
        balance -= num;
    }
}
