package kr.or.ddit.student.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kr.or.ddit.student.vo.StudentVO;

public class StudentBarChartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

    @FXML
    private BarChart<String, Number> stdBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    void btnCloseClick(ActionEvent event) {
    	Stage currentStage = (Stage) btnClose.getScene().getWindow();
    	currentStage.close();
    }
    
    public void showBarChart(ObservableList<StudentVO> stdList) {
    	// 각 과목을 Series로 만들어서 데이터를 셋팅한다.
    	
    	// 국어
    	XYChart.Series<String, Number> serKor = 
    			new XYChart.Series<String, Number>();
    	serKor.setName("국어");
    	
    	// 영어
    	XYChart.Series<String, Number> serEng = 
    			new XYChart.Series<String, Number>();
    	serEng.setName("영어");
    	
    	// 수학
    	XYChart.Series<String, Number> serMat = 
    			new XYChart.Series<String, Number>();
    	serMat.setName("수학");
    	
    	for(StudentVO stdVo : stdList) {
    		// 국어점수 셋팅
    		serKor.getData().add(
    			new Data<String, Number>(
    					stdVo.getStd_name(), stdVo.getStd_kor()));
    		
    		// 영어점수 셋팅
    		serEng.getData().add(
    				new Data<String, Number>(
    						stdVo.getStd_name(), stdVo.getStd_eng()));
    		
    		// 수학점수 셋팅
    		serMat.getData().add(
    				new Data<String, Number>(
    						stdVo.getStd_name(), stdVo.getStd_mat()));
    		
    	}
    	// Series객체를 Bar차트에 추가한다.
    	stdBarChart.getData().addAll(serKor, serEng, serMat);
    	
    	
    }

    @FXML
    void initialize() {

    }
}
