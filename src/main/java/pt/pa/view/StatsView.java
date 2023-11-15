package pt.pa.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.pa.controller.FileManager;
import pt.pa.model.File;

public class StatsView{
    private static FileManager fileManager;

    public StatsView(FileManager fileManager){
        this.fileManager = fileManager;
    }

    public static void display(){
        Stage stage = new Stage();
        stage.setTitle("Estatísticas");
        stage.setTitle("Estatísticas");
        // Layout em grade para as estatísticas
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Espaço ocupado pelos ficheiros
        Label labelSpace = new Label("Espaço ocupado pelos ficheiros:");
        Label valueSpace = new Label(fileManager.getFilesSpace() + "MB");

        // Nº de pastas e nº de ficheiros
        Label labelFolders = new Label("Nº de pastas: ");
        Label valueFolders = new Label(fileManager.getNumberOfFolders().toString());

        Label labelFiles = new Label("Nº de ficheiros: ");
        Label valuesFiles = new Label(fileManager.getNumberOfFiles().toString());

        // Profundidade
        Label labelHeight = new Label("Profundidade:");
        Label valueHeight = new Label(fileManager.getTreeHeight().toString());

        // Top 10 pastas com maior número de descendentes
        Label labelTop10 = new Label("Top 10 pastas com maior número de descendentes:");
        Label valorTop10 = new Label("Pasta 1, Pasta 2, ...");

        gridPane.add(labelSpace, 0, 0);
        gridPane.add(valueSpace, 1, 0);
        gridPane.add(labelFolders, 0, 1);
        gridPane.add(valueFolders, 1, 1);
        gridPane.add(labelFiles, 0, 2);
        gridPane.add(valuesFiles, 1, 2);
        gridPane.add(labelHeight, 0, 3);
        gridPane.add(valueHeight, 1, 3);
        gridPane.add(labelTop10, 0, 4);
        gridPane.add(valorTop10, 1, 4);

        Scene scene = new Scene(gridPane, 400, 200);
        stage.setScene(scene);

        stage.show();
    }
}
