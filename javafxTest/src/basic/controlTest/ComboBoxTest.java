package basic.controlTest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(10));
		
		HBox hbox = new HBox(15);
		TextArea txtArea = new TextArea();
		
		// ComboBox객체 생성 및 데이터 초기화 하기
		
		// 데이터 설정 방법1
		ComboBox<String> combo = new ComboBox<String>();
		combo.getItems().addAll("한강", "금강", "영산강", "낙동강");
		
		combo.setValue("금강");  // 처음부터 선택되는 값
		
		// 콤보박스의 선택값이 변경되었을 때 처리
		combo.valueProperty().addListener(
			new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, 
						String oldValue, String newValue) {
					
					txtArea.setText("선택한 강 : " + newValue );
					
				}
			}
		);
		
		// 데이터 설정 방법2
		ObservableList<String> data = FXCollections.observableArrayList(
			"사과", "딸기", "배", "포도", "감"
		);
		ComboBox<String> combo2 = new ComboBox<String>(data);
		//combo2.setValue(data.get(0));
		//combo2.setValue(combo2.getItems().get(3));
		
		// 데이터 추가하기
		combo2.getItems().addAll("복숭아", "대추", "수박");
		
		
		Button btnOk = new Button("확 인");
		btnOk.setOnAction(e->{
			//System.out.println(combo2.getValue());
			
			if(combo.getValue() != null && combo2.getValue() != null) {
				txtArea.setText(combo.getValue() + "지역의 과일은 ");
				txtArea.appendText(combo2.getValue() + "가 유명합니다.");
			}
			
		});
		
		hbox.getChildren().addAll(combo, combo2, btnOk);
		hbox.setPadding(new Insets(10));
		
		root.setTop(hbox);
		root.setCenter(txtArea);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("ComboBox 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
