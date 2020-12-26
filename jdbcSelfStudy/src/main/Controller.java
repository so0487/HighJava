package main;

import java.util.List;
import java.util.Scanner;

import service.IService;
import service.ServiceImpl;
import vo.Vo;

public class Controller {

	private IService service;

	Scanner scan = new Scanner(System.in);




	public Controller() {
		service = ServiceImpl.getInstance();
	}


	public int displayMemu() {
		System.out.println();
		System.out.println("-------작업 선택-------");
		System.out.println("1.자료 추가");
		System.out.println("2.자료 삭제");
		System.out.println("3.자료 수정");
		System.out.println("4.자료 출력");
		System.out.println("0.종료");
		System.out.println("-------------------");
		System.out.print("원하는 작업 선택 >>");
		int num=scan.nextInt();
		return num;
	}
	
	public void startMemu() {
		while(true) {
			int choice = displayMemu();
			
			switch (choice) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				display();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;

			default: 
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
				
			}
		}
	}

	public void  display() {
		System.out.println();
		System.out.println("전체 항목을 출력합니다.");
		
		
		System.out.println("----------------------------------------------");
		System.out.println("ID		이름			전화번호");
		System.out.println("----------------------------------------------");
		
		
	
		
	}

	public void  update() {
		System.out.println();
		System.out.println("정보를 수정합니다.");
	}
	
	
	public void  delete() {
		System.out.println();
		System.out.println("정보를 삭제합니다.");
	}
	
	public void  insert() {
		System.out.println();
		System.out.println("정보를 추가합니다.");
		
		
	}


	public static void main(String[] args) {
		Controller cnt = new Controller();
		
		cnt.startMemu();
	}
}

