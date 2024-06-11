package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class BettingController implements Initializable{
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private ImageView imageView;
    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));
    }

}
