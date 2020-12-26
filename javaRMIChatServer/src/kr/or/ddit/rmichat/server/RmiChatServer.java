package kr.or.ddit.rmichat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.rmichat.inf.ClientChat;
import kr.or.ddit.rmichat.inf.ServerChat;

//서버용 인터페이스를 구현한 클래스
public class RmiChatServer extends UnicastRemoteObject implements ServerChat{

	//접속한 클라이언트 정보가 저장될 List
	List<ClientChat> clientList = new ArrayList<ClientChat>();


	//생성자
	public RmiChatServer() throws RemoteException{}	



	//접속한 클라이언트 정보를 List에 추가하는 메서드
	@Override
	public void setClient(ClientChat client) throws RemoteException {
		clientList.add(client);

	}

	//리스트에 등록된 모든 클라이언트에게 메시지 보내기
	@Override
	public void transMessage(String msg) throws RemoteException {
		for(ClientChat client : clientList) {
			client.setMessage(msg);
			System.out.println(msg);
		}

	}
	public static void main(String[] args) {
		try {
			ServerChat server = new RmiChatServer();

			Registry reg = LocateRegistry.createRegistry(1099);

			reg.rebind("rmiChat", server);

			System.out.println("서버 준비 완료!!");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
