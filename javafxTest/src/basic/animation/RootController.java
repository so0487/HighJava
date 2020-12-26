package basic.animation;

import java.io.IOException;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class RootController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'root.fxml'.";
        
        btnLogin.setOnAction(e->loginClicked(e));

    }
    
    public void loginClicked(ActionEvent e) {
    	try {
			Parent login = FXMLLoader.load(RootController.class.getResource("login.fxml"));
			
			//현재 화면의 Root엘리먼트 객체를 구한다.
			//지금 예제에서는 StackPane이 된다.
			StackPane root = (StackPane) btnLogin.getScene().getRoot();
			
			root.getChildren().add(login);
			
			/*
			
			//이동하는 에니메이션
			
			//x축으로 평행 이동할 양 설정(에니메이션이 시작할 위치로 이동)
			login.setTranslateX(350);
			
			
			//타겟속성과 종료값을 설정
			KeyValue keyValue = new KeyValue(login.translateXProperty(), 0);
			
			
			//에니메이션의 지속시간과 KeyValue를 설정
			//형식1) new KeyFrame(지속시간, keyValue객체);
			//형식2) new KeyFrame(지속시간, 에니메이션이 종료된 후 처리할 이벤트, keyValue객체);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			
			
			//KeyFrame에 설정한 내용대로 에니메이션을 진행시키는 객체
			Timeline timeline = new Timeline();
			
			
			//TimeLine에 KeyFrame객체 추가
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();//에니메이션 실행
			
			*/
			
			
			
			/*
			//Fade효과 에니메이션(값의 범위 : 0.0(투명)~1.0(불투명))
			
			login.setOpacity(0);//불투명도의 값 설정(시작값)
			
			KeyValue keyValue = new KeyValue(login.opacityProperty(), 1);
			
			KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
			
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();//에니메이션 실행
			
			
			*/
			
			
			/*
			
			//rotate
			login.setRotate(180);
			
			KeyValue keyValue = new KeyValue(login.rotateProperty(),0);
			
			
			KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(keyFrame);
			
			
			timeline.play();//에니메이션 실행
			
			
			*/
			
			
			
			//2가지 에니메이션 효과를 동시에 진행
			login.setRotate(180);
			
			KeyValue keyValue1 = new KeyValue(login.rotateProperty(),1);
			KeyValue keyValue = new KeyValue(login.rotateProperty(),0);
			
			
			KeyFrame keyFrame1 = new KeyFrame(Duration.millis(1000), keyValue1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), keyValue);
			Timeline timeline = new Timeline();
			timeline.getKeyFrames().addAll(keyFrame1,keyFrame);
			
			
			timeline.play();//에니메이션 실행
			
			
			
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
    }
}
