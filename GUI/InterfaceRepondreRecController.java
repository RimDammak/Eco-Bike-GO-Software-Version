/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entite.Reclamation;
import entite.Type_Rec;
import entite.User;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceReclamation;


/**
 * FXML Controller class
 *
 * @author damak
 */
public class InterfaceRepondreRecController implements Initializable {

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
    private TextArea reponse;
    @FXML
    private TextArea ModifierReponse;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
    }   
     public ObservableList<Reclamation> afficherListeReclamations() {
        ServiceReclamation serviceReclamation = new ServiceReclamation();
        ObservableList<Reclamation> reclamationsList = FXCollections.observableArrayList(serviceReclamation.readAll());
        return reclamationsList;
    }

    @FXML
    private void btn_rep(ActionEvent event) {
    }

    
    
    
    
//     private void ajouter_on_action(ActionEvent event) {
//    if (txt_Id_User.getText().isEmpty() || text_id_type.getText().isEmpty() || text_rec.getText().isEmpty()) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(null);
//        alert.setContentText("Please fill in all the fields.");
//        alert.showAndWait();
//        return;
//    }
//    try{
//        int IdUser = Integer.parseInt(txt_Id_User.getText());
//        int id_type = Integer.parseInt(text_id_type.getText());
//
//        Reclamation r1 = new Reclamation();
//        r1.setT(new Type_Rec(id_type));
//        r1.setU(new User(IdUser));
//        r1.setDescription_rec(text_rec.getText());
//        r1.setDate_rec(Date.valueOf(txt_date.getValue()));
//        ServiceReclamation sr = new ServiceReclamation();
//        sr.insert(r1);
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Success");
//        alert.setHeaderText(null);
//        alert.setContentText(" Rec added successfully!");
//        alert.showAndWait();
//        TviewRec.refresh();
//         TviewRec.setItems(afficherListeReclamations());      
//     } catch (NumberFormatException e) {
//        // Afficher une boîte de dialogue si une erreur de conversion se produit
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(null);
//        alert.setContentText("Please enter valid values for the numeric fields.");
//        alert.showAndWait();
//    }
//    }
    
    
    
    @FXML
    private void repondre(ActionEvent event) {
    }

    @FXML
    private void sup_clic(ActionEvent event) {
        
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
    
}
