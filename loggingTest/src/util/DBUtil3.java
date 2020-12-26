package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

// JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드로
// 구성된 class 만들기

// ResourceBundle객체 이용하기
public class DBUtil3 {
	static Logger logger = Logger.getLogger(DBUtil3.class);
	
	static ResourceBundle bundle;  // ResourceBundle객체 변수 선언
	
	static{
		bundle = ResourceBundle.getBundle("dbinfo");  // 객체 생성
		logger.info("dbinfo.properties파일을 읽어와 bundle객체 생성");
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Class.forName(bundle.getString("driver"));
			logger.info("드라이버 로딩 성공...");
			
		} catch (ClassNotFoundException e) {
			//System.out.println("드라이버 로딩 실패~~~~");
			logger.error("드라이버 로딩 실패~~~");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
//			return DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", 
//					"JSY", "java");
			logger.info("주어진 url, user, password를 이용해서 Connection 객체 생성");
			return DriverManager.getConnection(
					bundle.getString("url"), 
					bundle.getString("user"), 
					bundle.getString("pass"));
			
		} catch (SQLException e) {
			//System.out.println("오라클 연결 실패!!");
			logger.error("오라클 연결 실패 - Connection객체 생성 실패", e);
			return null;
		}
		
	}
}
