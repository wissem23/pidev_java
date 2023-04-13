/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entites.Categorie;
import entites.Produit;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CategorieController implements Initializable {
    CategorieService categorieService=new CategorieService();
    
     @FXML
    private TableView<Categorie> tab_categorie;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_description;

    @FXML
    private TextField txt_nom;

    @FXML
    private TextArea txt_description;
    
    
       private ObservableList<Categorie> categoriesList;

    public void addCategorieShowListData() throws SQLException {
        List<Categorie> categories = categorieService.afficherListeCategorie();
        categoriesList = FXCollections.observableArrayList(categories);

        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
     

        tab_categorie.setItems(categoriesList);

    }
    @FXML
        public void addCategorieSelect() {
        Categorie categorie = tab_categorie.getSelectionModel().getSelectedItem();
        int num = tab_categorie.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        txt_nom.setText(categorie.getNom());
        txt_description.setText(categorie.getDescription());
     

     
    }
      

  @FXML
    private Button btn_add;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_clear;  
    
    
      @FXML
    void add()  throws SQLException  {
    try {
            Alert alert;

            if (txt_nom.getText().isEmpty()
                    || txt_description.getText().isEmpty()
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont obligatoires");
                alert.showAndWait();
            } else {
                Categorie categorie = new Categorie();
                categorie.setNom(txt_nom.getText());
                categorie.setDescription(txt_description.getText());
                categorieService.ajoutCategorie(categorie);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                addCategorieShowListData();
         clear();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 @FXML
    void clear() {
txt_nom.setText("");
txt_description.setText("");
    }

    @FXML
    void delete()  throws SQLException  {
     Categorie categorie=tab_categorie.getSelectionModel().getSelectedItem();
        int num = tab_categorie.getSelectionModel().getSelectedIndex();
        if((num - 1)<-1){return;}
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Association: " + categorie.getNom() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

         categorieService.supprimerCategorie(categorie.getId());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deleted!");
            alert.showAndWait();
            addCategorieShowListData();
             clear();
        
    }
    }

    @FXML
    void update()  throws SQLException  {
   Categorie categorieID=tab_categorie.getSelectionModel().getSelectedItem();
    int num = tab_categorie.getSelectionModel().getSelectedIndex();
    if((num - 1)<-1){return;}


    try {
        Alert alert;

        if (txt_nom.getText().isEmpty()
                || txt_description.getText().isEmpty()
                ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Les champs sont obligatoires");
            alert.showAndWait();
        } else {
Categorie categorie =new Categorie();
categorie.setNom(txt_nom.getText());
categorie.setDescription(txt_description.getText());
            categorieService.modifierCategorie(categorie, categorieID.getId());;
             addCategorieShowListData();
             clear();

        }
        } catch (Exception e) {
        e.printStackTrace();
    }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        try {
            addCategorieShowListData();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
