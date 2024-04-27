package Controller;


import Model.Customer;
import Model.Item;
import Model.Supplier;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierFormController {


    public TableColumn colCont;
    public TableColumn colNic;
    public TableColumn colAddre;
    public TableColumn colName;
    public TableColumn colSId;
    public TableView<Supplier> tblSupplier;
    public JFXButton btnNewCus;
    public JFXButton btnSaveSupplier;
    public AnchorPane AdminContext;
    public JFXTextField txtSppCont;
    public JFXTextField txtSppAddr;
    public JFXTextField txtNIC;
    public TextField txtSearch;
    public JFXTextField txtSppName;
    public JFXTextField txtSppId;


    public void initialize() {
        //btnSaveSupplier.setDisable(true);

        try {
            loadAllSuppliers();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        colSId.setCellValueFactory(new PropertyValueFactory("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddre.setCellValueFactory(new PropertyValueFactory("address"));
        colNic.setCellValueFactory(new PropertyValueFactory("nic"));
        colCont.setCellValueFactory(new PropertyValueFactory("contact"));





    }

    private void loadAllSuppliers() throws SQLException, ClassNotFoundException {


        ResultSet result = CrudUtil.execute("SELECT * FROM Supplier");
        ObservableList<Supplier> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new Supplier(
                            result.getString("supplierId"),
                            result.getString("name"),
                            result.getString("address"),
                            result.getString("nic"),
                            result.getString("contact")

                    )
            );
        }
        tblSupplier.setItems(obList);
    }



    private void Search(){

        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Supplier WHERE supplierId=?",txtSearch.getText());
            if (result.next()) {
                txtSppName.setText(result.getString(2));
                txtSppAddr.setText(result.getString(3));
                txtNIC.setText(result.getString(4));
                txtSppCont.setText(result.getString(5));

                txtSppId.setText(txtSearch.getText());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }


    public void EnterKeyRelease(KeyEvent keyEvent) {
    }

    public void SaveCustomerOnAction(ActionEvent actionEvent) {
    }

    public void NewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void txtSearchCustomer(ActionEvent actionEvent) {
    }

    public void UpdateSupplierOnAction(ActionEvent actionEvent) throws IOException {



        Supplier sup = new  Supplier(
                txtSppId.getText(),txtSppName.getText(),txtSppAddr.getText(),txtNIC.getText(),txtSppCont.getText());

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE Supplier SET name=?,address=?,nic=?,contact=?  WHERE  supplierId=?",sup.getName(),sup.getAddress(),sup.getNic(),sup.getContact(),sup.getSupplierId());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SupplierForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void DeleteSupplierOnAction(ActionEvent actionEvent) throws IOException {


        try{
            if (CrudUtil.execute("DELETE FROM Supplier WHERE supplierId=?",txtSearch.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SupplierForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void SearchSupplierOnAction(ActionEvent actionEvent) {
        Search();
    }

    public void SaveSupplierOnAction(ActionEvent actionEvent) throws IOException {


        Supplier spp = new Supplier(
                txtSppId.getText(),
                txtSppName.getText(),
                txtSppAddr.getText(),
                txtNIC.getText(),
                txtSppCont.getText());


        try {
            if (CrudUtil.execute("INSERT INTO Supplier VALUES (?,?,?,?,?)",spp.getSupplierId(),spp.getName(),spp.getAddress(),spp.getNic(),spp.getContact())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SupplierForm.fxml"));
        AdminContext.getChildren().add(parent);



    }

    public void GoToOnAction(ActionEvent actionEvent) throws IOException {

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SupplierDetailsForm.fxml"));
        AdminContext.getChildren().add(parent);
    }
}
