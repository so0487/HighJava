package kr.or.ddit.rmichat.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import kr.or.ddit.rmichat.inf.ClientChat;
import kr.or.ddit.rmichat.inf.ServerChat;

//클라이언트용 인터페이스를 구현한 클래스
public class RmiChatClient extends UnicastRemoteObject implements ClientChat{

	//생성자
	public RmiChatClient() throws RemoteException{}
	
	
	//서버가 보내온 메시지를 화면에 출력하는 메서드
	@Override
	public void setMessage(String msg) throws RemoteException {
		System.out.println(msg);

	}
	
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		try {
			//클라이언트 객체 생성
			ClientChat client = new RmiChatClient();
			
			Registry reg = LocateRegistry.getRegistry("localhost",1099);
			
			ServerChat server = (ServerChat) reg.lookup("rmiChat");
			
			
			//서버에 접속해서 현재 클라이언트를 List에 추가되도록 한다.
			server.setClient(client);
			while(true) {
				//메시지를 입력받아서 채팅작업을 수행한다.
				String msg = scan.nextLine();
				if("/end".equals(msg)) {
					break;
				}
				
				server.transMessage(msg);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
