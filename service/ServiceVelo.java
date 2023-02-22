/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.mysql.cj.Session;
import entite.Categorie;
import entite.Station;
import entite.Velo;
import java.sql.Blob;
import java.sql.Connection;

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
 * @author macbook
 */
public  class ServiceVelo implements IVeloService<Velo> {
private final Connection conn;
    public ServiceVelo(){
        conn = (Connection) DataSource.getInstance().getcnx();
    }
  
@Override
    public void insert(Velo v) {
        String requete = "insert into velo (id_velo,id_station,titre,prix,image,qte,id_categorie)values(?,?,?,?,?,?,?)";
    try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setInt(1, v.getId_velo());
            pst.setInt(2, v.getSta().getId_station());
            pst.setString(3, v.getTitre());
            pst.setFloat(4, v.getPrix());
            pst.setBlob(5, v.getImage());
            pst.setInt(6,v.getQte());
            pst.setInt(7,v.getCat().getId_categorie());
            System.out.println("velo Ajouté avec succés !");
       
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ServiceVelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
   
@Override
    public List<Velo> readAll() {
        List<Velo> velos = new ArrayList<>();
    String requete = "select * from velo";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
           Velo velo = new Velo();
            velo.setId_velo(rs.getInt("id_velo"));
           velo.setSta(new Station(rs.getInt("id_station")));
            velo.setTitre(rs.getString("titre"));
           velo.setPrix(rs.getFloat("prix"));
            velo.setImage(rs.getBlob("image"));
            velo.setQte(rs.getInt("qte"));
            velo.setCat(new Categorie(rs.getInt("id_categorie")));

            velos.add(velo);
            System.out.println("Affichage");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceVelo.class.getName()).log(Level.SEVERE, null, ex);
    }
    return velos;
    }

    /**
     *
     * @param id_velo
     * @return
     */
    public Velo readById(int id_velo) {
     Velo velo = new Velo();
    String requete = "select * from velo where id_velo = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_velo);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
           velo.setId_velo(rs.getInt("id_velo"));
            velo.setSta(new Station(rs.getInt("id_station")));
            velo.setTitre(rs.getString("titre"));
           velo.setPrix(rs.getFloat("prix"));
            velo.setImage(rs.getBlob("image"));
            velo.setQte(rs.getInt("qte"));
             velo.setCat(new Categorie(rs.getInt("id_categorie"), "nom_categrorie", "desc_categorie"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceVelo.class.getName()).log(Level.SEVERE, null, ex);
    }
    return velo;
    }

    /**
     *
     * @param id_velo
     * @param sta
     * @param titre
     * @param prix
     * @param image
     * @param qte
     * @param cat
     */
@Override
public void update( int id_velo,Station sta, String titre, float prix, Blob image, int qte ,Categorie cat) {
        
        String requete = "UPDATE velo SET id_station = ?, titre = ?, image = ?, prix = ? , qte = ?, id_categorie =? WHERE id_velo = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_velo);
       pst.setInt(2, sta.getId_station());
        pst.setString(3, titre);
        pst.setFloat(4, prix);
        pst.setBlob(5, image);
        pst.setInt(6, qte);
         pst.setInt(7, cat.getId_categorie());
        pst.executeUpdate();
        System.out.println("vélo modifié avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(ServiceVelo.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
//------------------Calculer le nbre totale des velos passé en paramétre ---------------------//

public int calculer_nbVelo(String titre) {
    int nbVelos = 0;
    String requete ="SELECT COUNT(*) FROM velo where titre='"+titre+"'"; 
    try {
        Statement st = conn.createStatement();
        ResultSet rs=st.executeQuery(requete);
        if (rs.next()){
            String chaine = String.valueOf(rs.getString(1));
            nbVelos = Integer.parseInt(chaine);
            return nbVelos;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println("Le nombre des velos pour ce titre est :");
    return nbVelos;
}
//---------- calculer le nbre totale des velos pésentes -------------/////

public int calculerNombreVelos() {
    int nombreVelos = 0;
    String requete = "SELECT COUNT(*) FROM velo";
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        if (rs.next()) {
            String chaine = String.valueOf(rs.getString(1));
            nombreVelos = Integer.parseInt(chaine);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println("Le nombre total des velos est : " +nombreVelos);
    return nombreVelos;
}
public Velo chercherVeloparTitre(String titre) throws SQLException {
    String requete = "SELECT * FROM velo WHERE titre = ?";
    try (PreparedStatement pst = conn.prepareStatement(requete)) {
        pst.setString(1, titre);
        try (ResultSet resultat = pst.executeQuery()) {
            if (resultat.next()) {
                int id_velo = resultat.getInt("id_velo");
                int id_station = resultat.getInt("id_station");
                String Titre = resultat.getString("titre");
                Blob image = resultat.getBlob("image");
                float Prix = resultat.getFloat("prix");
                int qte = resultat.getInt("qte");
                int id_categorie = resultat.getInt("id_categorie");
                Velo velo = new Velo();
                velo.setId_velo(id_velo);
                velo.setSta(new Station(id_station));
                velo.setTitre(Titre);
                velo.setPrix(Prix);
                velo.setImage(image);
                velo.setQte(qte);
                velo.setCat(new Categorie(id_categorie, "nom_categorie", "desc_categorie"));
                return velo;
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceVelo.class.getName()).log(Level.SEVERE, null, ex);
        throw ex;
    }
    return null; // ou lancer une exception explicite ou renvoyer une valeur par défaut
}

    public void delete1(Velo v) {
    String requete = "DELETE FROM velo WHERE id_velo = ? AND id_station = ? AND id_categorie = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, v.getId_velo());
        pst.setInt(2, v.getSta().getId_station());
        pst.setInt(3, v.getCat().getId_categorie());
        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Vélo supprimé avec succès !");
        } else {
            System.out.println("Le vélo à supprimer n'existe pas !");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceVelo.class.getName()).log(Level.SEVERE, null, ex);
    }
}

   

 public void deleteById(int id_velo) throws SQLException {
 
    PreparedStatement statement = conn.prepareStatement("DELETE FROM velo WHERE id_velo = ?");
    statement.setInt(1, id_velo);
    statement.executeUpdate();
}





    
@Override
    public Velo readByID(int id_velo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public void update_velo(Velo v) {
    String requete = "UPDATE velo SET id_station = ?, titre = ?, prix = ?, image = ?, qte = ?, id_categorie = ? WHERE id_velo = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, v.getSta().getId_station());
        pst.setString(2, v.getTitre());
        pst.setFloat(3, v.getPrix());
        pst.setBlob(4, v.getImage());
        pst.setInt(5, v.getQte());
        pst.setInt(6, v.getCat().getId_categorie());
        pst.setInt(7, v.getId_velo());
        int result = pst.executeUpdate();
        if (result > 0) {
            System.out.println("Vélo mis à jour avec succès !");
        } else {
            System.out.println("Le vélo à mettre à jour n'existe pas !");
        }
    } catch (SQLException ex) {
        Logger.getLogger(ServiceVelo.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public List<Velo> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
  
public void delete(int id_velo) {
    String requete = "DELETE FROM velo WHERE id_velo = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id_velo);
        pst.executeUpdate();
        System.out.println("vélo supprimé avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(ServiceVelo.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
  
  





  

