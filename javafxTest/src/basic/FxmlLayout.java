package basic;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxmlLayout extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		// fxml파일을 읽어와 현재 Stage객체에 적용하는 방법
		
		// fxml파일을 읽어오는 방법1
		//VBox root = 
		//	FXMLLoader.load(FxmlLayout.class.getResource("FxmlLayout.fxml"));
		
		// fxml파일 읽어오는 방법2
		FXMLLoader loader = 
			new FXMLLoader(FxmlLayout.class.getResource("FxmlLayout.fxml"));
		VBox root = loader.load();
		
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("fxml문서를 이용한 레이아웃");
		
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
