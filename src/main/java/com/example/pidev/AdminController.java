package com.example.pidev;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.*;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable {



    @FXML
    private AnchorPane line_chart_page;


    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private Button line_chart_button;
    @FXML
    private AreaChart<String, Integer> areaChart;

    @FXML
    private Button area_chart_button;

    @FXML
    private AnchorPane area_chart_page;

    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private Button bar_chart_button;

    @FXML
    private AnchorPane bar_chart_page;

    @FXML
    private TextField address;

    @FXML
    private Button btnProfile;

    @FXML
    private Label totalSimpleUser;

    @FXML
    private Label totalIsVeri;
    @FXML
    private Button btnOverview;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnUsers;

    @FXML
    private Label totalUsers;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private TextField cin;

    @FXML
    private Label totalCoach;
    @FXML
    private TableColumn<UserData, String> cin_col;

    @FXML
    private Button clearBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextField email;
    @FXML
    private TextField id;

    @FXML
    private TableColumn<UserData, String> email_col;

    @FXML
    private TextField fullName;

    @FXML
    private TableColumn<UserData, String> fullName_col;
    @FXML
    private TableColumn<UserData, String> id_col;

    @FXML
    private ComboBox<?> isverified;

    @FXML
    private TableColumn<UserData, String> isverified_col;

    @FXML
    private TableColumn<UserData, String> address_col;
    @FXML
    private Pane pnlProfile;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlUsers;

    @FXML
    private Button qddBtn;

    @FXML
    private ComboBox<?> roles;

    @FXML
    private TableColumn<UserData, String> roles_col;

    @FXML
    private TableView<UserData> tableView;

    @FXML
    private Button updateBtn;
    private Connection con;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    private Statement statement;
    @FXML
    private Label titleusername;

    @FXML
    private Pane pnlDataAnalysis;

    @FXML
    private Button btnDataAnalysis;




    // public void  account(){

     //   titleusername.setText(UserData.getFullname);


   // }

    public void userAddBtn(){


        database database=new database();
        database.connect();
        try {

            if (fullName.getText().isEmpty()||email.getText().isEmpty()||address.getText().isEmpty()||
                    cin.getText().isEmpty()||roles.getSelectionModel().getSelectedItem()==null||
                    isverified.getSelectionModel().getSelectedItem()==null){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank field");
                alert.showAndWait();


            }else {
                String checkData="SELECT fullname FROM user WHERE fullname='"+fullName.getText()+"'";
                prepare =con.prepareStatement(checkData);
                result=prepare.executeQuery();

                if (result.next()) {

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("this FullName is"+fullName.getText()+" is already exist");
                    alert.showAndWait();
                }else {
                    String insertData="INSERT INTO user (fullname,email,cin,address,roles,isVerified,date)"+"VALUES(?,?,?,?,?,?,?)";

                    prepare = con.prepareStatement(insertData);
                    prepare.setString(1, fullName.getText());
                    prepare.setString(2, email.getText());
                    prepare.setString(3, cin.getText());
                    prepare.setString(4, address.getText());
                    prepare.setString(5, (String)roles.getSelectionModel().getSelectedItem());
                    prepare.setString(6,(String) isverified.getSelectionModel().getSelectedItem());

                    java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
                    prepare.setDate(7, java.sql.Date.valueOf(String.valueOf(currentDate)));

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully added ! ");
                    alert.showAndWait();


                    prepare.executeUpdate();

                    UserShowData();
                    userClearBtn();

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void userUpdateBtn(){
        database database=new database();
        database.connect();
        try {

            if (fullName.getText().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank field");
                alert.showAndWait();


            }else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update this id = "+id.getText()+"?");
                Optional<ButtonType> option =alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){

                    String updateData="UPDATE user SET "+"fullname = '"+fullName.getText()+
                            "' ,email = '"+email.getText()+
                            "' ,cin = '"+cin.getText()+
                            "' ,address = '"+address.getText()+
                            "' ,roles = '"+roles.getSelectionModel().getSelectedItem()+
                            "' ,isVerified = '"+isverified.getSelectionModel().getSelectedItem()+
                            "' WHERE id = '"+id.getText()+"'"

                            ;
                    prepare =con.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully updated ! ");
                    alert.showAndWait();
                    UserShowData();
                    userClearBtn();


                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled !");
                    alert.showAndWait();
                }



            }




        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void userDeleteBtn(){

        database database=new database();
        database.connect();
        try {

            if (fullName.getText().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank field");
                alert.showAndWait();


            }else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete fullname = "+fullName.getText()+"?");
                Optional<ButtonType> option =alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){

                    String deleteData="DELETE FROM user WHERE fullname = '"+fullName.getText()+"'";
                    prepare =con.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("information message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted ! ");
                    alert.showAndWait();
                    UserShowData();
                    userClearBtn();


                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled !");
                    alert.showAndWait();
                }



            }




        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public  void userClearBtn(){
        id.setText("");
        fullName.setText("");
        address.setText("");
        cin.setText("");
        email.setText("");
        roles.getSelectionModel().clearSelection();
        isverified.getSelectionModel().clearSelection();



    }
    private String[] rolesList={"ROLE_USER","ROLE_COACH","ROLE_ADMIN"};

    public void userRolesList(){
        List<String>uList=new ArrayList<>();
        for (String data :rolesList){
            uList.add(data);
        }
        ObservableList listData =FXCollections.observableArrayList(uList);
        roles.setItems(listData);
    }
    private String[] isverifiedList={"1","2"};
    public void userIsverifiedList(){
        List<String>isvList=new ArrayList<>();
        for (String data :isverifiedList){
            isvList.add(data);
        }
        ObservableList listData =FXCollections.observableArrayList(isvList);
        isverified.setItems(listData);
    }

    public AdminController() {

        con = database.createorgetInstance().getCon();

    }
    public ObservableList<UserData> userListData()  {

        ObservableList<UserData> listData= FXCollections.observableArrayList();
        String selectData ="SELECT * FROM user";
        database database=new database();

        database.connect();
        try {
            prepare = con.prepareStatement(selectData);
            result = prepare.executeQuery();
            UserData sData;

            while(result.next()){
                sData=new UserData(result.getInt("id"),result.getString("fullname"),result.getString("email"),
                        result.getString("cin"),result.getString("address"),
                        result.getString("roles"),result.getInt("isverified"));
                listData.add(sData);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return listData;
    }



    private ObservableList<UserData> UserData;
    public void UserShowData()  {

        UserData = userListData();
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullName_col.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        cin_col.setCellValueFactory(new PropertyValueFactory<>("cin"));
        roles_col.setCellValueFactory(new PropertyValueFactory<>("roles"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        isverified_col.setCellValueFactory(new PropertyValueFactory<>("isverified"));

        tableView.setItems(UserData);


    }

    public void userSelectData(){
        UserData sData =tableView.getSelectionModel().getSelectedItem();
        int num =tableView.getSelectionModel().getSelectedIndex();
        if ((num-1)<-1)return;
            id.setText(String.valueOf(sData.getId()));
            fullName.setText(String.valueOf(sData.getFullname()));
            email.setText(String.valueOf(sData.getEmail()));
            address.setText(String.valueOf(sData.getAddress()));
            cin.setText(String.valueOf(sData.getCin()));




    }


    public void signoutBtnOnAction()


    {
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userRolesList();
        userIsverifiedList();
        UserShowData();


    }


    public void switchForm (ActionEvent e){
        if(e.getSource()==btnProfile){
            pnlProfile.setVisible(true);
            pnlUsers.setVisible(false);
            pnlDataAnalysis.setVisible(false);
            pnlOverview.setVisible(false);

        }else if (e.getSource()==btnUsers){
            pnlProfile.setVisible(false);
            pnlUsers.setVisible(true);
            pnlDataAnalysis.setVisible(false);
            pnlOverview.setVisible(false);



        }//else if (e.getSource()==btnDataAnalysis){
           // pnlDataAnalysis.setVisible(true);
            //pnlProfile.setVisible(false);
            //pnlUsers.setVisible(false);
            //pnlOverview.setVisible(false);


        //}
        //else if (e.getSource()==btnOverview){
        //  pnlDataAnalysis.setVisible(false);
        //  pnlProfile.setVisible(false);
        //  pnlUsers.setVisible(false);
        //  pnlOverview.setVisible(true);
        //  totalUsers();
        //  totalCoachs();
        //  totalIsVerified();
        //  totalSimpleUsers();

        //}

    }


    public void navigationChartButton(){

        if(line_chart_button.isFocused()){

            line_chart_page.setVisible(true);
            bar_chart_page.setVisible(false);
            area_chart_page.setVisible(false);

        }else if(bar_chart_button.isFocused()){

            line_chart_page.setVisible(false);
            bar_chart_page.setVisible(true);
            area_chart_page.setVisible(false);

        }else if(area_chart_button.isFocused()){

            line_chart_page.setVisible(false);
            bar_chart_page.setVisible(false);
            area_chart_page.setVisible(true);

        }

    }


    public void totalUsers(){

        database database=new database();
        database.connect();


        try{
            String sql = "SELECT count(fullname) FROM user WHERE roles != ''";
            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){

                String totalUser = result.getString("count(fullname)");

                totalUsers.setText(totalUser);

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }



    public void totalCoachs(){
        database database=new database();
        database.connect();


        try{
            String sql = "SELECT count(fullname) FROM user WHERE roles = 'ROLE_COACH'";

            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){

                String totalCoachs = result.getString("count(fullname)");

                totalCoach.setText(totalCoachs);

            }

        }catch(Exception e){}

    }
    public void totalSimpleUsers(){
        database database=new database();
        database.connect();


        try{
            String sql = "SELECT count(fullname) FROM user WHERE roles = 'ROLE_USER'";

            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){

                String totalSimple = result.getString("count(fullname)");

                totalSimpleUser.setText(totalSimple);

            }

        }catch(Exception e){}

    }
    public void totalIsVerified(){
        database database=new database();
        database.connect();


        try{
            String sql = "SELECT count(fullname) FROM user WHERE isVerified = '2'";

            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){

                String totalIsverifiedd= result.getString("count(fullname)");

                totalIsVeri.setText(totalIsverifiedd);

            }

        }catch(Exception e){}

    }

    public void showChart(){
//        THAT IS FOR JULY 28 DATE THAT THE STUDENT WAS CERTIFIED ENROLLED, GRADUATED, INACTIVE
        database database=new database();
        database.connect();
//        ASC MEANING, ASCENDING ORDER
        String sql = "SELECT count(fullname),date FROM user WHERE roles != '' ORDER BY date asc";

        XYChart.Series<String, Integer> chart = new XYChart.Series<>();

        try{

            prepare = con.prepareStatement(sql);
            result = prepare.executeQuery();

            while(result.next()){

                Integer count = Integer.parseInt(result.getString("count(fullname)"));

                chart.getData().add(new XYChart.Data<>(result.getString("date"), count));
            }
            if(line_chart_page.isVisible()){

                lineChart.getData().add(chart);

            }else if(bar_chart_page.isVisible()){

                barChart.getData().add(chart);

            }else if(area_chart_page.isVisible()){

                areaChart.getData().add(chart);

            }
        }catch(Exception e){}

    }
}

