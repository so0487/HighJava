package basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient1 {
	public static void main(String[] args) throws UnknownHostException, IOException {
		/*
			현재 자신 컴퓨터를 나타내는 방법
				- 원래의 IP주소 : (예시)192.168.207.33
				- 지정된 IP주소 : 127.0.0.1
				- 원래의 컴퓨터 이름 : 예) JSY
				- 지정된 컴퓨터 이름 : localhost일 때 가능
		*/
		String serverIp = "localhost";
		System.out.println(serverIp + "서버에 연결 중 입니다.");
		
		
		//서버의 IP주소와 PORT번호를 지정하여 Socket객체를 생성한다.
		//Socket객체는 생성이 완료되면 해당 서버로 요청신호를 보낸다.
		Socket socket = new Socket(serverIp,7777);
		
		//이 부분은 서버와 연결된 이후에 실행되는 코드 이다.
		System.out.println("서버에 연결되었습니다...");
		System.out.println();
		
		
		
		System.out.println("연결된 서버 정보");
		System.out.println("IP주소 : "+socket.getInetAddress().getHostAddress());
		System.out.println("포트번호 : "+socket.getPort());
		System.out.println();
		
		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP주소 : "+socket.getLocalAddress());
		System.out.println("Port 번호 : "+socket.getLocalPort());
		System.out.println();
		
		//서버에서 보내온 메시지를 받아서 출력하기
		
		//InputStream객체 생성
		InputStream is = socket.getInputStream();
		
		DataInputStream dis = new DataInputStream(is);
		
		//서버가 보낸 자료를 받아서 출력하기
		System.out.println("서버에서 보낸 메시지 : "+dis.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다");
		dis.close();
		socket.close();
	}
}
