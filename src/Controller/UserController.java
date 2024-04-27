package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import util.Loader;

import java.io.IOException;

public class UserController implements Loader  {


    public AnchorPane UserContext;
    public Button Cus_Management;
    public Button btnI_Management;
    public Button btnVehicle;
    public Button btnEmployeeManagement;
    public Button btnDeliveryManagement;
    public Button btnInstallment;
    public Button btnOrderManagement;

    public void I_ManagementOnAction(ActionEvent actionEvent) throws IOException {

        loadUi("ItemForm");

    }

    public void Cus_ManagementOnAction(ActionEvent actionEvent) throws IOException {

       loadUi("CustomerForm");

    }

    @Override
    public void loadUi(String location) throws IOException {
        UserContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"));
        UserContext.getChildren().add(parent);

    }

    public void VehicleManagementOnAction(ActionEvent actionEvent) throws IOException {

        loadUi("VehicleForm");
    }

   // public void EmployeeManagementOnAction(ActionEvent actionEvent) throws IOException {

        //loadUi("EmployeeForm");
    //}

    public void DeliveryManagementOnAction(ActionEvent actionEvent) throws IOException {
        loadUi("DeliveryForm");
    }

    public void InstallmenOnAction(ActionEvent actionEvent) {
    }

    public void OrderManagementOnAction(ActionEvent actionEvent) throws IOException {

        loadUi("OrderPurchaseController");


    }
}
