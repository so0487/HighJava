package basic.chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class BarChartTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		//X축, Y축을 담당하는 축 객체 생성
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis	yAxis = new NumberAxis();

		BarChart<String, Number> bc 
		= new BarChart<String, Number>(xAxis, yAxis);
		
		bc.setTitle("나라별 데이터");
		xAxis.setLabel("나라명");
		yAxis.setLabel("데이터");
		
		//출력할 데이터 설정
		XYChart.Series<String, Number> s1 = 
				new XYChart.Series<String, Number>();
		
		s1.setName("1월");
		s1.getData().add(new XYChart.Data<String, Number>("한국",100));
		s1.getData().add(new XYChart.Data<String, Number>("미국",150));
		s1.getData().add(new XYChart.Data<String, Number>("영국",70));
		s1.getData().add(new XYChart.Data<String, Number>("중국",30));
		
		
		
		
		
		
		XYChart.Series<String, Number> s2 = 
				new XYChart.Series<String, Number>();
		
		s2.setName("2월");
		s2.getData().add(new XYChart.Data<String, Number>("한국",80));
		s2.getData().add(new XYChart.Data<String, Number>("미국",300));
		s2.getData().add(new XYChart.Data<String, Number>("영국",110));
		s2.getData().add(new XYChart.Data<String, Number>("중국",60));
		
		
		
		XYChart.Series<String, Number> s3 = 
				new XYChart.Series<String, Number>();
		
		s3.setName("3월");
		s3.getData().add(new XYChart.Data<String, Number>("한국",150));
		s3.getData().add(new XYChart.Data<String, Number>("미국",100));
		s3.getData().add(new XYChart.Data<String, Number>("영국",60));
		s3.getData().add(new XYChart.Data<String, Number>("중국",100));
		
		
		
		bc.getData().addAll(s1,s2,s3);
		
		Scene scene = new Scene(bc,600,400);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("BarChart연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
