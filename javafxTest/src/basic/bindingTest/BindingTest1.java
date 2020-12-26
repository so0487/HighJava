package basic.bindingTest;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
	binding은 한 쪽의 내용이 변경되면 이와 연결된 다른 쪽의
	내용도 같이 변경되는 기술을 말한다.
	
	- 값이 변경될 때 영향을 받는 쪽에서 binding처리를 기술한다.
	- binding처리에 사용되는 메서드
	  1) bind()메서드   2) bindBidirectional()메서드
*/
public class BindingTest1 extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		
		TextArea txt1 = new TextArea();
		TextArea txt2 = new TextArea();
		
		Label lbl1 = new Label("첫번째");
		Label lbl2 = new Label("두번째");
		
		// 단방향
		//txt2.textProperty().bind(txt1.textProperty());
		
		// 양방향
		// 방법1 - 인스턴스 메서드 이용
		//txt2.textProperty().bindBidirectional(txt1.textProperty());
		
		// 방법2 - Bindings의 정적 메서드 이용
		Bindings.bindBidirectional(txt1.textProperty(), txt2.textProperty());
		
		
		root.getChildren().addAll(lbl1, txt1, lbl2, txt2);
		
		Scene scene = new Scene(root, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Binding 연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
