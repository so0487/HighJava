package basic.event;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventTest extends Application {
	TextArea taResult = new TextArea();
	TextField tfMsg = new TextField();
	
	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox(10);
		root.setPadding(new Insets(10));
		
		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(10));
		
		Button btn1 = new Button("첫번째");
		// 이벤트 처리 방법1
		// 객체.setOn이벤트명() 메서드에 EventHandler인터페이스를 
		// 익명 구현체 형식으로 구현한다. (이 인터페이스에는 handel()메서드가
		// 있는데 이 메서드에 처리할 내용을 기술한다.)
		// 이 EventHandler인터페이스는 함수적 인터페이스이기 때문에
		// 람다식으로도 구현할 수 있다.
		btn1.setOnAction(
			/*	
			new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// 이 곳에 이벤트에 대한 처리할 내용을 기술한다.
				String msg = tfMsg.getText();  // TextField의 문자열 가져오기
				
				// Label, TextField, PasswordField, TextArea, Button 등과 
				// 같은 컨트롤에 문자열을 출력할 때는 setText() 메서드를 이용한다.
				//taResult.setText("첫번째 버튼 -> " + msg);
				
				// 추가
				taResult.appendText("첫번째 버튼 -> " + msg + "\n");
				
			}
		}
			*/
				
			// 람다식
			e -> {
				String msg = tfMsg.getText(); 
				//taResult.setText("첫번째 버튼 -> " + msg);
				
				// 추가
				taResult.appendText("첫번째 버튼(람다식) -> " + msg + "\n");
				
				// 내용 지우기
				//tfMsg.setText("");
				tfMsg.clear();
				 
				// 포커스 주기
				tfMsg.requestFocus();
				
			}
		);
		
		
		Button btn2 = new Button("두번째");
		// 이벤트 처리 방법2
		// 객체.addEventHandler()메서드를 사용한다.
		// 이 메서드의 첫번째 매개변수 값으로 이벤트 종류를 지정하고
		// 두번째 매개변수 값으로는 EventHandler인터페이스를 구현한 객체를
		// 지정한다.
		btn2.addEventHandler(
			ActionEvent.ACTION, 
			
			new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String msg = tfMsg.getText(); 
					
					// 추가
					taResult.appendText("두번째 버튼 -> " + msg + "\n");
					
					// 내용 지우기
					tfMsg.clear();
					 
					// 포커스 주기
					tfMsg.requestFocus();					
				}
			});
		
		
		Button btn3 = new Button("세번째");
		// 이벤트 처리 방법 3-2
		// setOn이벤트명()메서드나 addEventHandler()메서드의
		// 매개변수로 EventHandler인터페이스를 구현한 객체의 
		// 인스턴스를 지정한다.
		btn3.setOnAction(new MyEventHandler());
		
		
		Button btn4 = new Button("네번째");
		// 이벤트 처리 방법 4-2
		btn4.addEventHandler(
			ActionEvent.ACTION, 
			new MyEventHandler2(taResult, tfMsg)
		);
		
		
		hbox.getChildren().addAll(btn1, btn2, btn3, btn4);
		
		root.getChildren().addAll(hbox, taResult, tfMsg);
		
		Scene scene = new Scene(root, 500, 350);
		tfMsg.requestFocus();
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Event 연습");
		primaryStage.show();
		
				
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	// 이벤트 처리 방법 3-1
	// inner클래스로 EventHandler인터페이스를 구현한 클래스를 작성한다.
	class MyEventHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			String msg = tfMsg.getText(); 
			
			// 추가
			taResult.appendText("세번째 버튼(inner클래스이용) -> " + msg + "\n");
			
			// 내용 지우기
			tfMsg.clear();
			 
			// 포커스 주기
			tfMsg.requestFocus();
			
		}
	}

	
}

// 이벤트 처리 방법 4-1
// 외부의 독립된 형태의 객체를 이용하는 방법
// 이 클래스도 EventHandler인터페이스를 구현하여 작성한다.
class MyEventHandler2 implements EventHandler<ActionEvent>{
	TextArea ta;
	TextField tf;
	
	// 생성자
	public MyEventHandler2(TextArea ta, TextField tf) {
		this.ta = ta;
		this.tf = tf;
	}
	
	@Override
	public void handle(ActionEvent event) {
		String msg = tf.getText(); 
		
		// 추가
		ta.appendText("네번째 버튼(외부 클래스이용) -> " + msg + "\n");
		
		// 내용 지우기
		tf.clear();
		 
		// 포커스 주기
		tf.requestFocus();
		
	}
}







