package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminLoginController {


    public Button btnAdLogin;
    public PasswordField pwdAdPassword;
    public TextField txtAdUserName;
    public AnchorPane AdLoginContext;
    int attempts=0;




    public void AdLoginOnAction(ActionEvent actionEvent) throws IOException {

        attempts++;
        if (attempts<=3) {
            if (txtAdUserName.getText().equals("admin") && pwdAdPassword.getText().equals("1234")) {

                Stage stage= (Stage)AdLoginContext.getScene().getWindow();
                stage.close();

               // AdLoginContext.getChildren().clear();
               //Parent parent =  FXMLLoader.load(getClass().getResource("../view/Admin.fxml"));
              // AdLoginContext.getChildren().add (parent);

               URL resource= getClass().getResource("../view/Admin.fxml");
                Parent load = FXMLLoader.load(resource);
                Scene scene = new Scene(load);
                stage = new Stage();
                stage.setScene(scene);
                stage.show();
                //Database.bellowUp.getChildren().add(parent);


            }

        }else {
            txtAdUserName.setEditable(false);
            pwdAdPassword.setEditable(false);

        }
    }
}


