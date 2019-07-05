package sample.forms;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.awt.Label;
import java.awt.List;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

//import com.sun.javafx.geom.Rectangle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Map;
import sample.ObjectMap.Audience;
import sample.ObjectMap.Corridor;
import sample.ObjectMap.Print;
import sample.ObjectMap.Sell;
import sample.ObjectMap.University;

import java.io.File;

import static sample.Main.primaryStage;

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
    private ChoiceBox<String> ChoiceBoxObject;
    
    @FXML
    private TextField coordX;

    @FXML
    private TextField coordY;

    @FXML
    private TextField width;

    @FXML
    private TextField height;
    
    ObservableList<String> listObject = FXCollections.observableArrayList("Коридор","Столовая", "Аудитория", "Печать","Вход в здание");
    
    Rect[][] rects;
    
    // Имя открытого файла
    private String nameOpenFile;

    private University univer = new University();
    private Map map;
    
    @FXML
    void initialize() {
        assert PanelLeft != null : "fx:id=\"PanelLeft\" was not injected: check your FXML file 'sample.fxml'.";
        assert settings != null : "fx:id=\"settings\" was not injected: check your FXML file 'sample.fxml'.";
        assert PanelRight != null : "fx:id=\"PanelRight\" was not injected: check your FXML file 'sample.fxml'.";
        
        
        ChoiceBoxObject.setItems(listObject);
        Rect.setTextField(coordX, coordY, width, height);
        map = univer.getMap();
        paintMap(map.getWidth(),map.getHeight());
    }

    @FXML
    public void start(){
        univer.start();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                map.update();
                updateMap();
            }
        }, 0, 1000);
    }

    @FXML
    void wayGroop(ActionEvent event) {
    	map.deicstraWay(Integer.parseInt(coordX.getText()), Integer.parseInt(coordY.getText()),
		Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));
    }    
    
    public void paintMap(int w, int h) {
    	int sizeR = 10;
    	rects = new Rect[w][h];
    	for(int i = 0; i < w; i++) {
    		for(int j = 0; j < h; j++){
    	        Rect rect = new Rect((sizeR*i)-1,(sizeR*j)-1,sizeR,sizeR, i,j);
    	        rect.setFill(Color.TRANSPARENT);
    	        rect.setStroke(Color.GREEN);
    	        rect.setStrokeWidth(1);
    	        rects[i][j] = rect;
                pane.getChildren().add(rect);

    	        //rectangle.getStyleClass().add("rectangleFx");
    		}
    	}
    }
    
    //Кнопка добавить объект
    public void addObject() {
    	if(ChoiceBoxObject.getValue() == "Коридор") {
    		map.setCorridor(new Corridor( Integer.parseInt(coordX.getText()),Integer.parseInt(coordY.getText()),
    				Integer.parseInt(width.getText()),Integer.parseInt(height.getText())));
    	}
    	if(ChoiceBoxObject.getValue() == "Столовая") {
    		map.setSell(new Sell( Integer.parseInt(coordX.getText()),Integer.parseInt(coordY.getText()),
    				Integer.parseInt(width.getText()),Integer.parseInt(height.getText())));
    	}
    	if(ChoiceBoxObject.getValue() == "Аудитория") {
    		map.setAudince(new Audience( Integer.parseInt(coordX.getText()),Integer.parseInt(coordY.getText()),
    				Integer.parseInt(width.getText()),Integer.parseInt(height.getText())));
    	}
    	if(ChoiceBoxObject.getValue() == "Печать") {
    		map.setPrint(new Print(Integer.parseInt(coordX.getText()),Integer.parseInt(coordY.getText()),
    				Integer.parseInt(width.getText()),Integer.parseInt(height.getText())));
    	}
    	if(ChoiceBoxObject.getValue() == "Вход в здание") {
    		map.setInput(Integer.parseInt(coordX.getText()),Integer.parseInt(coordY.getText()),
    				Integer.parseInt(width.getText()),Integer.parseInt(height.getText()));
    	}
    	updateMap();
    	
    }
    
    public void updateMap() {
    	for(Corridor corridor: map.get_corridorsList()){
            
    		int[][] mapCorridor = corridor.getMapCorridor();
    	    for(int i = 0; i < corridor.get_width(); i++)
        	    for(int j = 0; j < corridor.get_height(); j++)
        	    {
        	    	Color color = Color.WHITE;
        	    	if(mapCorridor[i][j] == 1)
        	    	    color = Color.BLACK;
        	    	if(mapCorridor[i][j] == 2)
        	    	    color = Color.INDIANRED;
        	    	if(mapCorridor[i][j] == 3)
        	    	    color = Color.BLUE;
        	    	if(mapCorridor[i][j] == 4)
        	    	    color = Color.ORANGE;
                    if(mapCorridor[i][j] == 5)
                        color = Color.DARKGREEN;
                    if(mapCorridor[i][j] == 6)
                        color = Color.CHARTREUSE;
        	    	rects[corridor.getCoordX()+i][corridor.getCoordY()+j].setFill(color);
        	    }
    	}

        for(Audience audience: map.get_audinceList()){
            int[][] mapAudienc = audience.getMapAudience();
            for(int i = 0; i < audience.getWidth(); i++)
            	for(int j = 0; j < audience.getHeight(); j++)
        	    {
        	    	Color color = Color.WHITE;
        	    	if(mapAudienc[i][j] == 1)
        	    	    color = Color.BLACK;
        	    	if(mapAudienc[i][j] == 2)
        	    	    color = Color.INDIANRED;
        	    	if(mapAudienc[i][j] == 3)
        	    	    color = Color.BLUE;
        	    	if(mapAudienc[i][j] == 4)
        	    	    color = Color.ORANGE;
                    if(mapAudienc[i][j] == 6)
                        color = Color.CHARTREUSE;
        	    	
        	    	rects[audience.getCoordX()+i][audience.getCoordY()+j].setFill(color);
        	    }
        }
    }
    
    public void showeDialog() {
        try {
    		Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/forms/setting.fxml"));
            stage.setTitle("Settings");
          //  stage.setMinHeight(202);
           // stage.setMinWidth(393);
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            // stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            stage.show();
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

    }
    
    
    public void MenuBar_File_Close_onAction(ActionEvent actionEvent) {
        primaryStage.close();
    }

    public void MenuBar_File_Open_onAction(ActionEvent actionEvent) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите файл");
            fileChooser.setInitialDirectory(new File("./Data"));
            File file = fileChooser.showOpenDialog(primaryStage);
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
            File file = fileChooser.showSaveDialog(primaryStage);
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
            File file = fileChooser.showSaveDialog(primaryStage);
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

