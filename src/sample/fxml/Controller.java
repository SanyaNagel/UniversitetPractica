package sample.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PanelLeft;

    @FXML
    private AnchorPane PanelRight;

    @FXML
    void initialize() {
        assert PanelLeft != null : "fx:id=\"PanelLeft\" was not injected: check your FXML file 'sample.fxml'.";
        assert PanelRight != null : "fx:id=\"PanelRight\" was not injected: check your FXML file 'sample.fxml'.";

    }
}

//Rectangle r = new Rectangle( 100, 100, 50, 50 );
