package Controller;


import Model.Customer;
import Model.Delivery;
import Model.Employee;
import Model.Item;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.CrudUtil;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class DeliveryFormController {


    public JFXTextField txtdate;
    public TableView<Delivery>tblDelivery;
    public JFXButton btnNewDelivery;
    public JFXButton btnSaveDelivery;
    public JFXTextField txtdeliveyFree;
    public JFXTextField txtaddress;
    public JFXTextField txtcontact;
    public JFXTextField txtoId;
    public JFXTextField txtdId;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnSearch;
    public TableColumn coldate;
    public TableColumn coldelifee;
    public TableColumn colcontact;
    public TableColumn coladdress;
    public TableColumn coloId;
    public TableColumn coldId;
    public TextField txtSearch;
    public AnchorPane AdminContext;
    public TableColumn colveNo;
    public JFXTextField txtveNo;


    public void initialize() {
       // btnSaveDelivery.setDisable(true);

        currentDateTime();
        try {
            loadAllDelivery();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        coldId.setCellValueFactory(new PropertyValueFactory("dId"));
        colveNo.setCellValueFactory(new PropertyValueFactory("veNo"));
        coladdress.setCellValueFactory(new PropertyValueFactory("address"));
        colcontact.setCellValueFactory(new PropertyValueFactory("contact"));
        coldelifee.setCellValueFactory(new PropertyValueFactory("deliveyFee"));
        coldate.setCellValueFactory(new PropertyValueFactory("date"));




    }

    private void loadAllDelivery() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM Delivery");
        ObservableList<Delivery> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new Delivery(
                            result.getString("dId"),
                            result.getString("veNo"),
                            result.getString("address"),
                            result.getString("contact"),
                            result.getDouble("deliveyFee"),
                            result.getString("date")


                    )
            );
        }
        tblDelivery.setItems(obList);

    }


    private void currentDateTime() {
       Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            txtdate.setText(currentDate.getYear()+"-"+currentDate.getMonthValue()+"-"+currentDate.getDayOfMonth()+
                    "    "+ currentTime.getHour() + ":" + currentTime.getMinute() + ":"+ currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    private void Search(){

        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Delivery WHERE dId=?",txtSearch.getText());
            if (result.next()) {
                txtveNo.setText(result.getString(2));
                txtaddress.setText(result.getString(3));
                txtcontact.setText(result.getString(4));
                txtdeliveyFree.setText(String.valueOf(result.getDouble(5)));
                txtdate.setText(result.getString(6));
                txtdId.setText(txtSearch.getText());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }


    public void NewDeliveryOnAction(ActionEvent actionEvent) {
    }

    public void SaveDeliveryOnAction(ActionEvent actionEvent) throws IOException {


        Delivery de= new Delivery(
                txtdId.getText(),
                txtveNo.getText(),
                txtaddress.getText(),
                txtcontact.getText(),
                Double.parseDouble(txtdeliveyFree.getText()),
                txtdate.getText());




        try {
            if (CrudUtil.execute("INSERT INTO Delivery VALUES (?,?,?,?,?,?)",de.getDId(),de.getVeNo(),de.getAddress(),de.getContact(),de.getDeliveyFee(),de.getDate())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DeliveryForm.fxml"));
        AdminContext.getChildren().add(parent);

    }

    public void txtSearchDelivery(ActionEvent actionEvent) {
    }

    public void UpdateDeliveryOnAction(ActionEvent actionEvent) throws IOException {


        Delivery c = new Delivery(
                txtdId.getText(),txtveNo.getText(),txtaddress.getText(),txtcontact.getText(),
                txtdeliveyFree.getText(),txtdate.getText());

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE Delivery SET veNo=?,address=?,contact=?,deliveyFee=?,date=?  WHERE dId=?",c.getVeNo(),c.getAddress(),c.getContact(),c.getDeliveyFee(),c.getDate());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DeliveryForm.fxml"));
        AdminContext.getChildren().add(parent);
    }

    public void DeleteDeliveryOnAction(ActionEvent actionEvent) throws IOException {


        try{
            if (CrudUtil.execute("DELETE FROM Delivery WHERE dId=?",txtSearch.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/DeliveryForm.fxml"));
        AdminContext.getChildren().add(parent);

    }

    public void SearchDeliveryOnAction(ActionEvent actionEvent) {

        Search();
    }
}
