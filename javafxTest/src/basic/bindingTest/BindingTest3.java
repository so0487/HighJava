package basic.bindingTest;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BindingTest3 extends Application {

	@Override
	public void start(Stage primaryStage) {
		HBox hbox = new HBox(20);
		hbox.setPadding(new Insets(10));
		
		Rectangle rect1 = new Rectangle(0, 5, 200, 15);
		rect1.setFill(null);	// 채우기 색 없음
		rect1.setStroke(Color.GREEN);  // 선의 색
		
		Rectangle rect2 = new Rectangle(0, 5, 10, 15);
		rect2.setFill(Color.BLUE);
		
		Button btn1 = new Button("감소");
		Button btn2 = new Button("증가");
		
		// 감소버튼
		btn1.setOnAction(e->{
			// rect2의 width속성값을 5씩 감소 시킨다.
			rect2.setWidth(rect2.getWidth() - 5);
		});
		
		// 증가버튼
		btn2.setOnAction(e->{
			// rect2의 width속성값을 5씩 증가 시킨다.
			rect2.setWidth(rect2.getWidth() + 5);
		});
		
		// 컨트롤객체를 비활성화하기
		//btn1.setDisable(true);
		
		// rect2의 width가 0이하가 되면 '감소버튼'을 비활성화 시킨다.
		// rect2의 width가 200이상이 되면 '증가버튼'을 비활성화 시킨다.
		// 버튼의 disable속성을 bind처리 한다.
		btn1.disableProperty().bind(
			Bindings.lessThanOrEqual(rect2.widthProperty(), 0)
		);
		
		btn2.disableProperty().bind(
			Bindings.greaterThanOrEqual(rect2.widthProperty(), 200)
		);
		
		Pane pane = new Pane();
		pane.getChildren().addAll(rect1, rect2);
		
		hbox.getChildren().addAll(btn1, pane, btn2);
		
		Scene scene = new Scene(hbox);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Binding 연습3");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
