package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimDataController implements Initializable {
    @FXML
    private StackPane stackPane;
    @FXML
    private ImageView imageView;
    
    @FXML
    private VBox vBox;
    @FXML
    private Label infoLabel;
    @FXML
    private HBox gamesHBox;
    @FXML
    private Label gamesLabel;
    @FXML
    private TextField gamesText;
    @FXML
    private Label gamesError;
    @FXML
    private HBox balanceHBox;
    @FXML
    private Label balaceLabel;
    @FXML
    private TextField balanceText;
    @FXML
    private Label balanceError;
    @FXML
    private HBox betHBox;
    @FXML
    private Label betLabel;
    @FXML
    private TextField betText;
    @FXML
    private Label betError;
    @FXML
    private HBox profitHBox;
    @FXML
    private Label profitLabel;
    @FXML
    private TextField profitText;
    @FXML
    private Label profitError;
    
    @FXML
    private Button runButton;
    
    private Game game;
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public SimDataController(Game g) {
        game = g;
    }
    
    public boolean validateField(String s, Label error) {
        try {
            int num = Integer.parseInt(s);
            if (num > 0 && num < Integer.MAX_VALUE) {
                error.setVisible(false);
                return true;
            }
            else {
                error.setText("Enter a positive value that is less than " + Integer.MAX_VALUE);
                error.setVisible(true);
                return false;
            }
        }
        catch (NumberFormatException e) {
            //show that there is an error
            error.setText("Enter a valid numeric value");
            error.setVisible(true);
            return false;
        }
    }
    
    public boolean validateInput() {
        return (validateField(gamesText.getText(),gamesError)
                && validateField(balanceText.getText(), balanceError)
                && validateField(betText.getText(), betError)
                && validateField(profitText.getText(), profitError));
    }
    
    
    public void runSim(ActionEvent e) throws IOException {
        //must check that all the inputs are valid and positive ints not too big
        if (validateInput()) {
            Simulations sim = new Simulations(Integer.parseInt(gamesText.getText()),
                Integer.parseInt(balanceText.getText()),
                Integer.parseInt(betText.getText()),
                Integer.parseInt(profitText.getText()),
                game);
            
            //change to other window and start the simulation
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SimScreen.fxml"));
                SimScreenController simController = new SimScreenController(sim, game);
                loader.setController( simController);
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
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //this makes the imageView the size of the anchorPane
        imageView.fitWidthProperty().bind(stackPane.widthProperty());
        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        imageView.setImage(new Image(getClass().getResource("/images/blackjack_Background_Image.jpg").toString()));
        
        
        //parent vBox
        vBox.prefHeightProperty().bind(stackPane.heightProperty());
        vBox.prefWidthProperty().bind(stackPane.widthProperty());
        
        //Bind all the Hbox, label and text fields
        infoLabel.setWrapText(true);
        infoLabel.prefHeightProperty().bind(vBox.heightProperty().divide(6));
        infoLabel.prefWidthProperty().bind(vBox.widthProperty().multiply(.66));
        gamesHBox.prefHeightProperty().bind(vBox.heightProperty().divide(6));
        gamesHBox.prefWidthProperty().bind(vBox.widthProperty());
        balanceHBox.prefHeightProperty().bind(vBox.heightProperty().divide(6));
        balanceHBox.prefWidthProperty().bind(vBox.widthProperty());
        betHBox.prefHeightProperty().bind(vBox.heightProperty().divide(6));
        betHBox.prefWidthProperty().bind(vBox.widthProperty());
        profitHBox.prefHeightProperty().bind(vBox.heightProperty().divide(6));
        profitHBox.prefWidthProperty().bind(vBox.widthProperty());
        runButton.prefHeightProperty().bind(vBox.heightProperty().divide(6));
        runButton.prefWidthProperty().bind(vBox.widthProperty().divide(4));
    }
    
    
}
