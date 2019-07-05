package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/forms/sample.fxml"));
            primaryStage.setTitle("Симулятор студента!");
            Scene scen = new Scene(root, 753, 529);
            scen.getStylesheets().add(0,"/sample/forms/style.css");
            primaryStage.setScene(scen);
            primaryStage.show();

             }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
    
 // Хендл окна для управления им
    public static Stage primaryStage;
}
