/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Event;
import service.ServiceEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Desktop;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import utils.DataSource;




/**
 * FXML Controller class
 *
 * @author choua
 */
public class InterfaceGestionEventController implements Initializable {

    @FXML
    private TableView<Event> TviewEvent;
    @FXML
    private TableColumn<Event, Integer> idEvent;
    @FXML
    private TableColumn<Event, String> nomEvent;
    @FXML
    private TableColumn<Event, Date> DateEvent;
    @FXML
    private TableColumn<Event, String> LocateEvent;
    @FXML
    private TableColumn<Event, String> ImageEvent;
    @FXML
    private TableColumn<Event, Integer> PlaceEvent;
    @FXML
    private Button BtnAjEvent;
    @FXML
    private Button BtnModEvent;
    @FXML
    private Button BtnSuppEvent;
    @FXML
    private Button pdf_but;
    @FXML
    private TextField nom_field;
    @FXML
    private DatePicker date_field;
    @FXML
    private TextField locate_field;
    @FXML
    private TextField dispo_field;
    @FXML
    private ImageView photo_field;
    @FXML
    private Button choose;
    @FXML
    private TextField serarch_field;
    @FXML
    private Button search_btn;
    
    @FXML
    private TextField nbreEvent;

    
    
       private String filePath;
       private String imagePath;
       private String photoPath;

    private ObservableList<Event> eventsList = FXCollections.observableArrayList();
    
      private Connection conn = DataSource.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        idEvent.setCellValueFactory(new PropertyValueFactory<>("id_event"));
        nomEvent.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
        DateEvent.setCellValueFactory(new PropertyValueFactory<>("date_event"));
        LocateEvent.setCellValueFactory(new PropertyValueFactory<>("locate_event"));
        ImageEvent.setCellValueFactory(new PropertyValueFactory<>("photo_event"));
        PlaceEvent.setCellValueFactory(new PropertyValueFactory<>("dispoplace_event"));

        // initialise la liste des événements dans le tableau
     TviewEvent.setItems(afficherListeEvents());
     ServiceEvent s  = new ServiceEvent();
      int dispo = s.calculerNombreEvenements();
     // nbreEvent.setText(dispo);
      nbreEvent.setText(String.valueOf(dispo));
      
    }    

    
        public ObservableList<Event> afficherListeEvents() {
   ServiceEvent serviceEvent = new ServiceEvent();
 ObservableList<Event> eventsList = FXCollections.observableArrayList(serviceEvent.readAll());
    return eventsList;
}
   
    
private void displayPhotoImageView(Event e, ImageView imageView) {
    String photoPath = e.getPhoto_event();
    if (photoPath != null && !photoPath.isEmpty()) {
        File photoFile = new File(photoPath);
        if (photoFile.exists()) {
            Image image = new Image(photoFile.toURI().toString());
            imageView.setImage(image);
        }
    }
}


    @FXML
private void AjouterEvent(ActionEvent event) throws IOException {
    if (photoPath == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select an image file.");
        alert.showAndWait();
        return;
    }

    // Vérifier si le champ nom est renseigné
    if (nom_field.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a name for the event.");
        alert.showAndWait();
        return;
    }

    // Vérifier si la date est renseignée
    if (date_field.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select a date for the event.");
        alert.showAndWait();
        return;
    }
    
    if (locate_field.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a name for the event.");
        alert.showAndWait();
        return;
    }

    // Insérer l'image dans la base de données
    Event e1 = new Event();
    e1.setNom_event(nom_field.getText());
    e1.setDate_event(Date.valueOf(date_field.getValue()));
    e1.setLocate_event(locate_field.getText());
    e1.setPhoto_event(photoPath);


    // Vérifier si le champ dispo est renseigné et s'il est numérique
    if (!dispo_field.getText().isEmpty()) {
        try {
            e1.setDispoplace_event(Integer.parseInt(dispo_field.getText()));
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a numeric value for the available places.");
            alert.showAndWait();
            return;
        }
    }

    ServiceEvent se = new ServiceEvent();
    se.insert(e1);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("Event added successfully!");
    alert.showAndWait();
    TviewEvent.refresh();
    TviewEvent.setItems(afficherListeEvents());

    nom_field.clear();
    date_field.setValue(null);
    locate_field.clear();
    dispo_field.clear();
    photoPath = null;
    photo_field.setImage(null);
    serarch_field.clear();
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

    // Store the selected file path in the global variable photoPath
    photoPath = file.getAbsolutePath();

    // Display the selected image in the ImageView
    Image image = new Image(file.toURI().toString());
    photo_field.setImage(image);
}





    @FXML


private void ModifierEvent(ActionEvent event) throws IOException {

    if (photoPath == null) {
        // Afficher un message d'erreur si imagePath n'est pas défini
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please select an image file");
        alert.showAndWait();
        return;
    }

    Event e1 = new Event();
    e1.setId_event(Integer.parseInt(serarch_field.getText()));
    e1.setNom_event(nom_field.getText());
    e1.setDate_event(Date.valueOf(date_field.getValue()));
    e1.setLocate_event(locate_field.getText());
    e1.setPhoto_event(photoPath);
    e1.setDispoplace_event(Integer.parseInt(dispo_field.getText()));

    // Appeler la méthode MettreAjour de ServiceEvent pour mettre à jour l'événement
    ServiceEvent se = new ServiceEvent();
    se.MettreAjour(e1);

    // Afficher une boîte de dialogue pour indiquer que la mise à jour a été effectuée avec succès
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("Event Updated successfully!");
    alert.showAndWait();

    // Rafraîchir la table des événements
    TviewEvent.refresh();
    TviewEvent.setItems(afficherListeEvents());
     nom_field.clear();
          //date_field.clear(); 
          date_field.setValue(null);
         locate_field.clear();
         dispo_field.clear();
         photo_field.setImage(null);
          serarch_field.clear();
}




    @FXML
    private void SupprimerEvent(ActionEvent event) {  
         Event selectedVelo = TviewEvent.getSelectionModel().getSelectedItem();
    if (selectedVelo == null) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select Event to delete.");
        alert.showAndWait();
        return;
    }

    Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmDialog.setTitle("Confirmation");
    confirmDialog.setHeaderText(null);
    confirmDialog.setContentText("Are you sure you want to delete this velo?");
    Optional<ButtonType> result = confirmDialog.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
     //   ServiceVelo serviceVelo = new ServiceVelo();
       // serviceVelo.delete(selectedVelo.getId_velo());

        ServiceEvent se = new ServiceEvent();
         se.deletebyId(selectedVelo.getId_event());
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Velo deleted successfully!");
        successAlert.showAndWait();

        TviewEvent.setItems(afficherListeEvents());
         nom_field.clear();
          //date_field.clear(); 
          date_field.setValue(null);
         locate_field.clear();
         dispo_field.clear();
        photo_field.setImage(null);
          serarch_field.clear();
    }
        
        
        
    }




    @FXML
    private void searchEvent(ActionEvent event) {
        
       
       int m=0;
        
            String requette = "select nom_event,date_event,locate_event,photo_event,dispoplace_event from event where id_event ='"+Integer.parseInt(serarch_field.getText())+"'";
    try{
       
        PreparedStatement pst = conn.prepareStatement(requette); 
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nom_field.setText(rs.getString("nom_event"));
            Date date = rs.getDate("date_event");
            date_field.setValue(date.toLocalDate());
            locate_field.setText(rs.getString("locate_event"));
           String photoPath = rs.getString("photo_event");
           Image image = new Image(new File(photoPath).toURI().toString());
           photo_field.setImage(image);
            int dispo = rs.getInt("dispoplace_event");
            dispo_field.setText(String.valueOf(dispo));
            m=1;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    if(m==0){
         Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Echec");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Aucun Evénement Trouvé");
        successAlert.showAndWait();
    }
    }
    
        @FXML
void pdf_click(ActionEvent event) throws FileNotFoundException, DocumentException, IOException {
    Document doc = new Document();

    try {
        PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\choua\\OneDrive\\Desktop\\reclamation\\Event.pdf"));
        doc.open();

        addImageToPdf(doc, "C:\\Users\\choua\\OneDrive\\Desktop\\reclamation\\src\\Assets\\velo.jpg", 200f, 200f);

        // Get all events from the database
        String query = "SELECT * FROM event";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();

        // Add event information to the PDF
        doc.add(new Paragraph("Liste des événements :"));
        doc.add(new Paragraph("------------------------\n"));

        while (rs.next()) {
            String nomEvent = rs.getString("nom_event");
            String dateEvent = rs.getString("date_event");
            String locateEvent = rs.getString("locate_event");
            String dispoPlaceEvent = rs.getString("dispoplace_event");
            String photo_Event = rs.getString("photo_event");

            doc.add(new Paragraph("Nom de l'événement : " + nomEvent));
            doc.add(new Paragraph("Date de l'événement : " + dateEvent));
            doc.add(new Paragraph("Lieu de l'événement : " + locateEvent));
            doc.add(new Paragraph("Places disponibles : " + dispoPlaceEvent));
            doc.add(new Paragraph("Photo Path : " + photo_Event));
            doc.add(new Paragraph("\n"));
        }

        doc.close();
        Desktop.getDesktop().open(new File("C:\\Users\\choua\\OneDrive\\Desktop\\reclamation\\Event.pdf"));

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void addImageToPdf(Document document, String imagePath, float width, float height) throws DocumentException, IOException {
    com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(imagePath);
    image.setAlignment(Element.ALIGN_CENTER);
    image.scaleAbsolute(70, 70); // set the width and height of the image
    document.add(image);
}

 
 
 
    
}
