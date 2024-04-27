package Controller;

import Model.supplierDetails;
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

public class SupplierDetailsFormController {
    public JFXTextField txtTime;
    public JFXTextField txtDate;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colStatus;
    public TableColumn colPayType;
    public TableColumn colPayment;
    public TableColumn colIid;
    public TableColumn colSId;
    public TableView<supplierDetails> tblsupplierDetails;
    public JFXButton btnNewCus;
    public JFXButton btnSaveSupplier;
    public JFXTextField txtStatus;
    public JFXTextField txtPayment;
    public JFXTextField txtPay_Type;
    public TextField txtSearch;
    public JFXTextField txtItem;
    public JFXTextField txtSid;
    public AnchorPane AdminContext;


    public void initialize() {
        //btnSaveSupplier.setDisable(true);

        try {
            loadAllSupplierDetails();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        colSId.setCellValueFactory(new PropertyValueFactory("sId"));
        colIid.setCellValueFactory(new PropertyValueFactory("i_Id"));
        colPayment.setCellValueFactory(new PropertyValueFactory("payment"));
        colPayType.setCellValueFactory(new PropertyValueFactory("paymentType"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
    }

    private void loadAllSupplierDetails() throws SQLException, ClassNotFoundException {


        ResultSet result = CrudUtil.execute("SELECT * FROM supplierDetails");
        ObservableList<supplierDetails> obList = FXCollections.observableArrayList();

        while (result.next()) {
            obList.add(
                    new supplierDetails(
                            result.getString("sId"),
                            result.getString("i_Id"),
                            result.getDouble("payment"),
                            result.getString("paymentType"),
                            result.getString("status"),
                            result.getString("date"),
                            result.getString("time")


                    )
            );
        }
        tblsupplierDetails.setItems(obList);
    }


    private void Search() {

        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM supplierDetails WHERE sId=?", txtSearch.getText());
            if (result.next()) {
                txtItem.setText(result.getString(2));
                txtPayment.setText(String.valueOf(result.getDouble(3)));
                txtPay_Type.setText(result.getString(4));
                txtStatus.setText(result.getString(5));
                txtDate.setText(result.getString(6));
                txtTime.setText(result.getString(7));


                txtSid.setText(txtSearch.getText());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void EnterKeyRelease(KeyEvent keyEvent) {
    }

    public void SaveSupplierOnAction(ActionEvent actionEvent) throws IOException {

        supplierDetails spp = new supplierDetails(
                txtSid.getText(),
                txtItem.getText(),
                Double.parseDouble(txtPayment.getText()),
                txtPay_Type.getText(),
                txtStatus.getText(),
                txtDate.getText(),
                txtTime.getText()
        );


        try {
            if (CrudUtil.execute("INSERT INTO supplierDetails VALUES (?,?,?,?,?,?,?)", spp.getSId(), spp.getI_Id(), spp.getPayment(), spp.getPaymentType(), spp.getStatus(), spp.getDate(), spp.getTime())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SupplierDetailsForm.fxml"));
        AdminContext.getChildren().add(parent);

    }

    public void NewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void DeleteSupplierOnAction(ActionEvent actionEvent) throws IOException {

        try {
            if (CrudUtil.execute("DELETE FROM SupplierDetails WHERE sId=?", txtSearch.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SupplierDetailsForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void UpdateSupplierOnAction(ActionEvent actionEvent) throws IOException {

        supplierDetails sup = new supplierDetails(
                txtSid.getText(),
                txtItem.getText(),
                Double.parseDouble(txtPayment.getText()),
                txtPay_Type.getText(),
                txtStatus.getText(),
                txtDate.getText(),
                txtTime.getText()
        );

        try {
            boolean isUpdated = CrudUtil.execute("UPDATE supplierDetails SET  i_Id=?,payment=?,paymentType=?,status=?,date=?,time=?   WHERE sId=?", sup.getI_Id(), sup.getPayment(), sup.getPaymentType(), sup.getStatus(), sup.getDate(), sup.getTime(), sup.getSId());
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        } catch (SQLException | ClassNotFoundException e) {

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/SupplierDetailsForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void SearchSupplierOnAction(ActionEvent actionEvent) {

        Search();
    }

}