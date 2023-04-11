/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wissem.services;

import wissem.entities.Seance;
import wissem.utils.DataSource;
//import wissem.utils.EnumDataSource;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.text.SimpleDateFormat;



/**
 *
 * @author wisse
 */

public class ServiceSeance implements IService<Seance> {

    //private final Connection cnx = EnumDataSource.INSTANCE.getCnx();
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Seance s) {
        
        try {
            String req1 = "INSERT INTO `seance` (`date`, `duration`, `level`, `adresse`, `coach_name`, `people_nbre`, `is_available`, `description`, `price`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req1);
           
           // ps.setTimestamp(1, new java.sql.Timestamp(s.getDate().getTime()));
           /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String formattedDate = dateFormat.format(s.getDate());
            ps.setString(1, formattedDate);*/
            //ps.setDate(1, new java.sql.Date(s.getDate().getTime()));
            ps.setTimestamp(1, new java.sql.Timestamp(s.getDate().getTime()));

            ps.setDouble(2, s.getDuration());
            ps.setString(3, s.getLevel());
            ps.setString(4, s.getAdresse());
            ps.setString(5, s.getCoachName());
            ps.setInt(6, s.getPeopleNbre());
            ps.setBoolean(7, s.isIsAvailable());

            ps.setString(8, s.getDescription());
            ps.setDouble(9, s.getPrice());
            ps.executeUpdate();
            System.out.println("Seance created!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public void supprimer(int id) {
        try {
            String req2 = "DELETE FROM `seance` WHERE id = " + id;
            Statement st = cnx.createStatement();
            st.executeUpdate(req2);
            System.out.println("Seance deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Seance s) {
        try {
            String req3 = "UPDATE `seance` SET `date` = ?, `duration` = ?, `level` = ?, `adresse` = ?, `coach_name` = ?, `people_nbre` = ?, `is_available` = ?, `description` = ?, `price` = ? WHERE `seance`.`id` = ?";
            PreparedStatement ps = cnx.prepareStatement(req3);
          //  ps.setTimestamp(1, Timestamp.valueOf(seance.getDate()));
         /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String formattedDate = dateFormat.format(s.getDate());
           
            ps.setString(1, formattedDate);*/
          //  ps.setDate(1, new java.sql.Date(s.getDate().getTime()));
            ps.setTimestamp(1, new java.sql.Timestamp(s.getDate().getTime()));
            ps.setDouble(2, s.getDuration());
            ps.setString(3, s.getLevel());
            ps.setString(4, s.getAdresse());
            ps.setString(5, s.getCoachName());
            ps.setInt(6, s.getPeopleNbre());
            ps.setBoolean(7, s.isIsAvailable());
            ps.setString(8, s.getDescription());
            ps.setDouble(9, s.getPrice());
            ps.setInt(10, s.getId());
            ps.executeUpdate();
            System.out.println("Seance updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

   @Override
    public List<Seance> afficher() {
        List<Seance> list = new ArrayList<>();
        
        try {
            String req4 = "Select * from seance";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req4);
            while(rs.next()){
                Seance s = new Seance();
                s.setId(rs.getInt(1));
                s.setDate(rs.getTimestamp(2));
                s.setDuration(rs.getDouble(3));
                s.setLevel(rs.getString(4));
                s.setAdresse(rs.getString(5));
                s.setCoachName(rs.getString(6));
                s.setPeopleNbre(rs.getInt(7));
                s.setIsAvailable(rs.getBoolean(8));
                s.setDescription(rs.getString(9));
                s.setPrice(rs.getDouble(10));
          
                list.add(s);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }



}
