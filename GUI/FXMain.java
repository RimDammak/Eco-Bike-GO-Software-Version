/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class FXMain extends Application {
    
    private static int id_user;

    public static int getId_user() {
        return id_user;
    }
    public static void setId_user(int id_user) {
        FXMain.id_user = id_user;
    }
    
   
    @Override
    public void start(Stage primaryStage) throws Exception {

Parent root;   
     try{
         root = FXMLLoader.load(getClass().getResource("InterfaceGestionEvent.fxml"));
         Scene scene = new Scene(root);
        
        primaryStage.setTitle("EcoBikeGO Desktop Application");
        primaryStage.setScene(scene);
        //primaryStage.getIcons().add(new Image("/Assets/backevent.jpg"));

        primaryStage.show();
     }
     catch(IOException ex)
     {
           System.out.println(ex.getMessage());
     }
        
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
