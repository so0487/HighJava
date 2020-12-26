package basic;

import java.util.ArrayList;
import java.util.Scanner;


/*
문제2) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중
       별명의 길이가 제일 긴 별명을 출력하시오.
      (별명의 길이는 같은 것이 있을 수 있다.)
*/
public class ArrayListTest4 {

	public static void main(String[] args) {
		ArrayList<String> aliasList = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("별명을 5번 입력하세요.");
		for(int i=1; i<=5; i++){
			System.out.print(i + "번째 별명 : ");
			String alias = scan.next();
			aliasList.add(alias);
		}
		
		
		// 제일 긴 별명의 길이가 저장될 변수를 선언하고
		// 이 변수에 첫번째 데이터의 길이를 저장한다.
		int maxLength = aliasList.get(0).length();
		
		for(int i=1; i<aliasList.size(); i++){
			if(maxLength < aliasList.get(i).length()){
				maxLength = aliasList.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명들...");
		for(int i=0; i<aliasList.size(); i++){
			if(aliasList.get(i).length() == maxLength){
				System.out.println(aliasList.get(i));
			}
		}
		
		
		
		
		

	}

}







