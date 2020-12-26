package kr.or.ddit.rmichat.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;


//서버용 인터페이스
public interface ServerChat extends Remote{
	//접속한 클라이언트 정보를 List에 추가하는 메서드
	public void setClient(ClientChat client) throws RemoteException;
	
	//한 클라이언트가 보내온 메시지를 리스트에 등록된 모든 클라이언트에게 전달하는 메서드
	public void transMessage(String msg) throws RemoteException;
}
