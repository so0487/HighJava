package basic.log4jTest;

import org.apache.log4j.Logger;

public class Log4jTest {
	
	// Logger클래스의 인스턴스 생성
	static Logger logger = Logger.getLogger(Log4jTest.class);

	public static void main(String[] args) {
		logger.trace("프로그램 시작...");
		
		int x, y;
		logger.debug("Debug레벨의 메시지 - 변수 선언 완료... ");
		
		x = 100;
		logger.info("Info레벨의 메시지 - 변수 초기화 x = " + x);
		
		y = 300;
		logger.warn("Warn레벨의 메시지 - 변수 초기화 y = " + y);
		
		int z = x * y;
		logger.error("Error레벨의 메시지 - 계산 처리...");
		
		System.out.println(x + " * " + y + " = " + z);
		logger.fatal("Fatal레벨의 메시지 - 처리된 결과 출력...");
		
		logger.trace("프로그램 끝...");
		
		
		
		

	}

}
