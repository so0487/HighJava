package basic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ProgramLayoutTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox();  	// VBox 컨테이너 객체 생성
		root.setPrefHeight(150);  	// VBox의 높이
		root.setPrefWidth(650);		// VBox의 너비
		
		root.setAlignment(Pos.CENTER); 	// 컨트롤들을 가운데 정렬한다.
		root.setSpacing(20);	// 컨트롤과 컨트롤사이의 간격
		
		// 안쪽 여백 설정
		// Insets객체는 위, 오른쪽, 아래, 왼쪽 순으로 설정해 준다.
		// 위의 4군데 값이 모두 같으면 값을 1개만 지정해도 된다.
		root.setPadding(new Insets(10, 10, 10, 10));
		//root.setPadding(new Insets(10));
		
		Label label = new Label();  // Label객체 생성
		label.setText("안녕하세요, JavaFX입니다.");  // 출력할 내용 설정
		label.setFont(new Font(50));  // Font객체를 이용하여 글자 크기 설정
		
		Button btnClose = new Button();  // Button객체 생성
		btnClose.setText(" 종 료 ");
		
		// 종료버튼을 클릭했을 때 이벤트 처리
		btnClose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// 처리할 내용을 이곳에 기술한다.
				//primaryStage.close();
				Platform.exit();  // javafx명령
				
				//System.exit(0);  // java명령
			}
		});
		
		
		HBox hbox = new HBox(10);  // HBox객체 생성 spacing값도 같이 설정
		
		hbox.setPadding(new Insets(10));  // 안쪽 여백
		
		hbox.setAlignment(Pos.CENTER);  // 가운데 정렬
		
		// TextField 객체 생성 후 가로 크기 설정
		TextField tfStr = new TextField();
		tfStr.setPrefWidth(300);
		
		// 버튼 객체 생성 => 캡션을 지정해서 생성
		Button btnOk = new Button(" 확 인 ");
		
		// 이벤트 설정을 람다식으로...
		btnOk.setOnAction(e ->{
			primaryStage.close();
		});
		
		
		// HBox에 TextField와 Button 넣기
		// ObservableList ==> JavaFx에서 컨테이너나 컨트롤에 데이터를
		//					  넣을 때 사용하는 컬렉션 객체
		
		// HBox의 ObservableList객체 얻어온다.
		ObservableList<Node> list = hbox.getChildren();
		
		// 생성한 컨트롤들을 HBox에 배치(추가)한다.
		
		//list.add(tfStr);  // 컨트롤들을 하나씩 추가
		//list.add(btnOk);
		
		list.addAll(tfStr, btnOk);  // 컨트롤 여러개를 한꺼번에 추가
		
		//-------------------------------
		// VBox(루트 컨테이너)에 컨트롤들을 추가한다.
		root.getChildren().addAll(label, hbox, btnClose);
		
		
		// VBox를 루트 컨테이너로 해서 Scene객체 생성
		Scene scene = new Scene(root);
		
		// Scene객체를 Stage객체에 추가
		primaryStage.setScene(scene);
		
		// 창 제목 설정
		primaryStage.setTitle("자바코드로 레이아웃 설정하기");
		
		primaryStage.show();  // 창 보이기
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
