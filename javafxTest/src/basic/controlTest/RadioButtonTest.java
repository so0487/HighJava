package basic.controlTest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RadioButtonTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		ImageView icon = new ImageView();
		
		// 라디오버튼들을 한 묶음으로 처리할 객체
		ToggleGroup group = new ToggleGroup();
		
		RadioButton rb1 = new RadioButton("Home");
		rb1.setToggleGroup(group);
		// 해당 객체를 선택했을 때의 사용할 값 설정
		rb1.setUserData("Home");  
		
		RadioButton rb2 = new RadioButton("Calendar");
		rb2.setToggleGroup(group);
		rb2.setUserData("Calendar");
		
		RadioButton rb3 = new RadioButton("Contacts");
		rb3.setToggleGroup(group);
		rb3.setUserData("Contacts");
		
		// 그룹내에서 선택된 라디오버튼이 변경되었을 때 처리
		group.selectedToggleProperty().addListener(
			new ChangeListener<Toggle>() {
				@Override
				public void changed(ObservableValue<? extends Toggle> observable, 
						Toggle oldValue, Toggle newValue) {
					// 여기에서 Toggle객체는 RadioButton으로 형변환이 가능하다.
					
					// 현재 선택된 라디오버튼 객체 구하기
					//RadioButton selRb = (RadioButton)newValue;  // 방법1
					
					// 방법2
					RadioButton selRb = 
							(RadioButton) group.getSelectedToggle(); 
					
					//String imgName = selRb.getText();
					String imgName = selRb.getUserData().toString();
					
					Image image = new Image(
						RadioButtonTest.class.getResourceAsStream(
								"../../images/" + imgName + ".jpg"
							));
					
					icon.setImage(image);
					
				}
			}
		);
		
		
		
		Button btnOk = new Button("확인");
		
		HBox hbox = new HBox();
		hbox.setSpacing(50);
		hbox.setPadding(new Insets(10));
		
		VBox vbox = new VBox(10);
		vbox.getChildren().addAll(rb1, rb2, rb3, btnOk);
		
		hbox.getChildren().addAll(vbox, icon);
		
		Scene scene = new Scene(hbox, 250, 150);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("라디오버튼 연습");
		primaryStage.show();
			
	}

	public static void main(String[] args) {
		launch(args);
	}
}
