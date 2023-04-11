/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wissem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wisse
 */

//Singleton Design Pattern by using Lazy Instantiation
public class DataSource {
    private Connection cnx;
    private static DataSource instance;
    
// Database properties
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/pique";

// Constructor is private to enforce singleton pattern
    private DataSource() {           //cnv to public to test cnx
        try {
            // Establish a connection to the database
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

// Method to get a singleton instance of this class
    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

// Method to get the Connection object
    public Connection getCnx() {
        return cnx;
    }
// Method to close the connection
    public void closeConnection() {
        if (cnx != null) {
            try {
                cnx.close();
                System.out.println("Connection closed successfully");
            } catch (SQLException e) {
                throw new RuntimeException("Unable to close connection", e);
            }
        }
    }
       
}

