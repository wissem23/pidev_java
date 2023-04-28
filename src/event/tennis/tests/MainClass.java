/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.tests;

import event.tennis.entities.Evennements;
import event.tennis.entities.Participation;
import event.tennis.entities.User;
import event.tennis.service.ServiceParticipation;
import event.tennis.service.ServiceEvenements;
import event.tennis.utils.BaseConnection;
import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class MainClass {
  public static void main(String[] args) {
      //  BaseConnection connection = new BaseConnection();
        Evennements p = new Evennements(2,"tttttt","noor","imge",LocalDate.of(2023,02,17),LocalDate.of(2023,03,01),3,8);
       // ServiceEvenements sp = new ServiceEvenements();
        //sp.add(p);
       // sp.supprimer(p);
       // System.out.println( sp.afficher());
        //sp.modifier(p);
         
        //BaseConnection connection = new BaseConnection();
        User d = new User(2);
          
       Participation s = new Participation("000",d,p);
                
        ServiceParticipation  l = new ServiceParticipation();
        l.add(s);
        System.out.println(l.afficher());
         
       
      
       // sp.modifier(p);
         
    }
}
