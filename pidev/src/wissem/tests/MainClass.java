/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wissem.tests;
import wissem.entities.Seance;
import wissem.entities.Reservation;
import wissem.services.ServiceSeance;
import wissem.services.ServiceReservation;

//import wissem.utils.EnumDataSource;
import wissem.utils.DataSource;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date ;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author wisse
 */
public class MainClass extends Application {

    
       @Override
    public void start(Stage primaryStage) throws Exception {
        
          Parent root = FXMLLoader.load(getClass().getResource("Page1.fxml"));

        // Définit la scène avec la racine
        Scene scene = new Scene(root);

        // Définit la scène principale
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        //DataSource test = new DataSource();
        //Connection connection = EnumDataSource.INSTANCE.getCnx();

      // Connection cnx = DataSource.getInstance().getCnx();
      
                                      //test seance crud
     // ServiceSeance sc =new ServiceSeance();
     
     // Seance s_ad = new Seance(new Date(123, 4, 9, 16, 30), 2, "Advanced", "rades", "Sami Tayari", 65, true, "yyy", 20);
     // sc.ajouter(s_ad);
     
     // Seance s_up = new Seance(3,new Date(123, 3, 9, 15, 30), 2, "Intermediate", "rades", "Sami Tayari", 25, true, "test", 20);
     // sc.modifier(s_up);
     
     // sc.supprimer(3); 
     
     // System.out.println(sc.afficher());
     
                                       //test reservation crud
     ServiceReservation rc =new ServiceReservation();
     //Reservation r_ad = new Reservation(3, 3, true,new Date()) ;
     //rc.ajouter(r_ad);
     
    // Reservation r_up = new Reservation(14, 3, 3, false, new Date());
    // rc.modifier(r_up);
    
    //rc.supprimer(14);
    
    //System.out.println(rc.afficher());
    

  
    
            launch(args);

    }      
    
    
}
