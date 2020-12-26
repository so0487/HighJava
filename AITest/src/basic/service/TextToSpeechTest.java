package basic.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.GetPronunciationOptions.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voices;

/*
 	IBM Text to Speech 서비스는 IBM의 음성합성기능을 사용하여
 	다양한 언어, 방언을 음성으로 텍스트를 자연스러운 발음으로 합성하는 API를 제공한다.
 	이 서비스는 각 언어에 대해 남성 또는 여성, 때로는 둘 다를 지원한다.
 */
public class TextToSpeechTest {
	private static final String API_KEY = "hXH0Ol7Ub5HZ9CjQu5K20Ai9RA939A9Zz9yGNe4fz8fR";
	private static final String URL = "https://stream.watsonplatform.net/text-to-speech/api";

	private TextToSpeech service; //TextToSpeech 서비스 객체 변수 선언

	//서비스 설정
	private void setService() {
		IamOptions iamoptions = new IamOptions.Builder()
				.apiKey(API_KEY).build();
		
		service = new TextToSpeech(iamoptions);
		service.setEndPoint(URL);
		
		
	}

	//서비스 헤더를 설정하는 메서드
	//Watson은 기본적으로 서비스 사용에 대한 로그를 남겨 서비스를 개선하는데 사용하고 있다.
	//만약, Watson에서 서비스의 내용을 바꾸길 원하지 않는다면
	//헤더의 내용에 내용을 명시해주어야 한다.

	private void setHeader() {
		Map<String, String>headers = new HashMap<String, String>();

		//true는 허용, false는 불허
		headers.put("X-Watson-Learning-Opt-out", "false");
		service.setDefaultHeaders(headers);
	}
	
	
	
	//음성 타입을 검색하여 출력하는 메서드
	private void getVoice() {
		Voices voices = service.listVoices().execute();
		
		System.out.println(voices);
	}
	
	//서비스를 실행하는 메서드
	private void executeService() {
		//String text = "Welcome to Java World! My name is Simpson. Nice to meet you. Have a nice day.";
		String text = "안녕하세요. 반갑습니다.";
		
		SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
				.text(text).accept(SynthesizeOptions.Accept.AUDIO_WAV)
				//.voice("en-US_LisaV2Voice").build();
				.voice("ko-KR_YunaVoice").build();
		
		InputStream stream = service.synthesize(synthesizeOptions).execute();
		
		//결과의 스트림을 파일로 저장하기
		try {
			InputStream in = WaveUtils.reWriteWaveHeader(stream);
			
			//File file = new File("d:/d_other/welcome.wav");
			File file = new File("c:/soo/d_other/welcome_ko.wav");
			
			OutputStream os = new FileOutputStream(file);
			
			
			byte[] temp = new byte[1024];
			int length;
			while((length=in.read(temp))>0){
				os.write(temp, 0 ,length);
			}
			os.flush();
			os.close();
			in.close();
			stream.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		TextToSpeechTest textToSpeech = new TextToSpeechTest();
		textToSpeech.setService();
		textToSpeech.setHeader();
		textToSpeech.getVoice();
		textToSpeech.executeService();
		
		System.out.println("작업 끝...");
	}
}
