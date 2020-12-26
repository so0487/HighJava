package basic;
/*
	1 ~ 20억까지의 합계를 구하는 프로그램을 
	하나의 쓰레드가 단독으로 처리할 때와
	여러개의 쓰레드가 협력해서 처리할 때의 경과시간을 비교해 보자.
*/
public class ThreadTest04 {

	public static void main(String[] args) {
		// 단독으로 처리하는 쓰레드
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		// 여럿이 협력해서 처리하는 쓰레드
		SumThread[] sums = new SumThread[]{
			new SumThread(         1L,  500000000L),	
			new SumThread( 500000001L, 1000000000L),	
			new SumThread(1000000001L, 1500000000L),	
			new SumThread(1500000001L, 2000000000L)	
		};
		
		// 단독으로 처리하는 경우
		long startTime = System.currentTimeMillis();
		
		sm.start();
		
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 때 경과 시간 : " + 
							(endTime - startTime));
		System.out.println();
		
		// 여러 쓰레드가 협력해서 처리하는 경우
		startTime = System.currentTimeMillis();
		for(int i=0; i<sums.length; i++){
			sums[i].start();
		}
		
		for(SumThread th : sums){
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리한 경과 시간 : " + 
							(endTime - startTime));

	}

}

class SumThread extends Thread{
	// 합계를 구한 영역의 시작값과 종료값이 저장될 변수 선언
	private long min, max; 
	
	public SumThread(long min, long max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i=min; i<=max; i++){
			sum += i;
		}
		
		System.out.println("합계 : " + sum);
	}
	
	
}

