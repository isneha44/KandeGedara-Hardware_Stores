package Controller;

import Model.Item;
import Model.OrderDetails;
import Model.PlaceOrder;
import com.jfoenix.controls.JFXButton;
//import db.Purchase;
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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class OrderPurchaseController {
    public JFXButton btnNewCus;
    public Label lblTotal;
    public Button btnMaxAdd;
    public TextField txtPrice;
    public TextField txtName;
    public TableView<PlaceOrder> tblView;
    public Button btnSave;
    public TextField txtSearch;
    public Label lblDate;
    public Label lblTime;
    public TextField txtOrderId;
    public TextField txtItemId;
    public TextField txtQty;
    public TextField txtTime;
    public TextField txtDate12;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colQty;
    public TableColumn colUnit;
    public TableColumn colItemCode;
    public TableColumn colOderId;
    public Button btnO_Purchase;
    public TableColumn colName;
    public TableColumn colqty;
    public TableColumn colTime1;
    public AnchorPane AdminContext;
    public TextField txtTotal;
    public TableColumn colTime11;


    public void initialize() {

        loadDateAndTime();


        try {
            LoadPlaceOrder();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            LoadAllOrder();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


       /* btnSave.setOnMouseClicked(event -> {

            OrderDetails od = new OrderDetails(
                    txtOrderId.getText(),
                    txtItemId.getText(),
                    Integer.parseInt(txtQty.getText()),
                    // Integer.parseInt(txtQtyOnhand.getText()),
                    Date.valueOf(txtDate12.getText()),
                    Time.valueOf(txtTime.getText())


            );


            try {
                if (CrudUtil.execute("INSERT INTO OrderDetails VALUES (?,?,?,?,?)", od.getOId(), od.getItemCode(), od.getQty(), od.getDate(), od.getTime())) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
                }

                AdminContext.getChildren().clear();
                Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderDetails.fxml"));
                AdminContext.getChildren().add(parent);


            } catch (SQLException throwables) {
                throwables.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });*/
    }


    private TextField SearchOrder() {


        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM orderdetials WHERE oId=?", txtOrderId.getText());
            if (result.next()) {
                txtOrderId.setText(result.getString(1));
                txtItemId.setText(result.getString(2));
                txtQty.setText(result.getString(3));


                txtDate12.setText(result.getString(5));
                txtTime.setText(result.getString(6));


            } else {
                txtOrderId.setStyle("-fx-background-color: red");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return txtOrderId;
    }

    private TextField SearchItem() {
        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Item WHERE itemCode=?",txtItemId.getText());
            if (result.next()) {
                txtName.setText(result.getString(2));
                txtPrice.setText(String.valueOf(result.getDouble(3)));


                // txtB.setText(txtB.getText());

            } else {
                //  new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return txtItemId;
    }




    private void LoadPlaceOrder() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM placeorder");
        ObservableList<PlaceOrder> obList = FXCollections.observableArrayList();

        while (result.next()) {
            obList.add(
                    new PlaceOrder(
                            result.getString("oId"),
                            result.getString("itemCode"),
                            result.getString("name"),
                            result.getDouble("unitPrice"),
                            result.getInt("qty"),
                            result.getDate("date"),
                            result.getTime("time"),
                            result.getDouble("total")

                    )
            );
        }

        tblView.setItems(obList);
        colOderId.setCellValueFactory(new PropertyValueFactory("oId"));

       colItemCode.setCellValueFactory(new PropertyValueFactory("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
       colUnit.setCellValueFactory(new PropertyValueFactory("unitPrice"));
        tblView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("qty"));
      colDate.setCellValueFactory(new PropertyValueFactory("date"));
     tblView.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("time"));
     tblView.getColumns().get(7).setCellValueFactory(new PropertyValueFactory("total"));


    }


    private void loadDateAndTime() {
/*
        txtDate12.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        //set Time/
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            txtTime.setText(currentTime.getHour() + ":" +
                    currentTime.getMinute() + ":" +
                    currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

*/
    }


    private void LoadAllOrder() throws SQLException, ClassNotFoundException, IOException {

        ResultSet result = CrudUtil.execute("SELECT * FROM orderdetials");
        ObservableList<OrderDetails> obList = FXCollections.observableArrayList();

        while (result.next()) {
            obList.add(
                    new OrderDetails(
                            result.getString("oId"),
                            result.getString("itemCode"),
                            result.getInt("qty"),
                            result.getDate("date"),
                            result.getTime("time")


                    )
            );
        }



    }



    public void NewCustomerOnAction(ActionEvent actionEvent) {


        txtOrderId.clear();
        txtItemId.clear();
        txtName.clear();
        txtPrice.clear();
        txtQty.clear();
        txtDate12.clear();
        txtTime.clear();
    }


    public void PurchaseOnAction(ActionEvent actionEvent) {
    }

    public void OrderIdKeyRelseed(KeyEvent keyEvent) {

        SearchOrder();
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TextField respone = SearchOrder();

            if (respone instanceof TextField) {
                TextField respone1 = respone;

                TextField textField = (TextField) respone;
                txtOrderId.setStyle("-fx-background-color: green");
            } else {
                txtOrderId.setStyle("-fx-background-color: red");
            }


        }
    }

    public void ItemIdKeyRelseed(KeyEvent keyEvent) {

        SearchItem();
        if (keyEvent.getCode() == KeyCode.ENTER) {
            TextField respone = SearchItem();

            if (respone instanceof TextField) {
                TextField respone1 = respone;

                TextField textField = (TextField) respone;
                txtItemId.setStyle("-fx-background-color: green");
            } else {
                txtItemId.setStyle("-fx-background-color: red");
            }


        }

        double x = Double.parseDouble(txtPrice.getText());
        int y = Integer.parseInt(txtQty.getText());
        double total = x * y;
        String s = String.valueOf(total);
        txtTotal.setText(s);

    }

    public void SaveOnAction(ActionEvent actionEvent) throws IOException {


        PlaceOrder orderDetails = new PlaceOrder(
                txtOrderId.getText(),
                txtItemId.getText(),
                txtName.getText(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText()),
                Date.valueOf(txtDate12.getText()),
                Time.valueOf(txtTime.getText()),
                Double.parseDouble(txtTotal.getText())
        );


        try {
            if (CrudUtil.execute("INSERT INTO placeorder VALUES (?,?,?,?,?,?,?,?)", orderDetails.getOId(), orderDetails.getItemCode(), orderDetails.getName(), orderDetails.getUnitPrice(), orderDetails.getQty(), orderDetails.getDate(), orderDetails.getTime(), orderDetails.getTotal())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();


            }




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderDetails.fxml"));
        AdminContext.getChildren().add(parent);

    }


    public void AddOrderOnAction (ActionEvent actionEvent) throws IOException {


        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/OrderForm.fxml"));
        AdminContext.getChildren().add(parent);




    }

    public void AddItemOnAction (ActionEvent actionEvent) throws IOException {

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"));
        AdminContext.getChildren().add(parent);
    }
}