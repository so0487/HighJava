package kr.or.ddit.zip.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.Provider.Service;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import kr.or.ddit.zip.service.IZipTbService;
import kr.or.ddit.zip.vo.ZipTbVO;
import util.AlertUtil;

public class ZipSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbSelect;

    @FXML
    private TextField txtData;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<ZipTbVO> zipTable;

    @FXML
    private TableColumn<?, ?> zipCol;

    @FXML
    private TableColumn<?, ?> sidoCol;

    @FXML
    private TableColumn<?, ?> gugunCol;

    @FXML
    private TableColumn<?, ?> dongCol;

    @FXML
    private TableColumn<?, ?> riCol;

    @FXML
    private TableColumn<?, ?> bldgCol;

    @FXML
    private TableColumn<?, ?> bunjiCol;

    
    
    //검색버튼 클릭 이벤트
    
    private IZipTbService service;
    @FXML
    void zipSearch(ActionEvent event) throws RemoteException {
    	
    	
    	//콤보박스에서 선택한 값 가져오기
    	String strSelect = cmbSelect.getValue();
    	
    	//검색단어 가져오기
    	String searchData = txtData.getText();
    	
    	
    	if(searchData.isEmpty()) {
    		AlertUtil.warnMsg("검색오류", "검색할 단어를 입력하세요");
    		txtData.requestFocus();
    		return;
    	}
    	
    	List<ZipTbVO>zipList = null;
    	if(strSelect.equals("동이름")) {
    		zipList = service.zipSearchDong(searchData); 
    	}else {
    		zipList = service.zipSearchCode(searchData);
    	}
    	
    	zipTable.setItems(FXCollections.observableArrayList(zipList));
    }

    @FXML
    void initialize() {
    	//TableView의 각 컬럼 설정
        assert cmbSelect != null : "fx:id=\"cmbSelect\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert txtData != null : "fx:id=\"txtData\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert zipTable != null : "fx:id=\"zipTable\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert zipCol != null : "fx:id=\"zipCol\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert sidoCol != null : "fx:id=\"sidoCol\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert gugunCol != null : "fx:id=\"gugunCol\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert dongCol != null : "fx:id=\"dongCol\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert riCol != null : "fx:id=\"riCol\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert bldgCol != null : "fx:id=\"bldgCol\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        assert bunjiCol != null : "fx:id=\"bunjiCol\" was not injected: check your FXML file 'ZipSearch.fxml'.";
        
        
        //ComboBox 설정
        cmbSelect.getItems().addAll("동이름","우편번호");
        cmbSelect.setValue("동이름");
        
        
        try {
			Registry reg = LocateRegistry.getRegistry("localhost",9988);
			service = (IZipTbService) reg.lookup("zipservice");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
