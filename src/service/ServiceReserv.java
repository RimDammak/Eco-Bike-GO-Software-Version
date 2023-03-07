/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Event;
import entite.Reserv;
import entite.user;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.DataSource;

/**
 *
 * @author choua
 */
public class ServiceReserv {

  private Connection conn;
    public ServiceReserv(){
        conn = DataSource.getInstance().getCnx();
    }    
    
    
    /////
public Event getEventById(int id_event) {
    Event event = null;
    String requete = "SELECT * FROM event WHERE id_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_event);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            event = new Event();
            event.setId_event(rs.getInt("id_event"));
            event.setNom_event(rs.getString("nom_event"));
            event.setDate_event(rs.getDate("date_event"));
            event.setLocate_event(rs.getString("locate_event"));
            event.setPhoto_event(rs.getBytes("photo_event"));
            event.setDispoplace_event(rs.getInt("dispoplace_event"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return event;
}

    
    
 public user getUserById(int idUser) {
    user user = null;
    String requete = "SELECT * FROM user WHERE IdUser = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, idUser);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("IdUser");
            user = new user(id);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return user;
}

    
 
    ///
    
public Reserv getReservation(int id_event, int idUser) {
    Reserv reservation = null;
    int nbDispoPlace;
    String requete = "SELECT dispoplace_event FROM event WHERE id_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_event);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nbDispoPlace = rs.getInt("dispoplace_event");
            if (nbDispoPlace > 0) {
                requete = "UPDATE event SET dispoplace_event = ? WHERE id_event = ?";
                pst = conn.prepareStatement(requete);
                pst.setInt(1, nbDispoPlace - 1);
                pst.setInt(2, id_event);
                pst.executeUpdate();

                requete = "INSERT INTO reser (id_event, IdUser) VALUES (?, ?)";
                pst = conn.prepareStatement(requete);
                pst.setInt(1, id_event);
                pst.setInt(2, idUser);
                pst.executeUpdate();

                System.out.println("Réservation effectuée avec succès");

                // Créer l'objet Reservation avec les données de la réservation
                reservation = new Reserv();
                reservation.setEvent(getEventById(id_event));
                reservation.setUser(getUserById(idUser));
            } else {
                System.out.println("Il n'y a plus de places disponibles pour cet événement");
            }
        } else {
            System.out.println("L'événement avec l'ID spécifié n'a pas été trouvé");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReserv.class.getName()).log(Level.SEVERE, null, ex);
    }
    return reservation;
}

public Reserv annulerLaReservation(int idReservation) {
     Reserv reservation = null;
    String requete = "SELECT id_event FROM reser WHERE id_res = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, idReservation);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int idEvent = rs.getInt("id_event");

            requete = "UPDATE event SET dispoplace_event = dispoplace_event + 1 WHERE id_event = ?";
            pst = conn.prepareStatement(requete);
            pst.setInt(1, idEvent);
            pst.executeUpdate();

            requete = "DELETE FROM reser WHERE id_res = ?";
            pst = conn.prepareStatement(requete);
            pst.setInt(1, idReservation);
            pst.executeUpdate();

            System.out.println("Réservation annulée avec succès");
            ServiceEvent eventService = new ServiceEvent();
            Event event = eventService.getEventById(idEvent);

            
            reservation = new Reserv(idReservation, event, null);

        } else {
            System.out.println("La réservation avec l'ID spécifié n'a pas été trouvée");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReserv.class.getName()).log(Level.SEVERE, null, ex);
    }
    return reservation;
}

  

public List<Reserv> afficherReservation() {
    List<Reserv> listReserv = new ArrayList<>();
    String requete = "SELECT * FROM reser JOIN event ON reser.id_event = event.id_event JOIN user ON reser.IdUser = user.IdUser";
    try {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(requete);
    while (rs.next()) {
    Reserv r = new Reserv();
    r.setId_res(rs.getInt("id_res"));
    Event ev = new Event();
    ev.setId_event(rs.getInt("id_event"));
    user u = new user();
    u.setIdUser(rs.getInt("IdUser"));
    r.setEv(ev);
    r.setU(u);
    listReserv.add(r);
}

    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
   System.out.println("Liste de réservations : " + listReserv); // vérification
    return listReserv;
}













public List<Reserv> getListeReservations() {
   List<Reserv> list = new ArrayList<>();
    String requete = "SELECT * FROM reser JOIN event ON reser.id_event = event.id_event JOIN user ON reser.IdUser = user.IdUser";
    try {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(requete);
        while (rs.next()) {
            Reserv r = new Reserv();
            r.setId_res(rs.getInt("id_res"));
             Event ev = new Event();
            ev.setId_event(rs.getInt("id_event"));
             user u = new user();
            u.setIdUser(rs.getInt("IdUser"));
           r.setEv(ev);
            r.setU(u);
            list.add(r);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
      System.out.println("Liste de réservations : " + list); // vérification
    return list;
}























}
