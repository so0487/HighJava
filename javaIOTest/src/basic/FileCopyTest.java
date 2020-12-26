package basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 문제) 
	'c:/soo/d_other'폴더에 저장된 '도라이몽.jpg'파일을
	'c:/soo/d_other/연습용'폴더에 '복사본-도라이몽.jpg'파일로 복사하는
	프로그램을 작성하시오.
*/

public class FileCopyTest {

	public static void main(String[] args) {
		String fileName = "도라이몽.jpg";
		File file = new File("C:/soo/d_other/" + fileName);
		
		if(!file.exists()){
			System.out.println(file.getPath() + "파일이 없습니다.");
			System.out.println("복사작업을 중지합니다.");
			return;
		}
		
		try {
			// 복사할 파일 스트림 객체 생성
			FileInputStream fin = 
					new FileInputStream(file);
			
			BufferedInputStream bis = new BufferedInputStream(fin);
			
			
			// 복사될 파일 스트림 객체 생성
			FileOutputStream fout =
					new FileOutputStream("C:/soo/d_other/연습용/복사본-도라이몽.jpg");
			
			BufferedOutputStream bos = new BufferedOutputStream(fout);
			
			
			
			System.out.println("복사 시작...");
			
			int data; // 읽어온 데이터를 저장할 변수
			
//			while((data = fin.read()) != -1){
//				fout.write(data);
//			}
//			fout.flush();
			
			// 사용한 스트림 객체 닫기
//			fout.close();
//			fin.close();
			
			// 버퍼스트림 사용하기
			while((data = bis.read()) != -1){
				bos.write(data);
			}
			bos.flush();
			
			// 스트림 닫기
			bos.close();
			bis.close();
			
			System.out.println("복사 완료...");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
