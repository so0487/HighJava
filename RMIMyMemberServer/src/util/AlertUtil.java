package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {
	public static void infoMsg(String header, String msg) {
		Alert info = new Alert(AlertType.INFORMATION);
		
		info.setTitle("INFORMATION");
		info.setHeaderText(header);
		info.setContentText(msg);
		info.showAndWait(); 
	}
	
	public static void errorMsg(String header, String msg) {
		Alert error = new Alert(AlertType.ERROR);
		
		error.setTitle("ERROR");
		error.setHeaderText(header);
		error.setContentText(msg);
		error.showAndWait();
	}
	
	public static void warnMsg(String header, String msg) {
		Alert warn = new Alert(AlertType.WARNING);
		
		warn.setTitle("WARNING");
		warn.setHeaderText(header);
		warn.setContentText(msg);
		warn.showAndWait();
	}
	
	// OK버튼을 누르면 true, 취소버튼을 누르면 false 반환
	public static boolean confirm(String header, String msg) {
		Alert confirm = new Alert(AlertType.CONFIRMATION);
		confirm.setTitle("CONFIRMATION");
		confirm.setHeaderText(header);
		confirm.setContentText(msg);
		
		ButtonType confirmResult = confirm.showAndWait().get();
		
		boolean select = false;
		
		if(confirmResult == ButtonType.OK) {  // 'OK 버튼'
			select = true;
		}
		
		return select;
	}
	
	// 사용자가 값을 입력하고 'OK버튼'을 누르면 입력한 값이 문자열로 반환되고
	// '취소버튼'을 누르면 null이 반환된다.
	public static String prompt(String msg) {
		TextInputDialog prompt = new TextInputDialog("기본값");
		
		prompt.setTitle("PROMPT창");
		prompt.setHeaderText(msg);
		prompt.setContentText("입 력 : ");
		
		// 창을 띄우고 사용자가 입력한 정보를 얻어온다.
		Optional<String> result = prompt.showAndWait();
		
		String value = null;
		if(result.isPresent()) {  // 값이 있으면..(OK버튼 눌렀을 경우)
			value = result.get();  // 값을 꺼내온다.
		}
		
		return value;
	}
	
}
