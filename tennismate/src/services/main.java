/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entites.Categorie;
import entites.Produit;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MSI
 */
public class main extends Application{
    
    public static void main(String[] args) throws SQLException {
        //test product crud
     /*   Produit p = new Produit();
        p.setNom("test");
        Categorie c = new Categorie();
        ProduitService produitService=new ProduitService();
        p.setNom("test");
        p.setPrix("1456");
        p.setDescription("test");
        p.setQstock("23");
       
        p.setImage("image.jpg");
       produitService.ajoutProduit(p,1);
    System.out.println(produitService.afficherListeP());  
     produitService.supprimerProduit(1);
       System.out.println(produitService.afficherListeP()); 
      Produit p1 = new Produit();
        p1.setNom("ppp1");
        p1.setNom("aaaaaaaaaa1");
        p1.setPrix("aaaaaaa1");
        p1.setDescription("zzzzzzzzz1");
        p1.setQstock("25");
        p1.setCategorie(c);
        p1.setImage("image1.jpg");
       produitService.modifierProduit(p1,6);
        */
     
     //test categorie crud
      // CategorieService categorieService=new CategorieService();
      // categorieService.supprimerCategorie(3);
      //    ProduitService produitService=new ProduitService();
     //   System.out.println(  categorieService.afficherCategorie(1));
      //  System.out.println(categorieService.afficherListeCategorie() );
    //  System.out.println(produitService.getProductsByCategorie(1));    
     launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
Parent root=FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));
Scene scene=new Scene(root);
primaryStage.setScene(scene);
primaryStage.show();



    }
    
}
