/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.gui;

import event.tennis.entities.Evennements;
import event.tennis.service.ServiceEvenements;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static java.util.stream.Collectors.toList;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;



/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CRUD_EVENTController implements Initializable {
String s;
    @FXML
    private ComboBox<String> tType;
    @FXML
    private TextField tDescription;
    @FXML
    private DatePicker tDate_Debut;
    @FXML
    private DatePicker tDate_Fin;
    @FXML
    private Button tImage;
    @FXML
    private TextField tLike;
    @FXML
    private TextField tTotaleLike;
    private TableView<Evennements> table;
    @FXML
    private Button btnADD;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnView;
    private TableColumn<Evennements, String> colType;
    private TableColumn<Evennements, DatePicker> colD_debut;
    private TableColumn<Evennements, DatePicker> colD_fin;
    private TableColumn<Evennements, String> colDescription;
    private TableColumn<Evennements, String> colImg;
    private TableColumn<Evennements, Integer> colLike;
    private TableColumn<Evennements, Integer> colTotale;
    private ObservableList<Evennements> getevennements;
    @FXML
    private ListView<Evennements> v_event;
    @FXML
    private TextField tRecherche;
    private Object filteredList;
    @FXML
    private Button rech;
    @FXML
    private Button dashBtn;
    @FXML
    private Button eventBtn;
    @FXML
    private Button PartBtn;
    @FXML
    private AnchorPane lst;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> typs
                = FXCollections.observableArrayList("Tournois", "Semenaire"," ");
        tType.setItems(typs);
        tType.setValue("Tournois");
        
        afficherEvenement();
    }    

    @FXML
    private void ajouterEvenement(ActionEvent event) {
         ServiceEvenements V_Service = new ServiceEvenements();
        Evennements e = new Evennements();
            
        if(tType.getSelectionModel().getSelectedItem().isEmpty() || tDescription.getText().isEmpty() ) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplissez tous les champs");
            alert.showAndWait();
            
        } else if (tDescription.getText().trim().length() == 10){
            JOptionPane.showMessageDialog(null, "Textfield can't be empty");
        }else if (tDate_Debut.getValue().isAfter(tDate_Fin.getValue())) {
                System.out.println("La date de début doit être inférieure ou égale à la date de fin.");
        
        }else {
            
            e.setType(tType.getSelectionModel().getSelectedItem());
            e.setDate_debut(tDate_Debut.getValue());
            e.setDate_fin(tDate_Fin.getValue());
            e.setImage(s);
            e.setliked(Integer.parseInt(tLike.getText()));
            e.settotalelike(Integer.parseInt(tTotaleLike.getText()));
            
            
            try {
                V_Service.add(e);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("L'event a été ajouté avec succès !");
                alert.show();
                
            } catch (Exception t) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur est survenue lors de l'ajout event : " + t.getMessage());
                alert.show();
            }}
         afficherEvenement();
    }

    @FXML
    private void modifierEvenement(ActionEvent event) {
        ServiceEvenements V_Service = new ServiceEvenements();
         Evennements e = new Evennements();
        if(tType.getSelectionModel().getSelectedItem().isEmpty() || tDescription.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplissez tous les champs");
            alert.showAndWait();
        } else {
            e.setType(tType.getSelectionModel().getSelectedItem());
            e.setDescription(tDescription.getText());
            e.setDate_debut(tDate_Debut.getValue());
            e.setDate_fin(tDate_Fin.getValue());
            e.setImage(s);
            e.setliked(Integer.parseInt(tLike.getText()));
            e.settotalelike(Integer.parseInt(tTotaleLike.getText()));
        
        Evennements selectedEvennements =   v_event.getSelectionModel().getSelectedItem();    
        e.setId(selectedEvennements.getId());
        V_Service.modifier(e);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your event has been UPDATED successfully!");
        alert.show();
    }
          afficherEvenement();
    }

    @FXML
    private void supprimerEvenement(ActionEvent event) {
       Evennements selectEvennements= v_event.getSelectionModel().getSelectedItem();

        ServiceEvenements V_Service = new ServiceEvenements();
        V_Service.supprimer(selectEvennements);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your event has been DELETED successfully!");
        alert.show();
         afficherEvenement();
    
    }

    @FXML
    private void afficherEvenement() {
        
        
        ServiceEvenements V_Service = new ServiceEvenements();
        List<Evennements> event = V_Service.afficher();
        ObservableList<Evennements> items = FXCollections.observableArrayList() ;
        for(Evennements evnt : event){
            items.add(evnt);
        }
       v_event.setItems(items);
         
        
    }
    
  
    @FXML
    private void recherche(ActionEvent event) {    
       ServiceEvenements V_Service = new ServiceEvenements();
        List<Evennements> events = V_Service.afficher();
        String o = tRecherche.getText();
         
         List<Evennements> bystatue = events.stream().filter(t -> t.getType().startsWith(o)||t.getDescription().equals(o)||  
                     Integer.toString(t.getliked()).equals(o)).collect(toList());
             
        
        
        if (tRecherche.getText().isEmpty()){
         
        show(events);}
       
        else {
            show(bystatue);
        
        }
    }
        
        
       
        

   @FXML
    private void AddImage(ActionEvent event) {
 FileChooser fileChooser = new FileChooser();
File selectedFile = fileChooser.showOpenDialog(null);
        
        s=selectedFile.toString();
        System.out.println(s);
    }
 
       
        
        
        
        

        
    


    private void show(List<Evennements> events) {
        ObservableList<Evennements> items =FXCollections.observableArrayList();
      
        for(Evennements evnt : events){
        
        
                    
        items.add(evnt);
          }
        v_event.setItems(items);
    }

    @FXML
    private void dashboard(ActionEvent event) throws IOException {
         Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/gui/Dashboard.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
    }

    @FXML
    private void evenment(ActionEvent event) throws IOException {
         Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/event/tennis/gui/CRUD_EVENT.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
    }

    @FXML
    private void participation(ActionEvent event) throws IOException {
         Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/event/tennis/gui/CRUD_PARTICIPATION.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
    }
}

    
    



