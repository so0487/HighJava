package basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
	문제1) 5명의 별명을 입력 받아 ArrayList에 저장하고 이들 중
	      별명의 길이가 제일 긴 별명을 출력하시오.
	      (단, 각 별명의 길이는 모두 다르게 입력한다.)
	      
	
	문제2) 문제1에서 별명의 길이가 같은 것이 있을 경우를 처리하시오.
	
*/
public class ArrayListTest3 {

	public static void main(String[] args) {
		ArrayList<String> aliasList = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		
		System.out.println("서로 다른 길이의 별명을 5번 입력하세요.");
		for(int i=1; i<=5; i++){
			System.out.print(i + "번째 별명 : ");
			String alias = scan.next();
			aliasList.add(alias);
		}
		
		// 제일 긴 별명이 저장될 변수를 선언하고 이 변수에는 
		// List의 첫번째 데이터로 초기화 한다.
		String maxAlias = aliasList.get(0);
		
		for(int i=1; i<aliasList.size(); i++){
			if(maxAlias.length() < aliasList.get(i).length()){
				maxAlias = aliasList.get(i);
			}
		}
		
		System.out.println("제일 긴 별명 : " + maxAlias);

	}

}










