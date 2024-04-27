package Controller;

import Model.Customer;
import Model.Item;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import db.Database;
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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static javax.xml.bind.DatatypeConverter.parseDouble;

public class ItemFormController {
    public TableColumn colPrice;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colCname;
    public TableColumn colItem_Code;
    public TableView<Item> tblItem;
    public JFXButton btnNewItem;
    public JFXButton btnSaveItem;
    public JFXTextField txtI_Price;
    public JFXTextField txtI_Time;
    public JFXTextField txtI_date;
    public JFXTextField txtI_Code;
    public JFXTextField txtI_Name;
    public JFXButton btnUpdate;
    public JFXButton btnDel;
    public JFXButton btnSearchI;
    public AnchorPane AdminContext;
    public TextField txtISearch;


    public void initialize() {
       // btnSaveItem.setDisable(true);

        try {
            loadAllItem();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        colItem_Code.setCellValueFactory(new PropertyValueFactory("i_Code"));
        colCname.setCellValueFactory(new PropertyValueFactory("name"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));




    }

    private void loadAllItem() throws SQLException, ClassNotFoundException {

        ResultSet result = CrudUtil.execute("SELECT * FROM Item");
        ObservableList<Item> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new Item(
                            result.getString("i_Code"),
                            result.getString("name"),
                            result.getDouble("price")


                    )
            );
        }
        tblItem.setItems(obList);




    }





    private void Search (){

        try {
            ResultSet result = CrudUtil.execute("SELECT * FROM Item WHERE itemCode=?",txtISearch.getText());
            if (result.next()) {
                txtI_Name.setText(result.getString(2));
                txtI_Price.setText(String.valueOf (result.getDouble(3)));


                txtI_Code.setText(txtISearch.getText());

            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Result").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }



    public void SearchIOnAction(ActionEvent actionEvent) {
        Search();
    }




    public void SaveItemOnAction(ActionEvent actionEvent) throws IOException {

        Item item= new Item(
                txtI_Code.getText(),
                txtI_Name.getText(),
                Double.parseDouble(txtI_Price.getText())
                );


        try {
            if (CrudUtil.execute("INSERT INTO Item VALUES (?,?,?)",item.getI_Code(),item.getName(),item.getPrice())){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved!..").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"));
        AdminContext.getChildren().add(parent);




    }


    public void DelItemOnAction(ActionEvent actionEvent) throws IOException {

        try{
            if (CrudUtil.execute("DELETE FROM Item WHERE itemCode=?",txtISearch.getText())){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"));
        AdminContext.getChildren().add(parent);

    }




    public void UpdateItemOnAction(ActionEvent actionEvent) throws IOException {


        Item i = new Item(
                txtI_Code.getText(),txtI_Name.getText(),Double.parseDouble(txtI_Price.getText())
                );

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE Item SET name=? , price=?  WHERE itemCode=?",i.getName(),i.getPrice(),i.getI_Code());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }

        AdminContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"));
        AdminContext.getChildren().add(parent);
    }











    public void NewItemOnAction(ActionEvent actionEvent) {

        btnSaveItem.setText("Save Item");
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
        Pattern patternId = Pattern.compile("^(I00-)[0-9]{3,5}$");
        Pattern patternName = Pattern.compile("^[A-z&\\s]{3,35}$");
        Pattern patternPrice = Pattern.compile("^[0-9 ]{2,12}(.[0-9]{2})?$");


        if (!patternId.matcher(txtI_Code.getText()).matches()){
            error(txtI_Code);
            return txtI_Code;
        }else{
            fixeError(txtI_Code);
            if (!patternName.matcher(txtI_Name.getText()).matches()){
                error(txtI_Name);
                return txtI_Name;
            }else{
                fixeError(txtI_Name);
                if(!patternPrice.matcher(txtI_Price.getText()).matches()){
                    error(txtI_Price);
                    return txtI_Price;
                }else{
                    fixeError(txtI_Price);

                }
            }
        }
        return true;

    }

    private void fixeError(TextField txt) {
        txt.setStyle("-fx-background-color: green");
        btnSaveItem.setDisable(false);
    }

    private void error(TextField txt) {
        if(txt.getText().length()>0){
            btnSaveItem.setDisable(true);
            txt.setStyle("-fx-background-color: red");
        }

    }





}

