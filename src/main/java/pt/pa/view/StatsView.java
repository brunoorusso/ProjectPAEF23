package pt.pa.view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatsView {
    public static void display(){
        Stage stage = new Stage();
        stage.setTitle("Estatísticas");
        stage.setTitle("Estatísticas");

        // Layout em grade para as estatísticas
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Espaço ocupado pelos ficheiros
        Label labelEspaco = new Label("Espaço ocupado pelos ficheiros:");
        Label valorEspaco = new Label("100 MB"); // Substitua com o valor real

        // Nº de pastas e nº de ficheiros
        Label labelNumeros = new Label("Nº de pastas e nº de ficheiros:");
        Label valorNumeros = new Label("50 pastas, 200 ficheiros"); // Substitua com os valores reais

        // Profundidade
        Label labelProfundidade = new Label("Profundidade:");
        Label valorProfundidade = new Label("5"); // Substitua com o valor real

        // Top 10 pastas com maior número de descendentes
        Label labelTop10 = new Label("Top 10 pastas com maior número de descendentes:");
        Label valorTop10 = new Label("Pasta 1, Pasta 2, ..."); // Substitua com os valores reais

        // Adicione os elementos ao layout em grade
        gridPane.add(labelEspaco, 0, 0);
        gridPane.add(valorEspaco, 1, 0);
        gridPane.add(labelNumeros, 0, 1);
        gridPane.add(valorNumeros, 1, 1);
        gridPane.add(labelProfundidade, 0, 2);
        gridPane.add(valorProfundidade, 1, 2);
        gridPane.add(labelTop10, 0, 3);
        gridPane.add(valorTop10, 1, 3);

        Scene scene = new Scene(gridPane, 400, 200);
        stage.setScene(scene);

        stage.show();
    }
}
