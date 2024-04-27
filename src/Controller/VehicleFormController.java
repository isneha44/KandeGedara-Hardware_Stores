package Controller;

import Model.Item;
import Model.Vehicle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.CrudUtil;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import static java.sql.Time.valueOf;

public class VehicleFormController {
    public JFXButton btnUpVe;
    public JFXButton btnDelVe;
    public JFXButton btnSeaVe;
    public TableColumn colOut;
    public TableColumn colIn;
    public TableColumn colDate;
    public TableColumn colDriver;
    public TableColumn colVeNo;
    public TableView<Vehicle> tblVehicle;
    public JFXButton btnSaveVe;
    public JFXTextField txtOutTime;
    public JFXButton btnNewCus;
    public JFXTextField txtDate;
    public JFXTextField txtInTime;
    public TextField txtSearch;
    public JFXTextField txtDriverN;
    public JFXTextField txtVeNo;
    public AnchorPane AdminContext;


    public void initialize(){
       // btnSaveVe.setDisable(true);

        loadDateAndTime();


        try {
            loadAllVehicle();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        colVeNo.setCellValueFactory(new PropertyValueFactory("veNo"));
        colDriver.setCellValueFactory(new PropertyValueFactory("driverName"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colIn.setCellValueFactory(new PropertyValueFactory("inTime"));
        colOut.setCellValueFactory(new PropertyValueFactory("outTime"));

    }

    private void loadAllVehicle() throws SQLException, ClassNotFoundException {


        ResultSet result = CrudUtil.execute("SELECT * FROM Vehicle");
        ObservableList<Vehicle> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new Vehicle(
                            result.getString("veNo"),
                            result.getString("driverName"),
                            result.getDate("date"),
                            result.getTime("inTime"),
                            result.getString("outTime")


                    )
            );
        }
        tblVehicle.setItems(obList);
    }


    private void loadDateAndTime() {
        /*set Date*/
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        /*set Time*/
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            txtInTime.setText(currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" +
                    currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    private void Search (){

        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Vehicle WHERE veNo=?",txtSearch.getText());
            if (result.next()) {
                txtDriverN.setText(result.getString(2));
                txtDate.setText(String.valueOf (result.getDate(3)));
                txtInTime.setText(String.valueOf(result.getTime(4)));
                txtOutTime.setText(String.valueOf(result.getTime(5)));


                txtVeNo.setText(txtSearch.getText());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    public void SaveVehicleOnAction(ActionEvent actionEvent) throws IOException {


       Vehicle ve= new Vehicle(
                txtVeNo.getText(),
                txtDriverN.getText(),
               Date.valueOf(txtDate.getText()),
                Time.valueOf(txtInTime.getText()),
                txtOutTime.getText());


        try {
            if (CrudUtil.execute("INSERT INTO Vehicle VALUES (?,?,?,?,?)",ve.getVeNo(),ve.getDriverName(),ve.getDate(),ve.getInTime(),ve.getOutTime())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/VehicleForm.fxml"));
        AdminContext.getChildren().add(parent);


    }



    public void DeleteVehicleOnAction(ActionEvent actionEvent) throws IOException {

        try{
            if (CrudUtil.execute("DELETE FROM Vehicle WHERE veNo=?",txtSearch.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/VehicleForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void UpdateVehicleOnAction(ActionEvent actionEvent) throws IOException {


        Vehicle vehicle = new Vehicle(
                txtVeNo.getText(),
                txtDriverN.getText(),
                Date.valueOf(txtDate.getText()),
                Time.valueOf(txtInTime.getText()),
                txtOutTime.getText());

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE Vehicle SET driverName=?,date=?,inTime=?, outTime=?  WHERE veNo=?",vehicle.getDriverName(),vehicle.getDate(),vehicle.getInTime(),vehicle.getOutTime());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/VehicleForm.fxml"));
        AdminContext.getChildren().add(parent);




    }


    public void SearchVehiclerOnAction(ActionEvent actionEvent) {

        Search();
    }

    public void EnterKeyRelease(KeyEvent keyEvent) {
    }



    public void NewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchCustomer(ActionEvent actionEvent) {
    }
}
