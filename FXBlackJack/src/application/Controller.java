package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller implements Initializable{
    
    @FXML
    private StackPane stackPane;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private VBox vBox;
    
    @FXML
    private Button welcomeButton;
    @FXML
    private Button simButton;
    
    @FXML
    private Label welcomeLabel;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;
    
    public void playGame(ActionEvent event) throws IOException {
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BettingScreen.fxml"));
            BettingController bettingController = new BettingController(game);
            loader.setController( bettingController);
            root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            
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
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void playSim(ActionEvent e) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SimData.fxml"));
            SimDataController controller = new SimDataController(game);

            loader.setController( controller);
            root = loader.load();
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            
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
        catch (IOException event) {
            event.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {   
        game = new Game();
        
        //this makes the imageView the size of the anchorPane
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));
        
        
        //this adds a listener to the sceneProperty to make sure it is not null
        vBox.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                // Bind the font size of the Label and Button to the window size
                welcomeLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", newScene.widthProperty().add(newScene.heightProperty()).divide(50).asString(), ";"));
                welcomeButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", newScene.widthProperty().add(newScene.heightProperty()).divide(50).asString(), ";"));

                // Bind the height of the Label and Button to the VBox height
                welcomeLabel.prefHeightProperty().bind(vBox.heightProperty().multiply(0.25).subtract(10));
                welcomeButton.prefHeightProperty().bind(vBox.heightProperty().multiply(0.10).subtract(10));
            }
        });
    }
}
