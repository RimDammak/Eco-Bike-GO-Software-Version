/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author houss
 */
public class gobike extends Application {
    private static int id_user;

    public static int getId_user() {
        return id_user;
    }
    public static void setId_user(int id_user) {
        gobike.id_user = id_user;
    }
    
    @Override
    public void start(Stage primaryStage) {

                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("ClientStation.fxml"));
            //root = FXMLLoader.load(getClass().getResource("USERLOGIN.fxml"));
            //root = FXMLLoader.load(getClass().getResource("AdminStation.fxml"));//eslm el fxml
            //primaryStage.getIcons().add(new Image("/Assets/velo.jpg"));
            primaryStage.getIcons().add(new Image("/Assets/logoico.png"));
            Scene scene = new Scene(root, 1280, 720);
            primaryStage.setTitle("Eco bike go");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {ex.printStackTrace();}
    }

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
