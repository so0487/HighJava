package basic.chart;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

public class PieChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		ObservableList<PieChart.Data>pieData = 
				FXCollections.observableArrayList(
						new PieChart.Data("오렌지", 40),
						new PieChart.Data("포도", 80),
						new PieChart.Data("복숭아", 50),
						new PieChart.Data("사과", 70)
						);
		
		//PieChart에 데이터 설정하는 방법
		
		//방법1
		//PieChart chart = new PieChart(pieData);
		
		//방법2
		PieChart chart = new PieChart(pieData);
		chart.setData(pieData);
		
		chart.setTitle("오늘의 과일 시세");	//차트 제목
		//chart.setLegendSide(Side.LEFT);	//범례의 표시 위치 지정
		//chart.setLegendSide(Side.TOP);	//범례의 표시 위치 지정
		chart.setLegendSide(Side.RIGHT);	//범례의 표시 위치 지정
		chart.setLabelLineLength(50);	//각 파이의 Lable과의 연결선 길이
		chart.setLegendVisible(true);	//범례 표시 여부
		chart.setLabelsVisible(false);	//각 파이의 Label 표시 여부
		
		Scene scene = new Scene(chart);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pie 차트 연습");
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
