/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.nio.sctp.Association;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CategorieService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ProduitController implements Initializable {
 ProduitService produitService=new ProduitService();
      CategorieService categorieService=new CategorieService();
    
    @FXML
    private TableView<Produit> tab_produit;

    @FXML
    private TableColumn<?, ?> col_nom;
        @FXML
    private TableColumn<?, ?> col_categorie;

    @FXML
    private TableColumn<?, ?> col_description;

    @FXML
    private TableColumn<?, ?> col_prix;

    @FXML
    private TableColumn<?, ?> col_quantite;

    @FXML
    private ComboBox<Categorie> combo_categorie;
    
     @FXML
    private TextField txt_nom;

    @FXML
    private TextField txt_quantite;

    @FXML
    private TextField txt_prix;

    @FXML
    private TextField txt_description;
    
       
       private ObservableList<Produit> produitsList;

    public void addProduitShowListData() throws SQLException {
        List<Produit> produits = produitService.afficherListeP();
        produitsList = FXCollections.observableArrayList(produits);

        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
         col_quantite.setCellValueFactory(new PropertyValueFactory<>("qstock"));
         col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tab_produit.setItems(produitsList);

        
        
        ObservableList<Categorie> obAssociation = FXCollections.observableList(categorieService.afficherListeCategorie());
       combo_categorie .setItems(obAssociation);
        combo_categorie.setCellFactory(param -> new ListCell<Categorie>() {
            @Override
            protected void updateItem( Categorie categorie, boolean empty) {
                super.updateItem(categorie, empty);
                if (empty || categorie == null) {
                    setText(null);
                } else {
                    setText(categorie.getNom());
                }
            }
        });
      combo_categorie.setButtonCell(new ListCell<Categorie>() {
            @Override
            protected void updateItem( Categorie categorie, boolean empty) {
                super.updateItem(categorie, empty);
                if (empty || categorie== null) {
                    setText(null);
                } else {
                    setText(categorie.getNom());
                }
            }
        });
        
    }
    
    
    
    
    @FXML
        public void addProduitSelect() {
        Produit produit = tab_produit.getSelectionModel().getSelectedItem();
        int num = tab_produit.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        txt_nom.setText(produit.getNom());
        txt_description.setText(produit.getDescription());
        txt_prix.setText(produit.getPrix());
         txt_quantite.setText(produit.getQstock());
     combo_categorie.setButtonCell(new ListCell<Categorie>() {
            @Override
            protected void updateItem( Categorie categorie, boolean empty) {
                super.updateItem(categorie, empty);
                if (empty || categorie== null) {
                    setText(null);
                } else {
                    setText(categorie.getNom());
                }
            }
        });
    }
      
   @FXML
    void add()  throws SQLException  {
      try {
            Alert alert;

            if (txt_nom.getText().isEmpty()
                    || txt_description.getText().isEmpty() 
                    ||  txt_prix.getText().isEmpty() 
                 ||    txt_quantite.getText().isEmpty() 
                    ||     combo_categorie.getValue()==null
            ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont obligatoires");
                alert.showAndWait();
            } else {
               Produit produit =new Produit();
                 produit.setNom(txt_nom.getText());
                produit.setDescription(txt_description.getText());
                produit.setPrix(txt_prix.getText());
                produit.setQstock(txt_quantite.getText());
               int idCategorie= combo_categorie.getValue().getId();
               produit.setCategorie(combo_categorie.getValue());
                produitService.ajoutProduit(produit,idCategorie);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                    alert.showAndWait();
              addProduitShowListData();
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
txt_prix.setText("");
txt_quantite.setText("");
    }

    @FXML
    void delete()  throws SQLException {
    Produit produit=tab_produit.getSelectionModel().getSelectedItem();
        int num = tab_produit.getSelectionModel().getSelectedIndex();
        if((num - 1)<-1){return;}
        Alert alert;
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cofirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Association: " + produit.getNom() + "?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {

         produitService.supprimerProduit(produit.getId());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Deleted!");
            alert.showAndWait();
            addProduitShowListData();
             clear();
    }
    }

    @FXML
    void update() throws SQLException  {
Produit produitID=tab_produit.getSelectionModel().getSelectedItem();
    int num = tab_produit.getSelectionModel().getSelectedIndex();
    if((num - 1)<-1){return;}


    try {
        Alert alert;

        if (txt_nom.getText().isEmpty()
                || txt_description.getText().isEmpty()
                ||  txt_prix.getText().isEmpty() 
                 ||    txt_quantite.getText().isEmpty() 
                ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Les champs sont obligatoires");
            alert.showAndWait();
        } else {
Produit produit =new Produit();
produit.setNom(txt_nom.getText());
produit.setDescription(txt_description.getText());
produit.setPrix(txt_prix.getText());
produit.setQstock(txt_quantite.getText());
            produitService.modifierProduit(produit, produitID.getId());;
             addProduitShowListData();
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
    public void initialize(URL url, ResourceBundle rb) {
     try {
         addProduitShowListData();
     } catch (SQLException ex) {
         Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }    

   
    
}
