/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.Reservation;
import entite.Station;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceReservation;
import service.ServiceStation;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class ClientStationController implements Initializable {

    @FXML
    private AnchorPane allp;
    @FXML
    private AnchorPane gauche;
    @FXML
    private AnchorPane droite;
    @FXML
    private TableView<Station> table;
    @FXML
    private TableColumn<?, ?> localisation;
    @FXML
    private TableColumn<?, ?> nbrvelo;
    ServiceStation  ss = null;
    ServiceReservation  sr = null;
    public int id;
    @FXML
    private Button btnr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ss = new ServiceStation();
       
        localisation.setCellValueFactory(new PropertyValueFactory<>("localisation_station"));
        nbrvelo.setCellValueFactory(new PropertyValueFactory<>("velo_station"));
        // initialise la liste des événements dans le tableau
        ObservableList<Station> s1 = FXCollections.observableArrayList(ss.readAll());
        table.setItems(s1);
        btnr.setVisible(false);

table.setOnMouseClicked(event -> {
    Station selectedStation = table.getSelectionModel().getSelectedItem();
    if (selectedStation != null) {
        btnr.setVisible(true);
    } else {
        btnr.setVisible(false);
    }
});
    }    

    @FXML
    private void home(ActionEvent event) {
        AnchorPane container;
        try {
            container = FXMLLoader.load(getClass().getResource("AdminStation.fxml"));
            allp.getChildren().removeAll();
            allp.getChildren().setAll(container);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void allereservation(ActionEvent event) throws IOException {
        Station a=table.getSelectionModel().getSelectedItem();
         if (a == null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("station non selectionne");
        alert.setHeaderText(null);
        alert.setContentText("svp selectionner une station.");
        alert.showAndWait();
        return;
    }
        ServiceStation serviceStation = new ServiceStation();
        int id = a.getId_station(); // l'identifiant de la station que vous voulez modifier
        System.out.println(id);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientReservation.fxml"));
        Parent root = loader.load();

        ClientReservationController suivantController = loader.getController();
        suivantController.initDonnees(a.getNom_station(),a.getLocalisation_station(),id, a.getVelo_station());

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) {
        AnchorPane init0;
        try {
            init0 = FXMLLoader.load(getClass().getResource("USERLOGIN.fxml"));
            allp.getChildren().removeAll();
            allp.getChildren().setAll(init0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void mesreservations(ActionEvent event) {
        AnchorPane newpan;
        try {
            newpan = FXMLLoader.load(getClass().getResource("mesreservations.fxml"));
            allp.getChildren().removeAll();
            allp.getChildren().setAll(newpan);
            Stage stage = (Stage) allp.getScene().getWindow();
            stage.setTitle("Mes Reservations");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    
}
