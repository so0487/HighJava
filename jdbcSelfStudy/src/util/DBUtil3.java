package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.ResourceBundle;

/*
 * JDBC드라이버를 로딩하고 Connection객체를 생성하는 메서드로 구성된 class 만들기
 * Properties객체를 이용하여 처리하기
 */

//ResourceBundle객체 이용하기
public class DBUtil3 {
	
	static ResourceBundle bundle;	//ResourceBundle객체 변수 선언
	
		
	
		static{
		
		bundle=ResourceBundle.getBundle("dbinfo");
		try {
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}

	}
	
		

	public static Connection getConnection(){
		try {
			//return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JSY","java");
			return DriverManager.getConnection(bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));

		} catch (Exception e) {
			System.out.println("오라클 연결 실패 :<");
			return null;
		}
	}
}