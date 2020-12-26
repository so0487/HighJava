package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class DaedeokHotel {
	HashMap<Integer, Room> hotelMap;
	Scanner scan;
	
	// 생성자
	public DaedeokHotel() {
		hotelMap = new HashMap<>();
		scan = new Scanner(System.in);
		
		// 객실 초기화
		for(int i=2; i<=4; i++){
			String roomType = null;
			switch(i){
				case 2 : roomType = "싱글룸"; break;
				case 3 : roomType = "더블룸"; break;
				case 4 : roomType = "스위트룸"; break;
			}
			
			for(int j=1; j<=9; j++){
				int roomNum = i * 100 + j;
				Room room = new Room(roomNum, roomType);
				hotelMap.put(roomNum, room);
			}
			
		}
		
		
	}
	
	// 시작 메서드
	public void hotelStart(){
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("      호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*******************************************");
		System.out.println();
		
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 :	// 체크인
					checkIn();
					break;
				case 2 :	// 체크아웃
					checkOut();
					break;
				case 3 :	// 객실상태
					showRoom();
					break;
				case 4 :	// 업무종료
					System.out.println();
					System.out.println("*******************************************");
					System.out.println("           호텔문을 닫았습니다.");
					System.out.println("*******************************************");
					System.out.println();
					return;
				default :
					System.out.println("잘못 입럭했습니다.");
			}
		}
		
	}
	
	// 체크인 메서드
	public void checkIn(){
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸");
		System.out.println(" * 301~309 : 더블룸");
		System.out.println(" * 401~409 : 스위트룸");
		System.out.println("----------------------------------------------");
		System.out.print("방 번호 입력 >> ");
		
		int num = scan.nextInt();
		
		// 입력한 값이 Map의 키값에 없으면 잘못 입력한 방번호이다.
		if(!hotelMap.containsKey(num)){
			System.out.println(num + "호 객실은 존재하지 않습니다.");
		}else if(hotelMap.get(num).getGuestName() != null){  // 해당 객실에 다른 투숙객이 있는지 검사
			System.out.println(num + "호 객실에는 이미 손님이 있습니다.");
		}else{
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 >> ");
			String name = scan.next();
			
			// 입력받은 투숙객 이름을 해당 객실의 투숙객 명단에 넣는다.
			hotelMap.get(num).setGuestName(name);
			
			System.out.println(name + "씨가 " + num + "호 객실에 체크인 되었습니다.");
		}
		
	}
	
	// 체크아웃 메서드
	public void checkOut(){
		System.out.println();
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃할 방 번호를 입력하세요.");
		System.out.print("방 번호 입력 >> ");
		int num = scan.nextInt();
		
		if(!hotelMap.containsKey(num)){
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}
		
		if(hotelMap.get(num).getGuestName()==null){  // 해당 객실에 투숙객이 없으면
			System.out.println(num + "호 객실에는 체그인한 사람이 없습니다.");
			return;
		}
		
		// 체크아웃 작업은 해당 객실의 투숙객 이름을 null로 변경한다.
		String name = hotelMap.get(num).getGuestName();  // 현재 투숙객 이름 구하기
		hotelMap.get(num).setGuestName(null);  // 투숙객 이름을 null로 변경
		
		System.out.println(num + "호 객실의 " + name + "님 체크아웃을 완료했습니다.");
		
		
	}
	
	
	// 객실 상태를 출력하는 메서드
	public void showRoom(){
		System.out.println();
		
		// 방번호를 순서대로 나오게 하기 위해서 방번호(Map의 key값)만 
		// List에 넣은 후 정렬하여 사용한다.
		ArrayList<Integer> roomNumList = new ArrayList<>();
		
		for(int roomNum : hotelMap.keySet()){  // Map의 키값을 List에 추가
			roomNumList.add(roomNum);
		}
		
		Collections.sort(roomNumList);   //  방번호를 오름차순으로 정렬
		
		System.out.println();
		System.out.println("-----------------------------------------");
		System.out.println("             현재 객실 상태");
		System.out.println("-----------------------------------------");
		System.out.println("방번호\t 방종류\t 투숙객 이름");
		System.out.println("-----------------------------------------");
		// List에서 방번호를 하나씩 차례로 꺼내와 Map에서 해당 방번호에
		// 해당하는 Room객체를 구해서 출력한다.
		for(int i=0; i<roomNumList.size(); i++){
			Room r = hotelMap.get( roomNumList.get(i)  );
			
			System.out.print(r.getRoomNumber() + "\t");		// 방번호 출력
			System.out.print(r.getRoomType() + "\t");		// 방종류 출력
			String name = "   -";
			if(r.getGuestName()!=null){   //  투숙객이 있으면...
				name = r.getGuestName();
			}
			System.out.println(name);    // 투숙객 이름 출력
		}
		
		System.out.println("-----------------------------------------");
		System.out.println();
		
		
	}
	
	
	// 메뉴 출력 및 작업번호를 반환하는 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.print(" 선택>> ");
		int num = scan.nextInt();
		return num;
	}
	
	
	
	
	
	public static void main(String[] args) {
		new DaedeokHotel().hotelStart();
	}
	
	
	
	

}


class Room{
	private int roomNumber;
	private String roomType;
	private String guestName;
	
	public Room(int roomNumber, String roomType) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

}