package myMember.main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import myMember.service.IMemberService;
import myMember.service.MemberServiceImpl;
import myMember.vo.MemberVO;
import util.AlertUtil;

public class MyMemberController {
    private ObservableList<MemberVO> memList;
    private IMemberService service;
    private String strWork = "";	// 추가, 수정 상태를 구분하기 위한 변수 선언
	
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtAddr;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private TableView<MemberVO> table;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> telCol;

    @FXML
    private TableColumn<?, ?> addrCol;

    @FXML
    void dataAdd(ActionEvent event) {
    	//텍스트 필드를 수정가능하게 해준다.
    	txtId.setEditable(true);
    	txtName.setEditable(true);
    	txtTel.setEditable(true);
    	txtAddr.setEditable(true);
    	
    	
    	
    	//확인, 취소 버튼 활성화
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	
    	//추가, 수정, 삭제 버튼 비활성화
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	
    	
    	//텍스트필드의 내용 삭제
    	txtId.clear();
    	txtName.clear();
    	txtTel.clear();
    	txtAddr.clear();
    	
    	
    	//포커스
    	txtId.requestFocus();
    	strWork="add";
    }

    @FXML
    void dataCancel(ActionEvent event) {
    	//텍스트필드를 편집가능
    	txtId.setDisable(false);
    	txtName.setDisable(false);
    	txtTel.setDisable(false);
    	txtAddr.setDisable(false);
    	
    	
    	//확인, 취소 버튼 활성화
    	btnOk.setDisable(true);
    	btnCancel.setDisable(true);
    	
    	
    	//추가, 수정, 삭제, 테이블뷰 비활성화
    	btnAdd.setDisable(false);
    	btnEdit.setDisable(false);
    	btnDel.setDisable(false);
    	table.setDisable(false);
    	
    	
    	
    	//이전 작업 전에 테이블뷰에서 선택된 데이터가 있으면
    	//해당 데이터를 텍스트필드에 출력
    	if(!table.getSelectionModel().isEmpty()) {
    		MemberVO mem = table.getSelectionModel().getSelectedItem();
    		
    		txtId.setText(mem.getMem_id());
    		txtName.setText(mem.getMem_name());
    		txtTel.setText(mem.getMem_tel());
    		txtAddr.setText(mem.getMem_addr());
    		
    	}
    	
    	
    	strWork="";
    
    }

    @FXML
    void dataDel(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("자료선택", "삭제할 자료를 선택하세요");
    		return;
    	}
    	
    	
    	//삭제할 MemId
    	String memId = txtId.getText();
    	
    	int cnt = service.deleteMember(memId);
    	
    	if(cnt>0) {
    		AlertUtil.infoMsg("작업결과", memId+"회원 삭제 완료");
    		
    		getAllMemberList();
    		
    		
    		txtId.clear();
    		txtName.clear();
    		txtTel.clear();
    		txtAddr.clear();
    		
    	}else {
    		AlertUtil.warnMsg("작업결과", memId+"회원삭제 실패");
    	}
    	
    	
    }

    @FXML
    void dataEdit(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("자료선택", "수정할 회원을 선택하세요");
    		return;
    	}
    	
    	
    	txtName.setEditable(true);
    	txtTel.setEditable(true);
    	txtAddr.setEditable(true);
    	
    	
    	
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	
    	
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	table.setDisable(true);
    	
    	
    	txtName.requestFocus();
    	strWork="edit";
    	
    	
    }

    @FXML
    void dataRun(ActionEvent event) {
    	String id = txtId.getText();
    	String name = txtName.getText();
    	String tel = txtTel.getText();
    	String addr = txtAddr.getText();
    	
    	
    	
    	if(id.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "회원ID를 입력하세요");
    		txtId.requestFocus();
    		return;
    	}
    	if(name.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "회원이름을 입력하세요");
    		txtId.requestFocus();
    		return;
    	}
    	if(tel.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "전화번호를 입력하세요");
    		txtId.requestFocus();
    		return;
    	}
    	if(addr.isEmpty()) {
    		AlertUtil.warnMsg("입력오류", "주소를 입력하세요");
    		txtId.requestFocus();
    		return;
    	}
    	
    	
    	
    	
    	
    	//입력한 회원정보를 MemberVO객체에 저장
    	MemberVO memVo = new MemberVO();
    	memVo.setMem_id(id);
    	memVo.setMem_name(name);
    	memVo.setMem_tel(tel);
    	memVo.setMem_addr(addr);
    	
    	
    	
    	
    	//추가 버튼을 누른 후 작업인지 검사
    	
    	if("add".equals(strWork)) {
    		//vo값을 DB에 저장
    		int cnt = service.insertMember(memVo);
    		
    		
    		if(cnt>0) {
    			AlertUtil.infoMsg("작업결과", "추가 성공");
    		}else {
    			AlertUtil.warnMsg("작업결과", "추가 실패");
    		}
    	}
    	
    	
    	//추가, 수정 작업이 완료된 후 전체 회원정보를 다시 가져온다.
    	getAllMemberList();
    	
    	
    	txtId.clear();
		txtName.clear();
		txtTel.clear();
		txtAddr.clear();
		
		
		// TextField들을 편집 가능 상태로 만든다.
    	txtId.setEditable(false);
    	txtName.setEditable(false);
    	txtTel.setEditable(false);
    	txtAddr.setEditable(false);
    	
    	// 확인, 취소버튼 활성화
    	btnOk.setDisable(true);
    	btnCancel.setDisable(true);
    	
    	// 추가, 수정, 삭제, 테이블뷰를  비활성화
    	btnAdd.setDisable(false);
    	btnEdit.setDisable(false);
    	btnDel.setDisable(false);
    	table.setDisable(false);
    	
    	strWork = "";

    }
    
    @FXML
    void tableClick(MouseEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	
    	
    	
    	MemberVO mem = table.getSelectionModel().getSelectedItem();
    	
    	txtId.setText(mem.getMem_id());
    	txtName.setText(mem.getMem_name());
    	txtTel.setText(mem.getMem_tel());
    	txtAddr.setText(mem.getMem_addr());
    }

    @FXML
    void initialize() {
  
    	service = MemberServiceImpl.getInstance();
    	
    	
    	idCol.setCellValueFactory(new PropertyValueFactory<>("memId"));
    	nameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
    	telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
    	addrCol.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
    }
    
    
    
    private void getAllMemberList() {
    	List<MemberVO> memData = service.getAllMemList();
    	memList = FXCollections.observableArrayList(memData);
    	table.setItems(memList);
    }
}
