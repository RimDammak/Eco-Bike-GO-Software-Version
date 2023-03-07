/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.Reservation;
import entite.Station;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import service.ServiceReservation;
import service.ServiceStation;
import utile.DataSource;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entite.user;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author houss
 */
public class ClientReservationController implements Initializable {

    @FXML
    private AnchorPane reservationpanel;
    @FXML
    private TextField nbrvelo;
    @FXML
    private DatePicker dd;
    @FXML
    private TextField nbrj;
    @FXML
    private TextField noms;
    @FXML
    private Label max;
    @FXML
    private Label idr;
    @FXML
    private TextField td;
    @FXML
    private TextField td1;
    @FXML
    private ComboBox<String> typev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> daraja = FXCollections.observableArrayList("Vélo de ville électrique", "Vélo pliant électrique","VTC électrique","VTT électrique");
        typev.setItems(daraja);
    }     

    @FXML
    private void ajouterreservation(ActionEvent event) {
            int iddevelo;
    String s = typev.getSelectionModel().getSelectedItem();
    if (typev.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez choisir le type de velo !");
        alert.showAndWait();
    } else {
        System.out.println("s====="+s);
        switch (s) {
            case "Vélo de ville électrique":
                iddevelo = 1;
                break;
            case "Vélo pliant électrique":
            {
                iddevelo = 4;
                System.out.println("idveloo==========="+iddevelo);
                break;
                
            }
                
            case "VTC électrique":
                iddevelo = 3;
                break;
            case "VTT électrique":
                iddevelo = 12;
                break;
            default:
                iddevelo = 1;
                break;
        }
    

    LocalDate date0 = dd.getValue();
    LocalTime temp0;
    try {
        int hour = Integer.parseInt(td.getText());
        int minute = Integer.parseInt(td1.getText());
        if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
            throw new IllegalArgumentException("L'heure saisie est invalide.");
        }
        temp0 = LocalTime.of(hour, minute);
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Heure de début invalide");
        alert.setContentText("Veuillez saisir une heure de début valide (format : hh:mm).");
        alert.showAndWait();
        return;
    } catch (IllegalArgumentException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Heure de début invalide");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
        return;
    }
            LocalDateTime dateTime0 = LocalDateTime.of(date0, temp0);
            int jours = Integer.parseInt(nbrj.getText());
            LocalDateTime newdate = dateTime0.plusDays(jours);

        //
           int ids= Integer.parseInt(idr.getText());
            //Station sta=new Station(jours, "e", "d", jours);
            String text = nbrvelo.getText();
            int nombre=Integer.parseInt(text);
        //
         Reservation r=new Reservation(dateTime0, newdate, ids, 6, iddevelo,nombre,3);
            //men hnee tabda     
        ServiceReservation hh=new ServiceReservation();
        hh.insertPst(r);//
        //ALERT
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Ajouter reservation");
                //alert.setHeaderText("Results:");
		alert.setContentText("Reservation ajoutée avec succèss!");
                alert.showAndWait();
                }
    }
            /*// Get the reservation start date and time
        LocalDate date0 = dd.getValue();
        String heure = td.getText();
        LocalTime time0 = LocalTime.parse(heure);
        LocalDateTime dateTime0 = date0.atTime(time0);

        // Get the number of days to add to the reservation start date and time
        int jours = Integer.parseInt(nbrj.getText());
        LocalDateTime newdate = dateTime0.plusDays(jours);

        // Get the reservation information from the text fields
        int ids = Integer.parseInt(idr.getText());
        System.out.println(ids);
        int nombre = Integer.parseInt(nbrvelo.getText());

        // Create a new reservation object
        Reservation r = new Reservation(dateTime0, newdate, ids, 6, 1, nombre, 3.);

        // Insert the reservation into the database using the ServiceReservation class
        ServiceReservation hh = new ServiceReservation();
        hh.insertPst(r);

        // Show an alert dialog to indicate that the reservation was added successfully
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ajouter reservation");
        alert.setContentText("Reservation ajoutée avec succès !");
        alert.showAndWait();
    }*/

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void retourstation(ActionEvent event) {
        AnchorPane allp;
        try {
            allp = FXMLLoader.load(getClass().getResource("ClientStation.fxml"));
            reservationpanel.getChildren().removeAll();
            reservationpanel.getChildren().setAll(allp);
        } catch (IOException ex) {}
    }

    public void initDonnees(String variable0, String variable1, int variable2, int variable3) {
        //label1.setText(variable1);
        noms.setText(String.valueOf(variable0)+" à "+String.valueOf(variable1));
        idr.setText(String.valueOf(variable2));
        max.setText(String.valueOf(variable3));
    }

    @FXML
    private void deconnecter(ActionEvent event) {
        AnchorPane init0;
        try {
            init0 = FXMLLoader.load(getClass().getResource("USERLOGIN.fxml"));
            reservationpanel.getChildren().removeAll();
            reservationpanel.getChildren().setAll(init0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
