package service;

import entite.Reclamation;
import entite.Type_Rec;
import entite.user;
import java.sql.Connection;
import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utile.DataSource;

/**
 *
 * @author damak
 */
public class ServiceReclamation implements IServiceRec<Reclamation> {

    private Connection conn;

    public ServiceReclamation() {
        conn = DataSource.getInstance().getCnx();
    }

    ///LA FONCTION INSERT AVEC R COMME PARAMETRE//

    /**
     *
     * @param r
     */
    @Override
    public void insert(Reclamation r) {
        String requette = "insert into reclamation (date_rec, description_rec, id_type ,etat_rec, reponse, IdUser) values (?,?,?,?,?,?)";

        try {
            PreparedStatement pst = conn.prepareStatement(requette);
 
            pst.setDate(1, (java.sql.Date) (Date) r.getDate_rec());
            pst.setString(2, r.getDescription_rec());
            // pst.setString(3, r.getT().getNom_type());
            pst.setInt(3, r.getT().getId_type());
            pst.setInt(4, r.getEtat_rec());
            pst.setString(5, r.getReponse());
            pst.setInt(6, r.getU().getIdUser());
           
            pst.executeUpdate();
            System.out.println("INSERSERTION AVEC SUCESS");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//LA FONCTION DELETE AVEC ID REC COMME PARAMATRE

    public void delete(int id_rec) {

        String requette = "DELETE FROM reclamation WHERE id_rec = ? ";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requette);
            pst.setInt(1, id_rec);
            pst.executeUpdate();
            System.out.println("Reclamation Supprimée AVEC SUCCES");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//la fonction update

    public void update(int id_rec, Date date_rec, String description_rec, Type_Rec T, int etat_rec, String reponse, user U) {

        String requete = "UPDATE reclamation SET id_rec=?, date_rec=?, description_rec=?, id_type=?, etat_rec=?, reponse=?, IdUser=?";
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(requete);
            pst.setInt(1, id_rec);
            pst.setDate(2, (java.sql.Date) date_rec);
            pst.setString(3, description_rec);
            pst.setInt(4, T.getId_type());
     //        pst.setString(5, T.getNom_type());
            pst.setInt(5, etat_rec);
            pst.setString(6, reponse);
            pst.setInt(7, U.getIdUser());
            pst.executeUpdate();
            System.out.println("Réclamation Modifiée AVEC SUCESS");

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
    public List<Reclamation> readAll() {
        List<Reclamation> reclamations = new ArrayList<>();
        String requette = "select * from reclamation";
        try {
            PreparedStatement pst = conn.prepareStatement(requette);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reclamation reclamation = new Reclamation();
                reclamation.setId_rec(rs.getInt("id_rec"));
                reclamation.setT(new Type_Rec(rs.getInt("id_type")));
              // reclamation.setT(new Type_Rec(rs.getString("nom_type")));
                reclamation.setDate_rec(rs.getDate("date_rec"));
                reclamation.setDescription_rec(rs.getString("description_rec"));
                reclamation.setEtat_rec(rs.getInt("etat_rec"));
                reclamation.setReponse(rs.getString("reponse"));
                reclamation.setU(new user(rs.getInt("IdUser")));
                   reclamations.add(reclamation);
                System.out.println("Affichage de tous les Reclamation");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reclamations;
    }


    public Reclamation readById(int id_rec) {

        Reclamation r =new Reclamation();
        String requette = "select * from Reclamation where id_rec= ?";

        try {
            PreparedStatement pst = conn.prepareStatement(requette);
            pst.setInt(1, id_rec);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

                r.setId_rec(rs.getInt("id_rec"));
                r.setDate_rec(rs.getDate("date_rec"));
                r.setDescription_rec(rs.getString("description_rec"));
                r.setT(new Type_Rec(rs.getInt("id_type")));
               // r.setT(new Type_Rec(rs.getString("nom_type")));
                r.setEtat_rec(rs.getInt("etat_rec"));
                r.setReponse(rs.getString("reponse"));
                r.setU(new user(rs.getInt("IdUser")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    @Override
    public void update(int id_rec, java.sql.Date date_rec, String description_rec, Type_Rec T, int etat_rec, String reponse, user U) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //fonction mettre a jour 
 public void MettreAjour(Reclamation r) {
    String requete = "UPDATE reclamation SET date_rec=?, description_rec=?, id_type=?, IdUser=? WHERE id_rec = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
     
        pst.setDate(1, (java.sql.Date) (Date) r.getDate_rec());
        pst.setString(2, r.getDescription_rec());
        pst.setInt(3, r.getT().getId_type());
        pst.setInt(4,r.getU().getIdUser());
        pst.setInt(5,r.getId_rec());
        
         
        pst.executeUpdate();
        System.out.println("Reclamation mis à jour avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    
    
    
    
    
    
    
    
    
}

   
