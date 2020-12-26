package basic.controlTest;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CheckBoxTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		String[] names = new String[] {"Security", "Project", "Chart"};
		Image[] images = new Image[names.length];
		ImageView[] icons = new ImageView[names.length];
		
		CheckBox[] chkboxs = new CheckBox[names.length];

		Rectangle rect = new Rectangle(90, 30);
		rect.setArcHeight(10);		// 꼭지점 모따기 (꼬지점을 둥글게 만든다.)
		rect.setArcWidth(10);
		rect.setFill(Color.rgb(41,41,41));
		
		for(int i=0; i<names.length; i++) {
			// 이미지 파일을 읽어와 Image객체에 넣기
			final Image img = images[i] = new Image(
				CheckBoxTest.class.getResourceAsStream(
						"../../images/" + names[i] + ".png"));
			
			final ImageView icon = icons[i] = new ImageView();
			
			chkboxs[i] = new CheckBox(names[i]);
			
			/*
			// CheckBox를 클릭했을 때 이벤트 처리
			chkboxs[i].setOnAction(e->{
				// 이벤트가 최초로 발생한 객체 구하기
				CheckBox chk = (CheckBox) e.getSource();
				
				// CheckBox의 체크 여부를 나타내는 메서드는
				// isSelected() 메서드이다.
				// 현재 체크된 상태이면 true, 체크가 안된상태이면 false를
				// 반환한다.
				
				// 체크된 CheckBox와 관련된 이미지 보이기
				icon.setImage(chk.isSelected() ? img : null);
			});
			*/
			
			
			// CheckBox의 체크 상태 값을 감시하고 이 값이 변경되었을 때 
			// 처리하는 방법
			chkboxs[i].selectedProperty().addListener(
				new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						// oldValue ==> 변경되기 전 값,
						// newValue ==> 변경된 후 값
						icon.setImage(newValue ? img : null);
					}
				}
			);
			
		}
		
		Button btnOk = new Button(" 확 인 ");
		btnOk.setOnAction(e->{
			// CheckBox의 체크 여부는 isSelected()를 이용한다.
			if(chkboxs[0].isSelected()==true) {
				System.out.println(chkboxs[0].getText() + " 선택");
			}else {
				System.out.println(chkboxs[0].getText() + " 선택 해제");
			}
			
			// CheckBox의 체크 여부 설정하기 - setSelected
			chkboxs[2].setSelected(true);  // 체크 상태 만들기
			chkboxs[1].setSelected(false); // 체크를 해제 시킨다.
			
		});
		
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(chkboxs);
		vbox.getChildren().add(btnOk);
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(0,0,0,5));
		hbox.getChildren().addAll(icons);
		
		StackPane stack = new StackPane();
		stack.getChildren().addAll(rect, hbox);
		StackPane.setAlignment(rect, Pos.TOP_CENTER);
		
		HBox root = new HBox(40);
		root.setPadding(new Insets(20, 10, 10, 20));
		root.getChildren().addAll(vbox, stack);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("CheckBox 연습");
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
