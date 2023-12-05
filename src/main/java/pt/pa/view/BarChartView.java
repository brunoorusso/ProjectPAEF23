package pt.pa.view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.pa.controller.FileManager;
import java.util.Map;

/**
 * Classe BarChartView representa uma visualizacao de grafico de barras para fins estatísticos.
 * Recorre a biblioteca JavaFX para criar graficos de barras com base nos dados fornecidos.
 * @author Bruno Russo 202001410
 */
public class BarChartView {

    /**
     * Instância de File Manager utilizada para obter dados para o grafico.
     */
    private static FileManager fileManager;

    /**
     * Construtor que cria uma nova instancia de BarChartView.
     *
     * @param fileManager file manager utilizado para obter dados para o grafico.
     */
    public BarChartView(FileManager fileManager){
        this.fileManager = fileManager;
    }

    /**
     * Exibe um grafico de barras com base nos dados fornecidos.
     *
     * @param createdData  Mapa com a informacao dos dados de criacao (mes -> quantidade).
     * @param modifiedData Mapa com a informacao dos dados de modificacao (mes -> quantidade).
     * @param year         O ano para o qual as estatisticas serao exibidas.
     */
    public static void display(Map<String, Integer> createdData, Map<String, Integer> modifiedData, int year){
        Stage stage = new Stage();
        stage.setTitle("Estatísticas");

        BarChart<String, Number> createdBarChart = createBarChart(createdData, "Número de Pastas/Ficheiros criados por mês - " + year);
        BarChart<String, Number> modifiedBarChart = createBarChart(modifiedData, "Número de Pastas/Ficheiros modificados por mês - " + year);

        VBox vbox = new VBox(createdBarChart, modifiedBarChart);

        Scene scene = new Scene(vbox, 1200, 800);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cria um grafico de barras com base nos dados fornecidos.
     *
     * @param data  Mapa com a informacao dos dados para o grafico (chave -> valor).
     * @param title Título do grafico.
     * @return Um objeto BarChart configurado com os dados fornecidos.
     */
    private static BarChart<String, Number> createBarChart(Map<String, Integer> data, String title) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Meses");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Número");
        yAxis.setForceZeroInRange(false);

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle(title);
        barChart.getData().add(series);
        barChart.setLegendVisible(false);

        return barChart;
    }
}
