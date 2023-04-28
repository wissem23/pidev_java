/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.gui;

import event.tennis.entities.Evennements;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import net.sf.jasperreports.engine.export.Grid;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class EventsController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label type;
    @FXML
    private Label date;
    @FXML
    private Label totale;

    /**
     * Initializes the controller class.
     */
    
    
   


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

     public void setData(Evennements event){
    
        Image imge =new Image(getClass().getResourceAsStream(event.getImage()));
        img.setImage(imge);
        type.setText(event.getType());
        date.setText(event.getDate_debut()+"Date");
        totale.setText(event.gettotalelike()+"TLike");
    }
}
