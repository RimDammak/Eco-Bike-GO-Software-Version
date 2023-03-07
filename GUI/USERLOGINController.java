/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import entite.user;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.UserService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class USERLOGINController implements Initializable {

    @FXML
    private AnchorPane loginForm;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtMdp;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink createAcc;
    @FXML
    private AnchorPane SignUpForm;
    @FXML
    private TextField txtTel;
    @FXML
    private PasswordField txtMdp_inscri;
    @FXML
    private Button btnInscri;
    @FXML
    private Hyperlink ConnectAcc;
    @FXML
    private TextField txtEmail_inscri;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtNom;
    @FXML
    private DatePicker txtDateNaiss;
    @FXML
    private TextField txtAdresse;
    @FXML
    private ComboBox<String> txtRole;

    private Connection conn = DataSource.getInstance().getCnx();
    @FXML
    private Hyperlink forgotPasswordBtn;
    @FXML
    private Button ImpImage;
    @FXML
    private ImageView ImgProf;
    
        private String filePath;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> Role = FXCollections.observableArrayList("Admin", "Client");
        txtRole.setItems(Role);
        //txtRole.setItems(FXCollections.observableArrayList("Admin", "Client"));

    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        String requete = "select * from user where Email=? and Mdp=?";

//        try {
//            PreparedStatement pst = conn.prepareStatement(requete);
//            pst.setString(1, txtEmail.getText());
//            pst.setString(2, txtMdp.getText());
//            ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                /*lpge elii bch yet3adelha ken Email w Mdp s7a7*/
//                if (rs.getString("Role").equals("Admin")) {
//                    FXMain.setId_user(rs.getInt("IdUser"));
//                    JOptionPane.showMessageDialog(null, "Connexion réussie", "EcoBikeGo", JOptionPane.INFORMATION_MESSAGE);
//                    btnLogin.getScene().getWindow().hide();
//                    Parent root = FXMLLoader.load(getClass().getResource("DashBordAdmin.fxml"));
//                    Scene scene = new Scene(root);
//                    Stage stage = new Stage();
//                    stage.setScene(scene);
//                    stage.setTitle("Admin Dashbord");
//                    stage.show();
//                } else {
//                    FXMain.setId_user(rs.getInt("IdUser"));
//                    JOptionPane.showMessageDialog(null, "Connexion réussie", "EcoBikeGo", JOptionPane.INFORMATION_MESSAGE);
//                    btnLogin.getScene().getWindow().hide();
//                    Parent root = FXMLLoader.load(getClass().getResource("ProfilUser.fxml"));
//                    Scene scene = new Scene(root);
//                    Stage stage = new Stage();
//                    stage.setScene(scene);
//                    stage.setTitle("Votre Profil");
//                    stage.show();
//                }
//
//            } else {
//                JOptionPane.showMessageDialog(null, "L'adresse email ou le mot de passe est incorrect !", "EcoBikeGo", JOptionPane.ERROR_MESSAGE);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(USERLOGINController.class.getName()).log(Level.SEVERE, null, ex);
//        }
try {
    PreparedStatement pst = conn.prepareStatement(requete);
    pst.setString(1, txtEmail.getText());
    pst.setString(2, txtMdp.getText());
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
        // Vérifier si le compte est activé ou non
        boolean etatCompte = rs.getBoolean("EtatCompte");
        if (etatCompte == true) {
            // Le compte est activé, permettre à l'utilisateur de se connecter
            if (rs.getString("Role").equals("Admin")) {
                FXMain.setId_user(rs.getInt("IdUser"));
                JOptionPane.showMessageDialog(null, "Connexion réussie", "EcoBikeGo", JOptionPane.INFORMATION_MESSAGE);
                btnLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("DashBordAdmin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Admin Dashbord");
                stage.show();
            } else {
                FXMain.setId_user(rs.getInt("IdUser"));
                JOptionPane.showMessageDialog(null, "Connexion réussie", "EcoBikeGo", JOptionPane.INFORMATION_MESSAGE);
                btnLogin.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
            }
        } else {
            // Le compte n'est pas activé, afficher un message d'erreur
            JOptionPane.showMessageDialog(null, "Votre compte n'est pas activé.", "EcoBikeGo", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(null, "L'adresse email ou le mot de passe est incorrect !", "EcoBikeGo", JOptionPane.ERROR_MESSAGE);
    }
} catch (SQLException ex) {
    Logger.getLogger(USERLOGINController.class.getName()).log(Level.SEVERE, null, ex);
}


    }

    @FXML
    private void SignUp(ActionEvent event) {

        String s = txtRole.getSelectionModel().getSelectedItem();
        if (txtRole.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un rôle!");
            alert.showAndWait();
        } else {
            user usr = new user();
            usr.setNomUser(txtNom.getText());
            usr.setPrenomUser(txtPrenom.getText());
            usr.setDateNaiss(Date.valueOf(txtDateNaiss.getValue()));
            usr.setNumTel(txtTel.getText());
            usr.setEmail(txtEmail_inscri.getText());
            usr.setImgUser(filePath);
            usr.setAdresse(txtAdresse.getText());
            usr.setMdp(txtMdp_inscri.getText());
            usr.setRole(txtRole.getSelectionModel().getSelectedItem());

            UserService su = new UserService();
            su.insertPst(usr);

            su.showNotification("Notifification ", "Votre Compte a été creé avec Succes ");

//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Success");
//            alert.setHeaderText(null);
//            alert.setContentText("User added successfully!");
//            alert.showAndWait();
        }

    }

    @FXML
    private void changeForm(ActionEvent event) {

        if (event.getSource() == ConnectAcc) {
            SignUpForm.setVisible(false);
            loginForm.setVisible(true);
        } else if ((event.getSource() == createAcc)) {
            SignUpForm.setVisible(true);
            loginForm.setVisible(false);
        }

    }

    @FXML
    private void forgetPassword(ActionEvent event) throws MessagingException, javax.mail.MessagingException {
        
        
        try {
                UserService us=new UserService();
                Connection cnx= DataSource.getInstance().getCnx();
                String s = "select * from user where Email = '"+txtEmail.getText()+"'";
                Statement st = cnx.createStatement();
                ResultSet rs = st.executeQuery(s);
                user usr = new user();
                if(rs.next()) {
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
                System.out.println(usr.getEmail()+" "+usr.getMdp());
                us.resetPassword(usr.getEmail(), usr.getMdp());
        } catch (SQLException ex) {
            Logger.getLogger(USERLOGINController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void ImpImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selection une image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png, *.gif)", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file == null) {
            // User didn't select a file
            return;
        }

        // Stockez le chemin d'accès du fichier sélectionné
        filePath = file.getAbsolutePath();

        // Affichez l'image sélectionnée dans l'ImageView
        Image image = new Image(file.toURI().toString());
        ImgProf.setImage(image);
    }

}
