package basic;

import java.io.IOException;

public class BrowserControl {
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("C:/Program Files/Internet Explorer/iexplore.exe https://www.playchat.ai/mobile/chatbot/sample_so04876725_1594445759253/");
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
