/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import com.itextpdf.text.BaseColor;
import entite.Station;
import entite.Reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceStation;
import service.ServiceReservation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entite.Resielo;
import java.io.FileOutputStream;
import javafx.scene.control.Label;
import service.ServiceResielo;




/**
 * FXML Controller class
 *
 * @author houss
 */
public class AdminStationController implements Initializable {

    @FXML
    private AnchorPane aspanel;
    @FXML
    private TextField nomdestation;
    @FXML
    private TextField localisation;
    @FXML
    private TextField nbrvelo;
    @FXML
    private TableView<Station> tableau;
    @FXML
    private TableColumn<?, ?> idtableau;
    @FXML
    private TableColumn<?, ?> nomtableau;
    @FXML
    private TableColumn<?, ?> localisationtableau;
    @FXML
    private TableColumn<?, ?> velotableau;
    
    ServiceStation  ss = null;
    ServiceReservation  sr = null;
    ServiceResielo sc =null;

    @FXML
    private AnchorPane container;
    @FXML
    private AnchorPane arpanel;
    @FXML
    private TableView<Resielo> tabreservation;
    @FXML
    private TableColumn<?, ?> ddreservation;
    @FXML
    private TableColumn<?, ?> dfreservation;
    @FXML
    private TableColumn<?, ?> idreservation;
    @FXML
    private TableColumn<?, ?> nomstation;
    @FXML
    private TableColumn<?, ?> localstation;
    @FXML
    private TableColumn<?, ?> nomusr;
    @FXML
    private TableColumn<?, ?> pnomusr;
    @FXML
    private TableColumn<?, ?> tel;
    @FXML
    private TableColumn<?, ?> tipevelo;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TableColumn<?, ?> nbrv;
    @FXML
    private TableColumn<Resielo, Double> tott;
    @FXML
    private Label totalr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        // TODO
        ss = new ServiceStation();
        idtableau.setCellValueFactory(new PropertyValueFactory<>("id_station"));
        nomtableau.setCellValueFactory(new PropertyValueFactory<>("nom_station"));
        localisationtableau.setCellValueFactory(new PropertyValueFactory<>("localisation_station"));
        velotableau.setCellValueFactory(new PropertyValueFactory<>("velo_station"));
        // initialise la liste des événements dans le tableau
        ObservableList<Station> s1 = FXCollections.observableArrayList(ss.readAll());
        tableau.setItems(s1);
        //System.out.println(s1);
        sc=new ServiceResielo(); 
                idreservation.setCellValueFactory(new PropertyValueFactory<>("id_reservation"));    
                ddreservation.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
                dfreservation.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
                nomstation.setCellValueFactory(new PropertyValueFactory<>("nom_station"));
                localstation.setCellValueFactory(new PropertyValueFactory<>("localisation_station"));
                nomusr.setCellValueFactory(new PropertyValueFactory<>("NomUser"));
                pnomusr.setCellValueFactory(new PropertyValueFactory<>("PrenomUser"));    
                tel.setCellValueFactory(new PropertyValueFactory<>("NumTel"));
                tipevelo.setCellValueFactory(new PropertyValueFactory<>("titre"));
                prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
                nbrv.setCellValueFactory(new PropertyValueFactory<>("nbr"));
                tott.setCellValueFactory(new PropertyValueFactory<>("prixr"));
                //ObservableList<Resielo> r66 = FXCollections.observableArrayList(sc.readAll());
                //tabreservation.setItems(r66);
                
                tableau.setOnMouseClicked(event -> {
                  
                    
            Station a = tableau.getSelectionModel().getSelectedItem();
               //System.out.println(a);
           nomdestation.setText(a.getNom_station());
                    localisation.setText(a.getLocalisation_station());
                    nbrvelo.setText(Integer.toString(a.getVelo_station()));
        });
       
                
    }    

    @FXML
    private void AjouterStation(ActionEvent event) {
        try {
    int nbrVelo = Integer.parseInt(nbrvelo.getText());
} catch (NumberFormatException e) {
                //ALERT
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		alert2.setTitle("erreur de controle de saisie");
                //alert.setHeaderText("Results:");
		alert2.setContentText("velo divponible doit etre un entier!");
                alert2.showAndWait();
            System.out.println("velo divponible doit etre entier");
            return;
}
        Station s=new Station(nomdestation.getText(), 
                localisation.getText(),
            Integer.parseInt(nbrvelo.getText())  );
            //men hnee tabda     
        ServiceStation h=new ServiceStation();
        h.insertPst(s);
        //ALERT
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Ajouter Station");
                //alert.setHeaderText("Results:");
		alert.setContentText("Station ajoutée avec succèss!");
                alert.showAndWait();
                remplirtableau();
                //ServiceStation em=new ServiceStation();
                //h.envoyer("mounirjallouli14@gmail.com");
    }

    @FXML
    private void ModifierStation(ActionEvent event) {
        Station a=tableau.getSelectionModel().getSelectedItem();
        ServiceStation serviceStation = new ServiceStation();
        int id = a.getId_station(); // l'identifiant de la station que vous voulez modifier
        String nom = nomdestation.getText();
        String local = localisation.getText();
        int velo = Integer.parseInt(nbrvelo.getText());
        serviceStation.modifierStation(id, nom, local, velo);
        remplirtableau();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Modifier Station");
                //alert.setHeaderText("Results:");
		alert.setContentText("Station modifiée avec succèss!");
                alert.showAndWait();
                remplirtableau();
    }

    @FXML
    private void SuprimerStation(ActionEvent event) {
        Station a=tableau.getSelectionModel().getSelectedItem();
        Station s=new Station(a.getId_station(),"z", "a",1  );
                ServiceStation h=new ServiceStation();
               ///ajout confirmation 
             Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmDialog.setTitle("Confirmation");
    confirmDialog.setHeaderText(null);
    confirmDialog.setContentText("est vous sure de suprimer la station?");
    Optional<ButtonType> result = confirmDialog.showAndWait();
        
     if (result.isPresent() && result.get() == ButtonType.OK) {
        //// supprime 
        if (h.supprimerStation(s)== true ){
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Succe");
            successAlert.setHeaderText(null);
            successAlert.setContentText("station supprimer avec succe!");
            successAlert.showAndWait();
        } else {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("erreur");
            successAlert.setHeaderText(null);
            successAlert.setContentText("il existe des reservations dans cette station!");
            successAlert.showAndWait();
        }
    }
        remplirtableau();
    }
    
    public void remplirtableau(){
        ObservableList<Station> s1 = FXCollections.observableArrayList(ss.readAll());
        tableau.setItems(s1);
                          
    }


    @FXML
private void apimprimer(ActionEvent event) {

    try {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("stations3.pdf"));
        

        
        document.open();
        
        // Insert page title
        Paragraph title = new Paragraph("Liste des stations de vélo", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        
        // Insert description
        Paragraph description = new Paragraph("Cette liste contient les informations relatives aux stations de vélo de la ville, y compris leur ID, nom, localisation et nombre de vélos disponibles. ");
        description.setAlignment(Element.ALIGN_JUSTIFIED);
        description.setSpacingBefore(20f);
        description.setSpacingAfter(20f);
        document.add(description);
        
        // Insert station table
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        PdfPCell cell;
        cell = new PdfPCell(new Phrase("ID"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nom"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Localisation"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nombre de vélos"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell);

        ObservableList<Station> stations = tableau.getItems();
        for (Station s : stations) {
            table.addCell(Integer.toString(s.getId_station()));
            table.addCell(s.getNom_station());
            table.addCell(s.getLocalisation_station());
            table.addCell(Integer.toString(s.getVelo_station()));
        }
        document.add(table);
        
        document.close();
        
        // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export PDF");
        alert.setContentText("Données exportées avec succès!");
        alert.showAndWait();
    } catch (DocumentException | IOException e) {
        e.printStackTrace();
    }
}


/*
private void infostation(ActionEvent event) throws SQLException, IOException {
    Reservation r = tabreservation.getSelectionModel().getSelectedItem();
    if (r == null){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("reservation non selectionne");
        alert.setHeaderText(null);
        alert.setContentText("svp selectionner une reservation.");
        alert.showAndWait();
        return;
    }
    ss = new ServiceStation();
        int id = r.getId_station(); // l'identifiant de la station que vous voulez modifier
        System.out.println(id);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("infos.fxml"));
        Parent root = loader.load();
        Station sta=ss.read(id);
        InfosController suivantController = loader.getController();
        suivantController.remplir(id);

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
}*/


    @FXML
    private void Actualiser(ActionEvent event) {
            ObservableList<Resielo> r1 = FXCollections.observableArrayList(sc.readAll());
            tabreservation.setItems(r1);

    }

    @FXML
    private void Actualisers(ActionEvent event) {
        
        remplirtableau();
    }

    @FXML
    private void calcultotal(ActionEvent event) {
    double total = sumColumnValues(tott);
    totalr.setText(String.valueOf(total)+"TND");
}


    @FXML
    private void apimprimer2(ActionEvent event) {
        try {

    Document document = new Document(PageSize.A4);
    PdfWriter.getInstance(document, new FileOutputStream("reservation.pdf"));
    document.open();
	// Insert page title
        Paragraph title = new Paragraph("Liste des Reservations de vélos", new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
		document.add(new Paragraph("Reservation Report"));
		// Insert description
        Paragraph description = new Paragraph("Cette liste contient les informations relatives aux Reservation de vélos de la ville, y compris leur ID,date de debut,date de fin , nom de station, localisation type de vélos prix et nombre. ");
        description.setAlignment(Element.ALIGN_JUSTIFIED);
        description.setSpacingBefore(20f);
        description.setSpacingAfter(20f);
        document.add(description);
		// Insert station table
        PdfPTable pdfTable = new PdfPTable(12);
        pdfTable.setWidthPercentage(100);
        pdfTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfTable.setSpacingBefore(10f);
        pdfTable.setSpacingAfter(10f);
		PdfPCell cell;
        cell = new PdfPCell(new Phrase("ID"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.PINK);//
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("date_debut"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.CYAN);
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("date_fin"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.ORANGE);
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("nom_station"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.WHITE);
        pdfTable.addCell(cell);
		cell = new PdfPCell(new Phrase("localisation_station"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.YELLOW);
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("NomUser"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.GREEN);
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("PrenomUser"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("NumTel"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.RED);
        pdfTable.addCell(cell);
		cell = new PdfPCell(new Phrase("titre"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.MAGENTA);
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("prix"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.PINK);
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("nbr"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.GREEN);
        pdfTable.addCell(cell);
        cell = new PdfPCell(new Phrase("prixr"));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setBackgroundColor(BaseColor.YELLOW);
        pdfTable.addCell(cell);
		//
    
    ObservableList<Resielo> resielo = tabreservation.getItems();

   for (Resielo r : resielo) {
    pdfTable.addCell(Integer.toString(r.getId_reservation()));
    pdfTable.addCell(r.getDate_debut().toString());
    pdfTable.addCell(r.getDate_fin().toString());
    pdfTable.addCell(r.getNom_station());
    pdfTable.addCell(r.getLocalisation_station());
    pdfTable.addCell(r.getNomUser());
    pdfTable.addCell(r.getPrenomUser());
    pdfTable.addCell(r.getNumTel());
    pdfTable.addCell(r.getTitre());
    pdfTable.addCell(Double.toString(r.getPrix()));
    pdfTable.addCell(Integer.toString(r.getNbr()));
    pdfTable.addCell(Double.toString(r.getPrixr()));
}
   document.add(pdfTable);
   document.close();
   // Show success message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Export PDF");
        alert.setContentText("Données exportées avec succès!");
        alert.showAndWait();

    }catch (Exception e) {e.printStackTrace();}
    }
    
    
    public double sumColumnValues(TableColumn<Resielo, Double> tott) {
    double sum = 0;
    for (Resielo r : tabreservation.getItems()) {
        Double value = tott.getCellData(r);
        if (value != null) { 
            sum += value;
        }
    }
    return sum;
}

    @FXML
    private void retour(ActionEvent event) {
        AnchorPane adminp;
        try {
            adminp = FXMLLoader.load(getClass().getResource("interface.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(adminp);
            Stage stage = (Stage) container.getScene().getWindow();
        stage.setTitle("Eco bike go");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

