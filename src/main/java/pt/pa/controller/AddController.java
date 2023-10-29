package pt.pa.controller;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import pt.pa.model.File;
import pt.pa.model.Folder;

public class AddController {
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
                        Folder newFolder = fileManager.createFolder(selectedFolder, new Folder(selectedFolder, newFolderName));
                        addItemToTree(selectedItem, newFolder.getName());
                    }
                }
            });
        }
    }

    private void addItemToTree(TreeItem<String> selectedItem, String itemName) {
        TreeItem<String> newItem = new TreeItem<>(itemName);
        selectedItem.getChildren().add(newItem);
        selectedItem.setExpanded(true);
    }
}
