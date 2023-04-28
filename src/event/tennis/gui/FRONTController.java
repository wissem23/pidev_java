/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.gui;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import event.tennis.entities.Evennements;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class FRONTController implements Initializable {

    @FXML
    private GridPane citiesGrid;

    /**
     * Initializes the controller class.
     */
    
    private List<Evennements> events;
    @FXML
    private Button event;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
    
    
    private Evennements getEvennementses(){
    
        
    Evennements ev = new Evennements();
    ev.setType("tournois");
    
    ev.setImage("/ressource/1.png");
    ev.settotalelike(15);
   
    return ev;
    }

    @FXML
    private void eventsfront(ActionEvent event) {
        try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./LIKE.fxml"));
        Parent view_2=loader.load();

        Stage stage;
             stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(view_2);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(LIKEController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
}
