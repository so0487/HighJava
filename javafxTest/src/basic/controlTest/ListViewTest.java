package basic.controlTest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListViewTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		Label lblMsg = new Label();
		lblMsg.setFont(new Font(20));
		
		// ListView에 출력할 데이터는 ObservableList에 담아서 넣어주어야 한다.
		ObservableList<String> data = 
			FXCollections.observableArrayList(
				"green", "gold", "red", "blue", "black",
				"brown", "pink", "blueviolet", "chocolate"
			);
		
		// ListView에 데이터를 셋팅하는 방법
		//ListView<String> list = new ListView<String>(data); // 방법1
		
		ListView<String> list = new ListView<String>(); // 방법2
		list.setItems(data);
		
		
		// ListView에서 데이터를 선택했을 때 처리
		list.getSelectionModel().selectedItemProperty().addListener(
			new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
					
					// 선택한 값을 Label에 출력
					lblMsg.setText(newValue);
					lblMsg.setTextFill(Color.web(newValue));
					
				}
			}
		);
		
		// ListView의 원래 데이터는 변경되지 않고
		// 화면에 보이는 모양을 변경하는 방법
		list.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						// 매개변수 item ==> 현재 Cell의 원본데이터
						// 매개변수 empty ==> 현재 Cell에 데이터가 없으면 true
						//if(item!=null) {  // 값이 있으면...
						if(!empty) {
							Rectangle rect = new Rectangle(100, 20);
							rect.setFill(Color.web(item));
							
							// 값변경
							//setText(item + " ***** ");	// 문자열로 변경할 때
							setGraphic(rect);   // Node객체로 변경할 때
						}
					}
				};
			}
		});
		
		
		
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		root.getChildren().addAll(list, lblMsg);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ListView 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
