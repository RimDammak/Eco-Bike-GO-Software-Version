/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author damak
 */
public class InterfaceController implements Initializable {

    @FXML
    private Button btnUser;
    @FXML
    private Button btnVelo;
    @FXML
    private Button btnStation;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnRec;
    @FXML
    private AnchorPane adminp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

//    
//     Parent root;   
//     try{
//         root = FXMLLoader.load(getClass().getResource("interface.fxml"));
//         Scene scene = new Scene(root);
//        
//        primaryStage.setTitle("EcoBikeGO Desktop Application");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//     }
//     catch(IOException ex)
//     {
//           System.out.println(ex.getMessage());
//     }
//        
        
     
    
    @FXML
    private void userclic(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceUser.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
        stage.setScene(scene);
           stage.setTitle("Gestion User");
        stage.show();
    }

    @FXML
    private void clicvelo(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceVelo.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
              stage.setTitle("Gestion Vélos");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clicstation(ActionEvent event) throws IOException {
        AnchorPane aspanel;
        try {
            aspanel = FXMLLoader.load(getClass().getResource("AdminStation.fxml"));
            adminp.getChildren().removeAll();
            adminp.getChildren().setAll(aspanel);
            Stage stage = (Stage) adminp.getScene().getWindow();
        stage.setTitle("Admin Station Window");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void clicevent(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceEvent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage=new Stage();
              stage.setTitle("Gestion Evenements");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void clicrec(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("InterfaceReclamation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        Stage stage=new Stage();
              stage.setTitle("Gestion Réclamations");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void retourstation(ActionEvent event) {
    }
    
}