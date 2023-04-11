/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wissem.gui;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wissem.entities.Reservation;
import wissem.services.ServiceReservation;

/**
 * FXML Controller class
 *
 * @author wisse
 */
public class Page1Controller implements Initializable {

    @FXML
    private TextField idTextfield;
    @FXML
    private TextField useridTextfield;
    @FXML
    private TextField seanceidTextfield;
    @FXML
    private Button AjouterReservationButton;
    @FXML
    private Button backButton;
    private TextField isavailableTextfield;
    @FXML
    private DatePicker dateTextfield;
    @FXML
    private Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void AjouterReservationFonction(ActionEvent event) throws IOException {
       
        if (idTextfield.getText().isEmpty() ||useridTextfield.getText().isEmpty()||seanceidTextfield.getText().isEmpty()||dateTextfield.getValue()==null )
        {errorLabel.setVisible(true);
      
        }
        else 
        {
            
            LocalDate localDate = dateTextfield.getValue();
Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
Date date = Date.from(instant);
                 Reservation r1 = new Reservation(parseInt(idTextfield.getText()),parseInt(useridTextfield.getText()), true,date) ;
                 ServiceReservation rs = new ServiceReservation() ;
                 rs.ajouter(r1);
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 Parent root = FXMLLoader.load(getClass().getResource("Page2.fxml"));

        // Crée une nouvelle scène avec la nouvelle racine
        Scene scene = new Scene(root);

        // Obtient la scène actuelle à partir de l'événement
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Définit la nouvelle scène
        window.setScene(scene);
        window.show();
        
        
        
        
        
        }
        
    }

    @FXML
    private void BackToPage2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Page2.fxml"));

        // Crée une nouvelle scène avec la nouvelle racine
        Scene scene = new Scene(root);

        // Obtient la scène actuelle à partir de l'événement
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Définit la nouvelle scène
        window.setScene(scene);
        window.show();
        
        
    }
    
}
