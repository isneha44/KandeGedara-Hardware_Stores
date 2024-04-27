package Controller;

import Model.Customer;
import Model.OrderDetails;
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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class OrderDetailsController {
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colQtyOnHand;
    public TableColumn colQty;
    public TableColumn colI_code;
    public TableView<OrderDetails>tblOrderDetails;
    public JFXButton btnNewCus;
    public JFXButton btnOrderDetails;
    public TextField txtSearch;
    public AnchorPane AdminContext;
    public JFXTextField txtI_code;
    public JFXTextField txtQty;
    public JFXTextField txtDate;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtTime;
    public JFXTextField txtOid;
    public TableColumn colOid;


    public void initialize() {
        // btnOrderDetails.setDisable(true);

        try {
            loadAllOrderDetails();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        colOid.setCellValueFactory(new PropertyValueFactory("oId"));
        colI_code.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colQty.setCellValueFactory(new PropertyValueFactory("qty"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory("qtyOnHand"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colTime.setCellValueFactory(new PropertyValueFactory("time"));

    }

    private void loadAllOrderDetails() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM OrderDetials");
        ObservableList<OrderDetails> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new OrderDetails(
                            result.getString("oId"),
                            result.getString("itemCode"),
                            result.getInt("qty"),
                            result.getInt("qtyOnHand"),
                            result.getDate("date"),
                            result.getTime("time")

                    )
            );
        }
        tblOrderDetails.setItems(obList);
    }


    private void Search(){

        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM orderdetials WHERE oId=?",txtSearch.getText());
            if (result.next()) {
                txtI_code.setText(result.getString(2));
                txtQty.setText(String.valueOf(result.getInt(3)));
                txtQtyOnHand.setText(String.valueOf(result.getInt(4)));
                txtDate.setText(String.valueOf(result.getDate(5)));
                txtTime.setText(String.valueOf(result.getTime(6)));

                txtOid.setText(txtSearch.getText());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }


    public void EnterKeyRelease(KeyEvent keyEvent) {
    }

    public void txtSearchCustomer(ActionEvent actionEvent) {
    }

    public void SaveOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {


        OrderDetails od = new OrderDetails(
                txtOid.getText(),
                txtI_code.getText(),
                Integer.parseInt(txtQty.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                Date.valueOf(txtDate.getText()),
                Time.valueOf(txtTime.getText()));


        try {
            if (CrudUtil.execute("INSERT INTO orderdetials VALUES (?,?,?,?,?,?)",od.getOId(),od.getItemCode(),od.getQty(),od.getQtyOnHand(),od.getDate(),od.getTime())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderDetails.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void NewCustomerOnAction(ActionEvent actionEvent) {
    }

    public void SearchOrderDetailsOnAction(ActionEvent actionEvent) {

        Search();
    }

    public void DeleteOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {

        try{
            if (CrudUtil.execute("DELETE FROM orderdetials WHERE oId=?",txtSearch.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderDetailsForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void UpdateOrderDetailsOnAction(ActionEvent actionEvent) throws IOException {

        OrderDetails c = new OrderDetails(
                txtOid.getText(),
                txtI_code.getText(),
                Integer.parseInt(txtQty.getText()),
                Integer.parseInt(txtQtyOnHand.getText()),
                Date.valueOf(txtDate.getText()),
                Time.valueOf(txtTime.getText()));

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE orderdetials SET itemCode=?,qty=?,qtyOnHand=?,date=?,time=?  WHERE oId=?",c.getItemCode(),c.getQty(),c.getQtyOnHand(),c.getDate(),c.getTime(),c.getOId());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderDetailsForm.fxml"));
        AdminContext.getChildren().add(parent);
    }
}
