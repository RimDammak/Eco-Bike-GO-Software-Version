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
 * @author damak
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
    private void consulter_rec_clic(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceConsulterRec.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
           stage.setTitle("Traiter Reclamations");
        stage.show();
        
    }

    @FXML
    private void traiter_rec_clic(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceRepondreRec.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
           stage.setTitle("Consulter Reclamations");
        stage.show();
    }

    @FXML
    private void clic_type_rec(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceGestionType.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
           stage.setTitle("Consulter Reclamations");
        stage.show();
    }
    
}
