/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author choua
 */
public class InterfaceReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Repondre(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RepondreReclamation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
              stage.setTitle("Repondre aux Reclamations");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Changer(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceChangerEtat.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
              stage.setTitle("Changer Etat des Reclamations");
        stage.setScene(scene);
        stage.show();
    }
    
}
