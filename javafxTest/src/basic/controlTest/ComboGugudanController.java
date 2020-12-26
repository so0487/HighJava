package basic.controlTest;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import util.AlertUtil;

public class ComboGugudanController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbDan;

    @FXML
    private Button btnView;

    @FXML
    private TextArea taResult;

    // '출력'버튼을 클릭했을 때 이벤트 처리
    @FXML
    void btnViewClicked(ActionEvent event) {
    	if(cmbDan.getValue() == null) {
    		AlertUtil.errorMsg("단 선택 오류", "출력할 단을 선택하세요.");
    		return;
    	}
    		
    	int dan = cmbDan.getValue();
    	taResult.setText( dan + " 단\n\n");
    	
    	for(int i=1; i<=9; i++) {
    		int r = dan * i;
    		taResult.appendText(dan + " * " + i + " = " + r + "\n");
    	}
    }

    @FXML
    void initialize() {
    	// 콤보박스에 데이터 초기화
    	cmbDan.getItems().addAll(1,2,3,4,5,6,7,8,9);
    	
    	cmbDan.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
			
			@Override
			public ListCell<Integer> call(ListView<Integer> param) {
				return new ListCell<Integer>() {
					@Override
					protected void updateItem(Integer item, boolean empty) {
						super.updateItem(item, empty);
						if(empty) {
							setText(null);
						}else {
							setText(item + " 단");
						}
					}
				};
			}
		});
    	
    	cmbDan.setButtonCell(new ListCell<Integer>() {
    		@Override
    		protected void updateItem(Integer item, boolean empty) {
    			super.updateItem(item, empty);
    			
    			if(empty) {
					setText(null);
				}else {
					setText(item + " 단");
				}
    		}
    	});
    	
    }
}
