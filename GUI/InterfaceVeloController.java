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
public class InterfaceVeloController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void velooo(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceGestionVelo.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
           stage.setTitle("Gestion Velo");
        stage.show();
        
    }

    @FXML
    private void catttt(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceGestionCatt.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
           stage.setTitle("Gestion Categorie");
        stage.show();
        
    }
    
}
