package basic.paneTest;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		FlowPane root = new FlowPane();
		root.setPrefSize(300, 100);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(10));
		
		Button btn1 = new Button("1번 버튼");
		Button btn2 = new Button("2번 버튼");
		Button btn3 = new Button("3번 버튼");
		Button btn4 = new Button("4번 버튼");
		Button btn5 = new Button("5번 버튼");
		Button btn6 = new Button("6번 버튼");
		Button btn7 = new Button("7번 버튼");
		
		root.getChildren().addAll(btn1, btn2, btn3, btn4, btn5, btn6, btn7);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("FlowPane 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
