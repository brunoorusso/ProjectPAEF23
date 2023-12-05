package pt.pa.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.pa.controller.FileManager;
import pt.pa.model.File;

/**
 * A classe StatsView representa uma visualização de estatísticas gerais do FileSystem.
 * Exibe diversas estatísticas, como espaço ocupado pelos arquivos, número de pastas, número de arquivos, profundidade da árvore.
 * @author Rafael Carvalho Martins 201700039
 */
public class StatsView{

    /**
     * O file manager utilizado para obter dados estatísticos.
     */
    private static FileManager fileManager;

    /**
     * Construtor que cria uma nova instância de StatsView.
     *
     * @param fileManager O file manager a ser utilizado para obter os dados estatísticos.
     */
    public StatsView(FileManager fileManager){
        this.fileManager = fileManager;
    }

    /**
     * Exibe uma janela com as estatísticas referentes ao FileSystem.
     */
    public static void display(){
        Stage stage = new Stage();
        stage.setTitle("Estatísticas");
        stage.setTitle("Estatísticas");
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label labelSpace = new Label("Espaço ocupado pelos ficheiros:");
        Label valueSpace = new Label(fileManager.getFilesSpace() + "MB");

        Label labelFolders = new Label("Nº de pastas: ");
        Label valueFolders = new Label(fileManager.getNumberOfFolders().toString());

        Label labelFiles = new Label("Nº de ficheiros: ");
        Label valuesFiles = new Label(fileManager.getNumberOfFiles().toString());

        Label labelHeight = new Label("Profundidade:");
        Label valueHeight = new Label(fileManager.getTreeHeight().toString());

        gridPane.add(labelSpace, 0, 0);
        gridPane.add(valueSpace, 1, 0);
        gridPane.add(labelFolders, 0, 1);
        gridPane.add(valueFolders, 1, 1);
        gridPane.add(labelFiles, 0, 2);
        gridPane.add(valuesFiles, 1, 2);
        gridPane.add(labelHeight, 0, 3);
        gridPane.add(valueHeight, 1, 3);

        Scene scene = new Scene(gridPane, 400, 200);
        stage.setScene(scene);

        stage.show();
    }
}
