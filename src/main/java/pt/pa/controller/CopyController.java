package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.model.Element;
import pt.pa.model.File;
import pt.pa.model.Folder;
import pt.pa.view.GenerateAlert;

/**
 * A classe CopyController fornece métodos para copiar elementos (pastas ou ficheiros) em uma estrutura de árvore.
 */
public class CopyController {
    /**
     * Copia um elemento (pasta ou ficheiro) na estrutura de árvore com base no item selecionado.
     *
     * @param selectedItem O item da árvore selecionado que será copiado.
     * @param fileManager  O file manager utilizado para criar a cópia do elemento.
     */
    public void copyElement(TreeItem<String> selectedItem, FileManager fileManager){
        if(selectedItem != null){
            if(selectedItem.getValue().equals("root")){
                GenerateAlert alert = new GenerateAlert();
                alert.showAlertError("Não é possível copiar esta pasta.");
            }else{
                Element selectedElement = fileManager.getElementByName(selectedItem.getValue());
                Folder selectedParent = (Folder) fileManager.getElementByName(selectedItem.getParent().getValue());
                if(selectedElement instanceof Folder){
                    Folder newFolder = fileManager.createFolder(selectedParent, new Folder(selectedParent, selectedElement.getName()));
                    addItemToTree(selectedItem, newFolder.getName());
                }else if(selectedElement instanceof File){
                    File newFile = fileManager.createFile(selectedParent, new File(selectedParent, selectedElement.getName(), ((File) selectedElement).getContent()));
                    addItemToTree(selectedItem, newFile.getName());
                }
            }
        }
    }

    /**
     * Adiciona uma cópia do item à árvore visual na interface gráfica.
     *
     * @param selectedItem O item da árvore original que está sendo copiado.
     * @param itemName      O nome do novo item a ser exibido na árvore.
     */
    private void addItemToTree(TreeItem<String> selectedItem, String itemName) {
        TreeItem<String> newItem = new TreeItem<>(itemName);
        selectedItem.getParent().getChildren().add(newItem);
        selectedItem.setExpanded(true);
    }
}
