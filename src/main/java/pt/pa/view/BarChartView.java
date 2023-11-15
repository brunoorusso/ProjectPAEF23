package pt.pa.view;

import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import pt.pa.controller.FileManager;

public class BarChartView {

    private static FileManager fileManager;

    public BarChartView(FileManager fileManager){
        this.fileManager = fileManager;
    }

    public static void display(){
        Stage stage = new Stage();
        stage.setTitle("Estatísticas");
        stage.setTitle("Estatísticas");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Janeiro", 20));
        series.getData().add(new XYChart.Data<>("Fevereiro", 40));
        series.getData().add(new XYChart.Data<>("Março", 15));
        series.getData().add(new XYChart.Data<>("Abril", 30));
        series.getData().add(new XYChart.Data<>("Maio", 30));
        series.getData().add(new XYChart.Data<>("Junho", 30));
        series.getData().add(new XYChart.Data<>("Julho", 30));
        series.getData().add(new XYChart.Data<>("Agosto", 30));
        series.getData().add(new XYChart.Data<>("Setembro", 30));
        series.getData().add(new XYChart.Data<>("Outubro", 30));
        series.getData().add(new XYChart.Data<>("Novembro", 30));
        series.getData().add(new XYChart.Data<>("Dezembro", 30));


        // Criar o eixo X (categórico)
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Meses");

        // Criar o eixo Y (numérico)
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Número");

        // Criar o gráfico de barras
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Número de Pastas/Ficheiros criados por mês");
        barChart.getData().add(series);

        // Criar a cena
        Scene scene = new Scene(barChart, 600, 400);

        // Configurar o palco
        stage.setTitle("JavaFX Bar Chart Example");
        stage.setScene(scene);

        // Exibir o palco
        stage.show();
    }
}
