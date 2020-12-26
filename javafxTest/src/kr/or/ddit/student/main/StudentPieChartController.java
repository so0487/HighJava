package kr.or.ddit.student.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import kr.or.ddit.student.vo.StudentVO;

public class StudentPieChartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClose;

    @FXML
    private PieChart stdPieChart;

    @FXML
    void btnCloseClick(ActionEvent event) {
    	Stage currentStage = (Stage) btnClose.getScene().getWindow();
    	currentStage.close();
    }

    // PieChart에 데이터를 셋팅해서 화면에 보여주는 메서드
    public void showPieChart(StudentVO stdVo) {
    	stdPieChart.setTitle(stdVo.getStd_name() + "학생의 성적 분포");
    	
    	stdPieChart.setData(FXCollections.observableArrayList(
    		new PieChart.Data("국어", stdVo.getStd_kor()),
    		new PieChart.Data("영어", stdVo.getStd_eng()),
    		new PieChart.Data("수학", stdVo.getStd_mat())
    	));
    }
    
    @FXML
    void initialize() {

    }
}
