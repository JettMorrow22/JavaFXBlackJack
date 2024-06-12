package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
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
    private Region spaceLeft;
    
    @FXML
    private Region spaceRight;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //this makes the imageView the size of the stackPane
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));
    
        //bind children of HBox to take up certain width
        balanceLabel.prefWidthProperty().bind(rootHBox.widthProperty().divide(4));
        centerVBox.prefWidthProperty().bind(rootHBox.widthProperty().divide(2));
        spaceLeft.prefWidthProperty().bind(rootHBox.widthProperty().divide(16));
        spaceRight.prefWidthProperty().bind(rootHBox.widthProperty().divide(16));
        dealButton.prefWidthProperty().bind(rootHBox.widthProperty().divide(8));
        dealButton.prefHeightProperty().bind(rootHBox.widthProperty().divide(8));
        
        // Bind the font size of the button to its height
        NumberBinding fontSizeBinding = Bindings.createDoubleBinding(() -> {
            // Adjust the multiplier to your preference
            return dealButton.getHeight() * 0.25; 
        }, dealButton.heightProperty());
        dealButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSizeBinding.asString(), "px;"));

        
    }
    
    public void deal(ActionEvent e ) throws IOException{
        
    }

}
