package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest2 {
	public static void main(String[] args) throws IOException {
		//URLConnection	:	어플리케이션과 URL간의 통신 연결을 위한 클래스

		//특정 서버의 정보와 파일 내용을 가져와 출력하는 예제

		URL url = new URL("https://www.naver.com/index.html");



		//URLConnection객체 구하기
		URLConnection urlCon = url.openConnection();



		//Header정보 가져오기
		/*
		System.out.println("Content-Type : "+urlCon.getContentType());
		System.out.println("Encoding : "+urlCon.getContentEncoding());
		System.out.println("Content : "+urlCon.getContent());		
		System.out.println();

		 */


		Map<String, List<String>> headerMap = urlCon.getHeaderFields();


		//headerMap의 키 값과 value값 출력하기
		for(String headerKey : headerMap.keySet()) {
			System.out.println(headerKey+" : "+headerMap.get(headerKey));
		}


		System.out.println();
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println();


		//해당 문서의 내용을 가져오기(index.html문서 내용 가져오기)

		//방법1	:	URLConnection객체를 이용하는 방법
		/*

		//파일을 읽어오기 위한 스트림 객체 생성
		InputStream is = urlCon.getInputStream();


		InputStreamReader isr = new InputStreamReader(is,"UTF-8");
		BufferedReader br = new BufferedReader(isr);

		//자료를 읽어와 출력하기
		while(true) {
			String str = br.readLine();
			if(str==null)
				break;
			System.out.println(str);
		}
		br.close();
		 */



		//방법2 : URL의 openStream()이용하기
		InputStream is2 = url.openStream();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(is2,"UTF-8"));

		while(true) {
			String str = br2.readLine();
			if(str==null)
				break;
			System.out.println(str);
		}
		br2.close();



	}
}
