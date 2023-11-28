package pt.pa.view;

import javafx.scene.control.Alert;

/**
 * A classe GenerateAlert fornece métodos para gerar e exibir alertas na aplicação JavaFX.
 * Permite a exibição de alertas de erro.
 */
public class GenerateAlert {

    /**
     * Exibe um alerta de erro com a mensagem fornecida.
     *
     * @param message A mensagem a ser exibida no alerta de erro.
     */
    public void showAlertError(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERRO");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
