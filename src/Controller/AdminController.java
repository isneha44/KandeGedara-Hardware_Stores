package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    public Button btnCustomerManagement;
    public Button btnItemManagement;
    public AnchorPane AdminContext;
    public Button btnOrderManagement;
    public Button btnInstallment;
    public Button btnReports;
    public Button btnSupplierManagement;
    public Button btnDeliveryManagement;
    public Button btnEmployeeManagement;
    public Button btnVehicle;


    public void ItemManagementOnAction(ActionEvent actionEvent) throws IOException {
        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"));
        AdminContext.getChildren().add(parent);
    }



    public void CustomerManagementOnAction(ActionEvent actionEvent) throws IOException {
        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"));
        AdminContext.getChildren().add(parent);


    }


    public void OrderManagementOnAction(ActionEvent actionEvent) throws IOException {

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderPurchaseController.fxml"));
        AdminContext.getChildren().add(parent);


    }

    public void InstallmenOnAction(ActionEvent actionEvent) throws IOException {

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/InstallmentPaymentForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void ReportsManagementOnAction(ActionEvent actionEvent) {
    }

    public void SupplierManagementOnAction(ActionEvent actionEvent) throws IOException {

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SupplierForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void DeliveryManagementOnAction(ActionEvent actionEvent) throws IOException {
        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DeliveryForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void EmployeeManagementOnAction(ActionEvent actionEvent) throws IOException {

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/EmployeeForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void VehicleManagementOnAction(ActionEvent actionEvent) throws IOException {

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/VehicleForm.fxml"));
        AdminContext.getChildren().add(parent);
    }
}

