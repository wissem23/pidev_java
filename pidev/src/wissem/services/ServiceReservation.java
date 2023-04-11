/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wissem.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import wissem.entities.Reservation;
import wissem.entities.Seance;
import wissem.utils.DataSource;

/**
 *
 * @author wisse
 */
public class ServiceReservation implements IService<Reservation>{
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Reservation r) {
        try {
            String req1 = "INSERT INTO `reservation` (`seance_id`, `user_id`, `is_status`, `create_at`) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req1);
           
            ps.setInt(1, r.getSeance_id());
            ps.setInt(2, r.getUser_id());
            ps.setBoolean(3, r.isIs_status());
            ps.setTimestamp(4, new java.sql.Timestamp(r.getCreate_at().getTime()));
            
            ps.executeUpdate();
            System.out.println("reservation created!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    
    

    

    @Override
    public void supprimer(int id) {
        try {
            String req2 = "DELETE FROM `reservation` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req2);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reservation r) {
try {
            String req3 = "UPDATE `reservation` SET `seance_id` = ?, `user_id` = ?, `is_status` = ?, `create_at` = ? WHERE `reservation`.`id` = ?";

            PreparedStatement ps = cnx.prepareStatement(req3);
           
            
            ps.setInt(1, r.getSeance_id());
            ps.setInt(2, r.getUser_id());
            ps.setBoolean(3, r.isIs_status());
           ps.setTimestamp(4, new java.sql.Timestamp(r.getCreate_at().getTime()));
            ps.setInt(5, r.getId());
            ps.executeUpdate();
            System.out.println("reservation updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
    }
    @Override
    public List<Reservation> afficher() {
    List<Reservation> list = new ArrayList<>();
        
        try {
            String req4 = "Select * from reservation";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req4);
            while(rs.next()){
                Reservation r = new Reservation();
                r.setId(rs.getInt(1));
                r.setSeance_id(rs.getInt(2));
                r.setUser_id(rs.getInt(3));
                r.setIs_status(rs.getBoolean(4));
                r.setCreate_at(rs.getTimestamp(5));
          
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }    
    
}
