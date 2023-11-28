package pt.pa.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.pa.controller.*;
import pt.pa.model.*;

import java.util.Map;

/**
 * Classe FileManagerApplication representa a aplicação principal do FileSystem.
 * Utiliza a biblioteca JavaFX para criar uma interface gráfica que permite interações com o FileSystem.
 */
public class FileManagerApplication extends Application{

    /**
     * O painel de detalhes exibido na parte inferior da interface.
     */
    private VBox detailsPanel;

    /**
     * Inicia a aplicação, criando a interface gráfica e configurando os controladores de eventos.
     *
     * @param primaryStage O palco principal da aplicação.
     */
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File System");
        detailsPanel = new VBox();
        detailsPanel.setMinHeight(100);
        detailsPanel.setStyle("-fx-background-color: lightgray;");
        DetailsController detailsController = new DetailsController(detailsPanel);
        Subject subject = new Subject();
        subject.addObserver(detailsController);

        FileManager fileManager = new FileManager();
        Folder root = fileManager.createRootFolder();

        TreeItem<String> rootItem = new TreeItem<>(root.getName());
        TreeView<String> treeView = new TreeView(rootItem);

        Button undoButton = new Button("UNDO");
        undoButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        undoButton.setOnAction(event -> {
            //fileManager.restoreMemento();
        });

        MenuBar menuBar = new MenuBar();

        Menu actions = new Menu("Opções");
        MenuItem undoItem = new MenuItem("Undo");
        undoItem.setOnAction(e -> fileManager.restoreMemento());
        actions.getItems().add(undoItem);

        Menu statsMenu = new Menu("Estatísticas");
        MenuItem generalItem = new MenuItem("Gerais");
        MenuItem graphItem = new MenuItem("Gráficos");
        generalItem.setOnAction(e -> openStatsView(fileManager));
        graphItem.setOnAction(e -> openBarChartView(fileManager));

        statsMenu.getItems().addAll(generalItem, graphItem);

        menuBar.getMenus().addAll(actions, statsMenu);

        ContextMenu contextMenu = new ContextMenu();

        MenuItem addItem = new MenuItem("Adicionar");
        MenuItem removeItem = new MenuItem("Remover");
        MenuItem copyItem = new MenuItem("Copiar");
        MenuItem renameItem = new MenuItem("Renomear");


        addItem.setOnAction(event -> {
            TreeItem<String> selectedItem = treeView.getSelectionModel().getSelectedItem();
            fileManager.createMemento();
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

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(treeView);
        borderPane.setBottom(detailsPanel);

        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Element selectedElement = fileManager.getElementByName(newValue.getValue());
            subject.setSelectedElement(selectedElement);

        });

        primaryStage.setScene(new Scene(borderPane, 600,450));
        primaryStage.show();
    }

    /**
     * Abre a visualização de estatísticas gerais.
     *
     * @param fileManager O file manager utilizado para obter os dados estatísticos.
     */
    private void openStatsView(FileManager fileManager) {
        StatsView statsView = new StatsView(fileManager);
        statsView.display();
    }

    /**
     * Abre a visualização dos gráficos de barras.
     *
     * @param fileManager O file manager utilizado para obter os dados para os gráficos.
     */
    private void openBarChartView(FileManager fileManager){
        BarChartView barChartView = new BarChartView(fileManager);
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Selecione o ano");
        dialog.setHeaderText("Ano:");

        dialog.showAndWait().ifPresent(result -> {
            Map<String, Integer> createdData = fileManager.getElementsByYear(Integer.parseInt(result), Element::getCreationDate);
            Map<String, Integer> modifiedData = fileManager.getElementsByYear(Integer.parseInt(result), Element::getModifiedDate);
            barChartView.display(createdData, modifiedData, Integer.parseInt(result));
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
