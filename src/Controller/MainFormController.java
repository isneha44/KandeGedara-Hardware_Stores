package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFormController {


    public Button btnUserLogin;
    public Button btnAdminLogin;

    public void AdminLoginOnAction(ActionEvent actionEvent) throws IOException {


        URL resource= getClass().getResource("../view/AdminLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void UserLoginOnAction(ActionEvent actionEvent) throws IOException {
        URL resource= getClass().getResource("../view/UserLogin.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

