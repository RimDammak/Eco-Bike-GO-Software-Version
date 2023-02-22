 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package service;
import entite.user;
import java.sql.Date;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utils.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author pc
 */
public class UserService implements IService<user>{
    
    private Connection conn;
    public UserService() {
        conn = DataSource.getInstance().getcnx();
    }

/*@Override
    public void insert(user t) {
 String requete = "insert into user (NomUser,PrenomUser,DateNaiss,NumTel,Email,Adresse,Mdp,Role) values "
                + "('" + t.getNomUser()+ "','" + t.getPrenomUser()+ "'," + t.getDateNaiss()+ ",'"+t.getNumTel()+ "','"+t.getEmail()+ "','" +t.getAdresse()+"','"+t.getMdp()+"',"+t.getRole()+ " )";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    
    @Override
     public void insertPst(user p) {
        String requete = "insert into user(NomUser,PrenomUser,DateNaiss,NumTel,Email,Adresse,Mdp,ImgUser,Role) values(?,?,?,?,?,?,?,?,?)";
        
if (!isEmailValid(p.getEmail())) {
   System.out.println("L'email n'est pas valide.");
} else {
 try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, p.getNomUser());
            pst.setString(2, p.getPrenomUser());
            pst.setDate(3, (Date) p.getDateNaiss());
            pst.setString(4, p.getNumTel());
            pst.setString(5, p.getEmail());
            pst.setString(6, p.getAdresse());
            pst.setString(7, p.getMdp());
            pst.setBlob(8, p.getImgUser());
            pst.setString(9, p.getRole());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }}

       

    }
    

    @Override
    public void delete(int id) {
        String sql = "DELETE from user where IdUser= '"+id+"' "; 
        try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println("User supprimé avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
    }

  /*  @Override
    public void update(int id,String nom,String prenom,Date dateNaiss,String num,String email,String adresse,String image,String role) {
       String sql= "UPDATE user SET NomUser='"+nom+"',PrenomUser= '"+prenom+"' ,DateNaiss= '"+dateNaiss+"',NumTel='"+num+"',Email='"+email+"' ,Adresse='"+adresse+"' ,ImgUser='"+image+"' ,Role='"+role+"' where IdUser = '"+id+"'";
       try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println(" User modifié avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }*/


    @Override
    public List<user> readAll() {
            List<user> users= new ArrayList<>();
            String requete="select * from user";
        try {
          PreparedStatement pst=conn.prepareStatement(requete);
          ResultSet rs=pst.executeQuery();
          while(rs.next()){
              user usr=new user();
              usr.setIdUser(rs.getInt("IdUser"));
              usr.setNomUser(rs.getString("NomUser"));
              usr.setPrenomUser(rs.getString("PrenomUser"));
              usr.setDateNaiss(rs.getDate("DateNaiss"));
              usr.setNumTel(rs.getString("NumTel"));
              usr.setEmail(rs.getString("Email"));
              usr.setAdresse(rs.getString("Adresse"));
              usr.setMdp(rs.getString("Mdp"));
              usr.setImgUser(rs.getBytes("ImgUser"));
              usr.setRole(rs.getString("Role"));
              users.add(usr);
          
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        return users;
    }

    @Override
    public user readById(int id) {
        user usr = new user();
    String requete = "select * from user where IdUser = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            usr.setIdUser(rs.getInt("IdUser"));
            usr.setNomUser(rs.getString("NomUser"));
            usr.setPrenomUser(rs.getString("PrenomUser"));
            usr.setDateNaiss(rs.getDate("DateNaiss"));
            usr.setNomUser(rs.getString("NumTel"));
            usr.setEmail(rs.getString("Email"));
            usr.setAdresse(rs.getString("Adresse"));
            usr.setImgUser(rs.getBlob("ImgUser"));
             usr.setMdp(rs.getString("Mdp"));
            usr.setRole(rs.getString("Role"));

        }
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return usr;
    }
    
    
    public int calculerNombreUsers() {
    int nbuser= 0;
    String requete = "SELECT COUNT(*) FROM user";
    try {
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(requete);
        if (rs.next()) {
            String chaine = String.valueOf(rs.getString(1));
            nbuser = Integer.parseInt(chaine);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    System.out.println("Le nombre total des user est : " + nbuser);
    return nbuser;
}
    
public void MettreAjour(user usr) {
    String requete = "UPDATE user SET NomUser = ?, PrenomUser = ?, DateNaiss = ?, NumTel = ?, Email = ?, Adresse = ?, ImgUser = ?, Mdp = ?, Role =? WHERE IdUser = ?";
    try {
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setString(1, usr.getNomUser());
        pst.setString(2, usr.getPrenomUser());
        pst.setDate(3, (Date) usr.getDateNaiss());
        pst.setString(4, usr.getNumTel());
        pst.setString(5, usr.getEmail());
        pst.setString(6, usr.getAdresse());
        pst.setBlob(7, usr.getImgUser());
        pst.setString(8, usr.getMdp());
        pst.setString(9, usr.getRole());
        pst.setInt(10, usr.getIdUser());
        pst.executeUpdate();
        System.out.println("User mis à jour avec succès !");
    } catch (SQLException ex) {
        Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
    }
}





    
   public boolean isEmailValid(String email) {
   boolean isValid = false;
   String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
   CharSequence inputStr = email;
   Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
   Matcher matcher = pattern.matcher(inputStr);
   if (matcher.matches()) {
      isValid = true;
   }
   return isValid;
}
@Override
    public void update(int id, String nom, String prenom, Date date, String tel, String email, byte[] img, String adresse, String mdp, String role) {
       String sql= "UPDATE user SET NomUser='"+nom+"',PrenomUser= '"+prenom+"' ,DateNaiss= '"+date+"',NumTel='"+tel+"',Email='"+email+"' ,Adresse='"+adresse+"' ,ImgUser='"+img+"' ,Mdp='"+mdp+"' ,Role='"+role+"' where IdUser = '"+id+"'";
       try{
           Statement st= conn.createStatement();
           st.executeUpdate(sql);
           System.out.println(" User modifié avec succés !");
       }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    }

    
    
    
}
