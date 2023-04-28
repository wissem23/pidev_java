/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event.tennis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author LENOVO
 */
public class BaseConnection {
    
   // public String url="jdbc:mysql://localhost:3306/tennisse";
     public static Object getInsatnce() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    final String URL ="jdbc:mysql://localhost:3306/tennissee";
    final String USERNAME="root";
    final String PWD ="";
   private  Connection cnx;
 private static  BaseConnection instance;
    private BaseConnection(){
        try {
            cnx =DriverManager.getConnection(URL, USERNAME, PWD);
            System.out.println("connected ....");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static BaseConnection getInstance (){
        if (instance == null)
            instance = new BaseConnection();
        return instance ;
    }
 
      public Connection getCnx(){
        return cnx;
    }
   public static void main(String[] args) {
    BaseConnection connection = new BaseConnection();
      }

}
