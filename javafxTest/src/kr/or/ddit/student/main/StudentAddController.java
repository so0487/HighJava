package kr.or.ddit.student.main;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.ddit.student.service.IStudentService;
import kr.or.ddit.student.service.StudentServiceImpl;
import kr.or.ddit.student.vo.StudentVO;
import util.AlertUtil;

public class StudentAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtKor;

    @FXML
    private TextField txtEng;

    @FXML
    private TextField txtMat;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;
    
    private StudentMainController stdMainController;

    public StudentMainController getStdMainController() {
		return stdMainController;
	}

	public void setStdMainController(StudentMainController stdMainController) {
		this.stdMainController = stdMainController;
	}

	// 취소 버튼
    @FXML
    void btnCancelClick(ActionEvent event) {
    	// 현재 창의 Stage객체를 구해서 창 닫기를 한다.
    	Stage currentStage = (Stage) btnCancel.getScene().getWindow(); 
    	currentStage.close();
    }

    // 저장 버튼
    @FXML
    void btnSaveClick(ActionEvent event) {
    	String name = txtName.getText();
    	String strKor = txtKor.getText();
    	String strMat = txtMat.getText();
    	String strEng = txtEng.getText();
    	
    	if(name.isEmpty()) {
    		AlertUtil.warnMsg("자료입력", "이름을 입력하세요.");
    		txtName.requestFocus();
    		return;
    	}
    	
    	if(!Pattern.matches("^[0-9]{1,3}$", strKor)) {
    		AlertUtil.warnMsg("자료입력", "국어점수를 정확히 입력하세요.");
    		txtKor.requestFocus();
    		return;
    	}
    	
    	if(!Pattern.matches("^[0-9]{1,3}$", strEng)) {
    		AlertUtil.warnMsg("자료입력", "영어점수를 정확히 입력하세요.");
    		txtEng.requestFocus();
    		return;
    	}
    	
    	if(!Pattern.matches("^[0-9]{1,3}$", strMat)) {
    		AlertUtil.warnMsg("자료입력", "수학점수를 정확히 입력하세요.");
    		txtMat.requestFocus();
    		return;
    	}
    	
    	StudentVO stdvo = new StudentVO(name, Integer.parseInt(strKor),
    			Integer.parseInt(strEng), Integer.parseInt(strMat));
    	
    	int cnt = service.insertStudent(stdvo);
    	
    	if(cnt>0) {
    		AlertUtil.infoMsg("입력결과", name + " 학생의 성적을 추가했습니다.");
    		
    		stdMainController.setData();  // 메인 창의 자료를 갱신한다.
    	}else {
    		AlertUtil.infoMsg("입력결과", "자료 추가 실패!!!");
    	}
    	
    	txtName.clear();
    	txtKor.clear();
    	txtMat.clear();
    	txtEng.clear();

    }
    private IStudentService service;

    @FXML
    void initialize() {
    	service = StudentServiceImpl.getInstance();
    }
}
