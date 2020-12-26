package mvc.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mvc.service.IMemberService;
import mvc.service.MemberServiceImpl;
import mvc.vo.MemberVO;

public class MemberController {
	private Scanner scan = new Scanner(System.in);
	
	private IMemberService service;  // service객체가 저장될 변수 선언
	
	// 생성자
	public MemberController() {
		//service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}
	
	
	// 메뉴를 출력하고 선택한 작업 번호를 반환하는 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("   -- 작업 선택 --");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2");
		System.out.println(" 0. 작업 끝.");
		System.out.println(" ---------------------");
		System.out.print("원하는 작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	// 작업을 시작하는 메서드
	public void startMember(){
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 : insertMember();	// 추가
					break;
				case 2 : deleteMember();	// 삭제
					break;
				case 3 : updateMember();	// 수정
					break;
				case 4 : displayMember();	// 전체 출력
					break;
				case 5 : updateMember2();	// 수정2
					break;
				case 0 :	// 작업 끝.
					System.out.println("작업을 마칩니다.");
					return;
				default : 
					System.out.println("번호를 잘못입력했습니다.");
					System.out.println("다시 입력하세요.");	
			}
			
		}
	}
	
	// 회원 정보 수정2
	public void updateMember2(){
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "는(은) 없는 회원ID입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		int num;
		String updateFiled = null;
		String updateStr = null;
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println("1.회원이름  2.전화번호  3.회원주소");
			System.out.println("---------------------------------");
			System.out.print(" 수정항목 입력 >> ");
			num =scan.nextInt();
			
			switch(num){
				case 1 : 
					updateFiled = "mem_name"; 
					updateStr ="회원이름"; break;
				case 2 : 
					updateFiled = "mem_tel"; 
					updateStr ="전화번호"; break;
				case 3 : 
					updateFiled = "mem_addr"; 
					updateStr ="회원주소"; break;
				default : 
					System.out.println("수정 항목을 잘못 선택했습니다.");
					System.out.println("다시 선택하세요.");
			}
			
		}while(num<1 || num>3);
		
		System.out.println();
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("새로운 " + updateStr + " >> ");
		String updateData = scan.nextLine();
		
		// 수정할 정보를 Map에 추가한다.
		// key값 정보 =>  회원ID(memid), 수정할 컬럼(field), 수정할데이터(data)
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("memid", memId);
		paramMap.put("field", updateFiled);
		paramMap.put("data", updateData);
		
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0){
			System.out.println("수정 작업 성공!!!");
		}else{
			System.out.println("수정 작업 실패~~~");
		}
		
		
	}
	
	// 전체 회원 출력
	public void displayMember(){
		System.out.println();
		
		List<MemberVO> memList = service.getAllMemberList();
		
		System.out.println("------------------------------------");
		System.out.println(" ID   이름    전화번호     주소");
		System.out.println("------------------------------------");
		if(memList==null || memList.size()==0){
			System.out.println("등록된 회원정보가 하나도 없습니다.");
		}else{
			for(MemberVO memVo : memList){
				String memId = memVo.getMem_id();
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();
				
				System.out.println(memId + "   " + memName + "   " 
							+ memTel + "   " + memAddr );
			}
		}
		System.out.println("------------------------------------");
			
		
	}
	
	
	// 회원 수정
	public void updateMember(){
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "는(은) 없는 회원ID입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		System.out.print("새로운 회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String memAddr = scan.nextLine();
		
		// 입력받은 수정할 데이터를 MemberVO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		int cnt = service.updateMember(memVo);
		
		if(cnt>0){
			System.out.println(memId + "회원 정보 수정 완료!!");
		}else{
			System.out.println(memId + "회원 정보 수정 실패~~~");
		}
			
	}
	
	// 회원 추가
	public void insertMember(){
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		int count = 0;
		String memId = null;   // 회원ID가 저장될 변수
		do{
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			count = service.getMemberCount(memId);
			if(count>0){
				System.out.println(memId + "은(는) 이미 등록된 회원ID입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
				System.out.println();
			}
				
		}while(count>0);
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		// 입력받은 데이터를 MemberVO객체에 담는다.
		MemberVO memVo = new MemberVO();
		
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		// 서비스의 insert하는 메서드를 호출한다.
		// 이때 insert할 데이터가 저장된 MemberVO객체를 보내준다.
		int cnt = service.insertMember(memVo);
		
		if(cnt>0){
			System.out.println("회원 추가 성공!!");
		}else{
			System.out.println("회원 추가 실패!!");
		}
		
	}
	
	// 회원 삭제
	public void deleteMember(){
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int cnt = service.deleteMember(memId);
		
		if(cnt>0){
			System.out.println("회원ID가 " + memId + "인 회원 정보 삭제 성공!!");
		}else{
			System.out.println(memId + "회원은 없는 회원이거나 삭제에 실패했습니다.");
		}
	}
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}

}
