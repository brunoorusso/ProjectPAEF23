package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.model.Element;
import pt.pa.model.File;
import pt.pa.view.GenerateAlert;

public class MoveController {
    public void moveItem(TreeItem<String> selectedItem, TreeItem<String> newParent, FileManager fileManager){
        if(selectedItem != null && newParent != null){
            String selectedElementName = selectedItem.getValue();
            String newParentName = newParent.getValue();
            Element selectedElement = fileManager.getElementByName(selectedElementName);
            Element newParentElement = fileManager.getElementByName(newParentName);
            if(newParentElement instanceof File){
                GenerateAlert alert = new GenerateAlert();
                alert.showAlertError("Não pode mover para dentro de ficheiros.");
            }else{
                TreeItem<?> originalParent = selectedItem.getParent();
                if (originalParent != null) {
                    originalParent.getChildren().remove(selectedItem);
                    newParent.getChildren().add(selectedItem);
                    fileManager.moveElement(selectedElement, newParentElement);
                }
            }
        }
    }
}
