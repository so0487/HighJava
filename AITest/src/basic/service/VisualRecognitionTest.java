package basic.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

//VisualRecognition서비스는 제공되는 이미지를 분석하는 서비스이다.



public class VisualRecognitionTest {
	private static final String API_KEY = "2GmyYnQeOcs72lRsXK8Q7sWeTXcwjMwuqkQ7CRhlXnMs";
	private void excuteService() {
		IamOptions options = new IamOptions.Builder().apiKey(API_KEY).build();

		VisualRecognition service = new VisualRecognition("2018-03-19",options);


		try {
			//File file = new File(VisualRecognitionTest.class.getResource("../data/test.jpg").getPath());
			//File file = new File(VisualRecognitionTest.class.getResource("../data/Koala.jpg").getPath());
			File file = new File(VisualRecognitionTest.class.getResource("../data/Actor.jpg").getPath());
			InputStream in = new FileInputStream(file);


			ClassifyOptions classifyOptions = new ClassifyOptions.Builder().imagesFile(in)
					.imagesFilename("text.jpg").build();


			ClassifiedImages result = service.classify(classifyOptions).execute();

			System.out.println(result);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		VisualRecognitionTest visual = new VisualRecognitionTest();
		visual.excuteService();
	}
}
