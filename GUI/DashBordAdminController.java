/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.user;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.UserService;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class DashBordAdminController implements Initializable {

    @FXML
    private ImageView ImgAdmin;
    @FXML
    private TextField TxtNomAdmin;
    @FXML
    private TableView<user> TviewUser;
    @FXML
    private TableColumn<user, Integer> Iduser;
    @FXML
    private TableColumn<user, String> nom;
    @FXML
    private TableColumn<user, String> prenom;
    @FXML
    private TableColumn<user, Date> dateNaiss;
    @FXML
    private TableColumn<user, String> telephone;
    @FXML
    private TableColumn<user, String> email;
    @FXML
    private TableColumn<user, String> adresse;
    @FXML
    private TableColumn<user, String> mdp;
    @FXML
    private Button btnchercher;
    @FXML
    private TextField searchfield;
    @FXML
    private Button btnBloquer;

        private Connection conn=DataSource.getInstance().getCnx();

    private ObservableList<user> UsersList = FXCollections.observableArrayList();
    @FXML
    private Label lab_velo_dash;
    @FXML
    private Label lab_station_dash;
    @FXML
    private Label lab_event_dash;
    @FXML
    private Label lab_rec_dash;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Iduser.setCellValueFactory(new PropertyValueFactory<>("IdUser"));
        nom.setCellValueFactory(new PropertyValueFactory<>("NomUser"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("PrenomUser"));
        dateNaiss.setCellValueFactory(new PropertyValueFactory<>("DateNaiss"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("NumTel"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        mdp.setCellValueFactory(new PropertyValueFactory<>("Mdp"));
                
        // initialise la liste des événements dans le tableau
     TviewUser.setItems(afficherListeUser());
        // TODO
    }  
    
    
     public ObservableList <user> afficherListeUser() {
    UserService Userservice = new UserService();
    ObservableList<user> UsersList = FXCollections.observableArrayList(Userservice.readAll());
    return UsersList;
}
    

    @FXML
private void chercherClient(ActionEvent event) {
   try {
        // Initialisez la requête SQL pour chercher l'utilisateur en fonction de son identifiant
        String requete = "SELECT NomUser, PrenomUser, NumTel, Email, Adresse, Mdp FROM user WHERE IdUser = ?";
        PreparedStatement pst = conn.prepareStatement(requete);
        pst.setInt(1, Integer.parseInt(searchfield.getText()));
        
        // Exécutez la requête SQL et récupérez le résultat
        ResultSet rs = pst.executeQuery();
        
        // Vérifiez si le résultat est vide
        if (rs.next()) {
            // Créez un nouvel objet utilisateur à partir des résultats de la requête SQL
            user user = new user(
                    rs.getString("NomUser"),
                    rs.getString("PrenomUser"),
                    //rs.getDate("DateNaiss").toLocalDate(),
                    rs.getString("NumTel"),
                    rs.getString("Email"),
                    rs.getString("Adresse"),
                    rs.getString("Mdp")
                   // rs.getString("ImgUser")
                    //rs.getString("Role")
            );
            
            // Obtenez la liste des éléments du TableView
            ObservableList<user> items = TviewUser.getItems();

            // Supprimez tous les éléments de la liste
            items.clear();      
            
            // Ajoutez l'objet utilisateur à la liste des éléments du TableView
            items.add(user);

            // Actualisez le TableView
            TviewUser.refresh();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @FXML
    private void BloquerClient(ActionEvent event) throws SQLException {
        String sql = "UPDATE user SET EtatCompte = ? WHERE idUser = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setBoolean(1, false);
pstmt.setInt(2,Integer.parseInt(searchfield.getText()));
int rowsAffected = pstmt.executeUpdate();
    }

    @FXML
    private void dash_velo(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("InterfaceVelo.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }

    @FXML
    private void dash_station(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ProfilUser.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }

    @FXML
    private void dash_event(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ProfilUser.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }

    @FXML
    private void dash_rec(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ProfilUser.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }
    
}
