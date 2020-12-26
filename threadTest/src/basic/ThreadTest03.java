package basic;

public class ThreadTest03 {

	public static void main(String[] args) {
		// 쓰레드가 수행되는 시간을 체크해 보기
		Thread th = new Thread( new MyRunner() );
		
		// 1970년1월1일 0시0분0초(표준시간)로부터 경과한 시간을
		// 밀리세컨드(1/1000) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join();  // 현재 실행중인 쓰레드에서 
						// 대상이 되는 쓰레드(변수 th )가 
						// 종료될 때까지 기다린다.
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("경과시간 : " + (endTime - startTime));
		

	}

}

class MyRunner implements Runnable{
	@Override
	public void run() {
		long sum = 0L;
		for(long i=1L; i<=1_000_000_000L; i++){
			sum += i;
		}
		System.out.println("합계 : " + sum);
		
	}
}
