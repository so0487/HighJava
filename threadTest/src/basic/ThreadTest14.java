package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
	10마리의 말들이 경주하는 경마 프로그램 작성하기
	
	말은 Horse라는 이름의 클래스로 구성하고,
	이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
	그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
	기능이 있다.( Comparable 인터페이스 구현)
	
	경기 구간은 1~50구간으로 되어 있다.
	
	경기 중 중간 중간에 각 말들의 위치를 나타내시오.
	예)
	01번말 --->------------------------------------
	02번말 ----->----------------------------------
	...
	10번말 -->-------------------------------------
	
	경기가 끝나면 등수 순으로 출력한다.


*/
public class ThreadTest14 {

	public static void main(String[] args) {
		Horse[] horses = new Horse[]{
			new Horse("01번말"),
			new Horse("02번말"),
			new Horse("03번말"),
			new Horse("04번말"),
			new Horse("05번말"),
			new Horse("06번말"),
			new Horse("07번말"),
			new Horse("08번말"),
			new Horse("09번말"),
			new Horse("10번말")
		};
		
		GameState gs = new GameState(horses);
		
		
		for(Horse h : horses){
			h.start();
		}
		
		gs.start();    // 경주 상태를 출력하는 쓰레드 시작
		
		
		for(Horse h : horses){
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}
		
		try {
			gs.join();
		} catch (InterruptedException e) {
		}
		
		
		System.out.println();
		System.out.println("경기 끝...");
		System.out.println();
		
		
		System.out.println("경기 결과");
		
		// 등수의 오름차순 정렬하기
		//Arrays.sort(horses);  // 배열 정렬하기
		/*
		for(Horse h : horses){
			System.out.println(h);
		}
		*/
		
		// 배열을 List에 담고, List 정렬하기
		ArrayList<Horse> horseList = new ArrayList<>();
		for(Horse h : horses){
			horseList.add(h);
		}
		Collections.sort(horseList);
		
		for(Horse h : horseList){
			System.out.println(h);
		}

	}

}

class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank = 0;
	
	private String horseName;	// 말이름
	private int rank;			// 등수
	private int position;		// 현재 위치
	
	// 생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등 입니다.";
	}

	// 등수의 오름차순 정렬 기준 설정하기
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(rank, horse.getRank());
	}
	
	@Override
	public void run() {
		for(int i=1; i<=50; i++){
			this.position = i; // 말의 현재 위치
			
			try {
				Thread.sleep((int)(Math.random() * 400));
			} catch (InterruptedException e) {
			}
		}
		// 한 마리의 말이 경주가 끝나면 등수를 구해서 설정한다.
		currentRank++;
		this.rank = currentRank;
		
	}
	
}

/*
	01번말 --->------------------------------------
	02번말 ----->----------------------------------
	...
	10번말 -->-------------------------------------

*/
// 경기 중에 말의 현재 위치를 나타내는 쓰레드
class GameState extends Thread{
	Horse[] horses;

	public GameState(Horse[] horses) {
		super();
		this.horses = horses;
	}
	
	@Override
	public void run() {
		while(true){
			// 모든 말들의 경기가 종료되었는지 여부 검사
			if(Horse.currentRank==horses.length){  
				break;
			}
			
			for(int i=1; i<=15; i++){
				System.out.println();
			}
			
			
			for(int i=0; i<horses.length; i++){
				System.out.print(horses[i].getHorseName() + " : ");
				for(int j=1; j<=50; j++){
					if(horses[i].getPosition() == j){
						System.out.print(">");
					}else{
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
	
	
}









