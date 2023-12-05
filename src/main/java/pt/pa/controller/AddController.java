package pt.pa.controller;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import pt.pa.model.File;
import pt.pa.model.Folder;

/**
 * A classe AddController fornece metodos para adicionar elementos (pastas ou ficheiros) a uma estrutura de arvore.
 * @author Bruno Russo 202001410
 */
public class AddController {

    /**
     * Adiciona um item (pasta ou arquivo) a estrutura de arvore com base no item selecionado e nos dados fornecidos pelo utilizador.
     *
     * @param selectedItem O item da arvore selecionado ao qual o novo item sera adicionado como filho.
     * @param fileManager  O file manager utilizado para criar novos elementos.
     */
    public void addItem(TreeItem<String> selectedItem, FileManager fileManager){
        if(selectedItem != null){
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Adicionar");
            dialog.setHeaderText("Indique o nome:");
            dialog.setContentText("Nome:");

            dialog.showAndWait().ifPresent(newFolderName -> {
                if(!newFolderName.isEmpty()){
                    //Obter nome do item selecionado
                    String selectedFolderName = selectedItem.getValue();
                    //Obter pasta selecionada
                    Folder selectedFolder = (Folder) fileManager.getElementByName(selectedFolderName);
                    //Se o nome terminar em .txt ou .csv cria um File. Caso contrário cria um Folder.
                    if(newFolderName.matches(".*\\..*")){
                        TextInputDialog contentDialog = new TextInputDialog();
                        contentDialog.setTitle("Conteúdo do ficheiro");
                        contentDialog.setHeaderText("Inserir conteúdo do ficheiro:");
                        contentDialog.setContentText("Conteúdo: ");

                        contentDialog.showAndWait().ifPresent(newContent -> {
                            if(!newContent.isEmpty()){
                                File newFile = fileManager.createFile(selectedFolder, new File(selectedFolder, newFolderName, newContent));
                                addItemToTree(selectedItem, newFile.getName());
                            }
                        });

                    }else{
                        //fileManager.createMemento();
                        Folder newFolder = fileManager.createFolder(selectedFolder, new Folder(selectedFolder, newFolderName));
                        addItemToTree(selectedItem, newFolder.getName());
                    }
                }
            });
        }
    }

    /**
     * Adiciona um novo item à árvore visual na interface gráfica.
     *
     * @param selectedItem O item da árvore ao qual o novo item será adicionado como filho.
     * @param itemName      O nome do novo item a ser exibido na árvore.
     */
    private void addItemToTree(TreeItem<String> selectedItem, String itemName) {
        TreeItem<String> newItem = new TreeItem<>(itemName);
        selectedItem.getChildren().add(newItem);
        selectedItem.setExpanded(true);
    }
}
