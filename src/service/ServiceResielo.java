/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entite.Resielo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.DataSource;
import entite.Resielo;
import entite.Station;
import java.sql.Statement;

/**
 *
 * @author houss
 */
public class ServiceResielo implements IResielo<Resielo>{
    private Connection conn;
    public ServiceResielo() {
        conn = DataSource.getInstance().getCnx();
    }
    
    @Override
    public List<Resielo> readAll() {
    List<Resielo> list = new ArrayList<>();
    String query = "SELECT reservation.id_reservation, reservation.date_debut, reservation.date_fin, reservation.nbr, reservation.prixr, " +
"velo.titre, velo.prix, " +
"station.nom_station, station.localisation_station, " +
"user.NomUser, user.PrenomUser, user.NumTel " +
"FROM reservation " +
"JOIN station ON reservation.id_station = station.id_station " +
"JOIN user ON reservation.iduser = user.IdUser " +
"JOIN velo ON reservation.id_velo = velo.id_velo " +
"GROUP BY reservation.id_reservation;";


    try (PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {             
        while (rs.next()) {
            Resielo resielo = new Resielo(
                    rs.getInt("id_reservation"),
                    rs.getTimestamp("date_debut").toLocalDateTime(),
                    rs.getTimestamp("date_fin").toLocalDateTime(),
                    rs.getString("nom_station"), rs.getString("localisation_station"),
                    rs.getString("NomUser"), rs.getString("PrenomUser"),
                    rs.getString("NumTel"), rs.getString("titre"),
                    rs.getDouble("prix"),rs.getInt("nbr"), rs.getDouble("prix")*rs.getInt("nbr"));
            //System.out.println(resielo.toString());
            list.add(resielo);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceResielo.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}
    
       
}
