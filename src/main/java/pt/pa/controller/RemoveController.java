package pt.pa.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import pt.pa.model.Element;

public class RemoveController {
    public void removeItem(TreeItem<String> selectedItem, FileManager fileManager){
        if(selectedItem != null){
            Alert dialog = new Alert(AlertType.CONFIRMATION);
            dialog.initStyle(StageStyle.UTILITY);
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Confirmação");
            dialog.setHeaderText("Deseja mesmo remover este elemento e todos os seus filhos?");

            //Botões
            ButtonType btnOk = new ButtonType("OK");
            ButtonType btnCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getButtonTypes().setAll(btnOk, btnCancel);

            dialog.showAndWait().ifPresent(result -> {
                if(result == btnOk){
                    String selectedElementName = selectedItem.getValue();
                    Element selectedElement = fileManager.getElementByName(selectedElementName);
                    if(selectedElement.getName() != "root"){
                        //Remove da árvore
                        fileManager.removeElement(selectedElement);
                        //Remove visualmente
                        selectedItem.getParent().getChildren().remove(selectedItem);
                    }
                }
            });
        }
    }
}
