/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;




import entite.Categorie;
import entite.Station;
import entite.Velo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import utils.DataSource;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Element;
import java.awt.Desktop;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
        

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
    private TableColumn<Velo, String> ImageVelo;
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
    private Button btn_pdf;
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
     @FXML
    private TextField nbrvelo;
    private String filePath;
    private String imagePath ;
    private String photoPath ;
    private  ObservableList<Velo> velosList = FXCollections.observableArrayList();
    private Connection conn =DataSource.getInstance().getCnx();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         idveloo.setCellValueFactory(new PropertyValueFactory<>("id_velo"));
        idcat.setCellValueFactory(new PropertyValueFactory<>("sta"));
        titreVelo.setCellValueFactory(new PropertyValueFactory<>("titre"));
        prixVelo.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ImageVelo.setCellValueFactory(new PropertyValueFactory<>("image"));
        QteVelo.setCellValueFactory(new PropertyValueFactory<>("qte"));
        idStation.setCellValueFactory(new PropertyValueFactory<>("cat"));

        TviewVelo.setItems(afficherListevelos());
        
        ServiceVelo s = new ServiceVelo();
        int dispo = s.calculerNombreVelos();
        nbrvelo.setText(String.valueOf(dispo));
        
    }    
    @FXML
 private void displayPhotoImageView(Velo e, ImageView imageView) throws SQLException, IOException {
    // Get the photo bytes from the database Blob
   String photoPath = e.getImage();
   
    
    // Check if the bis object is not null before creating an Image object
    if (photoPath != null && !photoPath.isEmpty()) {
        File photoFile =new File(photoPath);
        // Display image in the ImageView
        if(photoFile.exists()){
            Image image = new Image(photoFile.toURI().toString()); 
            imageView.setImage(image);
        }
    
    }
}
@FXML
 public ObservableList<Velo> afficherListevelos() {
    ServiceVelo serviceVelo = new ServiceVelo();
    List<Velo> velos = serviceVelo.readAll();
    ObservableList<Velo> velosList = FXCollections.observableArrayList(velos);
    return velosList;
}

    @FXML


private void ajouter_Velo(ActionEvent event) throws IOException, SQLException {
    if (photoPath == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select an image file.");
        alert.showAndWait();
        return;
    }

    
    
    // Vérifier que tous les champs sont remplis
    if ( txtidvs.getText().isEmpty() || txtidvt.getText().isEmpty()
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
       // int id_velo = Integer.parseInt(txtidv.getText());
        int id_station = Integer.parseInt(txtidvs.getText());
        String titre = txtidvt.getText();
        float prix = Float.parseFloat(txtidvp.getText());
        int qte = Integer.parseInt(txtvq.getText());
        int id_categorie = Integer.parseInt(txtvc.getText());

        // Créer un objet Velo avec les valeurs converties
        Velo v1 = new Velo();
       // v1.setId_velo(id_velo);
        v1.setSta(new Station(id_station));
        v1.setTitre(titre);
        v1.setPrix(prix);
         v1.setImage(photoPath);
        v1.setQte(qte);
        v1.setCat(new Categorie(id_categorie));
       
        
    
        // Appeler la méthode d'insertion dans la base de données
        ServiceVelo sv = new ServiceVelo();
        sv.insert(v1);

        // Afficher une boîte de dialogue pour informer l'utilisateur que l'opération a réussi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Velo added successfully!");
        sv.showNotification("Notifification d'ajout", "Un ajout de velo est fait.");

        alert.showAndWait();
        TviewVelo.refresh();
        TviewVelo.setItems(afficherListevelos());
        // Réinitialiser les champs de saisie
       // txtidv.clear();
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
    private void modifier_Velo(ActionEvent event) throws IOException, SQLException {
        // Obtenir le chemin d'accès complet du fichier image
  

    // Vérifier que imagePath est défini
    if (photoPath == null) {
        // Afficher un message d'erreur si imagePath n'est pas défini
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select an image file");
        alert.showAndWait();
        return;
    }

    

    // Créer un objet Event avec les valeurs des champs
    Velo v1 = new Velo();
   v1.setId_velo(Integer.parseInt(search_fieldd.getText()));
   //v1.setSta(new Station(id_station).);txtidvs
  
    v1.setSta(new Station(Integer.parseInt(txtidvs.getText())));
    v1.setPrix(Float.parseFloat(txtidvp.getText()));
    v1.setTitre(txtidvt.getText());
    //v1.setImage(imageBytes);
    v1.setImage(photoPath);
    v1.setQte(Integer.parseInt(txtvq.getText()));
   v1.setCat(new Categorie(Integer.parseInt(txtvc.getText())));
   
    // Appeler la méthode MettreAjour de ServiceEvent pour mettre à jour l'événement
    ServiceVelo sv = new ServiceVelo();
    sv.MettreAjour(v1);

    // Afficher une boîte de dialogue pour indiquer que la mise à jour a été effectuée avec succès
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("velo Updated successfully!");
     sv.showNotification("Notifification de modification", "Une modofication de velo est fait.");

    alert.showAndWait();

    // Rafraîchir la table des événements
    TviewVelo.refresh();
    TviewVelo.setItems(afficherListevelos());
    }


    @FXML
   private void supprimer_Velo(ActionEvent event) throws SQLException {
 
      Velo selectedVelo = TviewVelo.getSelectionModel().getSelectedItem();
if (selectedVelo == null) {
    // afficher une alerte d'avertissement
    return;
}

Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
confirmDialog.setTitle("Confirmation");
confirmDialog.setHeaderText(null);
confirmDialog.setContentText("Are you sure you want to delete this velo?");
Optional<ButtonType> result = confirmDialog.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK) {
    ServiceVelo serviceVelo = new ServiceVelo();
    Velo v = new Velo();
    v.setId_velo(selectedVelo.getId_velo());
   v.setSta(selectedVelo.getSta());
    v.setCat(selectedVelo.getCat());
    System.out.println(v);
//    serviceVelo.delete2(v);
serviceVelo.delete(selectedVelo.getId_velo());
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
        photoPath = file.getAbsolutePath();

        // Affichez l'image sélectionnée dans l'ImageView
        Image image = new Image(file.toURI().toString());
        photo_field.setImage(image);
    }
  @FXML
    private void searchVelo(ActionEvent event) {
          int m=0;
        String requette = "select id_velo,id_station,titre,prix,image,qte,id_categorie from velo where id_velo ='"+Integer.parseInt(search_fieldd.getText())+"'";
    try{
        PreparedStatement pst = conn.prepareStatement(requette);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            
            
             int id_station = rs.getInt("id_station");
            txtidvs.setText(String.valueOf(id_station));
            txtidvt.setText(rs.getString("titre"));
            int qte = rs.getInt("qte");
            txtvq.setText(String.valueOf(qte));
         float prix = rs.getFloat("prix");
         txtidvp.setText(String.valueOf(prix));

           
           String photoPath =rs.getString("image");
           Image image =new Image(new File(photoPath).toURI().toString());
            photo_field.setImage(image);
            int id_categorie = rs.getInt("id_categorie");
            txtvc.setText(String.valueOf(id_categorie));
            m=1;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }  if(m==0){
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Echec");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Aucun velo Trouvé");
        successAlert.showAndWait();
    }

    
}
  @FXML
    void pdfclick(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
      // Document doc = new Document();
      Document doc = (Document) new com.itextpdf.text.Document();


    try {
        PdfWriter.getInstance((com.itextpdf.text.Document) doc, new FileOutputStream("C:\\Users\\choua\\OneDrive\\Desktop\\UserItegration\\velo.pdf"));
        doc.open();

       // addImageToPdf(doc, "C:\\Users\\choua\\OneDrive\\Desktop\\reclamation\\src\\Assets\\velo.jpg", 200f, 200f);

        // Get all events from the database
        String query = "SELECT * FROM velo";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        // Add event information to the PDF
        doc.add(new Paragraph("Liste des velo :"));
        doc.add(new Paragraph("------------------------\n"));

        while (rs.next()) {
            String titre = rs.getString("titre");
            String prix = rs.getString("prix");
            String image = rs.getString("image");
            String quantite = rs.getString("qte");
           

            doc.add(new Paragraph("titre de vélo : " + titre));
            doc.add(new Paragraph("prix de vélo : " + prix));
            doc.add(new Paragraph("lien d image velo : " + image));
            doc.add(new Paragraph("quantité disponible : " + quantite));
            
            doc.add(new Paragraph("\n"));
        }

        doc.close();
        Desktop.getDesktop().open(new File("C:\\Users\\choua\\OneDrive\\Desktop\\UserItegration\\velo.pdf"));

    } catch (SQLException e) {
        e.printStackTrace();
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(InterfaceGestionVeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    /*
    private void addImageToPdf(Document document, String imagePath, float width, float height) throws DocumentException, IOException {
    com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(imagePath);
    image.setAlignment(Element.ALIGN_CENTER);
    image.scaleAbsolute(70, 70); // set the width and height of the image
    document.add(image);
}
    
    */
    
    }