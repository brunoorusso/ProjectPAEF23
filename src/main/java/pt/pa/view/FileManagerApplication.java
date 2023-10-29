package pt.pa.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.pa.controller.*;
import pt.pa.model.Element;
import pt.pa.model.Folder;

import java.time.LocalDateTime;

public class FileManagerApplication extends Application{

    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Explorer");

        FileManager fileManager = new FileManager();
        Folder root = fileManager.createRootFolder();
        //Criar Root Folder
        TreeItem<String> rootItem = new TreeItem(root.getName());
        //Criar TreeView
        TreeView<String> treeView = new TreeView(rootItem);
        //Update Details

        //MenuTOP -> adaptar posteriormente
        Button statsButton = new Button("Estatísticas");
        statsButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        statsButton.setOnAction(event -> {
            openStatsView();
        });

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

        });

        renameItem.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            RenameController renameController = new RenameController();
            renameController.renameItem(selectedItem, fileManager);
        });

        contextMenu.getItems().addAll(addItem, copyItem, renameItem, removeItem);

        treeView.setContextMenu(contextMenu);

        VBox detailsPanel = new VBox();
        detailsPanel.setMinHeight(100);
        detailsPanel.setStyle("-fx-background-color: lightgray;");

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(statsButton);
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
