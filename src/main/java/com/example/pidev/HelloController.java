package com.example.pidev;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelloController {
    @FXML
    private Label welcomeText;

   @FXML
   private TextField cinTextField;
   @FXML
   private TextField emailTextField;
   @FXML
   private TextField addressTextField;

   @FXML
   private Button cancelButton;

   @FXML
   private Button cancelButton2;

   @FXML
   private Button loginButton;

   @FXML
   private Button loginButton2;

   @FXML
   private Label loginMessageLabel;

   @FXML
   private Label loginMessageLabel2;

   @FXML
   private BorderPane login_form;

   @FXML
   private PasswordField passwordPasswordField;

   @FXML
   private PasswordField passwordPasswordField2;


   @FXML
   private Button su_loginAccountBtn1;

   @FXML
   private BorderPane signup_form;

   @FXML
   private Button su_creqteAccountBtn;

   @FXML
   private Button su_loginAccountBtn;

   @FXML
   private TextField emailTextField2;

   @FXML
   private TextField usernameTextField2;


   @FXML
   private BorderPane forget_form;

   @FXML
   private Button su_forgetBtn;


   private Connection con;
   private PreparedStatement prepare;
   private ResultSet result;
   private String roles = "ROLE_USER";
   private String isVerified = "yes";



   public HelloController() {

      con = database.createorgetInstance().getCon();

   }


   public void loginAccount() {

      String sql = "SELECT email,password FROM user WHERE email=? AND password=?";
      database database=new database();
      database.connect();
      try {
         Alert alert;
         if (emailTextField2.getText().isEmpty() || passwordPasswordField.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank field");
            alert.showAndWait();
         }else {
            prepare = con.prepareStatement(sql);
            prepare.setString(1, emailTextField2.getText());
            prepare.setString(2, passwordPasswordField.getText());

            result = prepare.executeQuery();

            if (result.next()) {

               alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("information message");
               alert.setHeaderText(null);
               alert.setContentText("successfully login");
               alert.showAndWait();

               loginButton.getScene().getWindow().hide();

               Parent root = FXMLLoader.load(getClass().getResource("DashAdmin.fxml"));
               Stage stage=new Stage();
               Scene scene=new Scene(root);
               stage.setScene(scene);
               stage.setTitle("TennisMate Dashboard Admin ");
               stage.show();


            }
            else{

               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error message");
               alert.setHeaderText(null);
               alert.setContentText("Incorrect username/password");
               alert.showAndWait();

            }
         }

      }catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (prepare != null) {
               prepare.close();
            }
            if (result != null) {
               result.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

   }



public void registerAccount(){
   String sql = "INSERT INTO user (fullname,email,password,cin,address,roles,isVerified,date)VALUES(?,?,?,?,?,?,?,?)";
   database database=new database();
   database.connect();

   try {
      Alert alert;
      if (emailTextField.getText().isEmpty() || passwordPasswordField2.getText().isEmpty()) {
         alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Error message");
         alert.setHeaderText(null);
         alert.setContentText("Please fill all blank field");
         alert.showAndWait();
      }else{

         String checkData="SELECT email FROM user WHERE email='"+emailTextField.getText()+"'";

         prepare =con.prepareStatement(checkData);

         result=prepare.executeQuery();
         if (result.next()) {

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("information message");
            alert.setHeaderText(null);
            alert.setContentText(emailTextField.getText()+" is already exist");
            alert.showAndWait();
         }else {

            if(passwordPasswordField2.getText().length()<6){
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error message");
               alert.setHeaderText(null);
               alert.setContentText("Invalid Password , atleast 6 characters needs");
               alert.showAndWait();

            }else if(cinTextField.getText().length()<8 ||cinTextField.getText().length()>8) {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error message");
               alert.setHeaderText(null);
               alert.setContentText("Invalid CIN , should be 8 characters needs");
               alert.showAndWait();
            } else if(validationEmail()&& validationCin() ) {


               prepare = con.prepareStatement(sql);
               prepare.setString(1, usernameTextField2.getText());
               prepare.setString(2, emailTextField.getText());
               prepare.setString(3, passwordPasswordField2.getText());
               prepare.setString(4, cinTextField.getText());
               prepare.setString(5, addressTextField.getText());
               prepare.setString(6, "ROLE_USER");
               prepare.setInt(7, 1);


               java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
               prepare.setDate(8, java.sql.Date.valueOf(String.valueOf(currentDate)));

               prepare.executeUpdate();

               alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("information message");
               alert.setHeaderText(null);
               alert.setContentText("successfully create a new account ! ");
               alert.showAndWait();


               login_form.setVisible(true);
               signup_form.setVisible(false);
               emailTextField.setText("");
               passwordPasswordField2.setText("");

            }
         }


      }



   }catch (Exception e) {
      e.printStackTrace();
   }


}


   public boolean validationEmail(){
      Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");

      Matcher match = pattern.matcher(emailTextField.getText());

      if(match.find() && match.group().equals(emailTextField.getText())){

         return true;

      }else{

         Alert alert = new Alert(Alert.AlertType.ERROR);

         alert.setTitle("Error Message");
         alert.setHeaderText(null);
         alert.setContentText("Invalid Email");
         alert.showAndWait();

         return false;

      }

   }

   public boolean validationCin(){


try {
   database database=new database();
   database.connect();
   String checkDataCin="SELECT cin FROM user WHERE cin='"+cinTextField.getText()+"'";
   prepare =con.prepareStatement(checkDataCin);

   result=prepare.executeQuery();
   if (result.next()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);

      alert.setTitle("Error Message");
      alert.setHeaderText(null);
      alert.setContentText("Cin"+cinTextField.getText()+ ", Already exist");
      alert.showAndWait();
      return false;

   }else{



      return true;

   }
}catch (Exception e) {
   e.printStackTrace();
}


   return true;}


   public void switchForm (ActionEvent e){
if(e.getSource()==su_loginAccountBtn){
   login_form.setVisible(true);
   signup_form.setVisible(false);
   forget_form.setVisible(false);

}else if (e.getSource()==su_creqteAccountBtn){
   login_form.setVisible(false);
   signup_form.setVisible(true);
   forget_form.setVisible(false);

} else if (e.getSource()==su_forgetBtn) {
   login_form.setVisible(false);
   signup_form.setVisible(false);
   forget_form.setVisible(true);


} else if (e.getSource()==su_loginAccountBtn1) {
   login_form.setVisible(true);
   signup_form.setVisible(false);
   forget_form.setVisible(false);
}

   }

   public void cancelButtonOnAction(ActionEvent e)
   {
      Stage stage =(Stage) cancelButton.getScene().getWindow();
      stage.close();



   }
   public void cancelButtonOnAction2(ActionEvent e)
   {
      Stage stage =(Stage) cancelButton2.getScene().getWindow();
      stage.close();



   }

}