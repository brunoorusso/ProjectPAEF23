package pt.pa.view;

import javafx.scene.control.Alert;

public class GenerateAlert {
    public void showAlertError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRO");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
