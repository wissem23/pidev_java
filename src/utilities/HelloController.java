package utilities;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;
import entities.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.database;
import sun.net.www.http.HttpClient;


//import org.scribe.builder.ServiceBuilder;
//import org.scribe.builder.api.GitHubApi;
//import org.scribe.model.Token;
//import org.scribe.oauth.OAuthService;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class HelloController {
   @FXML
   private Label welcomeText;
   @FXML
   private TextField cinTextField21;

   @FXML
   private TextField cinTextField;
   @FXML
   private TextField emailTextField;
   @FXML
   private TextField addressTextField;
   @FXML
   private TextField confirmationcodeTextField;

   @FXML
   private TextField emailcodeTextField;


   @FXML
   private Button verifyButton;

   @FXML
   private Button cancelButton;

   @FXML
   private Button cancelButton2;

   @FXML
   private Button su_loginAccountBtn11;

   @FXML
   private Button loginButton;

   @FXML
   private Button loginButton2;

   @FXML
   private Label loginMessageLabel;

   @FXML
   private Label loginMessageLabel2;

   @FXML
   public BorderPane login_form;
   @FXML
   public BorderPane forget_form1;


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
   private TextField emailTextField21;

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
   private String isVerified = "0";
   private String isBlocked = "0";


   public HelloController() {

      con = database.createorgetInstance().getCon();

   }
   public void loginAccount() {

      String sql = "SELECT id,email,password,roles,address,cin,fullname,description,image,specialite,cv,isVerified,isBlocked FROM user WHERE email=? AND password=?";
      String email = emailTextField2.getText();
      String password = passwordPasswordField.getText();

      database database = new database();
      database.connect();

      try {
         Alert alert;
         if (email.isEmpty() || password.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank field");
            alert.showAndWait();
         } else {
            prepare = con.prepareStatement(sql);
            prepare.setString(1, email);
            prepare.setString(2, password);

            result = prepare.executeQuery();

            if (result.next()) {
               UserData.email = result.getString("email");
               UserData.fullname=result.getString("fullname");
               UserData.address=result.getString("address");
               UserData.cin=result.getString("cin");
               UserData.description=result.getString("description");
               UserData.image=result.getString("image");
               UserData.specialite=result.getString("specialite");
               UserData.isverified=result.getInt("isVerified");


               String roles = result.getString("roles");
               Integer isBlocked = Integer.valueOf(result.getString("isBlocked"));

               if(isBlocked.equals(Optional.of(1))){
                  alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Error message");
                  alert.setHeaderText(null);
                  alert.setContentText("Your account is blocked now !, please Contact Us.");
                  alert.showAndWait();

               }
               else if (roles.equals("ROLE_USER") ) {
                  alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("information message");
                  alert.setHeaderText(null);
                  alert.setContentText("successfully login as user ");
                  alert.showAndWait();

                  loginButton.getScene().getWindow().hide();

                  Parent root = FXMLLoader.load(getClass().getResource("DashUser.fxml"));

                  Stage stage=new Stage();
                  Scene scene=new Scene(root);
                  stage.setScene(scene);
                  stage.setTitle("Hygie Dashboard User");
                  stage.show();

               } else if (roles.equals("ROLE_COACH")) {
                  alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("information message");
                  alert.setHeaderText(null);
                  alert.setContentText("successfully login as coach");
                  alert.showAndWait();

                  loginButton.getScene().getWindow().hide();

                  Parent root = FXMLLoader.load(getClass().getResource("DashCoach.fxml"));
                  Stage stage=new Stage();
                  Scene scene=new Scene(root);
                  stage.setScene(scene);
                  stage.setTitle("Hygie Dashboard Coach");
                  stage.show();

               } else if (roles.equals("ROLE_ADMIN")) {
                  alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("information message");
                  alert.setHeaderText(null);
                  alert.setContentText("successfully login as admin");
                  alert.showAndWait();

                  loginButton.getScene().getWindow().hide();

                  Parent root = FXMLLoader.load(getClass().getResource("DashAdmin.fxml"));
                  Stage stage=new Stage();
                  Scene scene=new Scene(root);
                  stage.setScene(scene);
                  stage.setTitle("Hygie Dashboard Admin");
                  stage.show();
               }

            } else {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error message");
               alert.setHeaderText(null);
               alert.setContentText("Incorrect username/password");
               alert.showAndWait();
            }
         }

      } catch (Exception e) {
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
      String sql = "INSERT INTO user (fullname,email,password,cin,address,roles,isVerified,date,image,specialite,cv,description,isBlocked)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
      String email = emailTextField.getText();
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
                  prepare.setString(7, "0");







                  java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                  prepare.setDate(8, java.sql.Date.valueOf(String.valueOf(currentDate)));
                  prepare.setString(9, null);
                  prepare.setString(10, null);
                  prepare.setString(11, null);
                  prepare.setString(12, null);
                  prepare.setString(13, "0");

                  prepare.executeUpdate();

                  sendEmailConfirmation(email);

                  alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("information message");
                  alert.setHeaderText(null);
                  alert.setContentText("successfully create a new account !A confirmation email has been sent to " + email + ".");
                  alert.showAndWait();

                  forget_form1.setVisible(true);
                  login_form.setVisible(false);
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
         forget_form1.setVisible(false);

      } else if (e.getSource()==su_forgetBtn) {
         login_form.setVisible(false);
         signup_form.setVisible(false);
         forget_form.setVisible(true);
         forget_form1.setVisible(false);


      } else if (e.getSource()==su_loginAccountBtn1) {
         login_form.setVisible(true);
         signup_form.setVisible(false);
         forget_form.setVisible(false);
         forget_form1.setVisible(false);

      } else if (e.getSource()==su_loginAccountBtn11) {

         login_form.setVisible(true);
         signup_form.setVisible(false);
         forget_form.setVisible(false);
         forget_form1.setVisible(false);
      }

   }
   public void cancelButtonOnAction(ActionEvent e) {
      Stage stage =(Stage) cancelButton.getScene().getWindow();
      stage.close();



   }
   public void cancelButtonOnAction2(ActionEvent e) {
      Stage stage =(Stage) cancelButton2.getScene().getWindow();
      stage.close();



   }
   public void forgetPassword() {
      String sql = "SELECT email,password FROM user WHERE email=? AND cin=?";
      String email = emailTextField21.getText();
      String cin = cinTextField21.getText(); // add CIN field

      database database = new database();
      database.connect();

      try {
         Alert alert;

         if (email.isEmpty() || cin.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter your email address and CIN");
            alert.showAndWait();
         } else {
            Pattern p = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
            Matcher m = p.matcher(email);

            if (!m.matches()) {
               alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Error message");
               alert.setHeaderText(null);
               alert.setContentText("Invalid email address");
               alert.showAndWait();
            } else {
               prepare = con.prepareStatement(sql);
               prepare.setString(1, email);
               prepare.setString(2, cin); // add CIN parameter

               result = prepare.executeQuery();

               if (result.next()) {
                  String password = result.getString("password");
                  String subject = "Password Recovery";
                  String message = "Your password is " + password;

                  // Create passwordField and showPasswordCheckBox
                  PasswordField passwordField = new PasswordField();
                  passwordField.setText(password);
                  passwordField.setEditable(false);

                  CheckBox showPasswordCheckBox = new CheckBox("Show Password");
                  showPasswordCheckBox.setOnAction(event -> {
                     if (showPasswordCheckBox.isSelected()) {
                        passwordField.setPromptText(passwordField.getText());
                        passwordField.setText("");
                        passwordField.setEditable(true);
                     } else {
                        passwordField.setText(passwordField.getPromptText());
                        passwordField.setEditable(false);
                     }
                  });

                  // Create vBox
                  VBox vBox = new VBox(passwordField, showPasswordCheckBox);
                  vBox.setAlignment(Pos.CENTER);
                  vBox.setSpacing(10);

                  // Show information message with VBox
                  alert = new Alert(Alert.AlertType.INFORMATION);
                  alert.setTitle("Information message");
                  alert.setHeaderText(null);
                  alert.getDialogPane().setContent(vBox);
                  alert.showAndWait();

               } else {
                  alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Error message");
                  alert.setHeaderText(null);
                  alert.setContentText("Email address and/or CIN not found");
                  alert.showAndWait();
               }
            }
         }
      } catch (Exception e) {
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
   public void sendEmailConfirmation(String to) {
      String from = "houssem.zorgui@esprit.tn";
      String password = "223JMT7265";
      String host="smtp.gmail.com";

      // Set mail server properties
      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Create a mail session with the specified properties
      Session session = Session.getInstance(props, new Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
         }
      });

      // Generate confirmation code
      String confirmationCode = UUID.randomUUID().toString();

      // Save confirmation code to database
      String sql = "UPDATE user SET confirmation_code = ? WHERE email = ?";
      database database = new database();
      database.connect();
      try {
         PreparedStatement prepare = database.con.prepareStatement(sql);
         prepare.setString(1, confirmationCode);
         prepare.setString(2, to);
         prepare.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Create email content
      String subject = "Confirm Your Account";
      String body = "Please copy code below to confirm your account:\n\n"
              + " Confirmation-Code ====> " + confirmationCode;

      // Create a new message
      Message message = new MimeMessage(session);
      try {
         message.setFrom(new InternetAddress(from));
         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
         message.setSubject(subject);
         message.setText(body);

         // Send the message
         Transport.send(message);

      } catch (MessagingException e) {
         e.printStackTrace();
      }
   }
   public void verificationCode(){

      verifyButton.setOnAction(event -> {
         String email = emailcodeTextField.getText();

         String confirmationCode = confirmationcodeTextField.getText();
         VerificationConfirmationCode(email, confirmationCode);


      });
   }
   public void VerificationConfirmationCode(String email, String confirmationCode) {
      // Check confirmation code against database
      String sql = "SELECT * FROM user WHERE email = ? AND confirmation_code = ?";
      database database = new database();
      database.connect();
      try {
         PreparedStatement prepare = database.con.prepareStatement(sql);
         prepare.setString(1, email);
         prepare.setString(2, confirmationCode);
         ResultSet result = prepare.executeQuery();

         // If confirmation code is valid, update user status to active
         if (result.next()) {
            sql = "UPDATE user SET isVerified = ? WHERE email = ?";
            prepare = database.con.prepareStatement(sql);
            prepare.setString(1, "1");
            prepare.setString(2, email);
            prepare.executeUpdate();

            // Show confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information message");
            alert.setHeaderText(null);
            alert.setContentText("Your account has been successfully verified.");
            alert.showAndWait();
            forget_form1.setVisible(false);
            login_form.setVisible(true);
            signup_form.setVisible(false);
            emailTextField.setText("");
            passwordPasswordField2.setText("");
         } else {
            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid confirmation code. Please check your email and try again.");
            alert.showAndWait();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }


   //  public void LoginWithGithub() {
   // String clientId = "YOUR_CLIENT_ID";
   //  String clientSecret = "YOUR_CLIENT_SECRET";

   // Initialize the Github OAuth client
   // OAuth20Service oauthService = new ServiceBuilder(clientId)
   // .apiSecret(clientSecret)
   // .defaultScope("user")
   //      .callback("http://localhost:8080/callback")
   //    .build(GitHubApi.instance());

   // Obtain the authorization URL and open it in the user's browser
   // String authorizationUrl = oauthService.getAuthorizationUrl();
   // HostServices hostServices = getHostServices();
   // hostServices.showDocument(authorizationUrl);

   // Wait for the user to authorize the application
   // The user will be redirected to the callback URL with a code parameter
   // String code = null;
   //  while (code == null) {
   //  try {
   //    Thread.sleep(1000);
   //  } catch (InterruptedException e) {
   //    e.printStackTrace();
   // }

   // String url = webEngine.getLocation();
   // if (url.startsWith("http://localhost:8080/callback")) {
   //    code = extractCodeFromUrl(url);
   // }
   // }

   // Exchange the authorization code for an access token
   //  OAuth2AccessToken accessToken = oauthService.getAccessToken(code);

   // Use the Github API to get the user's profile information
   //  GitHub gitHub = new GitHubBuilder().withOAuthToken(accessToken.getAccessToken()).build();
   //   GHMyself myself;
   //   try {
   //     myself = gitHub.getMyself();
   //  } catch (IOException e) {
   //    e.printStackTrace();
   //    return;
   //  }

   // Check if the user exists in the database
   // User user = getUserByEmail(myself.getEmail());
   //  if (user == null) {
   // Create a new user record
   //    user = new User();
   //    user.setEmail(myself.getEmail());
   //    user.setName(myself.getName());
   //    user.setAvatarUrl(myself.getAvatarUrl());
   //    saveUser(user);
   // }

   // Log the user in
   // logInUser(user);
   // }


   // public void sendSms(String recipientNumber, String message) {
   //    // Twilio account details
   //    String accountSid = "your_account_sid_here";
   //    String authToken = "your_auth_token_here";

   //   // create a Twilio REST client
   //   Twilio.init(accountSid, authToken);

   // send the SMS message
   //  Message smsMessage = Message.creator(
   //     new PhoneNumber(recipientNumber),
   //                  new PhoneNumber("+1 123-456-7890"), // Twilio phone number
   //   message)
   // .create();

   // check if the SMS was successfully delivered
   //         smsMessage.getStatus() == Message.Status.UNDELIVERED) {
   //    System.out.println("Failed to send SMS: " + smsMessage.getErrorMessage());
   // } else {
   //  System.out.println("SMS sent successfully to " + recipientNumber);
   //}
   // }







}