package kr.or.ddit.mymember.main;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
import kr.or.ddit.mymember.service.IMemberService;
import kr.or.ddit.mymember.vo.MemberVO;
import util.AlertUtil;

public class MemberController {

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

    // 추가버튼
    @FXML
    void dataAdd(ActionEvent event) {
    	// TextField들을 편집 가능 상태로 만든다.
    	txtId.setEditable(true);
    	txtName.setEditable(true);
    	txtTel.setEditable(true);
    	txtAddr.setEditable(true);
    	
    	// 확인, 취소버튼 활성화
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	// 추가, 수정, 삭제, 테이블뷰를  비활성화
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	table.setDisable(true);
    	
    	// TextField들의 내용을 모두 삭제한다.
    	txtId.clear();
    	txtName.clear();
    	txtTel.clear();
    	txtAddr.clear();
    	
    	txtId.requestFocus();  // 포커스 주기
    	strWork = "add";	   
    	
    }

    // 취소버튼
    @FXML
    void dataCancel(ActionEvent event) {
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
    	
    	// 이전 작업 전에 TableView에서 선택된 데이터가 있으면
    	// 해당 데이터를 TextField에 출력한다.
    	if(!table.getSelectionModel().isEmpty()) {
    		MemberVO mem = table.getSelectionModel().getSelectedItem();
        	
        	txtId.setText(mem.getMem_id());
        	txtName.setText(mem.getMem_name());
        	txtTel.setText(mem.getMem_tel());
        	txtAddr.setText(mem.getMem_addr());
    	}
    	strWork = "";
    	
    }

    // 삭제버튼
    @FXML
    void dataDel(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("자료선택", "삭제할 회원을 선택한 후 사용하세요.");
    		return;
    	}
    	
    	// 삭제할 회원ID 구하기
    	String memId = txtId.getText();
    	
    	int cnt;
		try {
			cnt = service.deleteMember(memId);
			if(cnt>0) {
				AlertUtil.infoMsg("작업결과", memId + " 회원을 삭제했습니다.");
				
				getAllMemberList();  // 삭제 후 회원 전체 데이터 다시 가져오기
				
				txtId.clear();
				txtName.clear();
				txtTel.clear();
				txtAddr.clear();
			}else {
				AlertUtil.warnMsg("작업결과", memId + " 회원 삭제 실패!!!");
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    // 수정버튼
    @FXML
    void dataEdit(ActionEvent event) {
    	// 수정할 데이터를 선택했는지 여부 검사
    	if(table.getSelectionModel().isEmpty()) {
    		AlertUtil.warnMsg("자료선택", "수정할 회원을 선택한 후 사용하세요");
    		return;
    	}
    	
    	// TextField들을 편집 가능 상태로 만든다. (txtId 제외)
    	// txtId.setEditable(true);
    	txtName.setEditable(true);
    	txtTel.setEditable(true);
    	txtAddr.setEditable(true);
    	
    	// 확인, 취소버튼 활성화
    	btnOk.setDisable(false);
    	btnCancel.setDisable(false);
    	
    	// 추가, 수정, 삭제, 테이블뷰를  비활성화
    	btnAdd.setDisable(true);
    	btnEdit.setDisable(true);
    	btnDel.setDisable(true);
    	table.setDisable(true);
    	
    	txtName.requestFocus();
    	strWork = "edit";
    	
    }

    // 확인버튼
    @FXML
    void dataRun(ActionEvent event) {
    	String id = txtId.getText();
		String name = txtName.getText();
		String tel = txtTel.getText();
		String addr = txtAddr.getText();
		
		if(id.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "회원 ID를 입력하세요.");
			txtId.requestFocus();
			return;
		}
		
		if(name.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "회원 이름을 입력하세요.");
			txtName.requestFocus();
			return;
		}
		
		if(tel.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "회원 전화번호를 입력하세요.");
			txtTel.requestFocus();
			return;
		}
		if(addr.isEmpty()) {
			AlertUtil.warnMsg("입력오류", "회원 주소를 입력하세요.");
			txtAddr.requestFocus();
			return;
		}
		
		// 입력한 정보를 MemberVO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
    	
    	
    	// 추가버튼을 누른 후의 작업인지 검사
    	if("add".equals(strWork)) {  // 추가 작업
    		
    		// VO값을 DB에 저장
    		int cnt;
			try {
				cnt = service.insertMember(memVo);
				if(cnt>0) {
					AlertUtil.infoMsg("작업결과", "추가 성공");
				}else {
					AlertUtil.warnMsg("작업결과", "추가 실패!!");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		
    	}else if("edit".equals(strWork)) {  // 수정 작업
    		
    		int cnt;
			try {
				cnt = service.updateMember(memVo);
				if(cnt>0) {
					AlertUtil.infoMsg("작업결과", "수정 작업 성공");
				}else {
					AlertUtil.warnMsg("작업결과", "수정 작업 실패!!");
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
    	// 추가, 수정 작업이 완료된 후 전체 회원 정보를 다시 가져온다.
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

    // TableView를 클릭했을 때
    @FXML
    void tableClick(MouseEvent event) {
    	if(table.getSelectionModel().isEmpty()) {
    		return;
    	}
    	
    	// 선택한 데이터 가져오기
    	MemberVO mem = table.getSelectionModel().getSelectedItem();
    	
    	txtId.setText(mem.getMem_id());
    	txtName.setText(mem.getMem_name());
    	txtTel.setText(mem.getMem_tel());
    	txtAddr.setText(mem.getMem_addr());
    	
    }

    private ObservableList<MemberVO> memList;
    private IMemberService service;
    private String strWork = "";	// 추가, 수정 상태를 구분하기 위한 변수 선언
    
    
    @FXML
    void initialize() {
    	//service = MemberServiceImpl.getInstance();  // Service객체 생성
    	
    	
    	Registry reg=null;
    	
    	try {
			reg = LocateRegistry.getRegistry("localhost",9988);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
    		service = (IMemberService) reg.lookup("imemberservice");
    		
        	// TableView의 각 컬럼 설정
        	idCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
        	nameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
        	telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
        	addrCol.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
        	
        	getAllMemberList();
    		
    		
    		
    	} catch (AccessException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (RemoteException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} catch (NotBoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	
    	
    }
    
    private void getAllMemberList() {
    	List<MemberVO> memData;
		try {
			memData = service.getAllMemberList();
			memList = FXCollections.observableArrayList(memData);
			table.setItems(memList);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
