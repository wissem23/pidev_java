/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.service;

import event.tennis.entities.Evennements;
import event.tennis.entities.User;
import event.tennis.utils.BaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class userc {
       Connection cnx;
     public List<User  > afficher() {
        List<User > evennements = new ArrayList<>();
        try {
            String qry = "SELECT * FROM `user`  ";
            cnx = BaseConnection.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                User p = new User();
                p.setId(rs.getInt(1));
                p.setFirst_name(rs.getString(6));
            
               evennements.add(p);
            }
            return evennements;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evennements;

     }
    
}
