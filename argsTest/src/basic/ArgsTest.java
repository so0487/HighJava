package basic;

public class ArgsTest {
	/*
		가변형 인수 - 메서드의 매개변수의 개수가 호출될 때마다 다를 때 사용한다.
					- 이 가변형 인수는 메서드 내에서는 배열로 처리된다.
					- 가변형 인수는 한 개의 메서드에 하나만 사용할 수 있다.
	*/
	// 배열을 이용한 메서드
	public int sumArr( int[] data  ){
		int total = 0;
		for(int i=0; i<data.length; i++){
			total += data[i];
		}
		
		return total;
		
	}
	
	// 가변형 인수를 이용한 메서드
	public int sumArg( int...data ){
		int total = 0;
		for(int i=0; i<data.length; i++){
			total += data[i];
		}
		
		return total;
	}
	
	// 가변형인수와 일반인수를 같이 사용할 경우에는 가변형 인수를
	// 제일 뒤에 배치해야 한다.
	//public void argTest(  String name, int...data){
	public void argTest( int num, int...data  ){
		
	}
	
	// argTest("홍길동", 1,2,3 );
	// argTest(1,2,3,4);

	public static void main(String[] args) {
		ArgsTest at = new ArgsTest();
		
		int[] nums = {1,2,3,4,5,6,7,8,9};
		System.out.println(  at.sumArr(nums) );
		
		System.out.println( at.sumArr( new int[]{ 10, 20, 30, 40}  ));
		System.out.println("-----------------------------------");
		
		System.out.println(at.sumArg(1,2,3,4,5,6,7,8,9));
		System.out.println(at.sumArg(10,20,30,40));
	}

}
