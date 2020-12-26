package basic.pagination;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.AlertUtil;

public class MemberPageController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

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
	private Pagination pagination;

	@FXML
	private ComboBox<String> cmbSearch;

	@FXML
	private TextField tfSearch;

	@FXML
	private Button btnSearch;


	private MemberService service;
	private int rowPerPage = 15;	//한 화면에 보여줄 레코드 수	ex)10
	private int totalRowCount;		//전체 레코드 수			ex)25	
	private int totalPageCount;		//전체 페이지 수			ex)3



	//검색 데이터와 페이징 처리에 필요한 값을 구성할 Map객체 변수
	private Map<String, String> searchMap;



	@FXML
	void btnSearchClicked(ActionEvent event) {
		String selectSearchField = cmbSearch.getValue();

		if(selectSearchField==null) {
			AlertUtil.warnMsg("검색오류", "검색 항목을 선택하세요");
			return;
		}


		//검색항목에 맞는 DB의 컬럼명 설정
		String searchField = null;

		switch (selectSearchField) {
		case "회원ID": searchField = "mem_id";
		break;
		case "회원이름":searchField = "mem_name";
		break;
		case "전화번호":searchField = "mem_tel";
		break;
		case "주소":searchField = "mem_addr";
		break;
		}


		//검색 단어 가져오기
		String searchWord = tfSearch.getText();

		//검색항목과 검색 단어를 Map에 저장
		searchMap.put("searchField", searchField);
		searchMap.put("searchWord", searchWord);

		//검색된 자료를 이용하여 Pagination객체 다시 설정하기
		setPagination();
	}







	@FXML
	void initialize() {

		//        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'MemberPage.fxml'.";
		//        assert idCol != null : "fx:id=\"idCol\" was not injected: check your FXML file 'MemberPage.fxml'.";
		//        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'MemberPage.fxml'.";
		//        assert telCol != null : "fx:id=\"telCol\" was not injected: check your FXML file 'MemberPage.fxml'.";
		//        assert addrCol != null : "fx:id=\"addrCol\" was not injected: check your FXML file 'MemberPage.fxml'.";
		//        assert pagination != null : "fx:id=\"pagination\" was not injected: check your FXML file 'MemberPage.fxml'.";
		//        assert cmbSearch != null : "fx:id=\"cmbSearch\" was not injected: check your FXML file 'MemberPage.fxml'.";
		//        assert tfSearch != null : "fx:id=\"tfSearch\" was not injected: check your FXML file 'MemberPage.fxml'.";
		//        assert btnSearch != null : "fx:id=\"btnSearch\" was not injected: check your FXML file 'MemberPage.fxml'.";

		service = MemberService.getInstance();		//Service객체 생성

		searchMap = new HashMap<String, String>();

		//tableView의 각 컬럼 설정
		idCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
		telCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
		addrCol.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));

		//검색용 콤보박스 초기값 설정
		cmbSearch.getItems().addAll("회원ID","회원이름","전화번호","주소");


		//Pagination의 페이지 번호가 변경되었을 때 이벤트 처리
		pagination.currentPageIndexProperty().addListener(
				new ChangeListener<Number>() {

					@Override
					public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
						changeTableView(newValue.intValue());

					}
				}
				);
		setPagination();
	}



	//Pagination을 초기화 하는 메서드
	public void setPagination() {
		//전체 레코드 수 구하기
		totalRowCount = service.getMemberCount(searchMap);


		//전체 페이지 수 계산
		totalPageCount =  (int)Math.ceil((double)totalRowCount / rowPerPage); 

		//Pagination에 전체 페이지수를 설정
		pagination.setPageCount(totalPageCount);

		//현재 화면에 보여줄 Pagination의 페이지 번호의 index값 설정
		pagination.setCurrentPageIndex(0);	//1페이지로 설정


		//한 화면에 보여줄 Pagination 페이지 번호 갯수(기본값 10)
		pagination.setMaxPageIndicatorCount(10);


		//선택한 현제 페이지에 맞는 데이터를 가져와 TableView에 셋팅
		changeTableView(pagination.getCurrentPageIndex());
	}


	//10개씩 4페이지(index : 3)
	//시작번호 31	==>30이 나와야 한다.
	//끝번호 40


	//Pagination에서 선택한 페이지의 index번째에 맞는 데이터를 가져와 
	//TableView에 다시 셋팅하는 메서드
	//index=> 0 -> 1페이지, 1->2페이지.....
	public void changeTableView(int index) {
		int start = index*rowPerPage;	//시작번호
		int end = Math.min(start+rowPerPage,totalRowCount);	//끝번호

		searchMap.put("start", String.valueOf(start));
		searchMap.put("end", String.valueOf(end));


		//시작번호부터 끝번호 사이의 자료를 가져와 Table에 셋팅

		table.setItems(
				FXCollections.observableArrayList(service.getMemList(searchMap)));
	}


}
