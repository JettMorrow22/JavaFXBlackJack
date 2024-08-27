package application;

public class Simulations {
    private int games;
    private int startingBalance;
    private int betAmount;
    private int desiredProfit;
    
    private Game game;    
    
    public Simulations(int g, int sB, int bA, int dP, Game ga) {
        games = g;
        startingBalance = sB;
        betAmount = bA;
        desiredProfit = dP;
        game = ga;
    }
}
