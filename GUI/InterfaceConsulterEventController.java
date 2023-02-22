/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Event;
import Entite.Reserv;
import Entite.User;
import Service.ServiceEvent;
import Service.ServiceReserv;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import utile.DataSource;

/**
 * FXML Controller class
 *
 * @author choua
 */
public class InterfaceConsulterEventController implements Initializable {

    @FXML
    private TextField id_event_field;
    @FXML
    private TextField id_client_field;
    @FXML
    private TextField chercher_fiel;
    @FXML
    private TableView<Reserv> T_Reservation;
    @FXML
    private TableColumn<Reserv, Integer> T_Reservation_id;
    @FXML
    private TableColumn<Reserv, Integer> Tservation_idevent_Re;
    @FXML
    private TableColumn<Reserv, Integer> T_Reservation_idclient;
     private Connection conn = DataSource.getInstance().getcnx();
   private ServiceReserv sr = new ServiceReserv();
   private ObservableList<Reserv> reservList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
   
   
@Override
public void initialize(URL url, ResourceBundle rb) {
   T_Reservation_id.setCellValueFactory(new PropertyValueFactory<>("id_res"));
Tservation_idevent_Re.setCellValueFactory(new PropertyValueFactory<>("ev"));

T_Reservation_idclient.setCellValueFactory(new PropertyValueFactory<>("u"));





   reservList.setAll(getListeReservations());
   T_Reservation.setItems(reservList);
}
 
public ObservableList<Reserv> getListeReservations() {
    ServiceReserv sr = new ServiceReserv();
    List<Reserv> reservations = sr.afficherReservation();

   for (Reserv reserv : reservations) {
        // Récupération de l'événement associé à la réservation
        ServiceEvent se = new ServiceEvent();
        Event event = se.getEventById(reserv.getEv().getId_event());
        reserv.setEv(event);

        // Récupération de l'utilisateur associé à la réservation
    //    ServiceUser su = new ServiceUser();
      //  User user = su.getUserById(reserv.getU().getIdUser());
    //    reserv.setU(user);
    }

    return FXCollections.observableArrayList(reservations);
}



    
 
    
    
    
    @FXML
    private void Reserv_Ajouter(ActionEvent event) {
        
  
         Reserv r1 = new Reserv();
        int id_event = Integer.parseInt(id_event_field.getText());
       
        r1.setEvent(new Event(id_event));
        
        
        int IdUser = Integer.parseInt(id_client_field.getText());
        
        r1.setUser(new User(IdUser));
        
        ServiceReserv sr = new ServiceReserv();
        
        sr.getReservation(id_event, IdUser);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Reservation added successfully!");
        alert.showAndWait();
        id_event_field.clear();
        id_client_field.clear();
        T_Reservation.refresh();
       T_Reservation.setItems(reservList);
        
    }

    @FXML
    private void Supp_Reserv(ActionEvent event) {
        Reserv r1= new Reserv();
        r1.setId_res(Integer.parseInt(chercher_fiel.getText()));
        ServiceReserv sr = new ServiceReserv();
        sr.annulerLaReservation(r1.getId_res());
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Reservation Deleted successfully!");
        alert.showAndWait();
        id_event_field.clear();
        id_client_field.clear();
          T_Reservation.refresh();
       T_Reservation.setItems(reservList);
    }

    @FXML
    private void cherch(ActionEvent event) {
  String requette = "select id_event ,IdUser  from reser where 	id_res  ='"+Integer.parseInt(chercher_fiel.getText())+"'";
    try{
       
        PreparedStatement pst = conn.prepareStatement(requette); 
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
         
            
            int id_event = rs.getInt("id_event");
            id_event_field.setText(String.valueOf(id_event));
            
            int IdUser = rs.getInt("IdUser");
            id_client_field.setText(String.valueOf(IdUser));
            
          
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        
        
    }
    
}
