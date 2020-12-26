package main;

import main.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			
			
			BorderPane root = new BorderPane();
			//left
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("../fxml/left.fxml"));
			AnchorPane left = loader.load();
			root.setLeft(left);
			
			//top
			//BorderPane root2 = new BorderPane();
			FXMLLoader loader2 = new FXMLLoader(Main.class.getResource("../fxml/top.fxml"));
			AnchorPane top = loader2.load();
			root.setTop(top);
			
			
			//center
			FXMLLoader loader1 = new FXMLLoader(Main.class.getResource("../fxml/center.fxml"));
			StackPane center = loader1.load();
			root.setCenter(center);
			
			Scene scene = new Scene(root,1200,1000);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
