package application;

public class Card {
    
    private String value;
    private String fileName;
  
    
    /**
     * To initialize a card we need a value
     * these will be "2-9, T, J, Q, K, A"
     * T, J, Q, & K = 10 A = 1,11
     * @param num the value of the card initialized 
     * @param name the filename of the card which includes suit
     */
    public Card(String num, String name) {
        value = num;
        fileName = name;
    }
    
    
    public String getFileName() {
        return fileName;
    }
    
        
    /**
     * basic getter for value field
     * @return the field value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * basic setter for value field
     * @param s the new value of the card
     */
    public void setValue( String s) {
        value = s;
    }
    
    /**
     * Override equals method to determine if two cards are the same
     * two cards are the same if their value is the same
     * @param o the object we are determining if it is equal or not
     * @return true if equal false if not
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        //a Card is equal if there value are the same
        if (this.getClass() == o.getClass()) {
            Card temp = (Card)o;
            return (this.getValue().equals(temp.getValue()));
        }
        return false;
    }
}
