package basic.controlTest;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ComboBoxTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		
		ComboBox<MyFriend> cmbFriend = new ComboBox<MyFriend>();
		TextArea taResult = new TextArea();
		
		ObservableList<MyFriend> list = FXCollections.observableArrayList(
			new MyFriend("aaa", "홍길동", "010-1111-1111", "대전"),
			new MyFriend("bbb", "이순신", "010-2222-1111", "인천"),
			new MyFriend("ccc", "변학도", "010-3333-1111", "부산"),
			new MyFriend("ddd", "강감찬", "010-4444-1111", "광주"),
			new MyFriend("eee", "이몽룡", "010-5555-1111", "포항"),
			new MyFriend("fff", "성춘향", "010-6666-1111", "전주")
		);
		
		cmbFriend.setItems(list);
		
		// 콤보박스의 데이터 목록이 보여지는 곳의 내용 변경하기
		cmbFriend.setCellFactory(
			new Callback<ListView<MyFriend>, ListCell<MyFriend>>() {
			
				@Override
				public ListCell<MyFriend> call(ListView<MyFriend> param) {
					return new ListCell<MyFriend>() {
						@Override
						protected void updateItem(MyFriend item, boolean empty) {
							super.updateItem(item, empty);
							if(item==null || empty) {
								setText(null);
							}else {
								setText(item.getId() + " [" + item.getName() + "]");
							}
						}
					};
				}
		});
		
		// 콤보박스의 선택한 데이터가 표시되는 영역을 버튼 영역이라 한다. 
		// 이 부분의 내용도 같이 변경해 주어야 한다.
		cmbFriend.setButtonCell(new ListCell<MyFriend>() {
			@Override
			protected void updateItem(MyFriend item, boolean empty) {
				super.updateItem(item, empty);
				
				if(item==null || empty) {
					setText(null);
				}else {
					setText(item.getId() + " [" + item.getName() + "]");
				}
				
			}
		});
		
		
		// 콤보박스를 클릭했을 때 이벤트 처리
		cmbFriend.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// 현재 선택된 데이터 구하기
				//MyFriend selData = cmbFriend.getValue();
				MyFriend selData = 
					cmbFriend.getSelectionModel().getSelectedItem();
				
				taResult.setText("ID : " + selData.getId() + "\n");
				taResult.appendText("이 름 : " + selData.getName() + "\n");
				taResult.appendText("전 화 : " + selData.getTel() + "\n");
				taResult.appendText("주 소 : " + selData.getAddr() + "\n");
				
			}
		});
		
		
		
		root.getChildren().addAll(cmbFriend, taResult);
		
		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("콤보박스에 VO객체 설정하기");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}


class MyFriend{
	private String id;
	private String name;
	private String tel;
	private String addr;
	
	public MyFriend() {	}

	public MyFriend(String id, String name, String tel, String addr) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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




