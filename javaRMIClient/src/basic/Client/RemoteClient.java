package basic.Client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import basic.inf.RemoteInterface;
import basic.vo.FileInfoVO;
import basic.vo.TestVO;

public class RemoteClient {
	public static void main(String[] args) {
		try {
		//RMI용 객체를 서버에서 구해와서 사용하는 순서
		
		//1. 서버에서 등록한 RMI용 객체를 찾기 위해 Registry객체를 생성
		//		(서버의 IP주소와 제공하는 포트번호를 지정하여 생성한다.)
		
			Registry reg = LocateRegistry.getRegistry("localhost",8888);
			
			//2. 서버에서 등록한 '객체의 Alias'를 이용하여 객체를 불러온다.
			//		형식)Registry객체변수.lookup("객체의 Alias")
			RemoteInterface inf = (RemoteInterface) reg.lookup("server");
			
			//3. 이제부터 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			int a = inf.doRemotePrint("안녕하세요");
			System.out.println("서버의 반환값 : "+a);
			
			List<String> list = new ArrayList<String>();
			list.add("복숭아");
			list.add("사과");
			list.add("딸기");
			list.add("대추");
			list.add("배");
			inf.doPrintList(list);
			System.out.println("List 전송 끝...");
			System.out.println();
			
			TestVO vo = new TestVO();
			vo.setTestId("A001");
			vo.setTestNum(123);
			inf.doPrintVO(vo);
			System.out.println("VO객체 전송 끝");
			System.out.println();
			
			//파일 전송 부분 시작
			//File file = new File("d:/d_Other/상품관리(prodInfo).png");
			File file = new File("c:/soo/d_Other/상품관리(prodInfo).jpg");
			FileInfoVO fileVo = new FileInfoVO();
			
			//파일 용량을 구해서 FileInfoVO의 fileData배열의 크기를 결정한다.
			int len = (int) file.length();
			byte[]data = new byte[len];
			
			FileInputStream fin = new FileInputStream(file);
			fin.read(data);
			
			fileVo.setFileName(file.getName());	//파일명 저장
			fileVo.setFileData(data);			//파일 내용 저장
			
			//서버의 파일 전송용 메서드를 호출
			inf.setFile(fileVo);
			System.out.println("파일 전송 작업 끝....");
			
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
