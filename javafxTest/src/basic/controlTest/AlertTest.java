package basic.controlTest;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AlertTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox(20);
		root.setPrefHeight(150);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		
		Button btn1 = new Button("INFO");
		btn1.setOnAction(e->{
			Alert info = new Alert(AlertType.INFORMATION);
			
			info.setTitle("INFORMATION");
			info.setHeaderText("각종 정보 출력");
			info.setContentText("Information Alert창 입니다.");
			info.showAndWait();  // Alert창 보이기
		});
		
		Button btn2 = new Button("ERROR");
		btn2.setOnAction(e->{
			Alert error = new Alert(AlertType.ERROR);
			
			error.setTitle("ERROR");
			error.setHeaderText("각종 에러 메시지");
			error.setContentText("Error Alert창 입니다.");
			error.showAndWait();  // Alert창 보이기
		});
		
		Button btn3 = new Button("WARN");
		btn3.setOnAction(e->{
			Alert warn = new Alert(AlertType.WARNING);
			
			warn.setTitle("WARNING");
			warn.setHeaderText("각종 경고 메시지");
			warn.setContentText("Warning Alert창 입니다.");
			warn.showAndWait();  // Alert창 보이기
		});
		
		Button btn4 = new Button("CONFIRM");
		btn4.setOnAction(e->{
			// 메시지를 출력하고 사용자로부터 'OK' 또는 '취소'버튼의
			// 선택을 기다린다.
			// 사용자가 'OK'버튼을 눌렀을 때와 '취소'버튼을 눌렀을 때를
			// 구분해서 처리한다.
			Alert confirm = new Alert(AlertType.CONFIRMATION);
			confirm.setTitle("CONFIRMATION");
			confirm.setHeaderText("선택 메시지 출력");
			confirm.setContentText("Confirmation Alert창 입니다.");
			
			// get()메서드 ==> 사용자가 누른 버튼 정보가 반환된다.
			ButtonType confirmResult = confirm.showAndWait().get();
			
			if(confirmResult == ButtonType.OK) {  // 'OK 버튼'
				System.out.println("OK버튼이 눌렸습니다.");
			}else if(confirmResult == ButtonType.CANCEL) { // 취소버튼
				System.out.println("취소버튼이 눌렸습니다.");
			}else {
				System.out.println("...");
			}
			
		});
		
		Button btn5 = new Button("PROMPT");
		btn5.setOnAction(e->{
			// 자바스크립트의 prompt창과 같은 기능을 갖는다.
			
			// TextInputDialog객체 생성 - 기본값은 생략 가능
			TextInputDialog prompt = new TextInputDialog("기본값");
			
			prompt.setTitle("PROMPT창");
			prompt.setHeaderText("입력 관련 메시지");
			prompt.setContentText("입 력 : ");
			prompt.setWidth(500);
			
			// 창을 띄우고 사용자가 입력한 정보를 얻어온다.
			Optional<String> result = prompt.showAndWait();
			
			String value = null;
			if(result.isPresent()) {  // 값이 있으면..(OK버튼 눌렀을 경우)
				value = result.get();  // 값을 꺼내온다.
			}
			
			System.out.println("입력 값 : " + value);
			
			
			
		});
		
		
		root.getChildren().addAll(btn1, btn2, btn3, btn4, btn5);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Alert 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
