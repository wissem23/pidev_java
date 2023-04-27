package com.example.pidev;


import java.sql.Connection;
import java.sql.DriverManager;


public class database {

    String url= "jdbc:mysql://localhost:3306/java_tennismate";
    String user = "root";
    String pwd = "";
    Connection con;



    public database(){
        connect();
    }
    public void connect(){

        try{

            con=(Connection) DriverManager.getConnection(url,user,pwd);
            System.out.println(" connexion succeed" );

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(" connexion failed" );

        }
    }
    public static database instance;

    public static database createorgetInstance(){
        if(instance ==null ){
            instance = new database();

        }

        return instance;
    }
    public Connection getCon() {
        return con;
    }
}
