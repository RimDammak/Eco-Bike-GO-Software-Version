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
public class InterfaceEventController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BtnEvent(ActionEvent event) throws IOException {
        System.out.print("test de button");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceGestionEvent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
              stage.setTitle("Gestion Evenements");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void bntConsult(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterEvent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
              stage.setTitle("Consulter listes des reservations Evenements");
        stage.setScene(scene);
        stage.show();
    }
    
}
