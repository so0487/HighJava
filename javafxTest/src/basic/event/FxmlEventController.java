package basic.event;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FxmlEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private TextArea taResult;

    @FXML
    private TextField tfMsg;

    // 첫번째 버튼을 클릭했을 때 처리할 메서드
    // 이 메서드 이름은 fxml문서에서 설정한다.
    @FXML
    void btn1Click(ActionEvent event) {
    	String str = tfMsg.getText();
    	
    	taResult.appendText("첫번째 버튼 이벤트 처리 --> " + str + "\n");
    }

    @FXML
    void initialize() {
        // fxml문서가 load된 후 제일 먼저 자동으로 실행되는 메서드
    	// 이 곳은 주로 초기화 작업이나 이벤트 설정등에 사용된다.
    	
    	// 이벤트 설정 직접하기
    	btn2.setOnAction(e->{
    		String str = tfMsg.getText();
        	
        	taResult.appendText("두번째 버튼 처리 --> " + str + "\n");
    	});
    }
}






