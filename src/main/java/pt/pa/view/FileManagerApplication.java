package pt.pa.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.pa.controller.*;
import pt.pa.model.CustomTreeItem;
import pt.pa.model.Folder;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileManagerApplication extends Application{

    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Explorer");


        //Image folderImg = new Image("/resources/folder.png");

        FileManager fileManager = new FileManager();
        Folder root = fileManager.createRootFolder();

        //Criar Root Folder
        TreeItem<String> rootItem = new TreeItem(root.getName());
        //CustomTreeItem  rootTest = new CustomTreeItem(root.getName(), folderImg);
        //Criar TreeView
        TreeView<String> treeView = new TreeView(rootItem);
        //TreeView<CustomTreeItem> newTree = new TreeView(rootTest);


        //MenuTOP -> adaptar posteriormente
        /*Button statsButton = new Button("UNDO");
        statsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        statsButton.setOnAction(event -> {
            //openStatsView();
            fileManager.restoreMemento();
        });*/

        MenuBar menuBar = new MenuBar();

        Menu statsMenu = new Menu("Estatísticas");
        MenuItem generalItem = new MenuItem("Gerais");
        MenuItem graphItem = new MenuItem("Gráficos");
        generalItem.setOnAction(e -> openStatsView());
        statsMenu.getItems().addAll(generalItem, graphItem);
        menuBar.getMenus().addAll(statsMenu);

        //Context Menu
        ContextMenu contextMenu = new ContextMenu();
        //Menu Item
        MenuItem addItem = new MenuItem("Adicionar");
        MenuItem removeItem = new MenuItem("Remover");
        MenuItem copyItem = new MenuItem("Copiar");
        MenuItem renameItem = new MenuItem("Renomear");

        addItem.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            AddController addController = new AddController();
            addController.addItem(selectedItem, fileManager);
        });

        removeItem.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            RemoveController removeController = new RemoveController();
            removeController.removeItem(selectedItem, fileManager);
        });

        copyItem.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            CopyController copyController = new CopyController();
            copyController.copyElement(selectedItem, fileManager);

        });

        renameItem.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            RenameController renameController = new RenameController();
            renameController.renameItem(selectedItem, fileManager);
        });

        contextMenu.getItems().addAll(addItem, copyItem, renameItem, removeItem);

        treeView.setCellFactory(param -> {
            TreeCell<String> cell = new TreeCell<>();
            cell.setOnDragDetected(event -> {
                if(!cell.isEmpty()){
                    Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(cell.getItem());
                    db.setContent(content);
                }
            });

            cell.setOnDragOver(event -> {
                if(event.getGestureSource() != cell && event.getDragboard().hasString()){
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            });

            cell.setOnDragDropped(event -> {
                if(event.getGestureSource() != cell && event.getDragboard().hasString()){
                    TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
                    TreeItem<String> newParent = cell.getTreeItem();
                    MoveController moveController = new MoveController();
                    moveController.moveItem(selectedItem, newParent, fileManager);
                }
            });
            cell.textProperty().bind(cell.itemProperty());
            return cell;
        });

        treeView.setContextMenu(contextMenu);

        VBox detailsPanel = new VBox();
        detailsPanel.setMinHeight(100);
        detailsPanel.setStyle("-fx-background-color: lightgray;");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(treeView);
        borderPane.setBottom(detailsPanel);

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            DetailsController details = new DetailsController();
            details.updateDetailsPanel(detailsPanel, newValue, fileManager);
        });

        primaryStage.setScene(new Scene(borderPane, 600,450));
        primaryStage.show();
    }

    private void openStatsView() {
        StatsView.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
