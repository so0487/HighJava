package basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer1 {
	public static void main(String[] args) throws IOException {
		//TCP소켓 통신을 하기 위해 ServerSocket객체를생성
		ServerSocket server = new ServerSocket(7777);
		
		System.out.println("접속을 기다립니다.");
		
		
		//accept메서드 : 
		//	-->클라이언트에서 연결 요청이 올 때 까지 계속 기다린다.
		//  -->연결 요청이 오면 Socket객체를 생성해서 클라이언트의 Socket과 연결한다.
		Socket socket = server.accept();
		
		
		//accept()메서드 이후의 소스는 연결이 완료되어야 실행된다.
		
		System.out.println("클라이언트와 연결되었습니다.");
		System.out.println();
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP주소 : "+socket.getInetAddress().getHostAddress());
		System.out.println("포트번호 : "+socket.getPort());
		System.out.println();
		
		System.out.println("연결된 서버 정보");
		System.out.println("IP주소 : "+socket.getLocalAddress());
		System.out.println("Port 번호 : "+socket.getLocalPort());
		System.out.println();
		
		//클라이언트에게 메시지 보내기
		//OutputStream을 구성해서 전송한다.
		//(Socket의 getOutputStream()메서드를 이용한다.)
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		
		//클라이언트에게 메시지 보내기
		dos.writeUTF("환영합니다. 어서오세요...");
		
		System.out.println("메시지를 보냈습니다....");
		
		//소켓과 연결 스트림 닫기
		dos.close();
		socket.close();
		server.close();
	}
}
