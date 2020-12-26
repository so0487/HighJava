package basic.controlTest;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import util.AlertUtil;

public class TableViewTest extends Application {
	boolean bTest = true;
	
	@Override
	public void start(Stage primaryStage) {
		ObservableList<Member> dataList = FXCollections.observableArrayList(
			new Member("홍길동", "gildong", 33, "010-1111-1111", "대전"),
			new Member("홍길서", "gilseo", 43, "010-2222-1111", "서울"),
			new Member("홍길남", "gilnam", 23, "010-3333-1111", "제주"),
			new Member("홍길북", "gilbook", 53, "010-4444-1111", "원주")
		);
		
		BorderPane root = new BorderPane();
		
		// TableView객체 생성 및 초기화
		// TableView<Member> table = new TableView<Member>(dataList);
		
		TableView<Member> table = new TableView<Member>();
		table.setItems(dataList);
		
		TableColumn<Member, String> nameCol = 
			new TableColumn<Member, String>("이 름");
		
		// 컬럼 객체를 생성할 때 컬럼의 제목을 지정해서 생성한다.
		TableColumn<Member, String> korName =
			new TableColumn<Member, String>("한 글");
		TableColumn<Member, String> engName =
			new TableColumn<Member, String>("영 문");
		
		// 생성된 컬럼에 출력할 데이터를 설정한다.
		// 출력할 VO객체의 변수명을 지정한다.
		korName.setCellValueFactory(
			new PropertyValueFactory<Member, String>("korName"));
		engName.setCellValueFactory(
			new PropertyValueFactory<Member, String>("engName"));
		
		// '이름컬럼'에 '한글컬럼'과 '영문컬럼'을 추가한다.
		nameCol.getColumns().addAll(korName, engName);
		
		TableColumn<Member, Integer> ageCol =
			new TableColumn<Member, Integer>("나 이");
		ageCol.setCellValueFactory(
			new PropertyValueFactory<>("age"));
		
		TableColumn<Member, String> telCol =
			new TableColumn<Member, String>("전화번호");
		telCol.setCellValueFactory(
			new PropertyValueFactory<>("tel"));
		
		TableColumn<Member, String> addrCol = 
			new TableColumn<Member, String>("주 소");
		addrCol.setCellValueFactory(
			new PropertyValueFactory<>("addr"));
		
		// 만들어진 컬럼을 TableView에 추가한다.
		table.getColumns().addAll(nameCol, ageCol, telCol, addrCol);
		
		GridPane grid = new GridPane();
		Text txt1 = new Text("한글이름");
		Text txt2 = new Text("영문이름");
		Text txt3 = new Text("나  이");
		Text txt4 = new Text("전화번호");
		Text txt5 = new Text("주  소");
		
		TextField tfKorName = new TextField();
		TextField tfEngName = new TextField();
		TextField tfAge = new TextField();
		TextField tfTel = new TextField();
		TextField tfAddr = new TextField();
		
		grid.add(txt1, 1, 1);
		grid.add(txt2, 2, 1);
		grid.add(txt3, 3, 1);
		grid.add(txt4, 4, 1);
		grid.add(txt5, 5, 1);
		
		grid.add(tfKorName, 1, 2);
		grid.add(tfEngName, 2, 2);
		grid.add(tfAge, 3, 2);
		grid.add(tfTel, 4, 2);
		grid.add(tfAddr, 5, 2);
		grid.setVgap(10);
		grid.setHgap(5);
		
		
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(5));
		
		Button btnAdd = new Button("추가");
		Button btnEdit = new Button("수정");
		Button btnDel = new Button("삭제");
		
		Button btnTest = new Button("연습");
		
		// 추가버튼 이벤트 처리
		btnAdd.setOnAction(e->{
			String kName = tfKorName.getText();
			String eName = tfEngName.getText();
			String strAge = tfAge.getText();
			String tel = tfTel.getText();
			String addr = tfAddr.getText();
			
			if(kName.isEmpty() || eName.isEmpty() || strAge.isEmpty() ||
					tel.isEmpty() || addr.isEmpty()) {
				AlertUtil.warnMsg("입력오류", "빈 항목이 있습니다.");
				return;
			}
			if(!Pattern.matches("^[0-9]+$", strAge)) {
				AlertUtil.warnMsg("입력오류", "나이는 숫자를 입력하세요.");
				tfAge.requestFocus();
				return;
			}
			
			dataList.add(
				new Member(kName, eName, 
						Integer.parseInt(strAge), tel, addr)
			);
			
			// TextField값 모두 지우기
			tfKorName.clear();
			tfEngName.clear();
			tfAge.clear();
			tfTel.clear();
			tfAddr.clear();
			
			AlertUtil.infoMsg("작업성공", "데이터가 추가되었습니다.");
		});
		
		// 수정버튼 클릭 이벤트 처리
		btnEdit.setOnAction(e->{
			if(table.getSelectionModel().isEmpty()) {
				AlertUtil.errorMsg("선택 오류", 
						"수정할 데이터를 선택한 후 사용하세요.");
				return;
			}
			
			String kName = tfKorName.getText();
			String eName = tfEngName.getText();
			String strAge = tfAge.getText();
			String tel = tfTel.getText();
			String addr = tfAddr.getText();
			
			if(kName.isEmpty() || eName.isEmpty() || strAge.isEmpty() ||
					tel.isEmpty() || addr.isEmpty()) {
				AlertUtil.warnMsg("입력오류", "빈 항목이 있습니다.");
				return;
			}
			if(!Pattern.matches("^[0-9]+$", strAge)) {
				AlertUtil.warnMsg("입력오류", "나이는 숫자를 입력하세요.");
				tfAge.requestFocus();
				return;
			}
			
			// TableView에서 현재선택한 데이터의 index번호 구하기
			int index = table.getSelectionModel().getSelectedIndex();
			
			dataList.set(index, 
					new Member(kName, eName, 
						Integer.parseInt(strAge), tel, addr));
			
			tfKorName.clear();
			tfEngName.clear();
			tfAge.clear();
			tfTel.clear();
			tfAddr.clear();
			
			AlertUtil.infoMsg("작업 결과", "수정 작업 성공!!!");
			
			
		});
		
		// 삭제버튼 클릭 이벤트 처리
		btnDel.setOnAction(e->{
			if(table.getSelectionModel().isEmpty()) {
				AlertUtil.errorMsg("작업오류", 
						"삭제할 자료를 선택한 후 사용하세요.");
				return;
			}
			
			dataList.remove(
					table.getSelectionModel().getSelectedIndex());
			
			AlertUtil.infoMsg("작업 결과", "삭제 작업 성공");
		});
		
		
		btnTest.setOnAction(e->{
			bTest = !bTest;
			System.out.println("bTest = " + bTest);
			
			btnAdd.setDisable(bTest);
			tfKorName.setEditable(bTest);
			
			/*
			btnAdd.setDisable(true);  // 비활성화
			btnAdd.setDisable(false); // 활성화
			
			tfKorName.setEditable(false);  // 읽기 전용
			tfKorName.setEditable(true);  // 쓰기 가능
			*/
		});
		
		
		vbox.getChildren().addAll(btnAdd, btnEdit, btnDel, btnTest);
		
		// TableView를 클릭했을 때 이벤트 처리
		table.setOnMouseClicked(e->{
			if(table.getSelectionModel().isEmpty()) {
				return;
			}
			
			// TableView에서 현재 선택된 데이터 구하기
			Member mem = table.getSelectionModel().getSelectedItem();
			
			tfKorName.setText(mem.getKorName());
			tfEngName.setText(mem.getEngName());
			tfAge.setText(String.valueOf( mem.getAge() ));
			tfTel.setText(mem.getTel());
			tfAddr.setText(mem.getAddr());
			
		});
		
		
		
		root.setPadding(new Insets(10));
		root.setCenter(table);
		root.setBottom(grid);
		root.setRight(vbox);
		
		Scene scene = new Scene(root, 600, 400);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("TableView 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// Inner 클래스
	public class Member{
		private String korName;
		private String engName;
		private int age;
		private String tel;
		private String addr;
		
		public Member() {}

		public Member(String korName, String engName, int age, String tel, String addr) {
			super();
			this.korName = korName;
			this.engName = engName;
			this.age = age;
			this.tel = tel;
			this.addr = addr;
		}

		public String getKorName() {
			return korName;
		}

		public void setKorName(String korName) {
			this.korName = korName;
		}

		public String getEngName() {
			return engName;
		}

		public void setEngName(String engName) {
			this.engName = engName;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}

	}
	
	
}








