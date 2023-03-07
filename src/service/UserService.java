/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import entite.user;
import java.net.PasswordAuthentication;
import javafx.util.Duration;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Notifications;
import utile.DataSource;

/**
 *
 * @author pc
 */
public class UserService implements IService<user> {

    private Connection conn;

    public UserService() {
        conn = DataSource.getInstance().getCnx();
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
    public void insertPst(user p) {
        String requete = "insert into user(NomUser,PrenomUser,DateNaiss,NumTel,Email,Adresse,Mdp,ImgUser,Role) values(?,?,?,?,?,?,?,?,?)";

        if (!isEmailValid(p.getEmail())) {
            System.out.println("L'email n'est pas valide.");
        } else if (!checkPassword(p.getMdp())) {
            System.out.println("Mdp n'est pas valide.");
        } else if (isEmailExists(p.getEmail())) {
            System.out.println("L'email existe déjà dans la base de données.");
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
                pst.setString(8, p.getImgUser());
                pst.setString(9, p.getRole());
                pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE from user where IdUser= '" + id + "' ";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            System.out.println("User supprimé avec succés !");
        } catch (SQLException ex) {
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
        List<user> list = new ArrayList<>();
        String requete = "select * from user";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                user U = new user(rs.getInt("IdUser"), rs.getString(2),
                        rs.getString(3), rs.getDate("DateNaiss"), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString("Role"));
                list.add(U);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
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
                usr.setNumTel(rs.getString("NumTel"));
                usr.setEmail(rs.getString("Email"));
                usr.setAdresse(rs.getString("Adresse"));
                usr.setImgUser(rs.getString("ImgUser"));
                usr.setRole(rs.getString("Role"));
                usr.setMdp(rs.getString("Mdp"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usr;
    }

    @Override
    public int calculerNombreUsers() {
        int nbuser = 0;
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

    public void update(int id, String nom, String prenom, java.util.Date dateNaiss, String num, String email, String adresse, String image, String role) {

        String sql = "UPDATE user SET NomUser='" + nom + "',PrenomUser= '" + prenom + "' ,DateNaiss= '" + dateNaiss + "',NumTel='" + num + "',Email='" + email + "' ,Adresse='" + adresse + "' ,ImgUser='" + image + "' ,Role='" + role + "' where IdUser = '" + id + "'";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            System.out.println(" User modifié avec succés !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void MettreAjour(user usr) {
        String requete = "UPDATE user SET NomUser = ?, PrenomUser = ?, DateNaiss = ?, NumTel = ?, Email = ?, Adresse = ?, ImgUser = ?, Mdp = ? WHERE IdUser = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(requete);
            pst.setString(1, usr.getNomUser());
            pst.setString(2, usr.getPrenomUser());
            pst.setDate(3, (Date) usr.getDateNaiss());
            pst.setString(4, usr.getNumTel());
            pst.setString(5, usr.getEmail());
            pst.setString(6, usr.getAdresse());
            pst.setString(7, usr.getImgUser());
            pst.setString(8, usr.getMdp());
            // pst.setString(9, usr.getRole());
            pst.setInt(9, usr.getIdUser());
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

    public void resetPassword(String userEmail, String newPassword) throws MessagingException, AddressException, javax.mail.MessagingException {

        // Adresse e-mail de l'expéditeur
        String from = "azizbnhamida@gmail.com";

        // Informations d'authentification pour se connecter au serveur SMTP
        final String username = "azizbnhamida@gmail.com";
        final String password = "mkgsamwrtmouqqkg";

        // Configuration des propriétés pour la session de messagerie
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Création de la session de messagerie
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(username, password);
            }
        });

        // Création du message de réinitialisation de mot de passe
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(userEmail));
        message.setSubject("Réinitialisation de mot de passe");
        message.setText("Bonjour,\n\nVous avez demandé une réinitialisation de votre mot de passe. Votre nouveau mot de passe est : " + newPassword + "\n\nCordialement,\nL'équipe de support");

        // Envoi du message de réinitialisation de mot de passe
        Transport.send(message);

        System.out.println("Le message de réinitialisation de mot de passe a été envoyé avec succès.");
    }
    
    
    public static boolean checkPassword(String password) {
    if (password == null || password.isEmpty()) {
        return false; // le mot de passe ne peut pas être nul ou vide
    }
    if (password.length() < 8) {
        return false; // le mot de passe doit avoir au moins 8 caractères
    }
    boolean hasUppercase = false;
    boolean hasLowercase = false;
    boolean hasDigit = false;
    boolean hasSpecialChar = false;
    for (int i = 0; i < password.length(); i++) {
        char c = password.charAt(i);
        if (Character.isUpperCase(c)) {
            hasUppercase = true;
        } else if (Character.isLowerCase(c)) {
            hasLowercase = true;
        } else if (Character.isDigit(c)) {
            hasDigit = true;
        } else if (isSpecialChar(c)) {
            hasSpecialChar = true;
        }
    }
    return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
}

private static boolean isSpecialChar(char c) {
    // Ajouter ici les caractères spéciaux que vous souhaitez autoriser
    String specialChars = "!@#$%^&*()_+";
    return specialChars.indexOf(c) != -1;
}
 
public void showNotification(String title, String message) {
        Notifications notificationsBuilder = Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);

        ImageView logoImage = new ImageView(new Image("/Images/logo.png"));
        logoImage.setFitWidth(50);
        logoImage.setPreserveRatio(true);
        notificationsBuilder.graphic(logoImage);

        notificationsBuilder.show();
    }

    @Override
    public boolean isEmailExists(String email) {
   boolean exists = false;
   String query = "SELECT * FROM users WHERE email = ?";
   try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
      preparedStatement.setString(1, email);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
         if (resultSet.next()) {
            exists = true;
         }
      }
   } catch (SQLException e) {
      e.printStackTrace();
   }
   return exists;
}


}
