/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author choua
 */
public class AcceuilController implements Initializable {

    @FXML
    private Label lab_acceuil;
    @FXML
    private Label Lab_velo;
    @FXML
    private Label station_lab;
    @FXML
    private Label event_lab;
    @FXML
    private Label rec_lab;
    @FXML
    private Label profil_lab;
    @FXML
    private AnchorPane formAcceuil;
    @FXML
    private AnchorPane FormVelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Acceuil_clk(MouseEvent event) {
    }

    @FXML
    private void veloclik(MouseEvent event) {
    }

    @FXML
    private void stationclik(MouseEvent event) {
    }

    @FXML
    private void eventclik(MouseEvent event) {
    }

    @FXML
    private void recclik(MouseEvent event) {
    }

    @FXML
    private void profilclik(MouseEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("ProfilUser.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }
    
}
