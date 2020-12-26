package basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = 
					new FileOutputStream("c:/soo/d_other/test.dat");
			
			// 자료형 단위를 출력할 보조스트림 DataOutputStream객체 생성
			DataOutputStream dos = new DataOutputStream(fout);
			
			dos.writeInt(200);			// 정수형으로 데이터 출력
			dos.writeFloat(123.45f); 	// 실수형(float)으로 데이터 출력
			dos.writeBoolean(false); 	// 논리형으로 데이터 출력
			dos.writeUTF("ABCDabcd");	// 문자열형식으로 출력
			
			System.out.println("출력 완료...");
			
			dos.close();	// 스트림 닫기
			
			// ----------------------------------------------
			
			// 출력한 자료 읽어오기
			FileInputStream fin = 
					new FileInputStream("C:/soo/d_other/test.dat");
			DataInputStream dis = new DataInputStream(fin);
			
			// DataInputStream으로 자료를 읽어올 때는 
			// 출력할 때의 순서와 같은 순서로 읽어와야 된다.
			System.out.println("정수형 : " + dis.readInt() );
			System.out.println("실수형 : " + dis.readFloat());
			System.out.println("논리형 : " + dis.readBoolean());
			System.out.println("문자열 : " + dis.readUTF());
			
			System.out.println("읽기 작업 완료...");
			dis.close();
			
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
