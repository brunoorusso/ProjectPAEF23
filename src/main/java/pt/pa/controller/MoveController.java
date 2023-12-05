package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.model.Element;
import pt.pa.model.File;
import pt.pa.view.GenerateAlert;

/**
 * A classe MoveController fornece metodos para mover um item na estrutura da arvore de elementos.
 * @author Bruno Russo 202001410
 */
public class MoveController {

    /**
     * Move um item representado por um TreeItem na arvore de elementos para um novo pai.
     *
     * @param selectedItem O TreeItem do item a ser movido.
     * @param newParent     O TreeItem do novo pai para o item movido.
     * @param fileManager   O FileManager que faz a gestao da estrutura da arvore de elementos.
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
