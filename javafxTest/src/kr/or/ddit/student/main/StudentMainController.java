package kr.or.ddit.student.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.student.service.IStudentService;
import kr.or.ddit.student.service.StudentServiceImpl;
import kr.or.ddit.student.vo.StudentVO;

public class StudentMainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<StudentVO> stdTable;

    @FXML
    private TableColumn<?, ?> stdNameCol;

    @FXML
    private TableColumn<?, ?> stdKorCol;

    @FXML
    private TableColumn<?, ?> stdEngCol;

    @FXML
    private TableColumn<?, ?> stdMatCol;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnViewBarChart;
    
    private Stage mainStage;   // 메인 창의 Stage객체 변수

    // 추가버튼 클릭
    @FXML
    void btnAddClick(ActionEvent event) throws IOException {
    	mainStage = (Stage) btnAdd.getScene().getWindow();

    	Stage addStage = new Stage(StageStyle.UTILITY);
    	addStage.initModality(Modality.WINDOW_MODAL);
    	addStage.initOwner(mainStage);
    	/*
    	Parent addRoot = FXMLLoader.load(
    		StudentMain.class.getResource("../fxml/StudentAdd.fxml")); 
    	*/
    	
    	// fxml문서에 설정한 controller객체를 구하는 방법
    	FXMLLoader loader = new FXMLLoader(
    			StudentMain.class.getResource("../fxml/StudentAdd.fxml"));
    	Parent addRoot = loader.load();
    	
    	StudentAddController stdAddController = loader.getController();
    	
    	// addController에 mainController객체를 저장한다.
    	// this는 StudentMainController 이다.
    	stdAddController.setStdMainController(this);
    	
    	//-----------------------------------------------------------
    	
    	Scene addScene = new Scene(addRoot);
    	addStage.setScene(addScene);
    	addStage.setTitle("자료추가");
    	addStage.show();
    	
    }

    // 막대 그래프 버튼 클릭
    @FXML
    void btnViewBarChartClick(ActionEvent event) throws IOException {
    	mainStage = (Stage) btnAdd.getScene().getWindow();
    	
    	Stage barStage = new Stage(StageStyle.UTILITY);
    	barStage.initModality(Modality.WINDOW_MODAL);
    	barStage.initOwner(mainStage);
    	
    	FXMLLoader loader = 
    		new FXMLLoader(
    				StudentMain.class.getResource(
    						"../fxml/StudentBarChart.fxml"));
    	
    	Parent barRoot = loader.load();
    	
    	StudentBarChartController barController = loader.getController();
    	
    	barController.showBarChart(stdList);
    	
    	Scene barScene = new Scene(barRoot);
    	barStage.setScene(barScene);
    	barStage.setTitle("막대 그래프");
    	barStage.show();
    	
    	
    }

    // 테이블뷰 클릭
    @FXML
    void stdTableClicked(MouseEvent event) throws IOException {
    	mainStage = (Stage) btnAdd.getScene().getWindow();
    	
    	if(stdTable.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	// 테이블뷰에서 선택한 객체(StudentVO) 구하기
    	StudentVO stdVo = stdTable.getSelectionModel().getSelectedItem();
    	
    	Stage pieStage = new Stage(StageStyle.UTILITY);
    	pieStage.initModality(Modality.WINDOW_MODAL);
    	pieStage.initOwner(mainStage);
    	
    	FXMLLoader loader = 
    		new FXMLLoader(
    			StudentMain.class.getResource("../fxml/StudentPieChart.fxml"));
    	Parent pieRoot = loader.load();
    	
    	StudentPieChartController pieController = loader.getController();
    	
    	pieController.showPieChart(stdVo);
    	
    	Scene pieScene = new Scene(pieRoot);
    	pieStage.setScene(pieScene);
    	pieStage.setTitle("원형 그래프");
    	pieStage.show();
    	
    	
    }

    private IStudentService service;
    private ObservableList<StudentVO> stdList;
    
    
    @FXML
    void initialize() {
    	service = StudentServiceImpl.getInstance();
    	
    	stdNameCol.setCellValueFactory(new PropertyValueFactory<>("std_name"));
    	stdKorCol.setCellValueFactory(new PropertyValueFactory<>("std_kor"));
    	stdMatCol.setCellValueFactory(new PropertyValueFactory<>("std_mat"));
    	stdEngCol.setCellValueFactory(new PropertyValueFactory<>("std_eng"));

    	setData();   // 데이터를 출력하는 메서드 호출
    }
    
    // 전체 학생 정보를 가져와 테이블뷰에 출력하는 메서드
    public void setData() {
    	stdList = FXCollections.observableArrayList(
    		service.getAllStudent()
    	);
    	
    	stdTable.setItems(stdList);
    }
    
    
}





