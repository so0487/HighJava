package basic.paneTest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AnchorPaneTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		AnchorPane root = new AnchorPane();
		root.setPrefSize(300, 150);
		
		Label lblId = new Label("아 이 디 : ");
		lblId.setLayoutX(60);  // x좌표 지정
		lblId.setLayoutY(20);  // y좌표 지정
		
		Label lblPass = new Label("패스워드 : ");
		lblPass.setLayoutX(60);
		lblPass.setLayoutY(65);
		
		TextField tfId = new TextField();
		tfId.setLayoutX(130);
		tfId.setLayoutY(20);
		
		PasswordField pass = new PasswordField();
		pass.setLayoutX(130);
		pass.setLayoutY(65);
		
		Button btnLogin = new Button("로그인");
		btnLogin.setLayoutX(85);
		btnLogin.setLayoutY(110);
		
		Button btnCancel = new Button("취 소");
		btnCancel.setLayoutX(165);
		btnCancel.setLayoutY(110);
		
		root.getChildren().addAll(lblId, lblPass, 
				tfId, pass, btnLogin, btnCancel);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("AnchorPane 연습");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
