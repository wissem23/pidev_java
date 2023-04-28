/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.gui;

import event.tennis.entities.Evennements;
import event.tennis.entities.Participation;
import event.tennis.entities.User;
import event.tennis.service.ServiceEvenements;
import event.tennis.service.ServiceParticipation;
import event.tennis.service.userc;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static java.util.stream.Collectors.toList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CRUD_PARTICIPATIONController implements Initializable {

    @FXML
    private Button btnADD;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnView;
    @FXML
    private ListView<Participation> v_participation;
    @FXML
    private TextField tCode;
    private TextField tIdutlisatuer;
    private TextField tIdevent;
    @FXML
    private TextField tRecherche;
    @FXML
    private Button PartBtn;
    @FXML
    private Button EventBtn;
    @FXML
    private Button partBtn;
    @FXML
    private ComboBox<User> roll_user;
    @FXML
    private ComboBox<Evennements> roll_event;
    @FXML
    private Button reche;
    @FXML
    private AnchorPane lst;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherParticipation();
       setEvenementCombox();
       setuserCombox();
    }    

    @FXML
    private void ajouterParticipation(ActionEvent event) {
        ServiceParticipation V_Service = new ServiceParticipation();
        Participation p = new Participation();
       
         Evennements e = roll_event.getSelectionModel().getSelectedItem();
            User u = roll_user.getSelectionModel().getSelectedItem();
        
        if(tCode.getText().isEmpty() ) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplissez tous les champs");
            alert.showAndWait();
            
            } 
         else {
            
            
            p.setCodeticket(tCode.getText());
            p.setEvenements(e);
            p.setUser(u);   
        }
            try {
                V_Service.add(p);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Le participant a été ajouté avec succès !");
                alert.show();
                
            } catch (Exception t) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur est survenue lors de l'ajout event : " + t.getMessage());
                alert.show();
            }
            afficherParticipation();
    }

    @FXML
    private void modifierParticipation(ActionEvent event) {
          ServiceParticipation V_Service = new ServiceParticipation();
          Participation p = new Participation();
          Evennements e = roll_event.getSelectionModel().getSelectedItem();
            User u = roll_user.getSelectionModel().getSelectedItem();
            
        if(tCode.getText().isEmpty() || tIdutlisatuer.getText().isEmpty() || tIdevent.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplissez tous les champs");
            alert.showAndWait();
        } else {
            
            p.setCodeticket(tCode.getText());
            
            p.setEvenements(e);
            p.setUser(u);
           //p.setUtilisateur_id(Integer.parseInt(tIdutlisatuer.getText()));
           // p.setEvennement_id(Integer.parseInt(tIdevent.getText()));
        
        Participation selectedParticipation =   v_participation.getSelectionModel().getSelectedItem();    
        p.setId(selectedParticipation.getId());
        V_Service.modifier(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your event has been UPDATED successfully!");
        alert.show();
    }
        afficherParticipation();
    }

    @FXML
    private void supprimerParticipation(ActionEvent event) {
        Participation selectParticipation= v_participation.getSelectionModel().getSelectedItem();

        ServiceParticipation V_Service = new ServiceParticipation();
        V_Service.supprimer(selectParticipation);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your event has been DELETED successfully!");
        alert.show();
        afficherParticipation();
    }

    @FXML
    private void afficherParticipation() {
        ServiceParticipation V_Service = new ServiceParticipation();
        List<Participation> part = V_Service.afficher();
        ObservableList<Participation> items = FXCollections.observableArrayList() ;
        for(Participation parti :part){
            
            items.add(parti);
        }
        
       v_participation.setItems(items);
        
    }
   
    public void setEvenementCombox() {
         ServiceEvenements n =new ServiceEvenements();
        List<Evennements> envents = n.afficher();
        
        ObservableList<Evennements> KL =FXCollections.observableArrayList(envents);
        roll_event.setItems(KL);
        }
      public void setuserCombox() {
         userc n =new userc();
         
        List<User> envents = n.afficher();
        ObservableList<User> KL =FXCollections.observableArrayList(envents);
        roll_user.setItems(KL);
        }

    @FXML
    private void rechercher(ActionEvent event) {
        
        ServiceParticipation V_Service = new ServiceParticipation();
        List<Participation> part = V_Service.afficher();
        String o = tRecherche.getText();
         
         List<Participation> bystatue = part.stream().filter(t -> t.getCodeticket().equals(o)).collect(toList());
             
        
        
        if (tRecherche.getText().isEmpty()){
         
        show(part);}
       
        else {
            show(part);
        
        }
    }

    private void show(List<Participation> parts) {
        ObservableList<Participation> items =FXCollections.observableArrayList();
      
        for(Participation part : parts){
                    
        items.add(part);
          }
        v_participation.setItems(items);
    }

    @FXML
    private void dashboard(ActionEvent event) throws IOException {
         Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/gui/Dashboard.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
       
    }

    @FXML
    private void evenement(ActionEvent event) throws IOException {
         Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/event/tennis/gui/CRUD_EVENT.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
    }

    @FXML
    private void partixipation(ActionEvent event) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/event/tennis/gui/CRUD_PARTICIPATION.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
    }
    
}
