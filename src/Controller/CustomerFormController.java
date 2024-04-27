package Controller;

import Model.Customer;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Pattern;

public class CustomerFormController {


    public JFXButton btnNewCus;
    public JFXButton btnSaveCustomer;
    public JFXTextField txtCusCont;
    public JFXTextField txtCusAddr;
    public JFXTextField txtNIC;
    public JFXTextField txtCusId;
    public JFXTextField txtCusName;


    public TableView <Customer>tblViewCustomer;
    public TableColumn colContact;
    public TableColumn colNic;
    public TableColumn colAddress;
    public TableColumn colName;
    public TableColumn colId;
    public AnchorPane AdminContext;
    public JFXButton btnDeleteCustomer;
    public JFXButton btnUpdateCustomer;
    public TextField txtSearch;

    public void initialize() {
        btnSaveCustomer.setDisable(true);

        try {
            loadAllCustomers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colNic.setCellValueFactory(new PropertyValueFactory("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact"));

        

      //  colCId.setCellValueFactory(new PropertyValueFactory("id"));
      //  colCname.setCellValueFactory(new PropertyValueFactory("name"));
      //  colCaddress.setCellValueFactory(new PropertyValueFactory("address"));
       // colNIC.setCellValueFactory(new PropertyValueFactory("nIC"));
    //    colContacts.setCellValueFactory(new PropertyValueFactory("contact"));
     //   colOption.setCellValueFactory(new PropertyValueFactory("btn"));

        /*loadAllCustomers();

        tblViewCustomer.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue !=null){
                        setData(newValue);
                    }

                });


        /*tblViewCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblViewCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblViewCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblViewCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblViewCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblViewCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("btn"));*/

    }

    /*private void loadAllCustomers() {
        ObservableList<Customer> obList = FXCollections.observableArrayList();
        for (Customer c : Database.customerTable
        ) {
            Button btn = new Button("Delete");
            Customer customer = new Customer (c.getId(), c.getName(), c.getAddress(), c.getNic(),c.getContact(),btn);
            obList.add(customer);

            btn.setOnAction((e) -> {




                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure?",
                            ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.get().equals(ButtonType.YES)){
                // delete
                Database.customerTable.remove(c);
                obList.remove(customer);
            }
        });

    }
       // tblViewCustomer.setItems(obList);
}



   /* private void setData(Customer customer) {
        btnSaveCustomer.setDisable(false);
        btnSaveCustomer.setText("Update Customer");
        txtCusId.setText(customer.getId());
        txtCusName.setText(customer.getName());
        txtCusAddr.setText(customer.getAddress());
        txtNIC.setText(customer.getNic());
        txtCusCont.setText(customer.getContact());
    }*/



    public void SaveCustomerOnAction(ActionEvent actionEvent) throws IOException {

        Customer customer = new Customer(
                txtCusId.getText(),
                txtCusName.getText(),
                txtCusAddr.getText(),
                txtNIC.getText(),
                txtCusCont.getText());


        try {
            if (CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?,?)",customer.getId(),customer.getName(),customer.getAddress(),customer.getNic(),customer.getContact())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

private void loadAllCustomers() throws SQLException, ClassNotFoundException {

    ResultSet result = CrudUtil.execute("SELECT * FROM Customer");
    ObservableList<Customer> obList = FXCollections.observableArrayList();

    while (result.next()){
        obList.add(
                new Customer(
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("address"),
                        result.getString("nic"),
                        result.getString("contact")

                )
        );
    }
    tblViewCustomer.setItems(obList);

}
private void Search(){

    try {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer WHERE id=?",txtSearch.getText());
        if (result.next()) {
            txtCusName.setText(result.getString(2));
            txtCusAddr.setText(result.getString(3));
            txtNIC.setText(result.getString(4));
            txtCusCont.setText(result.getString(5));

            txtCusId.setText(txtSearch.getText());

        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }catch (SQLException | ClassNotFoundException e){
        e.printStackTrace();
    }

}

















       /* if(btnSaveCustomer.getText().equals("Save Customer")){
            // save
            String customerId = txtCusId.getText();
            String customerName = txtCusName.getText();
            String customerAddress = txtCusAddr.getText();
            String customerNic = txtNIC.getText();
            String customerContact = txtCusCont.getText();

            Customer customer = new Customer(customerId,customerName,customerAddress,customerNic,customerContact);
            tblViewCustomer.getItems().add(customer);



         //   Database.customerTable.add(customer);

          //loadAllCustomers();
        }else{
            for (Customer customer :Database.customerTable
            ) {
                if(customer.getId().equals(txtCusId.getText())){
                    customer.setName(txtCusName.getText());
                    customer.setAddress(txtCusAddr.getText());
                    customer.setNic(txtNIC.getText());
                    customer.setContact(txtCusCont.getText());
                   // loadAllCustomers();
                    return;
                }
            }
        }
*/




    public void NewCustomerOnAction(ActionEvent actionEvent) {
       // btnSaveCustomer.setText("Save Customer");

    }

    public void clearText(){
        txtCusId.clear();
        txtCusName.clear();
        txtCusAddr.clear();
        txtNIC.clear();
        txtCusCont.clear();
    }


    public void txtSearchCustomer(ActionEvent actionEvent) {


    }

    public void EnterKeyRelease(KeyEvent keyEvent) {
        ValidationMethod();
        //If the keycode ENTER do the text
        if(keyEvent.getCode()== KeyCode.ENTER){
            Object respone = ValidationMethod();
            if(respone instanceof TextField){
                TextField textField = (TextField) respone;
                textField.requestFocus();
            }else if(respone instanceof  Boolean){
                System.out.println(respone);
            }
        }

}

    private Object ValidationMethod() {
//true
        //textfiled
        Pattern patternId = Pattern.compile("^(C00-)[0-9]{3,5}$");
        Pattern patternName = Pattern.compile("^[A-z]{3,15}$");
        Pattern patternAddress = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern patternNic = Pattern.compile("^[0-9V||v]{9,12}$");
        Pattern patternContact = Pattern.compile("^(077-|071-|078-|076-|074-|075-|072-)[0-9]{7}$");

        if (!patternId.matcher(txtCusId.getText()).matches()){
            error(txtCusId);
            return txtCusId;
        }else{
            fixeError(txtCusId);
            if (!patternName.matcher(txtCusName.getText()).matches()){
                error(txtCusName);
                return txtCusName;
            }else{
                fixeError(txtCusName);
                if(!patternAddress.matcher(txtCusAddr.getText()).matches()){
                    error(txtCusAddr);
                    return txtCusAddr;
                }else{
                    fixeError(txtCusAddr);
                    if(!patternNic.matcher(txtNIC.getText()).matches()){
                        error(txtNIC);
                        return txtNIC;
                    }else{
                        fixeError(txtNIC);
                        btnSaveCustomer.setDisable(false);
                        if(!patternContact.matcher(txtCusCont.getText()).matches()){
                            error(txtCusCont);
                        }else{
                            fixeError(txtCusCont);
                            return txtCusCont;
                        }
                    }
                }
            }
        }
        return true;

    }

    private void fixeError(TextField txt) {
        txt.setStyle("-fx-background-color: green");
        btnSaveCustomer.setDisable(false);
    }

    private void error(TextField txt) {
        if(txt.getText().length()>0){
            btnSaveCustomer.setDisable(true);
            txt.setStyle("-fx-background-color: red");
        }

    }

    public void DeleteCustomerOnAction(ActionEvent actionEvent) throws IOException {

        try{
            if (CrudUtil.execute("DELETE FROM Customer WHERE id=?",txtSearch.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"));
        AdminContext.getChildren().add(parent);

       // Search();

    }

    public void UpdateCustomerOnAction(ActionEvent actionEvent) throws IOException {




        Customer c = new Customer(
                txtCusId.getText(),txtCusName.getText(), txtCusAddr.getText(),txtNIC.getText(),
                txtCusCont.getText());

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE Customer SET name=? , address=? , nic=?,contact=? WHERE id=?",c.getName(),c.getAddress(),c.getNic(),c.getContact(),c.getId());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/CustomerForm.fxml"));
        AdminContext.getChildren().add(parent);

    }

    public void SearchCustomerOnAction(ActionEvent actionEvent) {
        Search();
    }
}