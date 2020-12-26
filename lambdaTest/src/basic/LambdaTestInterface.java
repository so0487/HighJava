package basic;
//  함수적 인터페이스 ==> 추상 메서드가 1개인 인터페이스

@FunctionalInterface
public interface LambdaTestInterface {
	// 반환값이 없고 매개변수도 없는 메서드 선언
	public void test();
}


@FunctionalInterface
interface LambdaTestInterface2{
	// 반환값이 없고 매개변수가 있는 메서드
	public void test2(int a);
}


@FunctionalInterface
interface LambdaTestInterface3{
	// 반환값이 있고 매개변수도 있는 메서드
	public int test3(int a, int b);
}
