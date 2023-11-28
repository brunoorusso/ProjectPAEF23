package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.model.Element;
import pt.pa.model.File;
import pt.pa.view.GenerateAlert;

/**
 * A classe MoveController fornece métodos para mover um item na estrutura da árvore de elementos.
 */
public class MoveController {

    /**
     * Move um item representado por um TreeItem na árvore de elementos para um novo pai.
     *
     * @param selectedItem O TreeItem do item a ser movido.
     * @param newParent     O TreeItem do novo pai para o item movido.
     * @param fileManager   O FileManager que faz a gestão da estrutura da árvore de elementos.
     */
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
