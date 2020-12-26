package basic;

/*
	제네릭 클래스 만드는 방법  ==> 자바 버전 1.5이상에서 사용 가능
	
	class 클래스명<제네릭타입글자>{
		제네릭타입글자 변수명;		// 멤버 변수 선언에 사용할 경우
		...
		
		제네릭타입글자 메서드명(){	// 메서드의 반환값으로 사용할 경우
			...
			return 값;
		}
		
		반환값타입 메서드명(제네릭타입글자 변수명){  // 메서드의 매개변수에 사용할 경우
			...
		}
		
		
	}
	
	
	
	// '제네릭 타입 글자'로 많이 사용하는 문자
	 T  ==> Type
	 K  ==> Key
	 V  ==> Value
	 E  ==> Element
	 
	
	
	
*/

class NonGenericClass{
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}


class MyGenericClass<T>{
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

}



public class GenericTest {

	public static void main(String[] args) {
		// 제네릭을 사용하지 않는 클래스 이용하기
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setValue("가나다라");
		
		String rtn = (String)ng1.getValue();
		System.out.println("반환값1 : " + rtn);
		System.out.println();
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setValue(100);
		int irtn = (int)ng2.getValue();
		System.out.println("반환값2 : " + irtn);
		
		
		//int irtn2 = (int)ng1.getValue();
		//System.out.println("반환값3 : " + irtn2);
		System.out.println();
		
		
		// 제네릭을 사용한 클래스 이용하기
		MyGenericClass<String> mg1 = new MyGenericClass<>();
		mg1.setValue("안녕하세요.");
		
		rtn = mg1.getValue();
		System.out.println("제네릭 문자열 반환값 : " + rtn);
		
		MyGenericClass<Integer> mg2 = new MyGenericClass<>();
		mg2.setValue(123);
		
		irtn = mg2.getValue();
		System.out.println("제네릭 정수 반환값 : " + irtn);
		
		//mg2.setValue("가나다라");
		
		
		

	}

}
