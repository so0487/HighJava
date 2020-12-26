package basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		
		try {
			// System.in ==> 콘솔(표준입출력장치) 입력장치
			// 입력용 바이트 기반 스트림을 문자기반 스트림으로 변환해준다.
			InputStreamReader isr = new InputStreamReader(System.in);
			
			FileWriter fw = new FileWriter("c:/soo/d_other/testchar.txt");
			
			System.out.println("아무 내용이나 입력하세요.(입력의 끝은 Ctrl + Z 입니다.");
			
			int c;
			
			// 콘솔에서 입력할 때 입력의 끝은 'Ctrl' + 'z'키를 누르면 된다.
			while((c=isr.read()) != -1){
				fw.write(c);   //  콘솔로 입력받은 데이터를 파일에 출력한다.
			}
			
			isr.close();
			fw.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
