package basic.menu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();

		//root.setPadding(new Insets(10));

		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

		root.setTop(menuBar);

		//File메뉴 : new, save, exit
		Menu fileMenu = new Menu("File");		//주 메뉴


		//주 메뉴에 부 메뉴를 추가한다.
		MenuItem newMenuItem = new MenuItem("New");
		MenuItem saveMenuItem = new MenuItem("Save");
		MenuItem exitMenuItem = new MenuItem("Exit");


		//주 메뉴에 부 메뉴를 추가
		fileMenu.getItems().addAll(newMenuItem,saveMenuItem,exitMenuItem);



		//메뉴를 선택 했을 때 처리는 이벤트 처리 방식으로 만든다.
		exitMenuItem.setOnAction(e->{
			Platform.exit();
		});


		//메뉴의 단축키 설정하기(Alt+shift+X)
		exitMenuItem.setAccelerator(
				new KeyCodeCombination(KeyCode.X, KeyCombination.ALT_DOWN, KeyCombination.SHIFT_DOWN));



		//-------------------------------------------------------------------------------------------------------//


		Menu webmeMenu = new Menu("Web");



		CheckMenuItem htmlMenu = new CheckMenuItem("HTML");
		htmlMenu.setSelected(true);


		CheckMenuItem cssMenu = new CheckMenuItem("CSS");
		CheckMenuItem scriptMenu = new CheckMenuItem("JavaScript");

		webmeMenu.getItems().addAll(htmlMenu, cssMenu, scriptMenu);



		//----------------------------------------------------------------------------------------------------------//

		Menu sqlMenu = new Menu("Sql");
		ToggleGroup tgroup = new ToggleGroup();

		RadioMenuItem mysqlMenu = new RadioMenuItem("MySQL");
		mysqlMenu.setToggleGroup(tgroup);
		RadioMenuItem oracleMenu = new RadioMenuItem("Oracle");
		oracleMenu.setToggleGroup(tgroup);
		oracleMenu.setSelected(true);
		RadioMenuItem msSqlMenu = new RadioMenuItem("MS-SQL");
		msSqlMenu.setToggleGroup(tgroup);


		sqlMenu.getItems().addAll(mysqlMenu,oracleMenu,msSqlMenu);



		//부 메뉴의 부 메뉴 만들기
		//1) Menu 객체 생성
		Menu tutorialMenu = new Menu("Tutorial");

		//2)MenuItem추가
		tutorialMenu.getItems().addAll(
				new CheckMenuItem("Java초급"),
				new CheckMenuItem("오라클"),
				new CheckMenuItem("Java고급"),
				new CheckMenuItem("HTML")

				);

		//3)Menu객체를 다른 메뉴의 부 메뉴에 추가한다.
		//sqlMenu.getItems().addAll(tutorialMenu);
		sqlMenu.getItems().addAll(new SeparatorMenuItem(),tutorialMenu);



		//-------------------------------------------------------------------------//




		//만들어진 메뉴를 MenuBar에 추가
		menuBar.getMenus().addAll(fileMenu, webmeMenu, sqlMenu);

		Scene scene = new Scene(root,300,250);
		primaryStage.setScene(scene);
		primaryStage.setTitle("메뉴 만들기 연습");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
