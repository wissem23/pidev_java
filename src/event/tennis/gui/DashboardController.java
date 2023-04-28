/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.gui;

import event.tennis.service.ServiceEvenements;
import event.tennis.utils.BaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import static javax.management.remote.JMXConnectorFactory.connect;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DashboardController implements Initializable {
    @FXML
    private AnchorPane lst;
     @FXML
    private AnchorPane lstt;

    @FXML
    private Button dashBtn;
    @FXML
    private Button eventBtn;
    @FXML
    private Button partBtn;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AreaChart<?, ?> dash_b;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //dashChart();
      ServiceEvenements se=new ServiceEvenements();
XYChart.Series chart = new XYChart.Series();
chart=se.chart();
dash_b.getData().add(chart);
    }    

    @FXML
    void Dashboard() throws IOException{
        
  Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/gui/Dashboard.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
    }
    

    @FXML
     void Event() throws IOException{
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/event/tennis/gui/CRUD_EVENT.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
    }

    @FXML
    private void Participant() throws IOException {
         Parent fxmlLoader = FXMLLoader.load(getClass().getResource("/event/tennis/gui/CRUD_PARTICIPATION.fxml"));
        lst.getChildren().removeAll();
        lst.getChildren().setAll(fxmlLoader);
    }
    
    public void dashChart() {

         dash_b.getData().clear();
       String sql = "SELECT date_debut, liked FROM evennement GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 7";

        connect = (Connection) BaseConnection.getInsatnce();
System.out.println(connect);
        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
System.out.println(result);
            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            //dash_b.getData().add(chart);
//dash_b.getda
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
