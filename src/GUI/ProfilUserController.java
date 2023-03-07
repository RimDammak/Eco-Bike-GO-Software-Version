/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.user;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utile.DataSource;
import GUI.USERLOGINController;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.UserService;
import java.sql.SQLException;
import javafx.scene.control.DatePicker;
import utile.DataSource;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ProfilUserController implements Initializable {

    @FXML
    private ImageView ImgProfil;
    @FXML
    private Button btnModifImg;
    @FXML
    private Label LabelProfil;
    @FXML
    private Label LabNotif;
    @FXML
    private Label LabMdp;
    @FXML
    private Label LabParam;
    @FXML
    private Label LabSupp;
    @FXML
    private TextField txtProfilNom;
    @FXML
    private TextField txtProfilPrenom;
    @FXML
    private TextField txtProfilMdp;
    @FXML
    private DatePicker txtProfilDate;
    @FXML
    private TextField txtProfilTel;
    @FXML
    private TextField txtProfilEmail;
    @FXML
    private TextField txtProfilAdresse;
    @FXML
    private Button btnModifier;

    private Connection conn = DataSource.getInstance().getCnx();
    private String filePath;
    UserService ps = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
        user u = ps.readById(gobike.getId_user());
        System.out.println(u.getMdp());

        txtProfilNom.setText(u.getNomUser());
        txtProfilPrenom.setText(u.getPrenomUser());
        txtProfilAdresse.setText(u.getAdresse());
        txtProfilTel.setText(u.getNumTel());
        txtProfilEmail.setText(u.getEmail());
        txtProfilMdp.setText(u.getMdp());

    }

    @FXML
    private void ImporterImg(ActionEvent event) {
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
        ImgProfil.setImage(image);
    }

    @FXML
    private void ModifierProfil(ActionEvent event) {
        user usr = new user();
        usr.setIdUser(gobike.getId_user());
        usr.setNomUser(txtProfilNom.getText());
        usr.setPrenomUser(txtProfilPrenom.getText());
        usr.setDateNaiss(Date.valueOf(txtProfilDate.getValue()));
        usr.setNumTel(txtProfilTel.getText());
        usr.setEmail(txtProfilEmail.getText());
        usr.setAdresse(txtProfilAdresse.getText());
        usr.setMdp(txtProfilMdp.getText());
        usr.setImgUser(filePath);
        // usr.setRole(txtRole.getText());
        UserService su = new UserService();
        su.MettreAjour(usr);

// Afficher une boîte de dialogue pour indiquer que la mise à jour a été effectuée avec succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("User Updated successfully!");
        alert.showAndWait();

    }

    /* void setUser(user usr){
        this.usr = usr;
    }*/
    @FXML
    private void SupprimerCompte(MouseEvent event) {
//         user selectedUser = TviewUser.getSelectionModel().getSelectedItem();

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("Are you sure you want to delete this user?");
        Optional<ButtonType> result = confirmDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            UserService Us = new UserService();
            Us.delete(gobike.getId_user());

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("User deleted successfully!");
            successAlert.showAndWait();
            // Scene scene = rootNode.getScene();

            // Récupère une référence à la fenêtre principale
            //Stage stage = (Stage) scene.getWindow();
            // Ferme la fenêtre principale
            //stage.close();
        }

    }

}
