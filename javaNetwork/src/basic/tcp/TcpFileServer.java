package basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

//클라이언트가 보낸 파일을 받아서 'c:/soo/d_other/연습용'폴더에 저장
public class TcpFileServer {
	
	private ServerSocket server;
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	private DataInputStream dis;
	
	
	public void serverStart() {
		File saveDir = new File("c:/soo/d_other/연습용");	//저장할 폴더
		
		if(!saveDir.exists()) {
			saveDir.mkdirs();
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			
			socket=server.accept();	//클라이언트의 요청을 기다린다.
			System.out.println("파일 다운로드 시작....");
			
			InputStream in = socket.getInputStream();
			dis = new DataInputStream(in);
			
			String fileName = dis.readUTF();	//첫번째로 수신된 파일이름을 저장한다
			
			
			//저장할 파일위치와 파일명을 지정하여 File객체 생성
			File saveFile = new File(saveDir,fileName);
			
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			byte[] temp = new byte[1024];
			int length = 0;
			
			//소켓으로 받은 데이터를 파일로 저장한다.
			while((length=bis.read(temp))>0) {
				bos.write(temp,0,length);
			}
			bos.flush();
			System.out.println("파일 다운로드 완료");
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패");
			e.printStackTrace();
		}finally {
			if(dis!=null) try {dis.close();}catch(IOException e) {}
			if(bis!=null) try {bis.close();}catch(IOException e) {}
			if(bos!=null) try {bos.close();}catch(IOException e) {}
			if(socket!=null) try {socket.close();}catch(IOException e) {}
			if(server!=null) try {server.close();}catch(IOException e) {}
		}
	}
	
	
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
}
