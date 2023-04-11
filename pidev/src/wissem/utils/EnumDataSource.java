/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package wissem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Singleton Design Pattern by using enum INSTANCE

// This is an enum that represents a singleton instance of a DataSource class
public enum EnumDataSource {
    INSTANCE;

    // This is the Connection object to interact with the database
    private final Connection cnx;

    // This constructor is private to enforce the singleton pattern
    private EnumDataSource() {
        // Set database connection parameters
        String USER = "root";
        String PWD = "";
        String URL = "jdbc:mysql://localhost:3306/tennisse";

        try {
            // Connect to the database using the provided credentials
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected successfully!");
        } catch (SQLException ex) {
            // Throw a runtime exception if connection cannot be established
            throw new RuntimeException("Unable to connect to database", ex);
        }
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
                // Throw a runtime exception if connection cannot be closed
                throw new RuntimeException("Unable to close connection", e);
            }
        }
    }
}
*/