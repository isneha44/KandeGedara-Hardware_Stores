package util;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class UtilValidate {

    public static Object Validate(LinkedHashMap<TextField, Pattern> map, Button btn) {
        //validation useing


        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if(!pattern.matcher(key.getText()).matches()){
                error(key,btn);
                return key;
            }else{
                fixeError(key);
            }
        }
        return true;
    }
    private static void fixeError(TextField txt) {
        txt.setStyle("-fx-background-color: green ");
    }

    private static void error(TextField txt,Button btn) {
        // btn.setDisable(true);
        txt.setStyle("-fx-background-color: red");
    }
}
