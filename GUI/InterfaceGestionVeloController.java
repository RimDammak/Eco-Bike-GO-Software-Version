/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;



import Entite.Event;
import entite.Categorie;
import entite.Station;
import entite.Velo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.sql.rowset.serial.SerialBlob;
import service.ServiceVelo;
import utile.DataSource;
/**
 * FXML Controller class
 *
 * @author macbook
 */
public class InterfaceGestionVeloController implements Initializable {


    @FXML
    private TableView<Velo> TviewVelo;
    @FXML
    private TableColumn<Velo, Integer> idveloo;
    
    @FXML
    private TableColumn<Velo, Integer> idcat;
    @FXML
    private TableColumn<Velo, String> titreVelo;
    @FXML
    private TableColumn<Velo, Float> prixVelo;
    @FXML
    private TableColumn<Velo, byte[]> ImageVelo;
    @FXML
    private TableColumn<Velo, Integer> QteVelo;
     @FXML
    private TableColumn<Velo, Integer> idStation;
    @FXML
    private Button BtnAjVelo;
    @FXML
    private Button BtnModVelo;
    @FXML
    private Button BtnSuppVelo;
    @FXML
    private TextField txtidv;
    @FXML
    private TextField txtidvs;
    @FXML
    private TextField txtidvt;
    @FXML
    private TextField txtidvp;
    @FXML
    private ImageView photo_field;
    @FXML
    private Button btnphotovelo;
    @FXML
    private TextField txtvq;
    @FXML
    private TextField search_fieldd;
    @FXML
    private TextField txtvc;
     private String filePath;
    private  ObservableList<Velo> velosList = FXCollections.observableArrayList();
    private Connection conn =DataSource.getInstance().getcnx();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         idveloo.setCellValueFactory(new PropertyValueFactory<>("id_velo"));
        idcat.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        titreVelo.setCellValueFactory(new PropertyValueFactory<>("titre"));
        prixVelo.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ImageVelo.setCellValueFactory(new PropertyValueFactory<>("image"));
        QteVelo.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        idStation.setCellValueFactory(new PropertyValueFactory<>("id_station"));

        TviewVelo.setItems(afficherListevelos());
    }    
 private void displayPhotoImageView(Velo e, ImageView imageView) throws SQLException, IOException {
    // Get the photo bytes from the database Blob
    Blob photoBlob = e.getImage();
    byte[] photoBytes = photoBlob.getBytes(1, (int)photoBlob.length());
    ByteArrayInputStream bis = new ByteArrayInputStream(photoBytes);
    
    // Check if the bis object is not null before creating an Image object
    if (bis != null) {
        Image image = new Image(bis);
        // Display image in the ImageView
        imageView.setImage(image);
    } else {
        imageView.setImage(null);
    }
}

 public ObservableList<Velo> afficherListevelos() {
    ServiceVelo serviceVelo = new ServiceVelo();
    List<Velo> velos = serviceVelo.readAll();
    ObservableList<Velo> velosList = FXCollections.observableArrayList(velos);
    return velosList;
}

    @FXML


private void ajouter_Velo(ActionEvent event) throws IOException, SQLException {
    if (filePath == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select an image file.");
        alert.showAndWait();
        return;
    }

    // Lire les octets de l'image à partir du fichier sélectionné
    byte[] imageBytes = Files.readAllBytes(Paths.get(filePath));
    
    // Vérifier que tous les champs sont remplis
    if (txtidv.getText().isEmpty() || txtidvs.getText().isEmpty() || txtidvt.getText().isEmpty()
            || txtidvp.getText().isEmpty() || txtvq.getText().isEmpty() || txtvc.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all the fields.");
        alert.showAndWait();
        return;
    }

    try {
        // Convertir les champs en leur type approprié
        int id_velo = Integer.parseInt(txtidv.getText());
        int id_station = Integer.parseInt(txtidvs.getText());
        String titre = txtidvt.getText();
        float prix = Float.parseFloat(txtidvp.getText());
        int qte = Integer.parseInt(txtvq.getText());
        int id_categorie = Integer.parseInt(txtvc.getText());

        // Créer un objet Velo avec les valeurs converties
        Velo v1 = new Velo();
        v1.setId_velo(id_velo);
        v1.setSta(new Station(id_station));
        v1.setTitre(titre);
        v1.setPrix(prix);
        v1.setQte(qte);
        v1.setCat(new Categorie(id_categorie));

        // Convertir les octets de l'image en un objet Blob
        Blob blob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
        v1.setImage(blob);
    
        // Appeler la méthode d'insertion dans la base de données
        ServiceVelo sv = new ServiceVelo();
        sv.insert(v1);

        // Afficher une boîte de dialogue pour informer l'utilisateur que l'opération a réussi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Velo added successfully!");
        alert.showAndWait();
        TviewVelo.refresh();
        TviewVelo.setItems(afficherListevelos());
        // Réinitialiser les champs de saisie
        txtidv.clear();
        txtidvs.clear();
        txtidvt.clear();
        txtidvp.clear();
        txtvq.clear();
        txtvc.clear();
    } catch (NumberFormatException e) {
        // Afficher une boîte de dialogue si une erreur de conversion se produit
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter valid values for the numeric fields.");
        alert.showAndWait();
    }
}


    @FXML
    private void modifier_Velo(ActionEvent event) {
    }

    @FXML
   private void supprimer_Velo(ActionEvent event) throws SQLException {
    Velo selectedVelo = TviewVelo.getSelectionModel().getSelectedItem();
    if (selectedVelo == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select a velo to delete.");
        alert.showAndWait();
        return;
    }

    Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmDialog.setTitle("Confirmation");
    confirmDialog.setHeaderText(null);
    confirmDialog.setContentText("Are you sure you want to delete this velo?");
    Optional<ButtonType> result = confirmDialog.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
        ServiceVelo serviceVelo = new ServiceVelo();
      //  serviceVelo.delete1(selectedVelo.getId_velo());

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Velo deleted successfully!");
        successAlert.showAndWait();

        TviewVelo.setItems(afficherListevelos());
    }
}


    
  @FXML
    private void ChoosePhoto(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
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
        photo_field.setImage(image);
    }
  @FXML
    private void searchVelo(ActionEvent event) {
        String requette = "select id_velo,id_station,titre,prix,image,qte,id_categorie from velo where id_velo ='"+Integer.parseInt(search_fieldd.getText())+"'";
    try{
        PreparedStatement pst = conn.prepareStatement(requette);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            int id_velo = rs.getInt("id_velo");
            txtidv.setText(String.valueOf(id_velo));
             int id_station = rs.getInt("id_station");
            txtidvs.setText(String.valueOf(id_station));
            txtidvt.setText(rs.getString("titre"));
            int qte = rs.getInt("qte");
            txtvq.setText(String.valueOf(qte));
         float prix = rs.getFloat("prix");
         txtidvp.setText(String.valueOf(prix));

           
            Blob imageBlob = rs.getBlob("image");
            InputStream imageStream = imageBlob.getBinaryStream();
            Image image = new Image(imageStream);
            photo_field.setImage(image);
            int id_categorie = rs.getInt("id_categorie");
            txtvc.setText(String.valueOf(id_categorie));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
}

    }
