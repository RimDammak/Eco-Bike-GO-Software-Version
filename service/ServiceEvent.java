/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entite.Event;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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
import sun.misc.IOUtils;
import utile.DataSource;

//import utile.DataSource;

/**
 *
 * @author choua
 */
public class ServiceEvent implements IEventServie<Event> {
    
    
       private Connection conn;
    public ServiceEvent(){
        conn = DataSource.getInstance().getcnx();
    }

    @Override
public void insert(Event e) {
    String requete = "insert into event (nom_event, date_event, locate_event, photo_event, dispoplace_event) values (?, ?, ?, ?, ?)";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, e.getNom_event());
        pst.setDate(2, e.getDate_event());
        pst.setString(3, e.getLocate_event());
        pst.setBlob(4, new ByteArrayInputStream(e.getPhoto_event().getBytes(1, (int) e.getPhoto_event().length())));
        pst.setInt(5, e.getDispoplace_event());
        System.out.println("Evénement Ajouté avec succés !");
        pst.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
}



    @Override
public void delete(String nom) {
    try {
        conn.setAutoCommit(false);
        String deleteReservationsQuery = "DELETE FROM reser WHERE id_event = (SELECT id_event FROM event WHERE nom_event = ?)";
        PreparedStatement deleteReservationsStatement = conn.prepareStatement(deleteReservationsQuery);
        deleteReservationsStatement.setString(1, nom);
        deleteReservationsStatement.executeUpdate();

        String deleteEventQuery = "DELETE FROM event WHERE nom_event = ?";
        PreparedStatement deleteEventStatement = conn.prepareStatement(deleteEventQuery);
        deleteEventStatement.setString(1, nom);
        deleteEventStatement.executeUpdate();
        
        conn.commit();
        System.out.println("Evénement supprimé avec succés !");
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        try {
            conn.rollback();
        } catch (SQLException e) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, e);
        }
    } finally {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}



    
    
    ////delete par id 
  public void deletebyId(int id) {
    try {
        conn.setAutoCommit(false);
        String deleteReservationsQuery = "DELETE FROM reser WHERE id_event = (SELECT id_event FROM event WHERE id_event = ?)";
        PreparedStatement deleteReservationsStatement = conn.prepareStatement(deleteReservationsQuery);
        deleteReservationsStatement.setInt(1, id);
        deleteReservationsStatement.executeUpdate();

        String deleteEventQuery = "DELETE FROM event WHERE id_event = ?";
        PreparedStatement deleteEventStatement = conn.prepareStatement(deleteEventQuery);
        deleteEventStatement.setInt(1, id);
        deleteEventStatement.executeUpdate();
        
        conn.commit();
        System.out.println("Evénement supprimé avec succés !");
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        try {
            conn.rollback();
        } catch (SQLException e) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, e);
        }
    } finally {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}

  
  
 public List<Event> readAll() {
List<Event> events = new ArrayList<>();
String requete = "select * from event";
try {
PreparedStatement pst = conn.prepareStatement(requete);
ResultSet rs = pst.executeQuery();
while (rs.next()) {
Event event = new Event();
event.setId_event(rs.getInt("id_event"));
event.setNom_event(rs.getString("nom_event"));
event.setDate_event(rs.getDate("date_event"));
event.setLocate_event(rs.getString("locate_event"));
event.setPhoto_event(rs.getBytes("photo_event"));
event.setDispoplace_event(rs.getInt("dispoplace_event"));
events.add(event);
}
} catch (SQLException ex) {
Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
}
return events;
}
  
  
     public Event readById(int id) {
     Event event = new Event();
    String requete = "select * from event where id_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
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

   public void update(int id, String nom, Date date, String locate, byte[]  photo, int dispoplace_event) {
        
        String requete = "UPDATE event SET nom_event = ?, date_event = ?, locate_event = ?, photo_event = ?, dispoplace_event = ? WHERE id_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, nom);
        pst.setDate(2, date);
        pst.setString(3, locate);
        pst.setBlob(4, new ByteArrayInputStream(photo));
        pst.setInt(5, dispoplace_event);
        pst.setInt(6, id);
        pst.executeUpdate();
        System.out.println("Evénement modifié avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }

    /* public Event getEventByNom(String nom_event) {
    Event event = null;
    String requete = "SELECT * FROM event WHERE nom_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, nom_event);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
             int id = rs.getInt("id_event");
            String nom = rs.getString("nom_event");
            Date date = rs.getDate("date_event");
            String locate = rs.getString("locate_event");
            String photo = rs.getString("photo_event");
            int dispoplace_event = rs.getInt("dispoplace_event");
            event = new Event(nom);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return event;
}*/
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   /*
    @Override


    @Override
 
  
    @Override



//------------------Calculer le nbre totale d'un événemnts passé en paramétre ---------------------//

public int calculer_nbEvent(String nom) {
    int nbEvents = 0;
    String requete ="SELECT COUNT(*) FROM event where nom_event='"+nom+"'"; 
    try {
        Statement st = conn.createStatement();
        ResultSet rs=st.executeQuery(requete);
        if (rs.next()){
            String chaine = String.valueOf(rs.getString(1));
            nbEvents = Integer.parseInt(chaine);
            return nbEvents;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println("Le nombre d'événements pour ce nom est :");
    return nbEvents;
}



//---------- calculer le nbre totale des événemnts pésentes -------------/////

public int calculerNombreEvenements() {
    int nombreEvenements = 0;
    String requete = "SELECT COUNT(*) FROM event";
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        if (rs.next()) {
            String chaine = String.valueOf(rs.getString(1));
            nombreEvenements = Integer.parseInt(chaine);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println("Le nombre total d'événements est : " + nombreEvenements);
    return nombreEvenements;
}

//------------- Resservation

public void reserver(int id_event, int IdUser) {
     int dispoplace_event;
    String requete = "SELECT dispoplace_event FROM event WHERE id_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_event);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int nbDispoPlace = rs.getInt("dispoplace_event");
            if (nbDispoPlace > 0) {
                requete = "UPDATE event SET dispoplace_event = ? WHERE id_event = ?";
                pst = conn.prepareStatement(requete);
                pst.setInt(1, nbDispoPlace - 1);
                pst.setInt(2, id_event);
                pst.executeUpdate();

                requete = "INSERT INTO reser (id_event, IdUser) VALUES (?, ?)";
                pst = conn.prepareStatement(requete);
                pst.setInt(1, id_event);
                pst.setInt(2, IdUser);
                pst.executeUpdate();

                System.out.println("Réservation effectuée avec succès");
            } else {
                System.out.println("Il n'y a plus de places disponibles pour cet événement");
            }
        } else {
            System.out.println("L'événement avec l'ID spécifié n'a pas été trouvé");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
}


public Event chercherEvenementParNom(String nom_event) {
    String requete = "SELECT * FROM event WHERE nom_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, nom_event);
        ResultSet resultat = pst.executeQuery();
        if (resultat.next()) {
            int id = resultat.getInt("id_event");
            String nom = resultat.getString("nom_event");
            Date date = resultat.getDate("date_event");
            String locate = resultat.getString("locate_event");
            String photo = resultat.getString("photo_event");
            int dispoplace_event = resultat.getInt("dispoplace_event");
            Event event = new Event(id, nom, date, locate, photo, dispoplace_event);
            return event;
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}

////Annuler reservtion

public void annulerReservation(int idReservation) {
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
        } else {
            System.out.println("La réservation avec l'ID spécifié n'a pas été trouvée");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
}

//afficher liste des evenements



/// trie des evenemts

public List<Event> trierEvenementsParNom() {
    List<Event> events = new ArrayList<>();
    String requete = "SELECT * FROM event ORDER BY nom_event ASC";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Event event = new Event();
            event.setId_event(rs.getInt("id_event"));
            event.setNom_event(rs.getString("nom_event"));
            event.setDate_event(rs.getDate("date_event"));
            event.setLocate_event(rs.getString("locate_event"));
            event.setPhoto_event(rs.getString("photo_event"));
            event.setDispoplace_event(rs.getInt("dispoplace_event"));
            events.add(event);
           
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return events;


    
}


public List<Event> displayEventsByLocation(String location) {
     List<Event> events = new ArrayList<>();
    String query = "SELECT * FROM event WHERE locate_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, location);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int idEvent = rs.getInt("id_event");
            String nomEvent = rs.getString("nom_event");
            String dateDebut = rs.getString("date_event");
            String locate =rs.getString("locate_event");
            String photo = rs.getString("photo_event");
            int dispoPlace = rs.getInt("dispoplace_event");
            System.out.println("ID: " + idEvent + ", Nom: " + nomEvent + ", Date de début: " + dateDebut + ", Date de fin: " + locate + ", Lieu: " + photo + ", Places disponibles: " + dispoPlace);
        }
        else
        {
            System.out.println("Desole ");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        
    }
    return events;
}

    public Event getEventById(int id_event) {
    Event event = null;
    String requete = "SELECT * FROM event WHERE id_event = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_event);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
             int id = rs.getInt("id_event");
            String nom = rs.getString("nom_event");
            Date date = rs.getDate("date_event");
            String locate = rs.getString("locate_event");
            String photo = rs.getString("photo_event");
            int dispoplace_event = rs.getInt("dispoplace_event");
            event = new Event(id, nom, date, locate, photo, dispoplace_event);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
    }
    return event;
}
*/

  

}