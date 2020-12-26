package basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 은행의 입출금을 쓰레드로 처리하는 예제
// (Lock객체를 이용한 동기화 처리 예제)

public class ThreadTest18 {
	private int balance;	// 잔액
	
	// Lock객체 생성 ==> 되도록이면 private final로 만든다.
	private final Lock lock = new ReentrantLock();  
	
	
	public int getBalance(){
		return balance;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	// 입금하는 메서드
	public void deposit(int money){
	/*	
		Lock객체는 lock()메서드로 락을 설정하고
		반드시 unlock()메서드로 락을 해제해 주어야 한다.
		
	*/
		lock.lock();  // 락 설정 시작...
		balance += money;
		lock.unlock();  // 락을 해제해 준다.
	}
	
	// 출금하는 메서드
	public boolean withdraw(int money){
		
		// try ~ catch블럭이 사용되는 부분에서 
		// unlock()메서드를 호출할 때는 finally영역에서 호출하도록 한다.
		
		
		lock.lock();
		boolean chk = false;
		
		try{
			if(balance>=money){
				for(int i=1; i<=100000000; i++){}
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			}else{
				chk = false;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
		return chk;
	}
	
	public static void main(String[] args) {
		final ThreadTest18 acount = new ThreadTest18();
		acount.setBalance(10000);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);
				System.out.println("쓰레드에서 result = " + result 
						+ ", balance = " + acount.getBalance());
				
			}
		};
		
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);
		
		th1.start();
		th2.start();

	}

}
