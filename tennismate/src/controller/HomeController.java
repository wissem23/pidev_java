/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class HomeController implements Initializable {
    
    
    
     @FXML
    private AnchorPane root;

     
     
     
  
    @FXML
    void btn_accueil()  throws IOException{
  Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/gui/acueilFxml.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxmlLoader);
    }

    
    @FXML
    void btn_categorie()throws IOException {
 Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/gui/categorieFxml.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxmlLoader);
    }

    @FXML
    void btn_produit() throws IOException {
 Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/gui/produitFxml.fxml"));
        root.getChildren().removeAll();
        root.getChildren().setAll(fxmlLoader);
    }

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
