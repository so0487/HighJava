package basic.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/*
 	Naver Clova의 얼굴 인식 서비스 
 */
public class FaceRecognition {

	private static final String CLIENT_ID = "HPhMS5z3Rlc1P1bvVauw";
	private static final String CLIENT_SECRET = "60fJyesyCs";


	//서비스 연결 Connection 객체 변수 선언
	private HttpsURLConnection con;

	//얼굴인식 URL 연결 설정 메서드
	private void setConnection() {
		//유명인 얼굴 인식
		//String apiURL = "https://openapi.naver.com/v1/vision/celebrity";

		//얼굴 감지
		String apiURL = "https://openapi.naver.com/v1/vision/face";


		try {
			URL url = new URL(apiURL);

			con = (HttpsURLConnection) url.openConnection();

			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//파일 전송 메서드
	//분석할 이미지 파일을 전송하는 메서드
	private void setFileTransfer() {
		String boundary = "---"+System.currentTimeMillis()+"---";
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);

		con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
		con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);


		try {
			OutputStream out = con.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,"UTF-8"),true);


			String LINE_FEED = "\r\n";

			//파일 추가
			//String imgFile = FaceRecognition.class.getResource("../data/Actor.jpg").getPath();
			//String imgFile = FaceRecognition.class.getResource("../data/actress.JPG").getPath();
			//String imgFile = FaceRecognition.class.getResource("../data/face.jpg").getPath();
			//String imgFile = FaceRecognition.class.getResource("../data/Koala.jpg").getPath();
			//String imgFile = FaceRecognition.class.getResource("../data/test.jpg").getPath();
			//String imgFile = FaceRecognition.class.getResource("../data/010-3389-3575.jpg").getPath();
			String imgFile = FaceRecognition.class.getResource("../data/family.jpg").getPath();

			File uploadFile = new File(imgFile);
			String paramName = "image";	//파라미터명을 image로 지정한다.
			String fileName = uploadFile.getName();

			writer.append("--"+boundary).append(LINE_FEED);
			writer.append("Content-Disposition: form-data; name=\""+
					paramName+"\"; filename=\""+fileName+"\"").append(LINE_FEED);
			writer.append("Content-Type: "+URLConnection.guessContentTypeFromName(fileName))
			.append(LINE_FEED);

			writer.append(LINE_FEED);
			writer.flush();

			FileInputStream fin = new FileInputStream(uploadFile);
			byte[]buffer = new byte[4096];
			int length;
			while((length=fin.read(buffer))>0) {
				out.write(buffer, 0, length);
			}

			out.flush();
			fin.close();

			writer.append(LINE_FEED).flush();
			writer.append("--"+boundary+"--").append(LINE_FEED);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	//응답을 수신하는 메서드
	private void receiveResponse() {
		BufferedReader br = null;
		int responseCode;
		try {
			responseCode = con.getResponseCode();

			if(responseCode==200) {//정상처리
				br = new BufferedReader(
						new InputStreamReader(con.getInputStream()));

			}else {//에러 발생
				System.out.println("Error!! Response Code = "+responseCode);


			}

			String inputLine;
			if(br!=null) {
				StringBuffer response = new StringBuffer();

				while((inputLine = br.readLine())!=null) {
					response.append(inputLine);
				}
				br.close();

				System.out.println(response.toString());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		FaceRecognition faceTest = new FaceRecognition();
		faceTest.setConnection();
		faceTest.setFileTransfer();
		faceTest.receiveResponse();
	}
}
