package pt.pa.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import pt.pa.model.Element;

/**
 * A classe RemoveController fornece métodos para remover um elemento e todos os seus filhos da árvore de elementos.
 */
public class RemoveController {

    /**
     * Remove um item representado por um TreeItem da árvore de elementos.
     * Exibe um diálogo de confirmação antes de efetuar a remoção.
     *
     * @param selectedItem O TreeItem do item a ser removido.
     * @param fileManager  O FileManager que efetua a gestão da estrutura da árvore de elementos.
     */
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
