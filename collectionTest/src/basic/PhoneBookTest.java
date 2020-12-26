package basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
	문제) 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고
		  Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오.
		  이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있다.
		  
		  삭제, 검색기능은 '이름'을 입력받아 처리한다.
		  
		  (Map의 구조는 key값으로 '이름'을 사용하고, 
		  	value값으로는 'Phone클래스의 인스턴스'로 한다.)
		  	
	실행예시)
	  --------------------------
		다음 메뉴를 선택하세요.
		
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
	  --------------------------
		번호입력 >> 1
	  	 
	  새롭게 등록할 전화번호 정보를 입력하세요.
	  이 름 >> 홍길동
	  전화번호 >> 010-1111-1111
	  주 소 >> 대전시 중구 대흥동
	  
	  '홍길동'전화번호 등록 완료!!
	  
	  --------------------------
	  	다음 메뉴를 선택하세요.
		
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
	  --------------------------
		번호입력 >> 1
		
	  새롭게 등록할 전화번호 정보를 입력하세요.
	  이 름 >> 홍길동
	  
	  '홍길동'은 이미 등록된 사람입니다.

	  --------------------------
	  	다음 메뉴를 선택하세요.
		
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
	  --------------------------
		번호입력 >> 5
		
	  -----------------------------------------------
	   번호      이  름     전화번호        주소
	  -----------------------------------------------
	  	 1       홍길동    010-1111-1111   대전시 중구 대흥동
	  	 ~~~
	  	 ~~~
	  -----------------------------------------------
	  출력 완료...
	  
	  --------------------------
	  	다음 메뉴를 선택하세요.
		
		1. 전화번호 등록
		2. 전화번호 수정
		3. 전화번호 삭제
		4. 전화번호 검색
		5. 전화번호 전체 출력
		0. 프로그램 종료
	  --------------------------
		번호입력 >> 0
		
	  프로그램을 종료합니다.

*/

public class PhoneBookTest {
	private HashMap<String, Phone> phoneBookMap;
	private Scanner scan;
	
	// 생성자
	public PhoneBookTest() {
		phoneBookMap = new HashMap<>();
		scan = new Scanner(System.in);
	}
	
	// 프로그램을 시작하는 메서드
	public void phoneStart(){
		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("      전화 번호 관리 프로그램");
		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println();
		
		while(true){
			int choice = displayMenu();
			
			switch(choice){
				case 1 : insert();	// 등록
					break;
				case 2 : update();	// 수정
					break;
				case 3 : delete();	// 삭제
					break;
				case 4 : search();	// 검색
					break;
				case 5 : displayAll();	// 전체 출력
					break;
				case 0 : 
					System.out.println("프로그램을 종료합니다.");
					return;
				default : 
					System.out.println("작업 번호를 잘못 입력하셨습니다.");
					System.out.println("다시 선택하세요.");
			}
		}
	}
	
	// 전화번호 정보를 검색하는 메서드
	public void search(){
		System.out.println();
		
		System.out.println("검색할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)){
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			return;
		}
		
		Phone p = phoneBookMap.get(name);
		System.out.println(name + "씨의 전화번호 정보");
		System.out.println("==========================");
		System.out.println("이    름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주    소 : " + p.getAddr());
		System.out.println("==========================");
		
		System.out.println();
	}
	
	
	// 전화번호 정보를 삭제하는 메서드
	public void delete(){
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 검사
		if(! phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 등록되지 않은 사람입니다.");
			return;
		}
		
		phoneBookMap.remove(name);
		
		System.out.println(name + "씨 정보를 삭제했습니다.");
		
	}
	
	
	
	// 전화번호 정보를 수정하는 메서드
	public void update(){
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요.");
		
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)){ // 해당 사람이 없으면 수정작업을 못한다.
			System.out.println(name + "씨의 전화번호 정보가 없습니다.");
			return;
		}
		
		System.out.print("새로운 전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine();
		System.out.print("새로운 주소 >> ");
		//String addr = scan.next();
		String addr = scan.nextLine();
		
		// 같은 키값에 새로운 전화번호 정보를 저장한다.==> 수정작업
		phoneBookMap.put(name, new Phone(name, tel, addr));
		
		System.out.println(name + "씨의 전화번호 정보를 변경하였습니다.");
		
		
	}
	
	
	// 전체 자료를 출력하는 메서드
	public void displayAll(){
		System.out.println();
		
		Set<String> phoneKeySet = phoneBookMap.keySet();
		
		System.out.println("-----------------------------------------------");
		System.out.println("   번호      이  름     전화번호        주소");
		System.out.println("-----------------------------------------------");
		
		if(phoneKeySet.size()==0){
			System.out.println("   등록된 전화번호 정보가 하나도 없습니다.");
		}else{
			int cnt = 0;
			Iterator<String> keyIt = phoneKeySet.iterator();
			while(keyIt.hasNext()){
				cnt++;
				String key = keyIt.next();    // 키값 구하기
				Phone p = phoneBookMap.get(key);   // value값 (Phone객체) 구하기
				System.out.println("   " + cnt + "\t" + p.getName() + "\t"
						+ p.getTel() + "\t" + p.getAddr());
			}
		}
		System.out.println("-----------------------------------------------");
		System.out.println(" 출력 끝... ");
		
	}
	
	/*
	 next(), nextInt(), nextDouble()....  
	 	==> 사이띄기, Tab키, Enter키를 구분 문자로 분리해서 분리된 자료만 읽어간다.
	 
		마마마
		가가
		
		나나나	다다다
		
		라라랄 잦자자자
		
		
	nextLine()  ==> 한 줄 단위로 입력한다.
					즉. 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어간다.
	
		-- 입력버퍼에 입력한 값이 저장된 후 차례로 꺼내와서 처리한다.
	
	*/
	
	
	// 새로운 전화번호 정보를 등록하는 메서드
	// 이미 등록된 사람은 등록되지 않는다.
	public void insert(){
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		System.out.print("이 름 >> ");
		String name = scan.next();
		
		// 이미 등록된 사람인지 검사
		if(phoneBookMap.containsKey(name)){
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine();   // 입력 버퍼를 비우는 역할을 수행한다.
		
		System.out.print("주 소 >> ");
		//String addr = scan.next();
		String addr = scan.nextLine();
		
//		Phone p = new Phone(name, tel, addr);
//		phoneBookMap.put(name, p);
		
		phoneBookMap.put(name, new Phone(name, tel, addr));
		
		System.out.println(name + " 전화번호 등록 완료!!");
		
	}
	
	
	// 메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	public int displayMenu(){
		System.out.println("--------------------------");
	  	System.out.println("다음 메뉴를 선택하세요.");
		System.out.println();
		System.out.println(" 1. 전화번호 등록");
		System.out.println(" 2. 전화번호 수정");
		System.out.println(" 3. 전화번호 삭제");
		System.out.println(" 4. 전화번호 검색");
		System.out.println(" 5. 전화번호 전체 출력");
		System.out.println(" 0. 프로그램 종료 ");
		System.out.println("--------------------------");
		System.out.print(" 번호입력 >> ");
		int num = scan.nextInt();
		
		return num;
		
	}
	
	
	
	
	

	public static void main(String[] args) {
//		PhoneBookTest phoneBook = new PhoneBookTest();
//		phoneBook.phoneStart();
		
		new PhoneBookTest().phoneStart();
	}

}



// 하나의 전화번호 정보를 갖는 Phone클래스 작성
class Phone{
	private String name;
	private String tel;
	private String addr;
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "Phone [name=" + name + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
	
}














