module com.example.logindata {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.pidev to javafx.fxml;
    exports com.example.pidev;
}