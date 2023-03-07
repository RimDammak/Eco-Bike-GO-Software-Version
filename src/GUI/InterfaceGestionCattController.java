/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import entite.Categorie;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceCategorie;

/**
 * FXML Controller class
 *
 * @author macbook
 */
public class InterfaceGestionCattController implements Initializable {

     @FXML
    private TableView<Categorie> TviewCat;
    @FXML
    private TableColumn<Categorie, Integer> id_categorie;
    @FXML
    private TableColumn<Categorie, String> nom_categorie;
    @FXML
    private TableColumn<Categorie, String> desc_cat;
    @FXML
    private Label nom_cat;
    @FXML
    private TextField textidc;
    @FXML
    private TextField txtcn;
    @FXML
    private TextField txtcd;
    @FXML
    private Label id_catt;
    @FXML
    private Button btn_ajoutcat;
    @FXML
    private Button btn_modifiercat;
    @FXML
    private Button btn_suppcat;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btn_searchcat;
 private String filePath;
      private  ObservableList<Categorie> categoriesList = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_categorie.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
        nom_categorie.setCellValueFactory(new PropertyValueFactory<>("nom_categorie"));
        desc_cat.setCellValueFactory(new PropertyValueFactory<>("desc_cat"));
        

        TviewCat.setItems(afficherListecategories());
    }    
  
public ObservableList<Categorie> afficherListecategories() {
    ServiceCategorie serviceCategorie = new ServiceCategorie();
    List<Categorie> velos = serviceCategorie.readAll();
   ObservableList<Categorie> categoriesList = FXCollections.observableArrayList();
    return categoriesList;
}

    @FXML
    private void ajouter_cat(ActionEvent event) {
        
    }

    @FXML
    private void ajouter_categorie(ActionEvent event) {
       
    
    
    // Vérifier que tous les champs sont remplis
    if (textidc.getText().isEmpty() || txtcn.getText().isEmpty() || txtcd.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please fill in all the fields.");
        alert.showAndWait();
        return;
    }

    try {
        // Convertir les champs en leur type approprié
        int id_categorie = Integer.parseInt(textidc.getText());
       
        String nom_categorie = txtcn.getText();
        String desc_categorie = txtcd.getText();
       
       

        // Créer un objet Velo avec les valeurs converties
        Categorie c1 = new Categorie();
        c1.setId_categorie(id_categorie);
         c1.setNom_categorie(nom_categorie);
        c1.setDesc_categorie(desc_categorie);
        

        
        // Appeler la méthode d'insertion dans la base de données
        ServiceCategorie sc = new ServiceCategorie();
        sc.insert(c1);

        // Afficher une boîte de dialogue pour informer l'utilisateur que l'opération a réussi
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Categorie added successfully!");
        alert.showAndWait();
        TviewCat.refresh();
        TviewCat.setItems(afficherListecategories());
        // Réinitialiser les champs de saisie
       textidc.clear();
        txtcn.clear();
        txtcd.clear();
       
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
    private void modifier_cat(ActionEvent event) {
    }

    @FXML
    private void supprimer_categorie(ActionEvent event) {
    }

    @FXML
    private void search_cat(ActionEvent event) {
    }
    
}
