package utilities;

import entities.UserData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.objects.NativeDebug.getClass;

public class UserController implements Initializable {

        @FXML
        private TextField addressTextField;
        @FXML
        private TextField emailTextField;
        @FXML
        private TextField cinTextField;
        @FXML
        private TextField fullNameTextField;
        @FXML
        private Label addressLabel;
        @FXML
        private Label cinlabel;
        @FXML
        private Label emaillabel;
        @FXML
        private Label fullnamelabel;
        @FXML
        private PasswordField passwordtextFielduser;
        @FXML
        private Button changerPassword;

        @FXML
        private Button verifyBtn;

        @FXML
        private AnchorPane panePassword;
        @FXML
        private Button btnDataAnalysis;
        @FXML
        private Button btnMenus;
        @FXML
        private Button btnOverview;
        @FXML
        private Button btnProfile;
        @FXML
        private Button btnSettings;
        @FXML
        private Button btnSignout;
        @FXML
        private Button btnUsers;

        @FXML
        private BorderPane login_form;
        @FXML
        private BorderPane forget_form1;
        @FXML
        private Label descriptionLabel;
        @FXML
        private Button changerRolesBtn;
        @FXML
        private ImageView imageView;
        @FXML
        private ComboBox<?> specialiteList;
        private String[] specialiteListt={"Coaching en relation","Coaching en parentalité","Coaching en carrière","Coaching en carrière"};
        @FXML
        private Button clearBtn;
        @FXML
        private TextField descriptionTextArea;
        @FXML
        private Button editProfile;
        @FXML
        private AnchorPane edit_form;
        @FXML
        private AnchorPane paneChangerRoles;
        @FXML
        private AnchorPane paneProfile;
        @FXML
        private Pane pnlDataAnalysis;
        @FXML
        private Pane pnlOverview;
        @FXML
        private Pane pnlProfile;
        @FXML
        private Pane pnlUsers;
        @FXML
        private PasswordField ConfirmerPasswordTextField;
        private FileChooser fileChooser;
        @FXML
        private ImageView imageViewUser;
        @FXML
        private ImageView imageViewUser2;
        @FXML
        private BorderPane signup_form;

        @FXML
        private ComboBox<?> roles;
        @FXML
        private TableColumn<?, ?> roles_col;
        @FXML
        private Label titleusername;
        @FXML
        private PasswordField currentPassword;
        @FXML
        private ImageView cvView;
        @FXML
        private Button updateBtn;
        @FXML
        private Button saveBtn;
        @FXML
        private PasswordField newpasswordtextFielduser;
        private Connection con;
        private PreparedStatement prepare;
        private ResultSet result;
        private Alert alert;
        private Statement statement;
        private Button btnChangeImage;
        private File selectedFile;

        @FXML
        private AnchorPane verifiedPane;
        public void userspecialiteList(){
                List<String> spList=new ArrayList<>();
                for (String data :specialiteListt){
                        spList.add(data);
                }
                ObservableList listData = FXCollections.observableArrayList(spList);
                specialiteList.setItems(listData);
        }
        public void account(){

                titleusername.setText(UserData.email);
                String imageUrl = UserData.image;
                if (imageUrl != null && !imageUrl.isEmpty()) {
                        Image image = new Image(imageUrl);
                        imageViewUser.setImage(image);
                        imageView.setImage(image);
                        imageViewUser2.setImage(image);

                } else {
                        Image defaultImage = new Image("file:/Users/macbookpro/Desktop/user/src/images/téléchargement_cc-removebg-previewccccc.png");
                        imageViewUser.setImage(defaultImage);
                        imageView.setImage(defaultImage);
                        imageViewUser2.setImage(defaultImage);

                }


                if (UserData.isverified.equals((0))){
                        verifiedPane.setVisible(true);
                }else {
                        verifiedPane.setVisible(false);

                }



        }
        public void getUserData(){
                fullNameTextField.setText(UserData.fullname);
                emailTextField.setText(UserData.email);
                addressTextField.setText(UserData.address);
                cinTextField.setText(UserData.cin);
                descriptionTextArea.setText(UserData.description);

        }
        public void verify() throws IOException {
                verifyBtn.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                Parent root = loader.load();
                HelloController otherController = loader.getController();
                otherController.forget_form1.setVisible(true);
                otherController.login_form.setVisible(false);


                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
        }


        public void getUserDataa() {
                fullnamelabel.setText(UserData.fullname);
                emaillabel.setText(UserData.email);
                addressLabel.setText(UserData.address);
                cinlabel.setText(UserData.cin);
                descriptionLabel.setText(UserData.description);

                if (UserData.image != null && !UserData.image.isEmpty()) {
                        File file = new File(UserData.image);

                        if (file.exists()) {
                                Image image = new Image(file.toURI().toString());
                                imageView.setImage(image);
                                imageViewUser.setImage(image);
                                imageViewUser2.setImage(image);
                        } else {
                                System.err.println("File not found: " + UserData.image);
                        }
                } else {
                        System.err.println("Image path not set for user: " + UserData.email);
                }
        }

        public void userUpdateBtn(){
                database database=new database();
                database.connect();
                try {

                        if (fullNameTextField.getText().isEmpty()){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText(null);
                                alert.setContentText("Please enter your full name.");
                                alert.showAndWait();
                                return;
                        } else if (database.con == null) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText(null);
                                alert.setContentText("Could not establish a database connection.");
                                alert.showAndWait();
                                return;
                        } else {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation");
                                alert.setHeaderText(null);
                                alert.setContentText("Are you sure you want to update "+fullNameTextField.getText()+"?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK){
                                        String updateData = "UPDATE user SET description=?, fullname=?, email=?, cin=?, address=? WHERE email=?";
                                        prepare = database.con.prepareStatement(updateData);
                                        prepare.setString(1, descriptionTextArea.getText());
                                        prepare.setString(2, fullNameTextField.getText());
                                        prepare.setString(3, emailTextField.getText());
                                        prepare.setString(4, cinTextField.getText());
                                        prepare.setString(5, addressTextField.getText());
                                        prepare.setString(6, UserData.email);
                                        int rowsAffected = prepare.executeUpdate();
                                        if (rowsAffected > 0){
                                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                                alert2.setTitle("Information");
                                                alert2.setHeaderText(null);
                                                alert2.setContentText("User data updated successfully!");
                                                alert2.showAndWait();
                                        } else {
                                                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                                                alert2.setTitle("Error");
                                                alert2.setHeaderText(null);
                                                alert2.setContentText("Could not update user data.");
                                                alert2.showAndWait();
                                        }
                                } else {
                                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                        alert2.setTitle("Information");
                                        alert2.setHeaderText(null);
                                        alert2.setContentText("Update cancelled.");
                                        alert2.showAndWait();
                                }
                        }




                }catch (Exception e){
                        e.printStackTrace();
                }

        }
        @FXML
        private void updateUserPassword(ActionEvent event) {
                String newPasswordStr = newpasswordtextFielduser.getText();
                String confirmPasswordStr = ConfirmerPasswordTextField.getText();

                // check if fields are not empty
                if ( newPasswordStr.isEmpty() || confirmPasswordStr.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill all fields.");
                        alert.showAndWait();
                        return;
                }

                // check if new password and confirm password match
                if (!newPasswordStr.equals(confirmPasswordStr)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("New password and confirm password do not match.");
                        alert.showAndWait();
                        return;
                }

                // check if current password is correct
                //if (!currentPasswordStr.equals(UserData.password)) {
                //        Alert alert = new Alert(Alert.AlertType.ERROR);
                //        alert.setTitle("Error");
                //       alert.setHeaderText(null);
                //        alert.setContentText("Current password is incorrect.");
                //       alert.showAndWait();
                //       return;
                //}

                // update password in database
                try {
                        database database = new database();
                        database.connect();
                        String updatePassword = "UPDATE user SET password=? WHERE email=?";
                        prepare = database.con.prepareStatement(updatePassword);
                        prepare.setString(1, newPasswordStr);
                        prepare.setString(2, UserData.email);
                        int rowsAffected = prepare.executeUpdate();
                        if (rowsAffected > 0){
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information");
                                alert.setHeaderText(null);
                                alert.setContentText("Password updated successfully!");
                                alert.showAndWait();
                                currentPassword.clear();
                                newpasswordtextFielduser.clear();
                                ConfirmerPasswordTextField.clear();
                        } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText(null);
                                alert.setContentText("Could not update password.");
                                alert.showAndWait();
                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Could not update password.");
                        alert.showAndWait();
                }
        }
        public void clear(){

                fullNameTextField.setText("");
                addressTextField.setText("");
                cinTextField.setText("");
                emailTextField.setText("");


        }
        public void clearRole(){

                specialiteList.setItems(FXCollections.observableArrayList());
                cvView.setImage(null);
        }
        public void clearPassword(){
                newpasswordtextFielduser.setText("");
                ConfirmerPasswordTextField.setText("");


        }
        public void switchForm (ActionEvent e) {
                if (e.getSource() == btnProfile) {
                        paneProfile.setVisible(false);
                        paneChangerRoles.setVisible(false);
                        edit_form.setVisible(true);
                        panePassword.setVisible(false);


                } else if (e.getSource() == changerRolesBtn) {
                        paneProfile.setVisible(false);
                        paneChangerRoles.setVisible(true);
                        edit_form.setVisible(false);
                        panePassword.setVisible(false);


                } else if (e.getSource() == editProfile) {
                        paneProfile.setVisible(true);
                        paneChangerRoles.setVisible(false);
                        edit_form.setVisible(false);
                        panePassword.setVisible(false);


                } else if (e.getSource() == changerPassword) {

                        paneProfile.setVisible(false);
                        paneChangerRoles.setVisible(false);
                        edit_form.setVisible(false);
                        panePassword.setVisible(true);


                }
        }
        public void signoutBtnOnAction() {
                btnSignout.getScene().getWindow().hide();
                try {
                        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

                        Scene scene = new Scene(root);

                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setScene(scene);
                        stage.show();


                }catch(Exception ex){
                        ex.printStackTrace();
                }

        }

        public void updateProfileImage(ActionEvent event) {
                database database=new database();

                database.connect();

                fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
                fileChooser.setTitle("Select Profile Image");

                selectedFile = fileChooser.showOpenDialog(null);

                if (selectedFile != null) {

                        try (FileInputStream fis = new FileInputStream(selectedFile)) {


                                String updateImageQuery = "UPDATE user SET image=? WHERE email=?";
                                prepare = database.con.prepareStatement(updateImageQuery);
                                prepare.setString(1, selectedFile.toURI().toString());
                                prepare.setString(2, UserData.email);
                                int rowsAffected = prepare.executeUpdate();
                                if (rowsAffected > 0) {
                                        Image image = new Image(selectedFile.toURI().toString());
                                        imageView.setImage(image);
                                        imageViewUser.setImage(image);
                                        imageViewUser2.setImage(image);


                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Success");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Profile image updated successfully");
                                        alert.showAndWait();
                                }  else {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Failed to update profile image");
                                        alert.showAndWait();
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                        }

                }
        }


        public void updateProfileCv(ActionEvent event) {
                database database=new database();

                database.connect();

                fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg","*.jpeg", "*.gif","*.pdf"));
                fileChooser.setTitle("Select your CV");

                selectedFile = fileChooser.showOpenDialog(null);

                if (selectedFile != null) {

                        try (FileInputStream fis = new FileInputStream(selectedFile)) {


                                String updateImageQuerycv = "UPDATE user SET cv=? WHERE email=?";
                                prepare = database.con.prepareStatement(updateImageQuerycv);
                                prepare.setString(1, selectedFile.toURI().toString());
                                prepare.setString(2, UserData.email);
                                int rowsAffected = prepare.executeUpdate();
                                if (rowsAffected > 0) {
                                        Image image = new Image(selectedFile.toURI().toString());
                                        cvView.setImage(image);



                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Success");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Your CV updated successfully");
                                        alert.showAndWait();
                                }  else {
                                        Alert alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Failed to update your CV");
                                        alert.showAndWait();
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                        }

                }
        }



        public void updateRoleUser(){
                database database = new database();
                database.connect();
                Alert alert;
                try {
                        // Vérifiez si une spécialité a été sélectionnée
                        if (specialiteList.getSelectionModel().isEmpty()){
                                alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error message");
                                alert.setHeaderText(null);
                                alert.setContentText("Please choose your speciality");
                                alert.showAndWait();
                        }
                        // Vérifiez si un CV a été téléchargé
                        else if (cvView.getImage() == null) {
                                alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error message");
                                alert.setHeaderText(null);
                                alert.setContentText("Please upload your cv");
                                alert.showAndWait();
                        } else {
                                // Affichez une boîte de dialogue de confirmation avant la mise à jour du rôle de l'utilisateur
                                alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation message");
                                alert.setHeaderText(null);
                                alert.setContentText("Are you sure you want to update your profile and become a coach?");
                                Optional<ButtonType> option =alert.showAndWait();
                                if (option.get().equals(ButtonType.OK)){
                                        // Mettre à jour le rôle de l'utilisateur dans la base de données
                                        String updateData="UPDATE user SET "+"cv = '"
                                                +cvView.getImage()+
                                                "' ,specialite = '"+specialiteList.getSelectionModel().getSelectedItem()+
                                                "' WHERE email = '"+UserData.email+"'";
                                        prepare = database.con.prepareStatement(updateData);
                                        prepare.executeUpdate();
                                        alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Information message");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Profile successfully updated!");
                                        alert.showAndWait();
                                } else {
                                        alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Information message");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Cancelled!");
                                        alert.showAndWait();
                                }
                        }
                } catch (Exception e){
                        e.printStackTrace();
                }
        }


        public void initialize(URL url, ResourceBundle resourceBundle) {

                userspecialiteList();
                account();
                getUserData();
                getUserDataa();



        }
}



