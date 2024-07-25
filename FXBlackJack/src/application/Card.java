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
}
