package basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

//서버에 접속되면 'c:/soo/d_other/도라이몽.jpg'파일을 서버로 전송
public class TcpFileClient {
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;


	public void clientStart() {
		//전송할 파일을 이용한 File객체 생성
		File file = new File("c:/soo/d_other/도라이몽.jpg");
		
		String fileName = file.getName();	//파일이름 구하기

		//전송할 파일이 있는지 검사
		if(!file.exists()) {
			System.out.println(fileName + " 파일이 없습니다.");
			return;
		}

		try {
			socket = new Socket("localhost",7777);
			
			System.out.println("파일 전송 시작..");
			
			OutputStream out = socket.getOutputStream();
			
			DataOutputStream dos = new DataOutputStream(out);
			dos.writeUTF(fileName);//서버에 접속하면 첫번째로 파일이름을 전송한다.
			
			
			//파일 읽기용 InputStream객체 생성
			bis = new BufferedInputStream(new FileInputStream(file));
			
			//서버로 전송할 OutputStream객체 생성
			bos = new BufferedOutputStream(out);
			
			byte[] temp = new byte[1024];
			int length=0;
			
			//파일의 내용을 읽어와 소캣을 통해서 전송한다.
			while((length = bis.read(temp))>0) {
				bos.write(temp, 0, length);
			}
			bos.flush();
			System.out.println("파일 전송 완료...");
					
		} catch (Exception e) {
			System.out.println("파일 전송 실패");
			e.printStackTrace();
		}finally {
			//if(dos!=null)try {dos.close();}catch(IOException e) {}
			if(bis!=null)try {bis.close();}catch(IOException e) {}
			if(bos!=null)try {bos.close();}catch(IOException e) {}
			if(socket!=null)try {socket.close();}catch(IOException e) {}
		}

	}


	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
}
