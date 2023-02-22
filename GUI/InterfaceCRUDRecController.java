/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import entite.Reclamation;
import entite.Type_Rec;
import entite.User;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceReclamation;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.T;
import service.IServiceRec;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author damak
 */
public class InterfaceCRUDRecController implements Initializable {

    private Connection conn;

    public InterfaceCRUDRecController() {
        conn = DataSource.getInstance().getCnx();
    }
    @FXML
    private TableView<Reclamation> TviewRec;
    @FXML
    private TableColumn<Reclamation, Integer> col_id_rec;
    @FXML
    private TableColumn<Reclamation, Integer> col_type_rec;
    @FXML
    private TableColumn<Reclamation, Date> col_date_rec;
    @FXML
    private TableColumn<Reclamation, String> col_descrip;
    @FXML
    private TableColumn<Reclamation, Integer> col_etat;
    @FXML
    private TableColumn<Reclamation, String> col_reponse;
    @FXML
    private TableColumn<Reclamation, Integer> col_IdUser;
    @FXML
    private TextArea text_rec;
    @FXML
    private Button btn_ajouter_rec;
    @FXML
    private Button btn_modf_rec;
    @FXML
    private Button btn_sup_rec;
    @FXML
    private TextField text_id_type;
    @FXML
    private Button btn_recherche;
    @FXML
    private TextField txt_recher_rec;
    @FXML
    private TextField txt_Id_User;
     @FXML
    private DatePicker txt_date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_id_rec.setCellValueFactory(new PropertyValueFactory<>("id_rec"));
        col_type_rec.setCellValueFactory(new PropertyValueFactory<>("T"));
        col_date_rec.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
        col_descrip.setCellValueFactory(new PropertyValueFactory<>("description_rec"));
        col_reponse.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        col_IdUser.setCellValueFactory(new PropertyValueFactory<>("U"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
        TviewRec.setItems(afficherListeReclamations());
        // TODO
    }

    public ObservableList<Reclamation> afficherListeReclamations() {
        ServiceReclamation serviceReclamation = new ServiceReclamation();
        ObservableList<Reclamation> reclamationsList = FXCollections.observableArrayList(serviceReclamation.readAll());
        return reclamationsList;
    }

    @FXML
    private void ajouter_on_action(ActionEvent event) {
    if (txt_Id_User.getText().isEmpty() || text_id_type.getText().isEmpty() || text_rec.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all the fields.");
        alert.showAndWait();
        return;
    }
    try{
        int IdUser = Integer.parseInt(txt_Id_User.getText());
        int id_type = Integer.parseInt(text_id_type.getText());

        Reclamation r1 = new Reclamation();
        r1.setT(new Type_Rec(id_type));
        r1.setU(new User(IdUser));
        r1.setDescription_rec(text_rec.getText());
        r1.setDate_rec(Date.valueOf(txt_date.getValue()));
        ServiceReclamation sr = new ServiceReclamation();
        sr.insert(r1);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(" Rec added successfully!");
        alert.showAndWait();
        TviewRec.refresh();
         TviewRec.setItems(afficherListeReclamations());      
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
    private void mod_on_action(ActionEvent event) {
         
        Reclamation r1 = new Reclamation();
        int IdUser = Integer.parseInt(txt_Id_User.getText());
        int id_type = Integer.parseInt(text_id_type.getText());
             r1.setT(new Type_Rec(id_type));
        r1.setU(new User(IdUser));
    r1.setId_rec(Integer.parseInt(txt_recher_rec.getText()));
    r1.setDescription_rec(text_rec.getText());
    r1.setDate_rec(Date.valueOf(txt_date.getValue()));

    // Appeler la méthode MettreAjour de ServiceEvent pour mettre à jour l'événement
    ServiceReclamation sr = new ServiceReclamation();
    sr.MettreAjour(r1);
 // Afficher une boîte de dialogue pour indiquer que la mise à jour a été effectuée avec succès
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText("Event Updated successfully!");
    alert.showAndWait();

    // Rafraîchir la table des événements
    TviewRec.refresh();
    TviewRec.setItems(afficherListeReclamations());
        
        
        
        
        
    }

    @FXML
    private void supprimer_on_action(ActionEvent event) {

        Reclamation selectedReclamation = TviewRec.getSelectionModel().getSelectedItem();
        if (selectedReclamation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select Reclamation to delete.");
            alert.showAndWait();
            return;
        }
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("Are you sure you want to delete this reclamation?");
        Optional<ButtonType> result = confirmDialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            //   ServiceVelo serviceVelo = new ServiceVelo();
            // serviceVelo.delete(selectedVelo.getId_velo());

            ServiceReclamation sr = new ServiceReclamation();
            sr.delete(selectedReclamation.getId_rec());
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Reclamation deleted successfully!");
            successAlert.showAndWait();

            TviewRec.setItems(afficherListeReclamations());
        }
    }

    @FXML
    private void clic_recherche_id(ActionEvent event) {

        String requete = "SELECT id_rec, date_rec, description_rec, id_type, etat_rec, reponse, IdUser FROM reclamation WHERE id_rec = '"+Integer.parseInt(txt_recher_rec.getText())+"'";
        try {
               PreparedStatement pst = conn.prepareStatement(requete);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
           
            text_rec.setText(rs.getString("description_rec"));
            Date date = rs.getDate("date_rec");
            txt_date.setValue(date.toLocalDate());
            int iduser = rs.getInt("IdUser");
            txt_Id_User.setText(String.valueOf(iduser));
            int idtype = rs.getInt("id_type");
            text_id_type.setText(String.valueOf(idtype));
            
            }
        } catch (SQLException e) {

        }

    }
    
    
   /* private void searchEvent(ActionEvent event) {
       
            String requette = "select nom_event,date_event,locate_event,photo_event,dispoplace_event from event where id_event ='"+Integer.parseInt(serarch_field.getText())+"'";
    try{
        PreparedStatement pst = conn.prepareStatement(requette);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            nom_field.setText(rs.getString("nom_event"));
            Date date = rs.getDate("date_event");
            date_field.setValue(date.toLocalDate());
            locate_field.setText(rs.getString("locate_event"));
            Blob imageBlob = rs.getBlob("photo_event");
            InputStream imageStream = imageBlob.getBinaryStream();
            Image image = new Image(imageStream);
            photo_field.setImage(image);
            int dispo = rs.getInt("dispoplace_event");
            dispo_field.setText(String.valueOf(dispo));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }*/
    
    
    
    

}
