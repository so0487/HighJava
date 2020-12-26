package basic.bindingTest;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BindingTest2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		Pane pane = new Pane();
		
		Circle circle1 = new Circle(50, 50, 50);
		Circle circle2 = new Circle(300, 300, 100);
		
		circle1.setFill(Color.BLUE);	// 원 내부에 색칠하기
		circle2.setFill(Color.AQUA);
		
		pane.getChildren().addAll(circle1, circle2);
		
		Scene scene = new Scene(pane);
		
		// bind메서드에서 계산에 사용되는 메서드는 
		// Bindings객체의 정적 메서드로 지원한다.
		
		// 원의 중심좌표 중 x좌표값을 현재 Scene의 너비의 절반값으로 지정한다. 
		circle2.centerXProperty().bind(
			Bindings.divide(scene.widthProperty(), 2)
		);
		
		// 원의 중심좌표 중 y좌표값 변경
		circle2.centerYProperty().bind(
			Bindings.divide(scene.heightProperty(), 2)
		);
		
		
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("Binding 연습2");
		primaryStage.setWidth(600);		// 창의 너비
		primaryStage.setHeight(600);   	// 창의 높이
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
