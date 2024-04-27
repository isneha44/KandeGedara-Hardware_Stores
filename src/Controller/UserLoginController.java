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

public class UserLoginController {
    public Button btnUsLogin;
    public PasswordField pwdUsPassword;
    public TextField txtUsUserName;
    public AnchorPane UsLoginContext;

    int attempts=0;


    public void UsLoginOnAction(ActionEvent actionEvent) throws IOException {

        attempts++;
        if (attempts<=3) {
            if (txtUsUserName.getText().equals("mallika") && pwdUsPassword.getText().equals("1234")) {

                Stage stage= (Stage)UsLoginContext.getScene().getWindow();
                stage.close();

                //Database.bellowUp.getChildren().clear();
                //Parent parent = FXMLLoader.load(getClass().getResource("../view/inParking.fxml"));
               // Database.bellowUp.getChildren().add(parent);

                URL resource= getClass().getResource("../view/User.fxml");
                Parent load = FXMLLoader.load (resource);
                Scene scene = new Scene(load);
                stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }

        }else {
            txtUsUserName.setEditable(false);
            pwdUsPassword.setEditable(false);

        }

    }
}
