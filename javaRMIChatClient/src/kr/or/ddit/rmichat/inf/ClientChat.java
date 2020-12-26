package kr.or.ddit.rmichat.inf;

import java.rmi.Remote;
import java.rmi.RemoteException;


//클라이언용 인터페이스
public interface ClientChat extends Remote {
	public void setMessage(String msg) throws RemoteException;
}
