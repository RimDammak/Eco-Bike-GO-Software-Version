/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.user;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.UserService;
import javafx.scene.control.DatePicker;
import java.sql.*;
import java.util.Base64;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import utile.DataSource;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class InterfaceUserController implements Initializable {

    @FXML
    private TableView<user> TviewUser;
    @FXML
    private TableColumn<user, Integer> idUser;
    @FXML
    private TableColumn<user, String> nomUser;
    @FXML
    private TableColumn<user, String> prenomUser;
    @FXML
    private TableColumn<user, Date> DateNaiss;
    @FXML
    private TableColumn<user, String> telUser;
    @FXML
    private TableColumn<user, String> emailUser;
    @FXML
    private TableColumn<user, String> adresseUser;
    @FXML
    private TableColumn<user, String> mdpUser;
    @FXML
    private TableColumn<user, String> roleUser;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private DatePicker txtDateNaiss;
    @FXML
    private ImageView ImgField;
    @FXML
    private TextField txtRole;
    @FXML
    private Button btnImg;
    @FXML
    private Button BtnAjUser;
    @FXML
    private Button BtnModUser;
    @FXML
    private Button BtnSuppUser;
    
    
    private String filePath;
    private ObservableList<user> UsersList = FXCollections.observableArrayList();
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtMdp;
    @FXML
    private TextField Search_field;
    @FXML
    private Button btnChercher;
    
    
    
    private Connection conn=DataSource.getInstance().getCnx();
    /* public UserService() {
        conn = DataSource.getInstance().getcnx();
    }*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          idUser.setCellValueFactory(new PropertyValueFactory<>("IdUser"));
        nomUser.setCellValueFactory(new PropertyValueFactory<>("NomUser"));
        prenomUser.setCellValueFactory(new PropertyValueFactory<>("PrenomUser"));
        DateNaiss.setCellValueFactory(new PropertyValueFactory<>("DateNaiss"));
        telUser.setCellValueFactory(new PropertyValueFactory<>("NumTel"));
        emailUser.setCellValueFactory(new PropertyValueFactory<>("Email"));
        adresseUser.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        mdpUser.setCellValueFactory(new PropertyValueFactory<>("Mdp"));
        roleUser.setCellValueFactory(new PropertyValueFactory<>("Role"));
        
        // initialise la liste des événements dans le tableau
     TviewUser.setItems(afficherListeUser());
    }

    
private void displayPhotoImageView(user U, ImageView imageView) throws SQLException, IOException {
    // Get the photo bytes from the database Blob
    String photoString = U.getImgUser();
    byte[] photoBytes = Base64.getDecoder().decode(photoString);
    ByteArrayInputStream bis = new ByteArrayInputStream(photoBytes);
    Image image = new Image(bis);

    // Display image in the ImageView
    imageView.setImage(image);
}

    
 public ObservableList <user> afficherListeUser() {
    UserService Userservice = new UserService();
    ObservableList<user> UsersList = FXCollections.observableArrayList(Userservice.readAll());
    return UsersList;
}
    
    
    
    
    

    @FXML
    private void ImporterImg(ActionEvent event) {FileChooser fileChooser = new FileChooser();
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
        ImgField.setImage(image);
        
        
    }

    @FXML
    private void AjouterUser(ActionEvent event) throws IOException {
        if (filePath == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an image file.");
            alert.showAndWait();
            return;
        }

        // Lisez les octets de l'image à partir du fichier sélectionné
        byte[] imageBytes = Files.readAllBytes(Paths.get(filePath));

        // Insert the image into the database
        user usr = new user();
        usr.setNomUser(txtNom.getText());
        usr.setPrenomUser(txtPrenom.getText());
        usr.setDateNaiss(Date.valueOf(txtDateNaiss.getValue()));
        usr.setNumTel(txtTel.getText());
        usr.setEmail(txtEmail.getText());
        usr.setImgUser(imageBytes);
        usr.setAdresse(txtAdresse.getText());
        usr.setMdp(txtMdp.getText());
        usr.setRole(txtRole.getText());
        
        UserService su = new UserService();
        su.insertPst(usr);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("User added successfully!");
        alert.showAndWait();
        TviewUser.refresh();
        TviewUser.setItems(afficherListeUser());
    }

    @FXML
    private void ModifierUser(ActionEvent event) throws IOException, SQLException {
      if (Search_field.getText() == null || Search_field.getText().isEmpty()) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter a valid user ID");
    alert.showAndWait();
    return;
}

// Convertir l'ID en entier
int userId = Integer.parseInt(Search_field.getText());

// Créer un objet User avec les valeurs des champs
user usr = new user();
usr.setIdUser(userId);
usr.setNomUser(txtNom.getText());
usr.setPrenomUser(txtPrenom.getText());
usr.setDateNaiss(Date.valueOf(txtDateNaiss.getValue()));
usr.setNumTel(txtTel.getText());
usr.setEmail(txtEmail.getText());
usr.setAdresse(txtAdresse.getText());
usr.setMdp(txtMdp.getText());
usr.setRole(txtRole.getText());

// Lisez les octets de la nouvelle image à partir du fichier sélectionné
        byte[] newImageBytes = null;
        if (filePath != null) {
            newImageBytes = Files.readAllBytes(Paths.get(filePath));
        }

// Appeler la méthode pour mettre à jour l'utilisateur
UserService su = new UserService();
su.MettreAjour(usr);

// Afficher une boîte de dialogue pour indiquer que la mise à jour a été effectuée avec succès
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Success");
alert.setHeaderText(null);
alert.setContentText("User Updated successfully!");
alert.showAndWait();

// Rafraîchir la table des événements
TviewUser.refresh();
TviewUser.setItems(afficherListeUser());

        
    }

    @FXML
    private void SupprimerUser(ActionEvent event) {
     
      user selectedUser = TviewUser.getSelectionModel().getSelectedItem();
    if (selectedUser == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select a user to delete.");
        alert.showAndWait();
        return;
    }

    Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmDialog.setTitle("Confirmation");
    confirmDialog.setHeaderText(null);
    confirmDialog.setContentText("Are you sure you want to delete this user?");
    Optional<ButtonType> result = confirmDialog.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
        UserService Us = new UserService();
        Us.delete(selectedUser.getIdUser());

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText("User deleted successfully!");
        successAlert.showAndWait();

        TviewUser.setItems(afficherListeUser());
    }
    }

    @FXML
    private void ChercherUser(ActionEvent event) {
         String requette = "select NomUser,PrenomUser,DateNaiss,NumTel,Email,Adresse,Mdp,ImgUser,Role from user where IdUser ='"+Integer.parseInt(Search_field.getText())+"'";
    try{
        PreparedStatement pst = conn.prepareStatement(requette);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            txtNom.setText(rs.getString("NomUser"));
            txtPrenom.setText(rs.getString("PrenomUser"));
            Date date = rs.getDate("DateNaiss");
            txtDateNaiss.setValue(date.toLocalDate());
            txtTel.setText(rs.getString("NumTel"));
            txtEmail.setText(rs.getString("Email"));
            txtAdresse.setText(rs.getString("Adresse"));
            txtMdp.setText(rs.getString("Mdp"));
            txtRole.setText(rs.getString("Role"));
            Blob imageBlob = rs.getBlob("ImgUser");
            InputStream imageStream = imageBlob.getBinaryStream();
            Image image = new Image(imageStream);
            ImgField.setImage(image);
            
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
        
        
    }

    
    
