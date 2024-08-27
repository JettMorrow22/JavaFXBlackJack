package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController implements Initializable{

    @FXML
    private StackPane stackPane;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private HBox rootHBox;
   
    @FXML
    private VBox middleVBox;
    //three main container for middle VBox
    @FXML
    private VBox dealerVBox;
    
    @FXML
    private HBox dealerCardBox;
    @FXML
    private Label dealerHandTotal;
 
    @FXML
    private Label middleLabel;
    
    @FXML
    private HBox playerHBox;
    @FXML
    private VBox playerVBox1;
    @FXML
    private HBox playerCardBox1;
    @FXML
    private Label playerTotal1;
    @FXML
    private Label playerBetAmount1;
    
    @FXML
    private VBox rightVBox;
    
    @FXML
    private ImageView deckImage;
    
    @FXML
    private VBox buttonVBox;
    @FXML
    private Button splitButton;
    @FXML
    private Button doubleButton;
    @FXML
    private Button hitButton;
    @FXML
    private Button standButton;
    
    @FXML
    private VBox labelVBox;
   
    @FXML
    private Label balanceLabel;
    @FXML
    private Label gamesLabel;
    @FXML
    private Label wonLabel;
    @FXML
    private Label lostLabel;
    @FXML
    private Label pushLabel;
    @FXML
    private Button hintButton;
    
    //containers for playerCardBox2
    private VBox playerVBox2;
    private HBox playerCardBox2;
    private Label playerTotal2;
    private Label playerBetAmount2;
    
    private int curHand = 0;
    
   
    private Game game;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public GameController(Game g) {
        game  = g; 
    }
    
    public void hintButton(ActionEvent e)throws IOException {
        String action = game.getBook().decision(game, curHand);
        //make the button background be bright red for a half second
        switch (action){
            case "H":
                flashButton(hitButton);
                break;
            case "S": 
                flashButton(standButton);
                break;
            case "D":
                if (game.getPlayer().getBalance() >= game.getPlayer().getHand(curHand).getBetAmount()
                 && game.getPlayer().getHand(curHand).getList().size() == 2) {
                    flashButton(doubleButton);
                }
                else {
                    flashButton(hitButton);
                }
                break;
            case "P": 
                if (game.getPlayer().getHands().size() == 2) {
                    flashButton(hitButton);
                }
                else {
                    flashButton(splitButton);
                }
                break;
            default: 
                System.out.println("What happened");
        }
    }
    
    public void flashButton( Button button) {
        String origStyle = button.getStyle();
        
        Timeline timeline = new Timeline (
            new KeyFrame(Duration.seconds(0), e -> button.setStyle("-fx-background-color: red;")),
            new KeyFrame(Duration.seconds(1), e -> button.setStyle(origStyle))
        );
        
        timeline.play();
            
    }
    
    public void addCardToPlayerBox(int hand) {
        //create container with image and add to scene and update Total
        Image playerCard = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getPlayer().getHand(hand).getTopCard().getFileName() + ".png"));
        ImageView playerImageView = new ImageView(playerCard);
        playerImageView.setPreserveRatio(true);
        ImageViewPane pPane = new ImageViewPane(playerImageView);
        pPane.prefWidthProperty().bind(pPane.heightProperty().multiply(.69));
        
        if (hand == 0) {
            playerCardBox1.getChildren().add(pPane);
            playerTotal1.setText("Total: " + game.calculateHand(game.getPlayer().getHand(0).getList()));
            
        }
        else {
            playerCardBox2.getChildren().add(pPane);
            playerTotal2.setText("Total: " + game.calculateHand(game.getPlayer().getHand(1).getList()));

        }
    }
    
    public void splitButton(ActionEvent e) throws IOException {
        //Create the second hand
        game.getPlayer().createHand();
        game.getPlayer().addToHand(1, game.getPlayer().getHand(0).getList().remove(1));
        game.getPlayer().getHand(1).setBetAmount(game.getPlayer().getHand(0).getBetAmount());
        game.getPlayer().decreaseBalance(game.getPlayer().getHand(0).getBetAmount());
        balanceLabel.setText("Balance: $" + game.getPlayer().getBalance());
        
        //create new containers for playerCardBox2
        playerVBox2 = new VBox();
        playerCardBox2 = new HBox();
        playerTotal2 = new Label("Total: " + game.calculateHand(game.getPlayer().getHand(1).getList()));
        playerTotal2.getStyleClass().add("custom-label");

        playerBetAmount2 = new Label("Bet Amount: $" + game.getPlayer().getHand(1).getBetAmount());
        playerBetAmount2.setFont(new Font(20));
        playerBetAmount2.getStyleClass().add("custom-label");

        
        //bind the dimensions
        playerVBox2.prefWidthProperty().bind(playerHBox.widthProperty().divide(playerHBox.getChildren().size()));
        playerCardBox2.prefHeightProperty().bind(playerVBox2.heightProperty().multiply(.8));
        playerCardBox2.prefWidthProperty().bind(playerVBox2.widthProperty());
        playerBetAmount2.prefHeightProperty().bind(playerVBox2.heightProperty().multiply(.2));
        playerBetAmount2.prefWidthProperty().bind(playerVBox2.widthProperty());

        //remove second card from first hand
        playerCardBox1.getChildren().remove(1);
        
        //update firstHand total
        playerTotal1.setText("Total: " + game.calculateHand(game.getPlayer().getHand(0).getList()));
            
        //add card image to second hand
        playerCardBox2.setAlignment(Pos.CENTER);
        addCardToPlayerBox(1);
        
        //add new container to the left on HBox
        playerVBox2.setAlignment(Pos.CENTER);
        playerBetAmount2.setAlignment(Pos.CENTER);
        playerVBox2.getChildren().addAll(playerCardBox2, playerTotal2, playerBetAmount2);        
        playerHBox.setAlignment(Pos.CENTER);
        playerHBox.getChildren().add(0, playerVBox2);
        
        //NOW I mut play out each hand
        splitButton.setVisible(false);
        
        //deal first card
        hitButton(e);
        
        if (game.getPlayer().getBalance() >= game.getPlayer().getHand(curHand).getBetAmount()) {
            doubleButton.setVisible(true);
        }
        
    }
    
    public void doubleButton(ActionEvent e) throws IOException {
        //take one card, update total
        game.dealPlayer(curHand);
        
        game.getPlayer().getHand(curHand).setDidBust(game.didBust(game.getPlayer().getHand(curHand).getList()));
        
        addCardToPlayerBox(curHand);
        
        //double bet
        game.getPlayer().decreaseBalance(game.getPlayer().getHand(curHand).getBetAmount());
        game.getPlayer().getHand(curHand).setBetAmount(game.getPlayer().getHand(curHand).getBetAmount() * 2);
        
        //update BetAmount
        if ( curHand == 0) {
            playerBetAmount1.setText("Bet Amount: " + game.getPlayer().getHand(curHand).getBetAmount());
        }
        else {
            playerBetAmount2.setText("Bet Amount: " + game.getPlayer().getHand(curHand).getBetAmount());
        }
        balanceLabel.setText("Balance: " + game.getPlayer().getBalance());

        game.getPlayer().getHand(curHand).setDidBust(game.didBust(game.getPlayer().getHand(curHand).getList()));
        
        standButton(e);
    }

    public void standButton(ActionEvent e) throws IOException {
        
        //if we on the last hand then we done
        if ( curHand + 1 == game.getPlayer().getHands().size()) {
            splitButton.setVisible(false);
            doubleButton.setVisible(false);
            hitButton.setVisible(false);
            standButton.setVisible(false);
            hintButton.setVisible(false);
            dealerAction();
        }
        else {
            curHand++;
            hitButton(e);
            doubleButton.setVisible(game.getPlayer().getBalance() >= game.getPlayer().getHand(curHand).getBetAmount());
        }
    }

    public void hitButton(ActionEvent e) throws IOException {
        //try addind card to player Hand and add card to screen and update Total
        
        //must deal another card, add the card to the total
        game.dealPlayer(curHand);
        
        //update if hand busted or not
        game.getPlayer().getHand(curHand).setDidBust(game.didBust(game.getPlayer().getHand(curHand).getList()));
        
        //add card and update Label
        addCardToPlayerBox(curHand);        
        
        //if busted then player hand over
        if (game.getPlayer().getHand(curHand).getDidBust()) {
            game.getPlayer().getHand(curHand).setDidBust(true);
            standButton(e);            
        }
        else if (game.calculateHand(game.getPlayer().getHand(curHand).getList()) == 21) {
            standButton(e);
        }
        else {
            splitButton.setVisible(false);
            doubleButton.setVisible(false);
        }
    }
    
    public void dealerAction() {
        //create the dealers best hand
        if (game.getPlayer().allHandsBusted()) {
            //add to lost
            for (int x = 0; x < game.getPlayer().getHands().size(); x++) {
                game.incrementLost();
            }
            gameOver(new String[] {"Player Busted"});
        }
        else {
            calculateDealerBestHand(() -> {
                gameOver(determineWinner());
            });
        }
    }
    
    public void calculateDealerBestHand(Runnable onComplete) {
        
        //annoying edge case if they have two Aces
        if (game.getPlayer().getHand(0).getCard(0).getValue().equals("A") && 
             game.getPlayer().getHand(0).getCard(1).getValue().equals("A") ){
            game.getPlayer().getHand(0).getCard(1).setValue("a");
        }
        
        //create the best delear hand
        while (game.calculateHand(game.getDealer().getHand().getList()) < 17) {
            game.dealDealer();            
            game.didBust(game.getDealer().getHand().getList());
        }
                
        //show cards on the screen and update total
        dealerCardBox.getChildren().remove(1);
        Timeline dealerCards = new Timeline(); 
        for (int x = 1; x < game.getDealer().getHand().getList().size(); x++) {
            int index = x;
            KeyFrame keyFrame = new KeyFrame(Duration.seconds((x + 1) * 0.5), e -> {
                Image dealerCard1 = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getDealer().getHand().getList().get(index).getFileName() + ".png"));
                ImageView dealerImageView1 = new ImageView(dealerCard1);
                dealerImageView1.setPreserveRatio(true);
                ImageViewPane dPane1 = new ImageViewPane(dealerImageView1);
                dealerCardBox.getChildren().add(dPane1);
                dPane1.prefWidthProperty().bind(dPane1.heightProperty().multiply(.69));
                
                dealerHandTotal.setText("Total: " + game.calculateHand(game.getDealer().getHand().getList().subList(0, index + 1)));
            });
            dealerCards.getKeyFrames().add(keyFrame);
        }
        
        dealerCards.setOnFinished(e -> onComplete.run());
        dealerCards.play();
    }
    
    public String[] determineWinner() {
        String[] results = new String[game.getPlayer().getHands().size()];
        for (int x = 0; x < game.getPlayer().getHands().size(); x++) {
            //player hand busted
            if (game.getPlayer().getHand(x).getDidBust()) {
                results[x] = "Player Busted";
                game.incrementLost();
                continue;
            }
            
            //dealer busted
            if (game.calculateHand(game.getDealer().getHand().getList()) > 21) {
                game.getPlayer().increaseBalance(game.getPlayer().getHand(x).getBetAmount() * 2);
                results[x] = "Dealer Busted";
                game.incrementWon();
                continue;
            }
            
            //neither busted compare who is higher
            if (game.calculateHand(game.getPlayer().getHand(x).getList()) >
                game.calculateHand(game.getDealer().getHand().getList()) ) {
                game.getPlayer().increaseBalance(game.getPlayer().getHand(x).getBetAmount() * 2);
                results[x] = "Player Wins";
                game.incrementWon();
            }
            else if (game.calculateHand(game.getPlayer().getHand(x).getList()) <
                game.calculateHand(game.getDealer().getHand().getList()) ) {
                results[x] = "Dealer Wins";
                game.incrementLost();
            }
            else {
                game.getPlayer().increaseBalance(game.getPlayer().getHand(x).getBetAmount());
                results[x] = "Push";
                game.incrementPushed();
            }
            
            //increase Hand played
        }
        
        return results;
    }
    
    public void gameOver(String[] winner) {  
        //clear player and dealer hands
        game.getPlayer().getHands().clear();
        game.getDealer().getHand().getList().clear();
        
        //update midelLabel
        Timeline timelineLabel = new Timeline();
        for (int x = 0; x < winner.length; x++) {
            int index = x; // create a final variable for lambda expression
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(3 * (x + 1)), e -> {
                middleLabel.setText(winner[index]);
            });
            timelineLabel.getKeyFrames().add(keyFrame);
        }
        timelineLabel.play();
        
        //go to betting screen
        Timeline timelineScreen = new Timeline();
        timelineLabel.setOnFinished(event -> {
            //check if money to playAgain
            if (game.getPlayer().getBalance() > 0) {
                // Create a timeline to delay for 5 seconds
                KeyFrame newScreenKeyFrame = new KeyFrame(Duration.seconds(3), e -> {
                //switch to betting screen
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("BettingScreen.fxml"));
                    BettingController bettingController = new BettingController(game);
                    loader.setController(bettingController);
                    root = loader.load();
                    stage = (Stage)stackPane.getScene().getWindow();
                        
                    double width = stage.getWidth();
                    double height = stage.getHeight();
                    double x = stage.getX();
                    double y = stage.getY();
                     
                    scene = new Scene(root);
                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    stage.setScene(scene);
                     
                    stage.setWidth(width);
                    stage.setHeight(height);
                    stage.setX(x);
                    stage.setY(y);
                      
                    stage.show(); 
                    }
                    catch (IOException error) {
                        error.printStackTrace();
                    }
                });
                timelineScreen.getKeyFrames().add(newScreenKeyFrame);
                timelineScreen.play();
                
            }
            else {
                dealerVBox.setVisible(false);
                playerHBox.setVisible(false);
                middleLabel.setText("You have Officially gone broke...");
            }
        });
    }
    
    
    
    public void checkTwoCard() {
        //Determine what the two cards mean

        //determine if split button should be displayed or not
        if ( game.getCardValue(game.getPlayer().getHand(0).getCard(0).getValue()) == 
             game.getCardValue(game.getPlayer().getHand(0).getCard(1).getValue()) && 
                game.getPlayer().getBalance() >= game.getPlayer().getHand(0).getBetAmount()) {
            splitButton.setVisible(true);
        }
        
        //determine if double button should be displayed or not
        if ( game.getPlayer().getBalance() >= game.getPlayer().getHand(0).getBetAmount()) {
            doubleButton.setVisible(true);
        }
        
        //annoying edge case if they have two Aces
        if (game.getPlayer().getHand(0).getCard(0).getValue().equals("A") && 
             game.getPlayer().getHand(0).getCard(1).getValue().equals("A") ){
            game.getPlayer().getHand(0).getCard(1).setValue("a");
        }
        
        //if player has BJ then pay out and round over unless dealer has BJ
        if (game.calculateHand(game.getPlayer().getHand(0).getList()) == 21) {
            //push
            splitButton.setVisible(false);
            doubleButton.setVisible(false);
            hitButton.setVisible(false);
            standButton.setVisible(false);
            
           if (game.calculateHand(game.getDealer().getHand().getList()) == 21) {
               game.getPlayer().increaseBalance(game.getPlayer().getHand(0).getBetAmount());
               game.incrementPushed();
               gameOver(new String[] {"Push"});
           }
           else { //player win
               game.getPlayer().increaseBalance((5 * game.getPlayer().getHand(0).getBetAmount()) / 2);
               game.incrementWon();
               gameOver(new String[] {"Player Has BlackJack"});
           }
        }
    }    
     
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        game.bettingCycle();
        
        balanceLabel.setText("Balance: $" + game.getPlayer().getBalance());
        if (game.getHandsPlayed() > 0) {
            gamesLabel.setText(String.format("Hands Played: %.0f", game.getHandsPlayed()));
            wonLabel.setText(String.format("won: %.0f%%", game.getWon() / game.getHandsPlayed() * 100));
            lostLabel.setText(String.format("lost: %.0f%%", game.getLost() / game.getHandsPlayed() * 100));
            pushLabel.setText(String.format("pushed: %.0f%%", game.getPushed() / game.getHandsPlayed() * 100));
        }
        
        //this makes the imageView the size of the stackPane
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));

        //HBox children to take up equal width and full height
        labelVBox.prefWidthProperty().bind(rootHBox.widthProperty().multiply(.2));
        labelVBox.prefHeightProperty().bind(rootHBox.heightProperty());
        middleVBox.prefWidthProperty().bind(rootHBox.widthProperty().multiply(.5));
        middleVBox.prefHeightProperty().bind(rootHBox.heightProperty());
        rightVBox.prefWidthProperty().bind(rootHBox.widthProperty().multiply(.3));
        rightVBox.prefHeightProperty().bind(rootHBox.heightProperty());
        
        //Height & width for children of middleVBox
        dealerVBox.prefHeightProperty().bind(middleVBox.heightProperty().multiply(.45));
        dealerVBox.prefWidthProperty().bind(middleVBox.widthProperty());
        middleLabel.prefHeightProperty().bind(middleVBox.heightProperty().multiply(.1));
        middleLabel.prefWidthProperty().bind(middleVBox.widthProperty());
        playerHBox.prefHeightProperty().bind(middleVBox.heightProperty().multiply(.45));
        playerHBox.prefWidthProperty().bind(middleVBox.widthProperty());
        playerVBox1.prefHeightProperty().bind(playerHBox.heightProperty());
        playerVBox1.prefWidthProperty().bind(playerHBox.widthProperty().divide(playerHBox.getChildren().size()));
        
        //player
        playerCardBox1.prefHeightProperty().bind(playerVBox1.heightProperty().multiply(.8));
        playerCardBox1.prefWidthProperty().bind(playerVBox1.widthProperty());
        playerTotal1.prefHeightProperty().bind(playerVBox1.heightProperty().multiply(.2));
        playerTotal1.prefWidthProperty().bind(playerVBox1.widthProperty());
        
        //load player cards
        Image playerCard1 = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getPlayer().getHand(0).getCard(0).getFileName() + ".png"));
        Image playerCard2 = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getPlayer().getHand(0).getCard(1).getFileName() + ".png"));
        ImageView playerImageView1 = new ImageView(playerCard1);
        ImageView playerImageView2 = new ImageView(playerCard2);
        playerImageView1.setPreserveRatio(true);
        playerImageView2.setPreserveRatio(true);
        ImageViewPane pane1 = new ImageViewPane(playerImageView1);
        ImageViewPane pane2 = new ImageViewPane(playerImageView2);
        playerCardBox1.getChildren().addAll(pane1, pane2);
        //SETTING THE WIDTH PROPORTIONAL TO THE HEIGHT
        pane1.prefWidthProperty().bind(pane1.heightProperty().multiply(.69));
        pane2.prefWidthProperty().bind(pane2.heightProperty().multiply(.69));
        
        playerBetAmount1.setText("Bet Amount: $" + game.getPlayer().getHand(0).getBetAmount());
        playerTotal1.setText("Total: " + game.calculateHand(game.getPlayer().getHand(0).getList()));
        
        //load dealer cards 
        dealerCardBox.prefHeightProperty().bind(dealerVBox.heightProperty().multiply(.8));
        dealerCardBox.prefWidthProperty().bind(dealerVBox.widthProperty());
        dealerHandTotal.prefHeightProperty().bind(dealerVBox.heightProperty().multiply(.2));
        dealerHandTotal.prefWidthProperty().bind(dealerVBox.widthProperty());
        
        Image dealerCard1 = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getDealer().getHand().getCard(0).getFileName() + ".png"));
        Image dealerCard2 = new Image(getClass().getResourceAsStream("/images/PNG-cards/back_of_card.png"));
        ImageView dealerImageView1 = new ImageView(dealerCard1);
        ImageView dealerImageView2 = new ImageView(dealerCard2);
        dealerImageView1.setPreserveRatio(true);
        dealerImageView2.setPreserveRatio(true);
        ImageViewPane dPane1 = new ImageViewPane(dealerImageView1);
        ImageViewPane dPane2 = new ImageViewPane(dealerImageView2);
        dealerCardBox.getChildren().addAll(dPane1, dPane2);
        //SETTING THE WIDTH PROPORTIONAL TO THE HEIGHT
        dPane1.prefWidthProperty().bind(dPane1.heightProperty().multiply(.69));
        dPane2.prefWidthProperty().bind(dPane2.heightProperty().multiply(.69));
        
        //show first dealer card amount
        dealerHandTotal.setText("Total: " + game.calculateHand(new ArrayList<>(Collections.singletonList( game.getDealer().getHand().getCard(0)))));
        
        //Everything RightVBox        
        //sets deck image to upper 33% of right VBox
        deckImage.fitHeightProperty().bind(stackPane.heightProperty().multiply(.33));
        deckImage.setImage(new Image(getClass().getResource("/images/PNG-cards/back_of_card.png").toString()));
        
        //buttons
        buttonVBox.prefHeightProperty().bind(stackPane.heightProperty().multiply(.67));
        buttonVBox.setSpacing(10);
        hitButton.getStyleClass().add("circular-button");
        standButton.getStyleClass().add("circular-button");
        doubleButton.getStyleClass().add("circular-button");
        splitButton.getStyleClass().add("circular-button");

        hitButton.prefHeightProperty().bind(buttonVBox.heightProperty().divide(5));
        hitButton.prefWidthProperty().bind(hitButton.heightProperty());
        standButton.prefHeightProperty().bind(buttonVBox.heightProperty().divide(5));
        standButton.prefWidthProperty().bind(standButton.heightProperty());
        doubleButton.prefHeightProperty().bind(buttonVBox.heightProperty().divide(5));
        doubleButton.prefWidthProperty().bind(doubleButton.heightProperty());
        splitButton.prefHeightProperty().bind(buttonVBox.heightProperty().divide(5));
        splitButton.prefWidthProperty().bind(splitButton.heightProperty());  
        
        
        //this ensures all components are fully initialized then
        //checkTwoCard is called
        Platform.runLater(() -> {
            checkTwoCard();
        }
        );
         
    }
}
