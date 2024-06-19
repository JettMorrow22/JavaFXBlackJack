package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BettingController implements Initializable{
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private HBox rootHBox;
    
    @FXML
    private Label balanceLabel;
    
    @FXML
    private Label betLabel;
    
    @FXML
    private VBox centerVBox;
    
    @FXML
    private Label placeBetsLabel;
    
    @FXML
    private HBox buttonsHBox;
    
    @FXML
    private Button button1;
    
    @FXML
    private Button button2;
    
    @FXML
    private Button button3;
    
    @FXML
    private Button button4;
    
    @FXML
    private Button dealButton;
    
    @FXML
    private Button clearButton;
    
    @FXML 
    private Region spaceLeft;
    
    @FXML
    private Region spaceRight;
    
    private Player player;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    //this needs to increment bet amount and decrease balance
    public void clickButton1(ActionEvent e) throws IOException{
        if(player.getBalance() >= 25) {
            player.decreaseBalance(25);
            player.getHand(0).increaseBetAmount(25);
            updateView();
        }
    }
    
    public void clickButton2(ActionEvent e) throws IOException{
        
        if (player.getBalance() >= 50) {
            player.decreaseBalance(50);
            player.getHand(0).increaseBetAmount(50);
            updateView();
        }
    }

    public void clickButton3(ActionEvent e) throws IOException{
        if (player.getBalance() >= 100) {
            player.decreaseBalance(100);
            player.getHand(0).increaseBetAmount(100);
            updateView();
        }

    }

    public void clickButton4(ActionEvent e) throws IOException{
        if (player.getBalance() >= 500) {
            player.decreaseBalance(500);
            player.getHand(0).increaseBetAmount(500);
            updateView();
        }

    }
    
    public void clickClearButton(ActionEvent e) throws IOException {
        player.increaseBalance(player.getHand(0).getBetAmount());
        player.getHand(0).setBetAmount(0);
        updateView();
    }
    
    public void updateView() {
        balanceLabel.setText("Balance: $" + player.getBalance());
        betLabel.setText("Bet Amount: $ " + player.getHand(0).getBetAmount());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        player = new Player();
        player.createHand();
        
        //this makes the imageView the size of the stackPane
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));
    
        //bind children of HBox to take up certain width
        balanceLabel.prefWidthProperty().bind(rootHBox.widthProperty().divide(4));
        centerVBox.prefWidthProperty().bind(rootHBox.widthProperty().divide(2));
        spaceLeft.prefWidthProperty().bind(rootHBox.widthProperty().divide(16));
        spaceRight.prefWidthProperty().bind(rootHBox.widthProperty().divide(16));
        dealButton.getStyleClass().add("circular-button");
        dealButton.prefWidthProperty().bind(rootHBox.widthProperty().divide(8));
        dealButton.prefHeightProperty().bind(dealButton.widthProperty());
        
        //set spacing and width of buttons
        buttonsHBox.setSpacing(10);
        button1.getStyleClass().add("circular-button");
        button2.getStyleClass().add("circular-button");
        button3.getStyleClass().add("circular-button");
        button4.getStyleClass().add("circular-button");
        
        button1.prefWidthProperty().bind(buttonsHBox.widthProperty().divide(5));
        button1.prefHeightProperty().bind(button1.widthProperty());
        button2.prefWidthProperty().bind(buttonsHBox.widthProperty().divide(5));
        button2.prefHeightProperty().bind(button1.widthProperty());
        button3.prefWidthProperty().bind(buttonsHBox.widthProperty().divide(5));
        button3.prefHeightProperty().bind(button1.widthProperty());
        button4.prefWidthProperty().bind(buttonsHBox.widthProperty().divide(5));
        button4.prefHeightProperty().bind(button1.widthProperty());


    }
    
    public void deal(ActionEvent e ) throws IOException{
      
        if ( player.getHand(0).getBetAmount() > 0) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
                GameController gameController = new GameController(player);
                loader.setController(gameController);
                root = loader.load();
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                stage.setScene(scene);
                stage.show(); 
            }
            catch (IOException event) {
                event.printStackTrace();
            }
        }
    }

}
