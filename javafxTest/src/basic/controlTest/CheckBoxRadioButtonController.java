package basic.controlTest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import util.AlertUtil;

public class CheckBoxRadioButtonController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfName;

    @FXML
    private RadioButton rdMan;

    @FXML
    private ToggleGroup tgsung;

    @FXML
    private RadioButton rdWoman;

    @FXML
    private CheckBox chk1;

    @FXML
    private CheckBox chk2;

    @FXML
    private CheckBox chk3;

    @FXML
    private CheckBox chk4;

    @FXML
    private CheckBox chk5;

    @FXML
    private CheckBox chk6;

    @FXML
    private CheckBox chk7;

    @FXML
    private CheckBox chk8;

    @FXML
    private Button btnView;

    @FXML
    private TextArea txtResult;
    
    CheckBox[] chkboxs;

    // '보기버튼'을 클릭했을 때 이벤트 처리
    @FXML
    void btnViewClicked(ActionEvent event) {
    	String name = tfName.getText();
    	//if("".equals(name)) {  // 이름이 입력되지 않았을 경우
    	if(name.isEmpty()) {
    		//txtResult.setText("이름을 입력하세요");
    		AlertUtil.warnMsg("입력 오류", "이름을 입력하세요.");
    		tfName.requestFocus();
    		return;
    	}
    	
    	String sung = "";
    	if(rdMan.isSelected()) {
    		sung = "남자";
    	}else {
    		sung = "여자";
    	}
    	
    	String hobby = "";
    	
    	for(int i=0; i<chkboxs.length; i++) {
    		if(chkboxs[i].isSelected()) {
    			if(!"".equals(hobby)) {
    				hobby += ", ";
    			}
    			hobby += chkboxs[i].getText();
    		}
    	}
    	
    	txtResult.setText(name + "씨\n");
    	txtResult.appendText("당신은 " + sung + "이고\n");
    	txtResult.appendText("취미는 " +
    			("".equals(hobby) ? "없군요" : hobby + "이군요" )  );
    	
    }

    @FXML
    void initialize() {
    	// 체크박스들을 배열에 저장한다.
        chkboxs = new CheckBox[] {
            chk1, chk2, chk3, chk4, chk5, chk6, chk7, chk8	
        };
    }
}
