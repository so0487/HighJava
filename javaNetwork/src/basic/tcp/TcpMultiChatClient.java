package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {
	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();
	}
	private void clientStart() {
		Socket socket = null;
		try {
			String serverIp = "localhost";
			//String serverIp = "192.168.207.33";
			socket  = new Socket(serverIp,7777);
			System.out.println("서버에 연결되었습니다.....");
			System.out.println();
			
			//메시지 전송용 Thread 생성
			ClientSender sender = new ClientSender(socket);
			
			//메시지 수신용 Thread 생성
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	//clientStart() 끝
	
	
	
	
	//메시지 전송용 Thread
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		private String name;
		private Scanner scan;	
		
		//생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				if(dos!=null) {
					//처음 프로그램이 시작하면 자신의 대화명을 서버로 전송하고 
					//대화명의 중복여부를 feedback으로 받아서 확인
					
					System.out.println("대화명 : ");
					String name = scan.nextLine();
					
					while(true) {
						dos.writeUTF(name);		//대화명 전송
						
						
						//대화명 중복여부를 받는다.
						String feedBack = dis.readUTF();
						
						if("이름중복".equals(feedBack)) {//대화명이 중복될 때
							System.out.println(name+" 은 이름이 중복됩니다.");
							System.out.println("다른 대화명을 입력하세요.");
							System.out.println("대화명 : ");
							name = scan.nextLine();
						}else {//중복되지 않을 때
							this.name = name;
							System.out.println(name+" 이름으로 대화방에 입장했습니다.");
							break;//반복문 탈출...
						}
						
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//생성자 종료
		
		@Override
		public void run() {
			try {
				while(dos!=null) {
					//키보드로 입력한 메시지를 서버로 전송한다.
					dos.writeUTF("[" +name+ "]"+scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	//메시지 수신용 Thread
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//생성자 끝
		
		@Override
		public void run() {
			try {
				while(dis!=null) {
					//서버로 부터 받은 메시지를 출력한다.
					System.out.println(dis.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}
