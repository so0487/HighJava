package basic;

/*
	람다식 ==> 익명함수를 생성하기 위한 식 (java 1.8부터 지원)
		   ==> 자바에서는 '매개변수를 가진 코드 블럭'으로 구성하고
		       '런타임시' '익명구현객체'로 생성되어 처리된다.
		   ==> 람다식으로 변환할 수 있는 것은 하나의 추상메서드가
		       선언된 인터페이스만 람다식으로 변환할 수 있다.
		       (이런 인터페이스를 '함수적 인터페이스'라고 한다.)
		       (annotation으로는 @FuntionalInterface로 나타낸다.)
		       
   형식)
     인터페이스명 변수명 = 람다식;
     
   람다식 형식)
     (자료형이름1 변수명1, 자료형이름2 변수명2, ...) -> { 실행문들; ... }

	규칙)
  	1. '자료형이름'을 생략할 수 있다.
  		(int a) -> {  System.out.println(a);  }
  		(a) -> {  System.out.println(a);  }
  	
  	2. 매개변수가 1개만 있으면 괄호'( )'를 생략할 수 있다.
  		a -> {  System.out.println(a);  }
  		
  	3. 실행문이 1개이면 중괄호 '{ }'를 생략할 수 있다.
  		a -> System.out.println(a) 
  	
  	4. 매개변수가 없거나 2개 이상이면 괄호 '( )'를 생략할 수 없다.
  		( ) -> System.out.println("안녕하세요")
  		
  	5. 반환값이 있을 경우에는 return명령을 사용한다.
  		(a, b) -> { return a + b; }
  		
	6. 실행문에 return문만 있을 경우에는 return과 
	   중괄호 '{ }'를 생략할 수 있다.
	   (a, b) ->  a + b
	   
*/
public class LambdaTest1 {

	public static void main(String[] args) {
		// Runnable인터페이스는 '함수적 인터페이스'이다.
		
		// 람다식을 사용하지 않는 경우
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=10; i++) {
					System.out.println(i);
				}
			}
		};
		
		Thread th1 = new Thread(r1);
		th1.start();
		
		
		// 람다식을 사용하는 경우
		Runnable r2 = () -> {
			for(int i=1; i<=10; i++) {
				System.out.println("람다식 - " + i);
			}
		};
		
		Thread th2 = new Thread(r2);
		th2.start();

	}

}
