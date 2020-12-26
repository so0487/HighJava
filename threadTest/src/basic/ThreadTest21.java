package basic;

public class ThreadTest21 {

	public static void main(String[] args) {
		DataBox databox = new DataBox();
		
		ProducerThread th1 = new ProducerThread(databox);
		ConsumerThread th2 = new ConsumerThread(databox);
		
		th1.start();
		th2.start();

	}

}

// 데이터를 공통으로 사용하는 클래스
class DataBox{
	private String data;
	
	// data값이 null이면 data에 문자열이 채워질때까지 기다리고
	// data에 값이 있으면 해당 문자열을 반환한다.
	// 반환 후에는 data값을 null로 만든다.
	public synchronized String getData(){
		if(data==null){
			try{
				wait();
			}catch(InterruptedException e){}
		}
		
		String returnData = data;
		System.out.println("쓰레드가 읽은 데이터 : " + returnData);
		
		data = null;
		
		notify();
		return returnData;
	}
	
	// data변수에 값이 있으면 data가 null이 될때까지 기다린다.
	// data가 null이되면 새로운 data값을 저장한다.
	public synchronized void setData(String data){
		if(this.data!=null){
			try {
				wait();
			} catch (InterruptedException e) {	}
		}
		
		this.data = data;
		System.out.println("Thread에서 새로 저장한 데이터 : " + data);
		
		notify();
		
	}
		
}



// 데이터를 넣어주는 쓰레드
class ProducerThread extends Thread{
	private DataBox databox;

	public ProducerThread(DataBox databox) {
		super();
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++){
			databox.setData("공급데이터 " + i);
		}
	}
}

// 데이터를 꺼내서 사용하는 쓰레드
class ConsumerThread extends Thread{
	private DataBox databox;

	public ConsumerThread(DataBox databox) {
		super();
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=3; i++){
			String data = databox.getData();
			
		}
	}
}

















