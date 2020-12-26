package basic.thread;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.javafx.webkit.ThemeClientImpl;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadTimeController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label lblTime;

	@FXML
	private Button btnStart;

	@FXML
	private Button btnStop;




	//이 변수가 true이면 쓰레드가 멈춘다.
	private boolean stop;	//쓰레드가 멈출지 여부를 결정하는 변수



	@FXML
	void btnStartClicked(ActionEvent event) {
		stop = false;
		Thread th = new Thread(()-> {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

			while(!stop) {
				String strTime = sdf.format(new Date());


				//사용자가 작성한 쓰레드에서 javaFx의 컨트롤 데이터를 
				//변경하게 되면 JavaFx의 쓰레드와 사용자가 작성한 
				//쓰레드가 충돌이 발생해서 오류가 발생한다.

				//이 때 오류를 발생하지 않고 컨트롤의 데이터를 변경하려면
				//Platform.runLater()메서드에 Runnable을 구현하여
				//매개변수 값으로 넣어주면 된다.
				Platform.runLater(()->{
					lblTime.setText(strTime);

				});


				try {
					Thread.sleep(100);
				} catch (Exception e) {
				}
			}
		});
		th.setDaemon(true);
		th.start();
	}

	@FXML
	void btnStopClicked(ActionEvent event) {
		stop=true;
	}

	@FXML
	void initialize() {
		assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
		assert btnStart != null : "fx:id=\"btnStart\" was not injected: check your FXML file 'ThreadTimer.fxml'.";
		assert btnStop != null : "fx:id=\"btnStop\" was not injected: check your FXML file 'ThreadTimer.fxml'.";

	}
}
