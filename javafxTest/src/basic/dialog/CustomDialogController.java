package basic.dialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CustomDialogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnclose;

    @FXML
    void btncloseClicked(ActionEvent event) {
    	//현재 화면에 출력된 컨트롤을 이용해서 
    	//현재 창의 Stage객체 구하기
    	
    	Stage currentStage = (Stage) btnclose.getScene().getWindow();
    	
    	currentStage.close();
    	
    	
    	
    	
    }

    @FXML
    void initialize() {
        assert btnclose != null : "fx:id=\"btnclose\" was not injected: check your FXML file 'customDialog.fxml'.";

    }
}
