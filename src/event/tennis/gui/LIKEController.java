/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.gui;

import event.tennis.entities.Evennements;
import event.tennis.utils.BaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class LIKEController implements Initializable {

    @FXML
    private Label types;
    @FXML
    private ImageView images;
    @FXML
    private ImageView likes;
    @FXML
    private ImageView like;
    @FXML
    private ImageView dislike;
    @FXML
    private Label totalelike;
    @FXML
    private Button particip;
    private Connection cnx;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        
    }    
    
    @FXML
    private void participate(ActionEvent event ) {
         try {
            System.out.println(event.toString());
            String qry = "SELECT * FROM participation` " ;
            cnx = BaseConnection.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            System.out.println("Ajout P avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void setData(Evennements event){
    
        Image imge =new Image(getClass().getResourceAsStream(event.getImage()));
        images.setImage(imge);
        if(!event.getType().isEmpty()){
       types.setText(event.getType());
        }
        if(!event.getImage().isEmpty()){
        imge=new Image(getClass().getResourceAsStream(event.getImage()));
        images.setImage(imge);
        }else{
        images.setVisible(false); 
        }
        totalelike.setText(String.valueOf(event.gettotalelike()));
        
    }
    
    private Evennements getEvennementses(){
    
        
    Evennements ev = new Evennements();
    ev.setType("tournois");
    ev.setImage("/ressource/1.png");
    ev.settotalelike(15);
    
    return ev;
    }
}
