package basic;

import java.io.FileReader;
import java.io.IOException;

public class FileIOTest03 {

	public static void main(String[] args) {
		try{
			// 문자 기반의 스트림을 이용한 파일내용 읽어와 출력하기
			FileReader fr = new FileReader("c:/soo/d_other/text.txt");
			
			int c;
			
			while((c=fr.read())!=-1){
				System.out.print((char)c);
			}
			
		}catch(IOException e){
			
		}

	}

}
