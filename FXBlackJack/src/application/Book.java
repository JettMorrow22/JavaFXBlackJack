package application;

public class Book {
    
    //I need 3 2d arrays to represent hard, soft, and pairs
    //the arrays will be look ups name[player][dealer]
    
    private String[][] hardHands;
    //when ace is 11
    private String[][] softHands;
    private String[][] pairHands;
    
    
    public Book() {
        hardHands = new String[][]{
            //2    3    4    5    6    7    8    9    10   A
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 5
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 6
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 7
            {"H", "H", "H", "H", "H", "H", "H", "H", "H", "H"}, // 8
            {"H", "D", "D", "D", "D", "H", "H", "H", "H", "H"}, // 9
            {"D", "D", "D", "D", "D", "D", "D", "D", "H", "H"}, // 10
            {"D", "D", "D", "D", "D", "D", "D", "D", "D", "D"}, // 11
            {"H", "H", "S", "S", "S", "H", "H", "H", "H", "H"}, // 12
            {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 13
            {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 14
            {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 15
            {"S", "S", "S", "S", "S", "H", "H", "H", "H", "H"}, // 16
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // 17
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // 18
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // 19
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}  // 20
        };
        
        softHands = new String[][] {
            //2    3    4    5    6    7    8    9    10   A
            {"H", "H", "H", "D", "D", "H", "H", "H", "H", "H"}, // A2 (13)
            {"H", "H", "H", "D", "D", "H", "H", "H", "H", "H"}, // A3 (14)
            {"H", "H", "D", "D", "D", "H", "H", "H", "H", "H"}, // A4 (15)
            {"H", "H", "D", "D", "D", "H", "H", "H", "H", "H"}, // A5 (16)
            {"H", "D", "D", "D", "D", "H", "H", "H", "H", "H"}, // A6 (17)
            {"S", "D", "D", "D", "D", "S", "S", "H", "H", "H"}, // A7 (18)
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // A8 (19)
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}  // A9 (20)
        };
        
        pairHands = new String[][] {
            //2    3    4    5    6    7    8    9    10   A
            {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 2-2
            {"P", "P", "P", "P", "P", "P", "H", "H", "H", "H"}, // 3-3
            {"H", "H", "H", "P", "P", "H", "H", "H", "H", "H"}, // 4-4
            {"D", "D", "D", "D", "D", "D", "D", "D", "H", "H"}, // 5-5
            {"P", "P", "P", "P", "P", "H", "H", "H", "H", "H"}, // 6-6
            {"P", "P", "P", "P", "P", "P", "P", "H", "H", "H"}, // 7-7
            {"P", "P", "P", "P", "P", "P", "P", "P", "P", "P"}, // 8-8
            {"P", "P", "P", "P", "P", "S", "P", "P", "S", "S"}, // 9-9
            {"S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}, // 10-10
            {"P", "P", "P", "P", "P", "P", "P", "P", "P", "P"}  // A-A
        };
    }
    
    //
    public String decision(Game game, int hand) {
        //determine which table to take from
        
        //player hand total vs dealer up card
        int player = game.calculateHand(game.getPlayer().getHand(hand).getList());
        int dealer = game.getCardValue(game.getDealer().getHand().getCard(0).getValue()) - 2;
        
        boolean canSplit = game.getPlayer().getBalance() >= game.getPlayer().getHand(hand).getBetAmount();
        //do we have enough to split, only two cards, and they a pair
        if ( canSplit && game.getPlayer().getHand(hand).getList().size() == 2 &&
             game.getCardValue(game.getPlayer().getHand(hand).getCard(0).getValue()) == 
             game.getCardValue(game.getPlayer().getHand(hand).getCard(1).getValue())) {
            int pair = game.getCardValue(game.getPlayer().getHand(hand).getCard(0).getValue());   
            return pairHands[pair - 2][dealer];
        }
        else if(game.hasAce(game.getPlayer().getHand(hand).getList())) { //do we have a soft ace 
            return softHands[player - 13][dealer];
        }
        else { //hard hand
            return hardHands[player - 5][dealer];
        }
    }
}
