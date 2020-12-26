package basic.chart;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PieChart_Fxml extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(PieChart_Fxml.class.getResource("PieChartTestFxml.fxml"));


			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("파이 차트 연습");
			primaryStage.setWidth(500);
			primaryStage.setHeight(500);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}