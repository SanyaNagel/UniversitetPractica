package sample.forms;

import javafx.scene.paint.Color;
import java.awt.Label;
import java.awt.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

//import com.sun.javafx.geom.Rectangle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;
import sample.Main;
import sample.Map;
import sample.ObjectMap.Corridor;
import sample.ObjectMap.University;

import java.io.File;

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
    private Pane pane;
    
    @FXML
    private ChoiceBox ChoiceBoxObject;

    ObservableList<String> listObject = FXCollections.observableArrayList("Коридор","Столовая", "Аудитория", "Печать");
    
    ArrayList<Rectangle> listRectangle = new ArrayList<Rectangle>();
    
    // Имя открытого файла
    private String nameOpenFile;
    
    @FXML
    void openSettings(ActionEvent event) {

    }

    private University univer = new University();
    private Map map;
    
    @FXML
    void initialize() {
        assert PanelLeft != null : "fx:id=\"PanelLeft\" was not injected: check your FXML file 'sample.fxml'.";
        assert settings != null : "fx:id=\"settings\" was not injected: check your FXML file 'sample.fxml'.";
        assert PanelRight != null : "fx:id=\"PanelRight\" was not injected: check your FXML file 'sample.fxml'.";
        ChoiceBoxObject.setItems(listObject);
        
        map = univer.getMap();
        paintMap(map.getWidth(),map.getHeight());
    }
    
    public void paintMap(int w, int h) {
    	int sizeR = 20;
    	for(int i = 0; i < w; i++) {
    		for(int j = 0; j < h; j++){
    	        Rectangle rectangle = new Rectangle((sizeR*i)-1,(sizeR*j)-1,sizeR,sizeR);
    	        rectangle.setFill(Color.TRANSPARENT);
    	        rectangle.setStroke(Color.BLACK);
    	        rectangle.setStrokeWidth(1);
    	        listRectangle.add(rectangle);
    	        pane.getChildren().add(rectangle);

    	        //rectangle.getStyleClass().add("rectangleFx");
    		}
    	}
    }
    
    //Кнопка добавить объект
    public void addObject() {
    	//"Столовая", "Аудитория", "Печать"
    	
    	if(ChoiceBoxObject.getValue() == "Коридор") {
    		map.setCorridor(new Corridor(1,2));
    	}
    }
    
//    @FXML
//    void openSettings () {
//        Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 600, 400));
//        primaryStage.show();
//
//    }
    
    
    public void MenuBar_File_Close_onAction(ActionEvent actionEvent) {
        Main.primaryStage.close();
    }

    public void MenuBar_File_Open_onAction(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите файл");
            fileChooser.setInitialDirectory(new File("./Data"));
            File file = fileChooser.showOpenDialog(Main.primaryStage);
            if (file == null) return;
            // Чтение файла
            map.loadFromFile(file);
            // Сохранить имя файла
            this.nameOpenFile = file.getName();
            // Графическое отображение библиотеки растений
            //this.initListPlants();
        }catch (Exception ex){
        	Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setContentText("Не удалось открыть файл" + System.lineSeparator() + "Лог ошибки: " + ex.getMessage());
            alert.show();
           
        }
    }

    public void MenuBar_File_Create_onAction(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Создать фаил");
            fileChooser.setInitialDirectory(new File("./Data"));
            File file = fileChooser.showSaveDialog(Main.primaryStage);
            if (file == null) return;
            // Создание нового файла
            file.createNewFile();
        } catch (Exception ex){
        	Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setContentText("Не удалось сохранить файл" + System.lineSeparator() + "Лог ошибки: " + ex.getMessage());
            alert.show();
           
        }
    }

    public void MenuBar_File_Save_onAction(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите файл");
            fileChooser.setInitialDirectory(new File("./Data"));
            
            fileChooser.setInitialFileName(this.nameOpenFile);
            File file = fileChooser.showSaveDialog(Main.primaryStage);
            if (file == null) return;
            // Сохранение
            map.saveToFile(file);
        }catch (Exception ex){
        	Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение");
            alert.setContentText("Не удалось сохранить файл" + System.lineSeparator() + "Лог ошибки: " + ex.getMessage());
            alert.show();
           
        }
    }

}

