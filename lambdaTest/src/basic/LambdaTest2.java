package basic;

public class LambdaTest2 {

	public static void main(String[] args) {
		// 람다식을 사용하지 않은 경우
		LambdaTestInterface lt1 = new LambdaTestInterface() {
			@Override
			public void test() {
				System.out.println("안녕하세요. 익명구현체 입니다.");
			}
		};
		
		lt1.test();
		
		// 람다식
		LambdaTestInterface lt2 = () -> { 
			System.out.println("안녕하세요. 람다식입니다.");
		};
		lt2.test();
		
		LambdaTestInterface lt3 = () ->	
			System.out.println("안녕하세요. 람다식입니다. 2222");
		lt3.test();
		
		// ========================================
		
		LambdaTestInterface2 lt4 = (int a) -> {
			int result = a + 10;
			System.out.println(a + " + 10 = " + result);
		};
		
		lt4.test2(200);
		
		LambdaTestInterface2 lt5 = (a) -> {
			int result = a * 10;
			System.out.println(a + " * 10 = " + result);
		};
		
		lt5.test2(20);
		
		LambdaTestInterface2 lt6 = a -> {
			int result = a - 10;
			System.out.println(a + " - 10 = " + result);
		};
		lt6.test2(300);
		//===========================================
		
		LambdaTestInterface3 lt7 = 
			(int x, int y) -> {
				int r = x + y;
				return r;
			};
		int k1 = lt7.test3(3, 8);
		System.out.println("k1 = " + k1);
		
		LambdaTestInterface3 lt8 =
			(a, b) -> { return a*b; };
			
		int k2 = lt8.test3(9, 31);
		System.out.println("k2 = " + k2);
		
		LambdaTestInterface3 lt9 =
			(a, b) -> a - b;
			
		int k3 = lt9.test3(200, 140);
		System.out.println("k3 = " + k3);
		
		
	}

}
