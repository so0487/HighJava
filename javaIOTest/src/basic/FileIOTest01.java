package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// FileInputStream을 이용한 파일 내용 읽기
		try {
			
			// 읽어올 파일을 매개변수로 받는 FileInputStream객체 생성
			
			// 방법1
			//FileInputStream fin = new FileInputStream("C:/soo/d_other/text.txt");
			
			// 방법2
			File file = new File("c:/soo/d_other/text.txt");
			FileInputStream fin = new FileInputStream(file);
			
			int c;  // 읽어온 데이터를 저장할 변수
			while( (c=fin.read()) != -1 ){
				// 읽어온 문자 화면에 출력하기
				System.out.print((char) c);
			}
			
			fin.close();   // 작업 완료 후 스트림 닫기
			
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다...");
		}

	}

}
