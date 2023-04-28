/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.service;

import event.tennis.entities.Evennements;
import event.tennis.utils.BaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author LENOVO
 */

            //String requete = "INSERT INTO evennements (id,type,description,image,date_debut,date_fin,liked,totalelike) VALUES (?,?,?,?,,?,?,?,?)";
public class ServiceEvenements implements IService<Evennements> {

    static Evennements afficher(int id_event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Connection cnx;

   public void add(Evennements t) {
        try {
            System.out.println(t.toString());
            String qry = "INSERT INTO `evennement` (type,description,image,date_debut,date_fin,liked,totalelike) VALUES ('" + t.getType() + "','" + t.getDescription() + "','" +t.getImage()+ "','" + t.getDate_debut() + "','" + t.getDate_fin() + "','" + t.getliked() + "','"+t.gettotalelike()+"');";
            cnx = BaseConnection.getInstance().getCnx();
//
            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            System.out.println("Ajout avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Evennements t) {

        try {
            String qry = "UPDATE `evennement` SET `type`='"+t.getType()+"', `description`=' "+ t.getDescription()+" ', `image`='" + t.getImage() + "', `Date_debut`='" + t.getDate_debut() + "', `Date_fin`='" + t.getDate_fin() + "', `liked`='" + t.getliked() + "', `totalelike`='" + t.gettotalelike()+  "' WHERE `id`='"+ t.getId()+"'";

            cnx = BaseConnection.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            System.out.println("Modification avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimer(Evennements t) {

        try {
            String qry =  "DELETE FROM evennement WHERE id = '"+ t.getId()+"'";

            cnx = BaseConnection.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            System.out.println("Suppression avec succées ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
     public List<Evennements> afficher() {
        List<Evennements> evennements = new ArrayList<>();
        try {
            String qry = "SELECT * FROM `evennement`  ";
            cnx = BaseConnection.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Evennements p = new Evennements();
                p.setId(rs.getInt(1));
                p.setType(rs.getString("type"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
               p.setDate_debut(rs.getDate("date_debut").toLocalDate());
               p.setDate_fin(rs.getDate("date_fin").toLocalDate());
                 p.setliked(rs.getInt("liked"));
               p.settotalelike(rs.getInt("totalelike"));
               evennements.add(p);
            }
            return evennements;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evennements;

     }
     
     
public XYChart.Series chart(){

    
  //dash_b.getData().clear();
       String sql = "SELECT date_debut, liked FROM evennement GROUP BY date_debut ORDER BY TIMESTAMP(date_debut) ASC LIMIT 7";
 XYChart.Series chart = new XYChart.Series();
       cnx = BaseConnection.getInstance().getCnx();

        try {
           

            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(sql);
System.out.println(rs);
            while (rs.next()) {
                chart.getData().add(new XYChart.Data(rs.getString(1), rs.getInt(2)));
            }

          //  dash_b.getData().add(chart);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chart;
}

    
   
    }