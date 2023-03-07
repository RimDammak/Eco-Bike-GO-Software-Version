/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import entite.Categorie;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.DataSource;
/**
 *
 * @author macbook
 */
public class ServiceCategorie  {
private final Connection conn;
public ServiceCategorie(){
        conn = (Connection) DataSource.getInstance().getCnx();
    }
  
    
   
    public void insert(Categorie categorie)  {
    String sql = "INSERT INTO categorie (id_categorie, nom_categorie, desc_categorie) VALUES (?, ?, ?)";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, categorie.getId_categorie());
        stmt.setString(2, categorie.getNom_categorie());
        stmt.setString(3, categorie.getDesc_categorie());

        stmt.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    /**
     *
     * @param id_categorie
     */
   public void delete(int id_categorie) {
    String sql = "DELETE FROM categorie WHERE id_categorie = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id_categorie);
        stmt.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public void update(int id_categorie, String nom_categorie, String desc_categorie) throws SQLException {
    String sql = "UPDATE categorie SET nom_categorie = ?, desc_categorie = ? WHERE id_categorie = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, nom_categorie);
        stmt.setString(2, desc_categorie);
        stmt.setInt(3, id_categorie);

        stmt.executeUpdate();
    }
}



    public List<Categorie> readAll() {
    String sql = "SELECT * FROM categorie";
    List<Categorie> categories = new ArrayList<>();
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            int id_categorie = rs.getInt("id");
            String nom_categorie = rs.getString("nom");
            String desc_categorie = rs.getString("description");
            
            Categorie categorie = new Categorie(id_categorie, nom_categorie, desc_categorie);
            categories.add(categorie);
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceCategorie.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return categories;
}

    public void setId_Categorie(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setNom_Categorie(String velo_ville) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setDesc_Categorie(String enfant) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }






   
}
