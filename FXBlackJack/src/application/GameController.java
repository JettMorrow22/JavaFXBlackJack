package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameController implements Initializable{

    @FXML
    private StackPane stackPane;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private HBox rootHBox;
    
    @FXML
    private Label balanceLabel;
    
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
    private Label playerHandTotal1;
    
    @FXML
    private Label playerHandBetAmount1;
    
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
    
    private Game game;
    
    public GameController(Game g) {
        game  = g; 
    }
    
    public void splitButton(ActionEvent e) throws IOException {
        
    }
    
    public void doubleButton(ActionEvent e) throws IOException {
        
    }

    public void standButton(ActionEvent e) throws IOException {
    
    }

    public void hitButton(ActionEvent e) throws IOException {
        
        //try addind card to player Hand and add card to screen and update Total
    }
    
    
    public void updateView() {
        //i need to update the card images for both dealer and player HBox's
    }
    
    public void gameOver() {
        //clear both player and dealer hands
        
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        game.bettingCycle();
        
        balanceLabel.setText("Balance: $" + game.getPlayer().getBalance());
        
        //this makes the imageView the size of the stackPane
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));

        //HBox children to take up equal width and full height
        balanceLabel.prefWidthProperty().bind(rootHBox.widthProperty().multiply(.2));
        balanceLabel.prefHeightProperty().bind(rootHBox.heightProperty());
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
        
        //player and dealer card boxes
        playerCardBox1.prefHeightProperty().bind(playerVBox1.heightProperty().multiply(.8));
        playerCardBox1.prefWidthProperty().bind(playerVBox1.widthProperty());
        playerHandTotal1.prefHeightProperty().bind(playerVBox1.heightProperty().multiply(.2));
        playerHandTotal1.prefWidthProperty().bind(playerVBox1.widthProperty());
        
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
        
        playerHandBetAmount1.setText("Bet Amount: $" + game.getPlayer().getHand(0).getBetAmount());
        playerHandTotal1.setText("Total: " + game.calculateHand(game.getPlayer().getHand(0).getList()));
        
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
        
        
        
        //Determine what the two cards mean
        //if player has BJ then pay out and round over unless dealer has BJ
        if (game.calculateHand(game.getPlayer().getHand(0).getList()) == 21) {
            
        }
        middleLabel.setText("hey");
        
        //determine if split button should be displayed or not
        if (game.getCardValue(game.getPlayer().getHand(0).getCard(0).getValue()) !=
                game.getCardValue(game.getPlayer().getHand(0).getCard(1).getValue())) {
            splitButton.setVisible(false);
        }
        
        //determine if double button should be displayed or not
        if ( game.getPlayer().getBalance() < game.getPlayer().getHand(0).getBetAmount()) {
            doubleButton.setVisible(false);
        }
    }
}
