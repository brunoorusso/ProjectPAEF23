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
 * Classe BarChartView representa uma visualização de gráfico de barras para fins estatísticos.
 * Recorre à biblioteca JavaFX para criar gráficos de barras com base nos dados fornecidos.
 */
public class BarChartView {

    /**
     * Instância de File Manager utilizada para obter dados para o gráfico.
     */
    private static FileManager fileManager;

    /**
     * Construtor que cria uma nova instância de BarChartView.
     *
     * @param fileManager file manager utilizado para obter dados para o gráfico.
     */
    public BarChartView(FileManager fileManager){
        this.fileManager = fileManager;
    }

    /**
     * Exibe um gráfico de barras com base nos dados fornecidos.
     *
     * @param createdData  Mapa com a informação dos dados de criação (mês -> quantidade).
     * @param modifiedData Mapa com a informação dos dados de modificação (mês -> quantidade).
     * @param year         O ano para o qual as estatísticas serão exibidas.
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
     * Cria um gráfico de barras com base nos dados fornecidos.
     *
     * @param data  Mapa com a informação dos dados para o gráfico (chave -> valor).
     * @param title Título do gráfico.
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
