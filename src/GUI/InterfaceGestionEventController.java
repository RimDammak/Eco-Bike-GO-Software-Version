/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entite.Event;
import Service.ServiceEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
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
    private TableColumn<Event, byte[]> ImageEvent;
    @FXML
    private TableColumn<Event, Integer> PlaceEvent;
    @FXML
    private Button BtnAjEvent;
    @FXML
    private Button BtnModEvent;
    @FXML
    private Button BtnSuppEvent;
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
     private String filePath;
    private ObservableList<Event> eventsList = FXCollections.observableArrayList();

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
    }    
    
      private void displayPhotoImageView(Event e, ImageView imageView) throws SQLException, IOException {
    // Get the photo bytes from the database Blob
    Blob photoBlob = e.getPhoto_event();
    byte[] photoBytes = photoBlob.getBytes(1, (int)photoBlob.length());
    ByteArrayInputStream bis = new ByteArrayInputStream(photoBytes);
    Image image = new Image(bis);

    // Display image in the ImageView
    imageView.setImage(image);
}
      
      
      
      
      public ObservableList<Event> afficherListeEvents() {
    ServiceEvent serviceEvent = new ServiceEvent();
    ObservableList<Event> eventsList = FXCollections.observableArrayList(serviceEvent.readAll());
    return eventsList;
}
      
      
      
      
      
      

    @FXML
    private void AjouterEvent(ActionEvent event) throws IOException {
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
        Event e1 = new Event();
        e1.setNom_event(nom_field.getText());
        e1.setDate_event(Date.valueOf(date_field.getValue()));
        e1.setLocate_event(locate_field.getText());
        e1.setPhoto_event(imageBytes);
        e1.setDispoplace_event(Integer.parseInt(dispo_field.getText()));
        ServiceEvent se = new ServiceEvent();
        se.insert(e1);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Event added successfully!");
        alert.showAndWait();
        TviewEvent.refresh();
         TviewEvent.setItems(afficherListeEvents());
    }

    @FXML
    private void ModifierEvent(ActionEvent event) {
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) {
    }

    @FXML
 
    
      private void ChoosePhoto(ActionEvent event) throws SQLException, IOException {
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
    
}
