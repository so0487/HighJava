package basic;

public class LambdaTest3 {
	
	// 메서드의 매개변수는 그 메서드의 지역변수이다.
	public void method(final int temp) {
		final int localVar = 100;
		
		int kor = 200;
		
		
		// 람다식 내부에서 사용되는 지역변수는 final이여야 한다.
		// 보통은 final을 붙이지 않으면 컴파일러가 자동으로 붙여준다.
		// (단 변수의 변동이 없을 경우)
		
		//temp = 500;
		//localVar = 300;
		kor = 700;
		
		// 람다식
		LambdaTestInterface lt = () -> {
			// 람다식 내부에서 지역변수 사용하기
			System.out.println("temp = " + temp);
			System.out.println("localVar = " + localVar);
		};
		
		lt.test();
		
		
	}

	public static void main(String[] args) {
		LambdaTest3 lambda = new LambdaTest3();
		lambda.method(50);
	}

}
