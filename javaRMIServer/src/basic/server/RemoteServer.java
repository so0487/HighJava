package basic.server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import basic.inf.RemoteInterface;
import basic.vo.FileInfoVO;
import basic.vo.TestVO;

/*
	RMI 기술을 제공하는 클래스 만들기
		- RMI용 인터페이스를 구현하고, UnicastRemoteObject를 상속해서 작성한다.
 */
public class RemoteServer extends UnicastRemoteObject implements RemoteInterface{

	//생성자도 반드시 RemoteException을 throws하도록 작성한다.
	public RemoteServer() throws RemoteException {

	}


	@Override
	public int doRemotePrint(String str) throws RemoteException {
		System.out.println("doRemotePrint()메서드 시작...");
		System.out.println("클라이언트에서 보내온 내용 : "+str);
		System.out.println("doRemotePrint()메서드 처리 끝...");
		System.out.println();

		return 200;	//이 반환값은 서버에서 클라이언트로 보내는 값이다.
	}

	@Override
	public void doPrintList(List<String> list) throws RemoteException {
		System.out.println("doPrintList()메서드 시작...");
		for(int i=0;i<list.size();i++) {
			System.out.println(i + "번째 자료 : " + list.get(i));
		}

		System.out.println("doPrintList()메서드 끝...");
		System.out.println();

	}

	@Override
	public void doPrintVO(TestVO vo) throws RemoteException {
		System.out.println("doPrintVO()메서드 시작...");
		System.out.println("ID : " + vo.getTestId());
		System.out.println("Num : " + vo.getTestNum());
		System.out.println("doPrintVO()메서드 끝...");
		System.out.println();

	}
	
	
	//클라이언트가 보내온 파일 정보를 이용하여 서버쪽에 해당 파일을 저장한다.
	@Override
	public void setFile(FileInfoVO filevo) throws RemoteException {
		String dir = "c:/soo/d_Other/연습용/";		//저장할 폴더명
		System.out.println("파일 저장 시작...");
		
		FileOutputStream fout = null;
		
		try {
			//클라이언트가 보내온 파일명을 이용하여 스트림객체 생성
			fout = new FileOutputStream(dir + filevo.getFileName());	
			
			//클라이언트에서 보내온 파일데이터(byte[])를 저장한다.
			fout.write(filevo.getFileData());
			
			fout.flush();
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("파일 저장 완료...");
		
	}


	public static void main(String[] args) {
		try {
		//RMI서비스를 제공하는 과정
		
		//1. RMI용 인터페이스를 구현한 객체의 인스턴스를 만든다.
		//		(변수는 RMI용 인터페이스 자료형으로 선언한다.)

			RemoteInterface inf = new RemoteServer();
			
		//2. 구현한 객체를 클라이언트에서 찾을 수 있도록 관리하는 Registry객체를 생성한다.
		//		(통신에서 사용할 포트번호를 지정하여 생성한다.
		//		(RMI의 기본포트는 1099)
			Registry reg = LocateRegistry.createRegistry(8888);
		
		//3. Registry에 서버에서 제공하는 객체들을 등록한다.
		//		(즉, RMI용 인터페이스를 구현한 객체의 인스턴스를 등록한다.)
		//		(형식 : Registry객체변수 rebind("객체의 Alias, RMI용 객체의 인스턴스)
		//		==> 이 곳에 지정한 '객체의 Alias'는 클라이언트에서 이 객체를 구할 때 사용하는 이름이다.
			reg.rebind("server", inf);
			
			System.out.println("서버가 준비되었습니다.");
			
		} catch (RemoteException e) {
			
			e.printStackTrace();
		}
	}


}
