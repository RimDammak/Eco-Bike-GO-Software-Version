/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entite.Reservation;
import entite.Station;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.DataSource;

/**
 *
 * @author houss
 */
public class ServiceReservation implements IReservation<Reservation>{
    private Connection conn;
    public ServiceReservation() {
        conn = DataSource.getInstance().getCnx();
    }
    
    public void insertPst(Reservation R) {
        System.out.println("Insertion Reservation    ================="+R);
        String mareservation = "insert into reservation(date_debut, date_fin, id_station, iduser, id_velo, nbr, prixr) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(mareservation);
            pst.setObject(1, R.getDate_debut());
            pst.setObject(2, R.getDate_fin());
            pst.setInt(3, R.getId_station());
            pst.setInt(4, R.getIduser());
            pst.setInt(5, R.getId_velo());
            pst.setInt(6, R.getNbr());
            pst.setDouble(7, R.getPrixr());
            
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceStation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Reservation> readAll() {
    List<Reservation> list = new ArrayList<>();
    String query = "SELECT * FROM reservation";

    try (PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {

            
            Reservation reservation = new Reservation(
                rs.getInt("id_reservation"),
                rs.getTimestamp("date_debut").toLocalDateTime(),
                rs.getTimestamp("date_fin").toLocalDateTime(),
                rs.getInt("id_station"),
                rs.getInt("iduser"),
                rs.getInt("id_velo"),
                rs.getInt("nbr"),
                rs.getInt("prixr")    
            );
            list.add(reservation);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}

    //
public Reservation read(int id) {
    Reservation reservation = null;
    String query = "SELECT * FROM reservation WHERE id_reservation = ?";
    try (PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                reservation = new Reservation(
                    rs.getInt("id_reservation"),
                    rs.getTimestamp("date_debut").toLocalDateTime(),
                    rs.getTimestamp("date_fin").toLocalDateTime(),
                    rs.getInt("id_station"),
                    rs.getInt("iduser"),
                    rs.getInt("id_velo"),
                    rs.getInt("nbr"),
                    rs.getInt("prixr")
                );
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);
    }
    return reservation;
}


}

