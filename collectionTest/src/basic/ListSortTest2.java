package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add( new Member(1, "홍길동", "010-1111-1111")  );
		memList.add( new Member(5, "이순신", "010-2222-1111")  );
		memList.add( new Member(10, "성춘향", "010-3333-1111")  );
		memList.add( new Member(3, "강감찬", "010-4444-1111")  );
		memList.add( new Member(6, "일지매", "010-5555-1111")  );
		memList.add( new Member(2, "변학도", "010-6666-1111")  );
		
		System.out.println("정렬전...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------------");
		
		Collections.sort(memList);
		
		System.out.println("정렬후...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------------");

		// Member의 num값의 내림차순으로 정렬하는 외부 정렬 기준 클래스를 이용하여
		// 정렬하시오.
		
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("정렬후...");
		for(Member mem : memList){
			System.out.println(mem);
		}
		System.out.println("----------------------------------------------------");

		
	}

}

/*
	정렬과 관련된 interface는 Comparable, Comparator 이렇게 두 가지가 있다.
	
	Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을때
	구현하는 인터페이스이고,
	
	Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때 구현하는 인터페이스이다.
	
	
	Comparable에서는 compareTo()메서드를 재정의하고
	Comparator에서는 compare()메서드를 재정의해야 한다.

*/

// Member클래스의 회원이름을 기준으로 오름차순이 되도록 내부 정렬 기준을 추가하기
// Comparable인터페이스를 구현한다.
class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	
	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
	@Override
	public int compareTo(Member mem) {
		
		return this.getName().compareTo(mem.getName());
	}
	
}

// Member의 num값의 내림차순으로 정렬하는 외부 정렬 클래스
class SortNumDesc implements Comparator<Member>{
	@Override
	public int compare(Member mem1, Member mem2) {
		/*
		if(mem1.getNum() > mem2.getNum()){
			return -1;
		}else if(mem1.getNum() < mem2.getNum()){
			return 1;	// 양수를 반환하면 두 값의 순서가 바뀐다.
		}else{
			return 0;
		}
		*/
		
		// Wrapper클래스를 이용하는 방법1
		//return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
		// Wrapper클래스를 이용하는 방법2
		return Integer.compare(mem1.getNum(), mem2.getNum()) * -1;
		
	}
}



