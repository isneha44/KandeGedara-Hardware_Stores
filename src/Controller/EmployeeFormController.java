package Controller;

import Model.Customer;
import Model.Employee;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;
import util.UtilValidate;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class EmployeeFormController {
    public JFXTextField txtSalary;
    public JFXButton btnEmUp;
    public JFXButton btnEmDel;
    public JFXButton btnEmSearch;
    public TableColumn colEmSalary;
    public TableColumn colEmDob;
    public TableColumn colEmDesi;
    public TableColumn colEmAddress;
    public TableColumn colEmName;
    public TableColumn colEmId;
    public TableView <Employee> tblEmployee;
    public JFXButton btnNewCus;
    public JFXButton btnSaveEm;
    public JFXTextField txtEmDob;
    public JFXTextField txtEmAddr;
    public JFXTextField txtDesi;
    public TextField txtEmSearch;
    public JFXTextField txtEmName;
    public JFXTextField txtEmId;
    public AnchorPane AdminContext;



    public void initialize(){




        //btnSaveEm.setDisable(true);


        try {
            loadAllEmployee();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        colEmId.setCellValueFactory(new PropertyValueFactory("em_id"));
        colEmName.setCellValueFactory(new PropertyValueFactory("em_name"));
        colEmAddress.setCellValueFactory(new PropertyValueFactory("em_address"));
        colEmDesi.setCellValueFactory(new PropertyValueFactory("designation"));
        colEmDob.setCellValueFactory(new PropertyValueFactory("dob"));
        colEmSalary.setCellValueFactory(new PropertyValueFactory("salary"));

    }

    private void loadAllEmployee() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM Employee");
        ObservableList<Employee> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new Employee(
                            result.getString("em_id"),
                            result.getString("em_name"),
                            result.getString("em_address"),
                            result.getString("designation"),
                            result.getDate("dob"),
                            result.getDouble("salary")


                    )
            );
        }
        tblEmployee.setItems(obList);

    }
    private void Search() {


        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Employee WHERE em_id=?", txtEmSearch.getText());
            if (result.next()) {
                txtEmName.setText(result.getString(2));
                txtEmAddr.setText(result.getString(3));
                txtDesi.setText(result.getString(4));
                txtEmDob.setText(String.valueOf(result.getDate(5)));
                txtSalary.setText(String.valueOf(result.getString(6)));

                txtEmId.setText(txtEmSearch.getText());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void EnterKeyRelease(KeyEvent keyEvent) {




    }

    public void SaveEmOnAction(ActionEvent actionEvent) throws IOException {

        Employee em = new Employee(
                txtEmId.getText(),
                txtEmName.getText(),
                txtEmAddr.getText(),
                txtDesi.getText(),
                Date.valueOf(txtEmDob.getText()),
                Double.parseDouble(txtSalary.getText())

                );


        try {
            if (CrudUtil.execute("INSERT INTO Employee VALUES (?,?,?,?,?,?)",em.getEm_id(),em.getEm_name(),em.getEm_address(),em.getDesignation(),em.getDob(),em.getSalary())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/EmployeeForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void NewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void EmSearchOnAction(ActionEvent actionEvent) {

        Search();
    }

    public void EmDelOnAction(ActionEvent actionEvent) throws IOException {


        try{
            if (CrudUtil.execute("DELETE FROM Employee WHERE em_id=?",txtEmSearch.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/EmployeeForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void EmUpOnAction(ActionEvent actionEvent) throws IOException {


        Employee a = new Employee(
                txtEmId.getText(),txtEmName.getText(), txtEmAddr.getText(),txtDesi.getText(),
                Date.valueOf(txtEmDob.getText()),Double.parseDouble(txtSalary.getText()));

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE Employee  SET em_name=? , em_address=? , designation=?, dob=? ,salary=? WHERE em_id=?",a.getEm_name(),a.getEm_address(),a.getDesignation(),a.getDob(),a.getSalary(),a.getEm_id());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/EmployeeForm.fxml"));
        AdminContext.getChildren().add(parent);
    }
}
