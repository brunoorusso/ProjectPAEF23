package pt.pa.controller;

import javafx.scene.control.TreeItem;
import pt.pa.model.Element;
import pt.pa.model.File;
import pt.pa.model.Folder;
import pt.pa.view.GenerateAlert;

/**
 * A classe CopyController fornece metodos para copiar elementos (pastas ou ficheiros) em uma estrutura de arvore.
 * @author Bruno Russo 202001410
 */
public class CopyController {
    /**
     * Copia um elemento (pasta ou ficheiro) na estrutura de arvore com base no item selecionado.
     *
     * @param selectedItem O item da arvore selecionado que sera copiado.
     * @param fileManager  O file manager utilizado para criar a copia do elemento.
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
     * Adiciona uma cópia do item a arvore visual na interface grafica.
     *
     * @param selectedItem O item da arvore original que esta sendo copiado.
     * @param itemName      O nome do novo item a ser exibido na arvore.
     */
    private void addItemToTree(TreeItem<String> selectedItem, String itemName) {
        TreeItem<String> newItem = new TreeItem<>(itemName);
        selectedItem.getParent().getChildren().add(newItem);
        selectedItem.setExpanded(true);
    }
}
