package basic.event;

import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GuGuDanEventTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 300);
		root.setPadding(new Insets(10));
		
		HBox hbox = new HBox(10);
		hbox.setPadding(new Insets(10));
		hbox.setAlignment(Pos.CENTER);
		
		TextField tfDan = new TextField();
		tfDan.setPrefWidth(50);
		
		Label lblDan = new Label("단");
		Button btnOk = new Button("출 력");
		
		TextArea taResult = new TextArea();
		
		
		btnOk.setOnAction(e->{
			String strDan = tfDan.getText();
			
			if(!Pattern.matches("^[0-9]+$", strDan)) {
				taResult.setText("출력할 구구단을 정확히 입력하세요.");
				return;
			}
			
			int dan = Integer.parseInt(strDan);
			taResult.setText(dan + " 단\n\n");
			
			for(int i=1; i<=9; i++) {
				int r = dan * i;
				taResult.appendText(dan + " * " + i + " = " + r + "\n");
			}
			
		});
		
		hbox.getChildren().addAll(tfDan, lblDan, btnOk);
		
		
		
		root.setTop(hbox);
		root.setCenter(taResult);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("구구단 이벤트 처리 연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
