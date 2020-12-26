package basic;


import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lotto {
	public static void main(String[] args) {
		Lotto lot= new Lotto();
		lot.lottoStart();
	
	
	}
	public void lottoStart(){
		while(true){
			System.out.println("======================");
			System.out.println("Lotto프로그램");
			System.out.println("--------------");
			System.out.println("1.Lotto 구입");
			System.out.println("2.프로그램종료");
			System.out.println("======================");
			Scanner sc = new Scanner(System.in);
			int input = 0;
			try {
				System.out.print("메뉴선택 : " );
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("잘못입력하셨습니다");
				continue;
			}
			int result = input;
			switch (result) {
				case 1:
					lottoBuy();
					break;
				case 2:
					System.out.println("감사합니다");
					System.exit(0);
					break;
				default:
					System.out.println("다시입력해주세요^^");
			}
		}
	}
	
	//로또 구입매서드
	public void lottoBuy(){
		//while(true){
			Scanner sc = new Scanner(System.in);
			int input = 0;
			System.out.println("로또한장의 금액은 1000원입니다.");
			System.out.println("금액을 입력해주세요");
			try {
				input = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해주세요^^");
			}
			if(input < 1000){
				System.out.println("입력금액이 너무 적습니다 로또구매실패!!!");
				//break;
			}else if(input > 100000){
				System.out.println("입력금액이 너무 많습니다 로또 구매실패!!!");
				//break;
			} else{
				lottoNum(input);
			}
		//}
	}
	
	//로또 번호 출력 메서드
	public void lottoNum(int input){
		System.out.println("행운의 로또 번호는 아래와 같습니다.");
		for (int i = 0; i < input/1000; i++) {
			HashSet<Integer> lotto = new HashSet<>();
			while(lotto.size()<6){
				lotto.add((int)(Math.random()*45+1));
			}
			System.out.println("로또번호" +(i+1)+"는"+lotto);
		}
		System.out.println();
		System.out.println("받은 금액은"+input + "거스름돈은"+input%1000+"입니다.");
		
	} 
		

	
}

