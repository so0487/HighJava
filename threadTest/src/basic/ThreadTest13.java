package basic;

// 3개의 쓰레드가 각각 알파벳을 A~Z까지 출력하는데
// 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기

public class ThreadTest13 {

	public static void main(String[] args) {
		DisplayCharacter[] disChars = new DisplayCharacter[]{
			new DisplayCharacter("홍길동"),
			new DisplayCharacter("이순신"),
			new DisplayCharacter("강감찬")
		};
		
		for(DisplayCharacter dc : disChars){
			dc.start();
		}
		
		for(int i=0; i<disChars.length; i++){
			try {
				disChars[i].join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : " + DisplayCharacter.setRank);

	}

}

// A ~ Z 까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	public static String setRank = ""; 
	
	private String name;
	
	// 생성자
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for(char c='A'; c<='Z'; c++){
			System.out.println(name + "의 출력 문자 " + c);
			try {
				// 200 ~ 500사이의 난수값으로 일시 정지 시킨다.
				Thread.sleep((int)(Math.random() * (500-200 + 1) + 200 ));
			} catch (InterruptedException e) {
				
			}
		}
		System.out.println(name + " 출력 끝......");
		
		// 출력을 끝낸 순서대로 이름을 배치한다.
		DisplayCharacter.setRank += name + "   ";  
		
	}
}







