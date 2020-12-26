package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		try {
			File file = new File("c:/soo/d_other/out.txt");
			FileOutputStream fos = new FileOutputStream(file);
			
			for(char ch='A'; ch<='Z'; ch++){
				fos.write(ch);  // ch변수의 데이터를 파일로 출력한다.
			}
			
			System.out.println("작업 완료!!");
			fos.close();  // 쓰기 작업 완료 후 스트림 닫기
			
			//=============================================
			
			// 저장된 파일 내용 읽어와 확인하기
			FileInputStream fin = new FileInputStream(file);
			int c;
			while((c=fin.read()) != -1){
				System.out.print((char)c );
			}
			fin.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
