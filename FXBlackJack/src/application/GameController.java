package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    
    @FXML
    private VBox rightVBox;
    
    @FXML
    private HBox dealerHBox;
    
    @FXML
    private HBox playerHBox;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //this makes the imageView the size of the stackPane
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));

        
        
        //bind the width of the root HBox children to take up equal width
        balanceLabel.prefWidthProperty().bind(rootHBox.widthProperty().divide(3));
        middleVBox.prefWidthProperty().bind(rootHBox.widthProperty().divide(3));
        rightVBox.prefWidthProperty().bind(rootHBox.widthProperty().divide(3));
        
        //bind the height of the dealer and playerHBox to take up the whole height
        dealerHBox.prefHeightProperty().bind(middleVBox.heightProperty().divide(2));
        playerHBox.prefHeightProperty().bind(middleVBox.heightProperty().divide(2));
        
    }
}
