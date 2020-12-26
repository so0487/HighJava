package kr.or.ddit.lprod.main;

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
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.vo.LprodVO;

public class LprodController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<LprodVO> lprodTable;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> guCol;

    @FXML
    private TableColumn<?, ?> nmCol;

    @FXML
    void tableClicked(MouseEvent event) {

    }
    
    // TableView에 넣을 데이터가 저장될 List 객체 변수 선언
    private ObservableList<LprodVO> lprodList;
    
    private ILprodService service;  // Service객체 변수 선언
    
    @FXML
    void initialize() {
    	// Service객체 생성
    	//service = LprodServiceImpl.getInstance();
    	Registry reg = null;
		try {
			reg = LocateRegistry.getRegistry("localhost",9988);
			//reg = LocateRegistry.getRegistry("192.168.207.33",9988);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			service = (ILprodService) reg.lookup("lprodservice");
			// TableView의 컬럼들과 VO를 연결한다.
			idCol.setCellValueFactory(
					new PropertyValueFactory<>("lprod_id"));
			guCol.setCellValueFactory(
					new PropertyValueFactory<>("lprod_gu"));
			nmCol.setCellValueFactory(
					new PropertyValueFactory<>("lprod_nm"));
			
			// DB에서 자료 가져와서 ObservableList객체로 변환하기
			List<LprodVO> list = service.getAllLprod();
			lprodList = FXCollections.observableArrayList(list);
			
			// TableVie에 데이터 셋팅
			lprodTable.setItems(lprodList);
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
}
