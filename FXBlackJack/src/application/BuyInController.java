package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuyInController implements Initializable{

    @FXML
    private StackPane stackPane;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private VBox vBox;
    
    @FXML
    private Label label;
    
    @FXML
    private Button lowButton;
    
    @FXML
    private Button midButton;
    
    @FXML
    private Button highButton;
    
    //for the new scene
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Game game;
    
    
    
    //For each of these I must set up the player buy in amount, bet amounts,
    //and switch to the Betting Screeen
    //create game amount and update the betting amounts
    public void lowStakes(ActionEvent event) throws IOException {
        
        game = new Game(new int[] {1, 5, 10, 25});
        
        try {
            root = FXMLLoader.load(getClass().getResource("BettingScreen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
         }
         catch(IOException e) {
             e.printStackTrace();
         }
    }
    
    public void middleStakes(ActionEvent event) throws IOException {
        game = new Game(new int[] {5, 25, 50, 100});
        
        try {
            root = FXMLLoader.load(getClass().getResource("BettingScreen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
         }
         catch(IOException e) {
             e.printStackTrace();
         }
    }

    public void highStakes(ActionEvent event) throws IOException {
        game = new Game(new int[] {250, 500, 1000, 2500});
        
        try {
            root = FXMLLoader.load(getClass().getResource("BettingScreen.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show(); 
         }
         catch(IOException e) {
             e.printStackTrace();
         }
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //this makes the imageView the size of the stackPane
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));
        
        //this adds a listener to the sceneProperty to make sure it is not null
        vBox.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                // Bind the font size of the Label and Button to the window size
                label.styleProperty().bind(Bindings.concat("-fx-font-size: ", newScene.widthProperty().add(newScene.heightProperty()).divide(50).asString(), ";"));
                lowButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", newScene.widthProperty().add(newScene.heightProperty()).divide(50).asString(), ";"));
                midButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", newScene.widthProperty().add(newScene.heightProperty()).divide(50).asString(), ";"));
                highButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", newScene.widthProperty().add(newScene.heightProperty()).divide(50).asString(), ";"));

                // Bind the height of the Label and Button to the VBox height
                label.prefHeightProperty().bind(vBox.heightProperty().multiply(0.25).subtract(10));
                lowButton.prefHeightProperty().bind(vBox.heightProperty().multiply(0.10).subtract(10));
                midButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", newScene.widthProperty().add(newScene.heightProperty()).divide(50).asString(), ";"));
                highButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", newScene.widthProperty().add(newScene.heightProperty()).divide(50).asString(), ";"));
            
            }
        });
    }

    public Game getGame() {
        return game;
    }

}
