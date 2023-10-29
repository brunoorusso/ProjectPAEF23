package pt.pa.view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatsView {
    public static void display(){
        Stage statsStage = new Stage();
        statsStage.setTitle("Estatísticas");

        VBox vBox = new VBox();

        Scene statsScene = new Scene(vBox, 400, 300);
        statsStage.setScene(statsScene);
        statsStage.show();
    }
}
