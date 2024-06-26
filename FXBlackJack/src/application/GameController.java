package application;

import java.io.IOException;
import java.net.URL;
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
    private Region middleRegion;
    
    @FXML
    private HBox playerHBox;
    
    @FXML
    private VBox playerVBox1;
    
    @FXML
    private HBox playerCardBox1;
    
    @FXML
    private Label playerHandTotal1;
    
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
    
    }
    
    
    public void updateView() {
        //i need to update the card images for both dealer and player HBox's
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
        balanceLabel.prefWidthProperty().bind(rootHBox.widthProperty().divide(3));
        balanceLabel.prefHeightProperty().bind(rootHBox.heightProperty());
        middleVBox.prefWidthProperty().bind(rootHBox.widthProperty().divide(3));
        middleVBox.prefHeightProperty().bind(rootHBox.heightProperty());
        rightVBox.prefWidthProperty().bind(rootHBox.widthProperty().divide(3));
        rightVBox.prefHeightProperty().bind(rootHBox.heightProperty());
        
        //Height for children of middleVBox
        dealerVBox.prefHeightProperty().bind(middleVBox.heightProperty().multiply(.4));
        middleRegion.prefHeightProperty().bind(middleVBox.heightProperty().multiply(.2));
        playerHBox.prefHeightProperty().bind(middleVBox.heightProperty().multiply(.4));
        
        //player and dealer card boxes
        playerCardBox1.prefHeightProperty().bind(playerHBox.heightProperty());
        playerCardBox1.setSpacing(5);
        
        //load player cards
        Image playerCard1 = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getPlayer().getHand(0).getCard(0).getFileName() + ".png"));
        Image playerCard2 = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getPlayer().getHand(0).getCard(1).getFileName() + ".png"));
        ImageView playerImageView1 = new ImageView(playerCard1);
        ImageView playerImageView2 = new ImageView(playerCard2);
        playerImageView1.fitHeightProperty().bind(playerCardBox1.heightProperty());
        playerImageView1.fitWidthProperty().bind(playerCardBox1.widthProperty().divide(2));
        playerImageView2.fitHeightProperty().bind(playerCardBox1.heightProperty());        
        playerImageView2.fitWidthProperty().bind(playerCardBox1.widthProperty().divide(2));
        playerImageView1.setPreserveRatio(true);
        playerImageView2.setPreserveRatio(true);
        playerCardBox1.getChildren().addAll(playerImageView1, playerImageView2);
        
        //load dealer cards 
        dealerCardBox.prefHeightProperty().bind(dealerVBox.heightProperty());
        dealerCardBox.setSpacing(5);
        
        Image dealerCard1 = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getDealer().getHand().getCard(0).getFileName() + ".png"));
        Image dealerCard2 = new Image(getClass().getResourceAsStream("/images/PNG-cards/" + game.getDealer().getHand().getCard(1).getFileName() + ".png"));
        ImageView dealerImageView1 = new ImageView(dealerCard1);
        ImageView dealerImageView2 = new ImageView(dealerCard2);
        dealerImageView1.fitHeightProperty().bind(dealerCardBox.heightProperty());
        dealerImageView1.fitWidthProperty().bind(dealerCardBox.widthProperty().divide(2));
        dealerImageView2.fitHeightProperty().bind(dealerCardBox.heightProperty());  
        dealerImageView2.fitHeightProperty().bind(dealerCardBox.widthProperty().divide(2));
        dealerImageView1.setPreserveRatio(true);
        dealerImageView2.setPreserveRatio(true);
        dealerCardBox.getChildren().addAll(dealerImageView1, dealerImageView2);
        
        
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
    }
}
