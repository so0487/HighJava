package basic.animation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class LoginController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private BorderPane loginForm;

	@FXML
	private Button btnMain;

	@FXML
	void initialize() {
		assert loginForm != null : "fx:id=\"loginForm\" was not injected: check your FXML file 'login.fxml'.";
		assert btnMain != null : "fx:id=\"btnMain\" was not injected: check your FXML file 'login.fxml'.";

		btnMain.setOnAction(e->mainClicked(e));
	}

	public void mainClicked(ActionEvent e) {
		try {
			StackPane root = (StackPane) btnMain.getScene().getRoot();

			//root.getChildren().remove(loginForm);




			//에니메이션 효과 넣기
			
			//쓸기 효과
			/*
			
			loginForm.setTranslateX(0);	//출발점
			KeyValue keyValue = new KeyValue(loginForm.translateXProperty(), 350);

			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), 
					ee->{
						//에니메이션이 끝난 후의 작업 내용을 기술
						root.getChildren().remove(loginForm);
					},					
					keyValue);

			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
			
			*/
			
			
			
			//효과
			//사라지기 효과
			loginForm.setOpacity(1);
			KeyValue keyValue = new KeyValue(loginForm.opacityProperty(), 0);
			
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), 
					ee->{
						//에니메이션이 끝난 후의 작업 내용을 기술
						root.getChildren().remove(loginForm);
					},					
					keyValue);
			
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
			
			

		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}
