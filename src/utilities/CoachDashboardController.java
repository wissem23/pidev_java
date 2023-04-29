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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.database;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CoachDashboardController implements Initializable {

    @FXML
    private PasswordField ConfirmerPasswordTextField;
    @FXML
    private PasswordField newpasswordtextFielduser;
    @FXML
    private TextField fullNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField cinTextField;
    @FXML
    private ComboBox<?> specialiteList;


    @FXML
    private TextField descriptionTextField;

    @FXML
    private Button editProfile;
    @FXML
    private Button btnProfile;

    @FXML
    private AnchorPane paneEditProfile;
    @FXML
    private AnchorPane paneChangerPassword;
    @FXML
    private AnchorPane profilepane;
    private FileChooser fileChooser;

    @FXML
    private ImageView imageView;

    private Connection con;
    private PreparedStatement prepare;
    private ResultSet result;
    private Alert alert;
    private Statement statement;
    @FXML
    private Button changerPassBtn;
    @FXML
    private Label titleusername;

    @FXML
    private Button btnSignout;
    @FXML
    private Label addressLabel;

    @FXML
    private Label specialiteLabel;
    private File selectedFile;


    @FXML
    private Label cinlabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ImageView cvView;

    @FXML
    private Label emaillabel;
    @FXML
    private ImageView imageViewUser;
    @FXML
    private ImageView imageViewUser2;
    @FXML
    private Label fullnamelabel;
    private String[] specialiteListt={"Coaching en relation","Coaching en parentalité","Coaching en carrière","Coaching en carrière"};

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


    }
    public void getUserData(){
        fullnamelabel.setText(UserData.fullname);
        emaillabel.setText(UserData.email);
        addressLabel.setText(UserData.address);
        cinlabel.setText(UserData.cin);
        descriptionLabel.setText(UserData.description);
        specialiteLabel.setText(UserData.specialite);



    }

    public void getUserDataa(){
        fullNameTextField.setText(UserData.fullname);
        emailTextField.setText(UserData.email);
        addressTextField.setText(UserData.address);
        cinTextField.setText(UserData.cin);
        descriptionTextField.setText(UserData.description);


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


    public void switchForm (ActionEvent e) {
        if (e.getSource() == editProfile) {
            paneEditProfile.setVisible(true);
            paneChangerPassword.setVisible(false);
            profilepane.setVisible(false);
        } else if (e.getSource() == changerPassBtn) {
            paneEditProfile.setVisible(false);
            paneChangerPassword.setVisible(true);
            profilepane.setVisible(false);

        }else if (e.getSource() == btnProfile) {
            paneEditProfile.setVisible(false);
            paneChangerPassword.setVisible(false);
            profilepane.setVisible(true);
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

    public void clearPassword(){
        newpasswordtextFielduser.setText("");
        ConfirmerPasswordTextField.setText("");


    }

    public void userspecialiteList(){
        List<String> spList=new ArrayList<>();
        for (String data :specialiteListt){
            spList.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(spList);
        specialiteList.setItems(listData);
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


    public void clear(){

        fullNameTextField.setText("");
        addressTextField.setText("");
        cinTextField.setText("");
        emailTextField.setText("");
        descriptionTextField.setText("");


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
                    String updateData = "UPDATE user SET description=?, fullname=?, email=?, cin=?, address=?,specialite=? WHERE email=?";
                    prepare = database.con.prepareStatement(updateData);
                    prepare.setString(1, descriptionTextField.getText());
                    prepare.setString(2, fullNameTextField.getText());
                    prepare.setString(3, emailTextField.getText());
                    prepare.setString(4, cinTextField.getText());
                    prepare.setString(5, addressTextField.getText());
                    prepare.setString(6, (String)specialiteList.getSelectionModel().getSelectedItem());

                    prepare.setString(7, UserData.email);
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


    public void initialize(URL url, ResourceBundle resourceBundle) {


        userspecialiteList();
        getUserData();
        account();
        getUserDataa();
    }
}
