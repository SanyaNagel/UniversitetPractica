package sample.forms;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.ObjectMap.University;

public class ControllerSettings {

	 	@FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField sizeMapX;

	    @FXML
	    private TextField sizeMapY;
	    
	    @FXML
	    private TextField numberPrepod;

	    @FXML
	    private TextField numberStudent;

	    @FXML
	    private Label numberGroupText;

	    @FXML
	    private TextField studentShow;

	    @FXML
	    void initialize() {
	        assert numberPrepod != null : "fx:id=\"numberPrepod\" was not injected: check your FXML file 'setting.fxml'.";
	        assert numberStudent != null : "fx:id=\"numberStudent\" was not injected: check your FXML file 'setting.fxml'.";
	        assert numberGroupText != null : "fx:id=\"numberGroupText\" was not injected: check your FXML file 'setting.fxml'.";
	        assert studentShow != null : "fx:id=\"studentShow\" was not injected: check your FXML file 'setting.fxml'.";

	    }
	    
    @FXML
    void addGroup(ActionEvent event) {
    	System.out.println("Добавление группы:");
    	University.getUniver().createGroup(Integer.parseInt(numberStudent.getText()));
        numberGroupText.setText(String.valueOf(1+(Integer.parseInt(numberGroupText.getText()))));
    }

    @FXML
    void clickTack(ActionEvent event) {
    	University.getUniver().getMap().setSizeMap(Integer.parseInt(sizeMapX.getText()),Integer.parseInt(sizeMapY.getText()));
    	University.getUniver().createTeacher(Integer.parseInt(numberPrepod.getText()));
        University.getUniver().setIntervalCreate(Integer.parseInt(studentShow.getText()));
    }
    
}
