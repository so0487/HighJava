package basic.chart;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class BarChartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<?, ?> barChart;

	@FXML
	void initialize() {
		assert barChart != null : "fx:id=\"barChart\" was not injected: check your FXML file 'BarChart.fxml'.";

		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis();


		BarChart<String, Number>bc
		= new BarChart<String, Number>(xAxis, yAxis);

		bc.setTitle("나라별 데이터");
		xAxis.setLabel("나라명");
		yAxis.setLabel("데이터");

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

		s2.getData().add(new XYChart.Data<String, Number>("한국",100));
		s2.getData().add(new XYChart.Data<String, Number>("미국",150));
		s2.getData().add(new XYChart.Data<String, Number>("영국",70));
		s2.getData().add(new XYChart.Data<String, Number>("중국",30));
		
		
		
		

		XYChart.Series<String, Number> s3 = 
				new XYChart.Series<String, Number>();
			
		s3.setName("3월");
		
		s3.getData().add(new XYChart.Data<String, Number>("한국",100));
		s3.getData().add(new XYChart.Data<String, Number>("미국",150));
		s3.getData().add(new XYChart.Data<String, Number>("영국",70));
		s3.getData().add(new XYChart.Data<String, Number>("중국",30));
		
		
		bc.getData().addAll(s1,s2,s3);
		
	}
}
