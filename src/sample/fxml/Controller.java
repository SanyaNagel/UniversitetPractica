package sample.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PanelLeft;

    @FXML
    private Menu settings;

    @FXML
    private AnchorPane PanelRight;

    @FXML
    void initialize() {
        assert PanelLeft != null : "fx:id=\"PanelLeft\" was not injected: check your FXML file 'sample.fxml'.";
        assert settings != null : "fx:id=\"settings\" was not injected: check your FXML file 'sample.fxml'.";
        assert PanelRight != null : "fx:id=\"PanelRight\" was not injected: check your FXML file 'sample.fxml'.";

    }

    @FXML
    void openSettings() {
//        Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 600, 400));
//        primaryStage.show();

    }
}


//Rectangle r = new Rectangle( 100, 100, 50, 50 );
