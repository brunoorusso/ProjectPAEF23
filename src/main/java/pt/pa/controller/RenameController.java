package pt.pa.controller;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import pt.pa.model.Element;
import pt.pa.view.GenerateAlert;

public class RenameController {
    public void renameItem(TreeItem<String> selectedItem, FileManager fileManager){
        if(selectedItem != null){
            Element selectedElement = fileManager.getElementByName(selectedItem.getValue());
            if(selectedElement.getName() == "root"){
                GenerateAlert alert = new GenerateAlert();
                alert.showAlertError("Não pode modificar a pasta root");
            }else{
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Adicionar");
                dialog.setHeaderText("Indique o novo nome:");
                dialog.setContentText("Nome:");
                System.out.println("Antes -> " + selectedElement);
                dialog.showAndWait().ifPresent(newName -> {
                    if(!newName.isEmpty()){
                        selectedItem.setValue(newName);
                        selectedElement.setName(newName);
                    }
                });
            }
        }
    }
}
