package basic.menu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MenuTest2 extends Application {
	private String fileName = "noName.txt";
	private File fileDir = new File("c:/soo/d_other");

	@Override
	public void start(Stage primaryStage) {


		BorderPane root = new BorderPane();

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		root.setTop(menuBar);

		Menu fileMenu = new Menu("File");


		MenuItem newMenu = new MenuItem("�깉濡� 留뚮뱾湲�");
		MenuItem openMenu = new MenuItem("�뿴湲�");
		MenuItem saveMenu = new MenuItem("�깉�씠由꾩쑝濡� ���옣");
		MenuItem exitMenu = new MenuItem("�떕湲�");

		fileMenu.getItems().addAll(newMenu, openMenu, saveMenu,
				new SeparatorMenuItem(),exitMenu);

		menuBar.getMenus().add(fileMenu);

		TextArea txtMain = new TextArea();
		root.setCenter(txtMain);

		//�깉濡� 留뚮뱾湲�
		newMenu.setOnAction(e->{
			fileName = "noName.txt";
			txtMain.clear();
			primaryStage.setTitle(fileName+"-Fx 硫붾え�옣");
		});

		//�떕湲�
		exitMenu.setOnAction(e->{
			Platform.exit();
		});

		//�뿴湲�
		openMenu.setOnAction(e->{
			FileChooser filechooser = new FileChooser();
			filechooser.getExtensionFilters().addAll(
					new ExtensionFilter("�뀓�뒪�듃 臾몄꽌 (*.txt)", "*.txt"),
					new ExtensionFilter("紐⑤뱺 �뙆�씪 (*.*)", "*.*")
					);

			//�뿴湲고븷 Directory�꽕�젙
			filechooser.setInitialDirectory(fileDir);

			File selectFile = filechooser.showOpenDialog(primaryStage);



			if(selectFile==null) {
				return;
			}
			fileDir = selectFile.getParentFile();	//�쁽�옱 �꽑�깮�븳 �뤃�뜑 ���옣

			fileName = selectFile.getName();

			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(selectFile));

				txtMain.clear();

				char[]data = new char[1024];	//�씫�뼱�삱 �뜲�씠�꽣媛� ���옣�맆 諛곗뿴

				int len=0;
				while((len=br.read(data))>0) {
					String temp = new String(Arrays.copyOf(data, len));
					txtMain.appendText(temp);
				}
			} catch (IOException e2) {
				fileName = "noName.txt";
				e2.printStackTrace();

			}finally {
				if(br!=null) try {br.close();}catch(IOException e3) {}
			}
			//李� �젣紐� 蹂�寃�
			primaryStage.setTitle(fileName+"-Fx 硫붾え�옣");


		});


		//�깉 �씠由꾩쑝濡� ���옣
		saveMenu.setOnAction(e->{
			FileChooser filechooser = new FileChooser();
			filechooser.getExtensionFilters().addAll(
					new ExtensionFilter("�뀓�뒪�듃 臾몄꽌 (*.txt)", "*.txt"),
					new ExtensionFilter("紐⑤뱺 �뙆�씪 (*.*)", "*.*")
					);

			//�뿴湲고븷 Directory�꽕�젙
			filechooser.setInitialDirectory(fileDir);

			//���옣�븷 �뙆�씪紐� �꽕�젙
			filechooser.setInitialFileName(fileName);

			File selectFile = filechooser.showSaveDialog(primaryStage);

			if(selectFile==null) {
				return;
			}

			fileDir = selectFile.getParentFile();
			fileName = selectFile.getName();

			BufferedWriter bw = null;

			try {
				bw=new BufferedWriter(new FileWriter(selectFile));

				bw.write(txtMain.getText());
				bw.flush();
			} catch (IOException e2) {
				e2.printStackTrace();
			}finally {
				try {bw.close();}catch(IOException e3) {}
			}
			//李� �젣紐� 蹂�寃�
			primaryStage.setTitle(fileName+"-Fx 硫붾え�옣");
		});



		Scene scene = new Scene(root,300,250);
		primaryStage.setScene(scene);
		primaryStage.show();












		/*

		//湲곕낯��(pane, menuBar�옉�꽦)
		boolean saveFile=false;
		BorderPane root = new BorderPane();


		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		root.setTop(menuBar);



		//二쇰찓�돱 �옉�꽦
		Menu fileMenu = new Menu("File");

		//遺�硫붾돱 �옉�꽦 
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem openMenuItem = new MenuItem("Open");
		MenuItem saveAsMenuItem = new MenuItem("Save As");
		MenuItem exitMenuItem = new MenuItem("Exit");


		//二� 硫붾돱�뿉 遺� 硫붾돱 異붽�
		fileMenu.getItems().addAll(newMenuItem,openMenuItem,
				saveAsMenuItem,exitMenuItem);




		//湲곕뒫蹂� event �옉�뾽

		FileChooser fileChooser = new FileChooser();



		//open
		openMenuItem.setOnAction(e->{
			File openDir = new File("c:/soo/d_other");
			fileChooser.setInitialDirectory(openDir);

			File selectFile = fileChooser.showOpenDialog(primaryStage);


		});






		//Save As
		saveAsMenuItem.setOnAction(e->{

			fileChooser.getExtensionFilters().addAll(
					new ExtensionFilter("Text File", "*.txt"),
					new ExtensionFilter("All File", "*.*")
					);


			File selectFile  = fileChooser.showSaveDialog(primaryStage);


			if(selectFile!=null) {
				System.out.println("�꽑�깮�븳 �뙆�씪 : "+selectFile.getPath());
			}


		});






		//�궘�젣(Exit)
		exitMenuItem.setOnAction(e->{
			Platform.exit();
		});



		//scene媛앹껜 �깮�꽦 諛� 留뚮뱺 媛앹껜瑜� 紐⑤몢 異붽�
		menuBar.getMenus().addAll(fileMenu);

		Scene scene = new Scene(root,300,250);
		primaryStage.setScene(scene);


		String saveFileName = "";

		if(saveFile==false) {
			primaryStage.setTitle("No name");

		}else {
			primaryStage.setTitle(saveFileName);
		}

		primaryStage.show();

		 */
	}

	public static void main(String[] args) {
		launch(args);
	}

}
