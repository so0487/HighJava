package basic;

import javafx.application.Application;
import javafx.stage.Stage;

/*
	JavaFX의 객체들
	
	Stage객체 ==> window를 나타내는 객체
	Scene객체 ==> 하나의 장면을 나타내는 객체
	
	컨테이너 객체 ==> 컨트롤 객체나 또 다른 컨테이너 객체를
				담을 수 있는 객체
	컨트롤 객체 ==> 사용자가 직접 사용하는 객체
	
	
	JavaFX의 실행 순서
	- main()메서드 --> raunch()메서드 호출 -->
	  현재 객체 생성(생성자 호출) --> init()메서드 실행
	  --> start()메서드 실행 --> 
	  창이 나타나고 사용자가 이 창을 이용해서 작업을 수행한다.
	  --> 종료 --> stop()메서드 실행
	  
	- 종료되는 경우
	1. 마지막 창(Stage)가 닫힐때
	2. 마지막 창(Stage)의 close()메서드가 호출될 때
	3. 자바 명령 중 Platform.exit()메서드를 호출하거나
	   Systme.exit(0)메서드를 호출할 때
	   (단, System.exit(0)메서드를 호출해서 종료하면
	        stop()메서드가 실행되지 않고 바로 종료된다.)

*/
public class JavaFxLifeCycle extends Application {
	
	// 생성자
	public JavaFxLifeCycle() {
		System.out.println(Thread.currentThread().getName() +
				" : 생성자 메서드 실행...");
	}
	
	
	@Override
	public void init() throws Exception {
		// 주로 프로그램의 초기화 작업에 사용된다.
		System.out.println(Thread.currentThread().getName() + 
				" : init()메서드 실행...");
	}

	@Override
	public void start(Stage primaryStage) {
		// 창과 화면을 구성하고 사용자의 명령을 처리하는 부분
		System.out.println(Thread.currentThread().getName() + 
				" : start메서드 실행...");
		
		primaryStage.show();  // 창을 보이게 한다.
	}
	
	@Override
	public void stop() throws Exception {
		// 주로 자원을 정리하는 작업에 사용된다.
		System.out.println(Thread.currentThread().getName() +
				" : stop()메서드 실행...");
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + 
				" : main메서드 시작...");
		launch(args);
	}
}
