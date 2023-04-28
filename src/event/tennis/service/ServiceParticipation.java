/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.service;

import event.tennis.entities.Evennements;
import event.tennis.entities.Participation;
import event.tennis.entities.User;
import event.tennis.utils.BaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;

/**
 *
 * @author LENOVO
 */
public class ServiceParticipation implements IService<Participation>{
    Connection cnx;
    
    public void add(Participation t) {
        try {
            System.out.println(t.toString());
            String qry = "INSERT INTO `participation` (codeticket,utilisateur_id,evennement_id) VALUES ('" + t.getCodeticket()+ "','" +t.getUser().getId()+ "','" +t.getEvenements().getId()+ "');";
            cnx = BaseConnection.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            System.out.println("Ajout P avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public List<Participation> afficher() {
    List<Participation> participations = new ArrayList<>();
    try {
        
        String qry = "SELECT * FROM participation  ";
         cnx = BaseConnection.getInstance().getCnx();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(qry);
        while (rs.next()) {
            Participation p = new Participation();
            userc o = new userc();
            List <User> aaa = o.afficher();
             User u = new User();
             int q =rs.getInt(2);
               List<User> bystatue=  aaa.stream().filter(t -> t.getId() == q ).collect(toList());
             u = bystatue.get(0);
            
             ServiceEvenements n = new ServiceEvenements();
             List <Evennements> bbb = n.afficher(); 
            Evennements e = new Evennements();
            int m =rs.getInt(3);
               List<Evennements> status=  bbb.stream().filter(t -> t.getId() == m ).collect(toList());
             e =status.get(0);
            
            p.setId(rs.getInt(1));
            p.setCodeticket(rs.getString("Codeticket"));
            
            e.setId(rs.getInt(3));
            p.setEvenements(e);
            p.setUser(u);
            participations.add(p);
        }
        return participations;

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    
    return participations;



    }
    }

    
    public void modifier(Participation t) {
        try {
            String qry = "UPDATE `participation` SET `codeticket`='"+t.getCodeticket()+"', `user`=' "+ t.getUser().getId()+" ', `evennement`='" + t.getEvenements().getId()+ "' WHERE `id`='"+ t.getId()+"'";

            cnx = BaseConnection.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            stm.executeUpdate(qry);
            System.out.println("Modification p avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
    public void supprimer(Participation t) {
       try {  
            String qry =  "DELETE FROM participation WHERE id = '"+ t.getId()+"'";

            cnx = BaseConnection.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            System.out.println("Suppression p avec succées ");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
