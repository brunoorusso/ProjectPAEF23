package pt.pa.controller;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import pt.pa.model.Element;
import pt.pa.view.GenerateAlert;

import java.time.LocalDateTime;

/**
 * A classe RenameController fornece metodos para renomear um elemento na arvore de elementos.
 * @author Bruno Russo 202001410
 */
public class RenameController {

    /**
     * Renomeia um item representado por um TreeItem na arvore de elementos.
     *
     * @param selectedItem O TreeItem do item a ser renomeado.
     * @param fileManager  O FileManager que efetua a gestao da estrutura da arvore de elementos.
     */
    public void renameItem(TreeItem<String> selectedItem, FileManager fileManager){
        if(selectedItem != null){
            Element selectedElement = fileManager.getElementByName(selectedItem.getValue());
            if(selectedElement.getName().equals("root")){
                GenerateAlert alert = new GenerateAlert();
                alert.showAlertError("Não pode modificar a pasta root");
            }else{
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Adicionar");
                dialog.setHeaderText("Indique o novo nome:");
                dialog.setContentText("Nome:");
                dialog.showAndWait().ifPresent(newName -> {
                    if(!newName.isEmpty()){
                        selectedItem.setValue(newName);
                        selectedElement.setName(newName);
                        selectedElement.setModifiedDate(LocalDateTime.now());
                        selectedElement.setNumberOfChanges(selectedElement.getNumberOfChanges() + 1);
                    }
                });
            }
        }
    }
}
