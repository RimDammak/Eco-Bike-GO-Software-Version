/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entite.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author houss
 */
public class ServiceStation implements IStation<Station>{
    private Connection conn;
    public ServiceStation() {
        conn = DataSource.getInstance().getCnx();
    }
    
    public void insertPst(Station p) {
   
        String requete = "insert into station(nom_station,localisation_station,velo_station) values(?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, p.getNom_station());
            pst.setString(2, p.getLocalisation_station());
            pst.setInt(3, p.getVelo_station());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public boolean supprimerStation(Station s) {
    
    String sql = "DELETE FROM station WHERE Id_Station =" + s.getId_station();
    try {
        Statement st = conn.createStatement();
        int rs1 = st.executeUpdate(sql);

        if (rs1 > 0) {
            System.out.println("Suppression faite avec succes");
            return true;
        }

    } catch (SQLException e) {
        System.out.println("Erreur lors de la suppression");
        return false;
    }
    return false;
}

    
    public List<Station> readAll() {
    List<Station> list = new ArrayList<>();
    String requete = "SELECT * FROM station";
    try (Statement st = conn.createStatement();
         ResultSet rs = st.executeQuery(requete)) {
        while (rs.next()) {
            Station s = new Station(rs.getInt("id_station"), rs.getString("nom_station"),
                    rs.getString("localisation_station"), rs.getInt("velo_station"));
            list.add(s);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceStation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }
    
public Station read(int id) throws SQLException {
    Station s = null;
    String query = "SELECT * FROM station WHERE id_station = ?";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                s = new Station(
                    rs.getInt("id_station"),
                    rs.getString("nom_station"),
                    rs.getString("localisation_station"),
                    rs.getInt("velo_station")
                );
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceStation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return s;
}


    
    public void modifierStation(int id, String nom, String localisation, int velodisponible) {
    String requete = "UPDATE station SET nom_station = ?, localisation_station = ?, velo_station = ?   WHERE id_station = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, nom);
        pst.setString(2, localisation);
        pst.setInt(3, velodisponible);
        pst.setInt(4, id);
        int resultat = pst.executeUpdate();
        if (resultat > 0) {
            System.out.println("La station a été modifiée avec succès !");
        } else {
            System.out.println("La station n'a pas été modifiée.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceStation.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Erreur lors de la modification de la station.");
    }
    }


public String Mail = "houssemjallouli99@gmail.com";
public String Password = "uujtoekvvvctgxsq";
public void envoyer(String recepient) {
Properties properies = new Properties();
properies.put("mail.smtp.host", "smtp.gmail.com");
properies.put("mail.smtp.port", "465");
properies.put("mail.smtp.auth", "true");
properies.put("mail.smtp.starttls.enable", "true");
properies.put("mail.smtp.starttls.required", "true");
properies.put("mail.smtp.ssl.protocols", "TLSv1.2");
properies.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
Session session = Session.getInstance(properies,
new javax.mail.Authenticator() {
@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(Mail, Password);
}
});
try {
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("houssemjallouli99@gmai"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(recepient));
message.setSubject("Test email");
message.setText("Bonjour, admin une nouvelle station a ete ajouter!");
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
      System.out.println("error" + e.toString());
} }


 
}
