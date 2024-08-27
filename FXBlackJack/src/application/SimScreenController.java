package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SimScreenController implements Initializable{

    @FXML
    private StackPane stackPane;
    
    @FXML
    private ImageView imageView;
    
    private Game game;
    private Simulations sims;
    
    public SimScreenController(Simulations sim, Game g) {
        sims = sim;
        game = g;
    }
    
    public void runSim() {
        
    }
    
    public void updateView() {
        
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));
           
        
        
    }

}
