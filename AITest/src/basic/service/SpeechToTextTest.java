package basic.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModels;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResult;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.RecognizeCallback;

/*
 	IBM Watson Speech to Text 서비스는 IBM 음성 인식기능을
 	응용프로그램에 추가할 수 있는 API를 제공

 	 이 서비스는 다양한 언어 및 오디오 형식의 음성을 빠르게 텍스트로 변환

 	 모든 응답 내용은 UTF-8 인코딩의 JSON 형식으로 반환한다.
 */
public class SpeechToTextTest {
	private static final String API_KEY = 
			"Lt2xV6RqBb-twvWs2FDR8UV4wXZ1IGR0P7uByAy5by3s";
		private static final String URL = 
			"https://api.us-south.speech-to-text.watson.cloud.ibm.com/instances/ea011053-82dc-4b40-80ab-f861ef88687f";

	private SpeechToText service;	//SpeechToText 서비스 객체 변수 선언
	private RecognizeOptions options;	// 서비스 옵션 객체 변수 선언
	private RecognizeCallback callback;	//서비스 콜백 객체 변수 선언

	//서비스를 설정하는 메서드
	//IBM Watson에 등록한 사용자 계정으로 서비스에 접속한다.

	private void setService() {
		IamOptions iamoptions = new IamOptions.Builder().apiKey(API_KEY).build();

		service = new SpeechToText(iamoptions);
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


	//서비스 모델을 검색하는 메서드
	//서비스에서 사용할 수 있는 언어 모델을 검색하여 출력한다.

	private void getModel() {
		ServiceCall<SpeechModels> serviceCall = service.listModels();

		//서비스 요청을 실행해서 모델 리스트를 구해온다.
		SpeechModels speechmodels = serviceCall.execute();


		//구해온 모든 언어 모델 출력하기
		System.out.println(speechmodels);
	}


	//서비스 옵션을 설정하는 메서드
	private void setOption() {
		try {
			FileInputStream fis
			= new FileInputStream(
					new File(SpeechToTextTest.class
							//.getResource("../data/obama_speech.flac").getPath()));
							.getResource("../data/no.mp3").getPath()));


			/*
			  	model 		: 언어 모델

			  	contentType : 컨텐츠 타입
			  					- audio/flac
			  					- audio/mp3
			  					- audio/mpeg
			  					- audio/wav
			  					- audio/ogg
			  					......

			  	interimResult : 중간 결과를 반환할지 여부 설정
			  					- true : 임시 결과는 JSON SpeechRecognitionResult객체의 스트림으로반환된다.
			  					- false : 최종 결과만 있는 단일 SpeechRecognitionResult객체의 스트림을 반환한다.

			  	maxAlternatives : 반환 될 대체 성적 증명서의 최대수(기본값은 1이 된다.)

			  	keywords 		: 오디오에서 발견할 수 있는 키워드 목록을 지정한다.
			  						- 키워드는 최종결과에서만 발견되며 최대 1000개의 키워드를 발견할 수 있다.
			  						- 키워드를 알아볼 필요가 없을 때에는 매개변수를 생략하거나 빈 배열을 지정한다.
			  	keywordsThreshold : 키워드 검색을 위한 신뢰값
			  							- 신뢰도가 임계값보다 크거나 같으면 단어가 키워드와 일치하는 것으로 판단
			  								(값은 0부터 1사이로 확률을 지정한다.)


			 */

			List<String> keywords = new ArrayList<String>();
			keywords.add("colorado");
			keywords.add("tornado");

			options = new RecognizeOptions.Builder()
					//.model("en-US_BroadbandModel").contentType("audio/flac")
					.model("ko-KR_NarrowbandModel").contentType("audio/mp3")
					.audio(fis).interimResults(true).maxAlternatives(3)
					.keywords(keywords).keywordsThreshold(0.5f)
					.build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//서비스를 실행한 후의 콜백을 지정하는 메서드
	//서비스를 실행한 후에 처리할 내용을 지정한다.
	private void setCallBack() {
		callback = new BaseRecognizeCallback() {
			//문자변환시 처리할 내용
			@Override
			public void onTranscription(SpeechRecognitionResults speechResults) {
				
				for(SpeechRecognitionResult srResult : speechResults.getResults()) {
					String text = srResult.getAlternatives().get(0).getTranscript();
					System.out.println(text);
				}
			}
			
			//연결 종료시 처리할 내용
			@Override
			public void onDisconnected() {
				// TODO Auto-generated method stub
				super.onDisconnected();
				System.exit(0);
			}
		};
	}
	
	
	//서비스를 실행하는 메서드
	private void executeService() {
		service.recognizeUsingWebSocket(options, callback);
	}
	
	

	public static void main(String[] args) {
		SpeechToTextTest speechToText = new SpeechToTextTest();
		speechToText.setService();
		speechToText.setHeader();
		//speechToText.getModel();
		speechToText.setOption();
		speechToText.setCallBack();
		speechToText.executeService();
		
	}
}
