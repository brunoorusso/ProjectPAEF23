package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.model.Element;

public class MoveController {
    public void moveItem(TreeItem<String> selectedItem, TreeItem<String> newParent, FileManager fileManager){
        if(selectedItem != null && newParent != null){
            String selectedElementName = selectedItem.getValue();
            String newParentName = newParent.getValue();
            Element selectedElement = fileManager.getElementByName(selectedElementName);
            Element newParentElement = fileManager.getElementByName(newParentName);


            TreeItem<?> originalParent = selectedItem.getParent();
            System.out.println(selectedItem.getParent().getChildren());
            if (originalParent != null) {
                originalParent.getChildren().remove(selectedItem);
                newParent.getChildren().add(selectedItem);

                // Certifique-se de que a estrutura da árvore seja atualizada corretamente
                fileManager.moveElement(selectedElement, newParentElement);
            }
        }
    }
}
