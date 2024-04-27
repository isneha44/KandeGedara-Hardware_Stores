package Controller;

import Model.Order;
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

public class OrderFormController {
    public JFXTextField txtoId;
    public JFXTextField txtcId;


    public JFXTextField txtdate;
    public TableView tblOrder;
    public JFXButton btnNewOrder;
    public JFXButton btnSaveOrder;
    public JFXTextField txtstatus;
    public JFXTextField txteId;
    public JFXTextField txtdId;
    public TextField txtSearch;
    public TableColumn colOId;
    public TableColumn colCId;
    public TableColumn colEm_id;
    public TableColumn colDId;
    public TableColumn colStatus;
    public TableColumn colDate;
    public AnchorPane AdminContext;


    public void initialize() {
       // btnSaveOrder.setDisable(true);

        try {
            loadAllOrders();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        colOId.setCellValueFactory(new PropertyValueFactory("oId"));
        colCId.setCellValueFactory(new PropertyValueFactory("cId"));
        colEm_id.setCellValueFactory(new PropertyValueFactory("eId"));
        colDId.setCellValueFactory(new PropertyValueFactory("dId"));
        colStatus.setCellValueFactory(new PropertyValueFactory("status"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));






    }

    private void loadAllOrders() throws SQLException, ClassNotFoundException {


        ResultSet result = CrudUtil.execute("SELECT * FROM `Order`");
        ObservableList<Order> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new Order(
                            result.getString("oId"),
                            result.getString("cId"),
                            result.getString("eId"),
                            result.getString("dId"),
                            result.getString("status"),
                            result.getString("date")

                    )
            );
        }
        tblOrder.setItems(obList);

    }



    private void Search() {

        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM `Order` WHERE oId=?", txtSearch.getText());
            if (result.next()) {
                txtcId.setText(result.getString(2));
                txteId.setText(result.getString(3));
                txtdId.setText(result.getString(4));
                txtstatus.setText(result.getString(5));
                txtdate.setText(result.getString(6));

                txtoId.setText(txtSearch.getText());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
        public void NewOrderOnAction(ActionEvent actionEvent) {
    }

    public void SaveOrderOnAction(ActionEvent actionEvent) throws IOException {


        Order order = new Order(
                txtoId.getText(),
                txtcId.getText(),
                txteId.getText(),
                txtdId.getText(),
                txtstatus.getText(),
                txtdate.getText());

        try {
            if (CrudUtil.execute("INSERT INTO `Order` VALUES (?,?,?,?,?,?)", order.getOId(), order.getCId(), order.getEId(), order.getDId(), order.getStatus(), order.getDate())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void txtSearchOrder(ActionEvent actionEvent) {
    }

    public void SearchOrderOnAction(ActionEvent actionEvent) {
        Search();
    }

    public void DeleteOrderOnAction(ActionEvent actionEvent) {
    }

    public void UpdateOrderOnAction(ActionEvent actionEvent) {
    }

    public void GoToOnAction(ActionEvent actionEvent) throws IOException {

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderDetails.fxml"));
        AdminContext.getChildren().add(parent);
    }
}
