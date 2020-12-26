package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬전 : " + list);
		
		// 정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		// 정렬은 기본적으로 오름차순 정렬을 수행한다.
		Collections.sort(list);
		
		System.out.println("정렬후 : " + list);
		
		Collections.shuffle(list);  // 자료 섞기
		System.out.println("자료 섞기 후 : " + list);
		
		// 외부 정렬 기준 클래스를 지정해서 정렬하기
		Collections.sort(list, new Desc());
		
		System.out.println("내림차순 정렬후 : " + list);
		
	}

}

// 정렬 방식을 정해 주는 Class를 만든다. (외부 정렬 기준 클래스라고 한다.)
// Comparator인터페이스를 구현해서 작성해야 한다.
class Desc implements Comparator<String>{
	
	// compare()메서드를 이용해서 정렬하고자 하는 기준을 정해준다.
	
	// compare()메서드의 반환값 
	// 0은 두 값이 같다. 
	// 양수는 앞, 뒤의 순서를 바꾼다.
	// 음수는 앞, 뒤의 순서를 바꾸지 않는다.
	
	// 예) 오름차순일 경우 ==> 앞의 값이 크면 양수, 같으면 0, 
	//                        앞의 값이 작으면 음수를 반환하도록 구현하면 된다.
	
	/*
		String객체에는 정렬을 위해서 compareTo()메서드가 구현되어 있는데
		이 메서드의 반환값은 오름차순에 맞게 반환되도록 구현되어 있다.
		(Wrapper클래스와 Date, File클래스에도 구현되어 있다.)
	*/
	@Override
	public int compare(String o1, String o2) {
		//  내림차순으로 구현하려고 한다.
		if(o1.compareTo(o2)>0){
			return -1;
		}else if(o1.compareTo(o2)==0){
			return 0;
		}else {
			return 1;
		}
		
	}
}



