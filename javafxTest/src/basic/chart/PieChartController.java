package basic.chart;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

public class PieChartController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private PieChart pieChart;

	@FXML
	void initialize() {
		assert pieChart != null : "fx:id=\"pieChart\" was not injected: check your FXML file 'PieChartTestFxml.fxml'.";

		ObservableList<PieChart.Data>pieData=
				FXCollections.observableArrayList(
						new PieChart.Data("오렌지", 40),
						new PieChart.Data("포도", 80),
						new PieChart.Data("복숭아", 50),
						new PieChart.Data("사과", 70)

						);


		PieChart chart = new PieChart(pieData);

		chart.setLegendSide(Side.LEFT);
		chart.setLabelLineLength(50);
		chart.setLegendVisible(true);
		chart.setLabelsVisible(true);



	}
}
