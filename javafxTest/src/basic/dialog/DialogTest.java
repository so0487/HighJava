package basic.dialog;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		HBox root = new HBox(10);
		root.setPrefHeight(150);
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);

		Button btnFileOpen = new Button("Open FileChooser �떎�뻾");
		btnFileOpen.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();

			// �꽑�깮�븷 �뙆�씪 醫낅쪟 援щ텇�븯湲�
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text File", "*.txt"),
					new ExtensionFilter("Image File", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("Audio File", "*.wav", "*.mp3", "*.aac"),
					new ExtensionFilter("All File", "*.*")
					);

			// �뿴湲� �븷 �뵒�젆�넗由�(�뤃�뜑) �꽕�젙 
			File openDir = new File("C:/soo/d_other");
			fileChooser.setInitialDirectory(openDir);

			// �뙆�씪 �뿴湲� 李� �떎�뻾
			File selectFile = fileChooser.showOpenDialog(primaryStage);

			if(selectFile != null) {
				// �꽑�깮�븳 �뙆�씪�쓣 �씠�슜�븳 �씫湲� �옉�뾽�쓣 �닔�뻾�븳�떎.
				System.out.println("�꽑�깮�븳 �뙆�씪 : " + selectFile.getPath());
			}

		});


		Button btnFileSave = new Button("Save FileChooser �떎�뻾");
		btnFileSave.setOnAction(e->{
			FileChooser fileChooser = new FileChooser();

			// �꽑�깮�븷 �뙆�씪 醫낅쪟 援щ텇�븯湲�
			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text File", "*.txt"),
					new ExtensionFilter("All File", "*.*")
					);

			// �뙆�씪 ���옣 李� �떎�뻾
			File selectFile = fileChooser.showSaveDialog(primaryStage);

			if(selectFile != null) {
				// �꽑�깮�븳 �뙆�씪�쓣 �씠�슜�븳 �벐湲� �옉�뾽�쓣 �닔�뻾�븳�떎.
				System.out.println("�꽑�깮�븳 �뙆�씪 : " + selectFile.getPath());
			}
		});

		Button btnDirectory = new Button("DriectoryChooser �떎�뻾");
		btnDirectory.setOnAction(e->{
			DirectoryChooser dirChooser = new DirectoryChooser();

			File selectDir = dirChooser.showDialog(primaryStage);

			if(selectDir!=null) {
				System.out.println("�꽑�깮�븳 �뤃�뜑 : " + selectDir.getPath());
			}

		});


		Button btnPopup = new Button("Popup �떎�뻾");
		btnPopup.setOnAction(e->{
			Popup popup = new Popup();

			HBox popRoot = new HBox();
			popRoot.setAlignment(Pos.CENTER_LEFT);
			popRoot.setStyle(
					"-fx-background-color: black; -fx-background-radius: 20;");

			ImageView imgView = new ImageView();
			imgView.setImage(new Image(
					DialogTest.class.getResourceAsStream("../../images/ok.png")
					));

			imgView.setFitHeight(30);
			imgView.setFitWidth(30);

			imgView.setOnMouseClicked(evt->{
				popup.hide();  // Popup李� �떕湲�
			});


			Label lblMsg = new Label("硫붿떆吏�媛� �솕�뒿�땲�떎.");
			lblMsg.setStyle("-fx-text-fill: red;");
			HBox.setMargin(lblMsg, new Insets(0, 5, 0, 5));

			popRoot.getChildren().addAll(imgView, lblMsg);

			popup.getContent().add(popRoot);

			// �떎瑜� �쁺�뿭�쓣 �겢由��븯硫� �옄�룞�쑝濡� �궗�씪吏꾨떎.
			//popup.setAutoHide(true);

			popup.setAutoHide(false);  

			popup.show(primaryStage);

		});


		Button btnCustom = new Button("�궗�슜�옄 �젙�쓽 Dialog �떎�뻾");
		btnCustom.setOnAction(e->{

			try {
				//�깉濡쒖슫 Stage媛앹껜 �깮�꽦
				Stage dialog = new Stage(StageStyle.DECORATED);

				//�깉 李쎌쓽 遺�紐� 李� �꽕�젙
				dialog.initOwner(primaryStage);

				//紐⑤떖 李� �뿬遺� �꽕�젙
				dialog.initModality(Modality.WINDOW_MODAL);

				//�깉 李쎌뿉 �굹���궇 Fxml臾몄꽌 �씫�뼱�삤湲�

				Parent childRoot = FXMLLoader.load(
						DialogTest.class.getResource("customDialog.fxml"));

				//�옄�떇 李� Stage�뿉 �씫�뼱�삩 Fxml臾몄꽌 �궡�슜�쓣 �굹���궦�떎.

				Scene childScene = new Scene(childRoot);

				dialog.setScene(childScene);
				dialog.setTitle("�옄�떇李� �뿰�뒿");
				dialog.show();


			} catch (IOException e2) {
				e2.printStackTrace();
			}
		});




		root.getChildren().addAll(btnFileOpen, btnFileSave, btnDirectory,
				btnPopup, btnCustom);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Dialog �뿰�뒿");
		primaryStage.show();


	}

	public static void main(String[] args) {
		launch(args);
	}
}
