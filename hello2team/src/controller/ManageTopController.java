package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ManageTopController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnM1;

    @FXML
    private Button btnM2;

    @FXML
    private Button btnM3;

    @FXML
    private Button btnM4;

    @FXML
    private Button btnM5;

    @FXML
    void initialize() {
    	btnM1.setOnAction(e->{
    		FXMLLoader loader = new FXMLLoader(ManageTopController.class.getResource("../mfxml/managecenter.fxml"));
			ScrollPane root;
			try {
				root = loader.load();
				((StackPane)((BorderPane)(btnM1.getScene().getRoot())).getCenter()).getChildren().add(root);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	
    	
    	});

    }

}
