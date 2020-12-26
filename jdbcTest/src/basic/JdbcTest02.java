package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제1) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다
//		 lprod_id가 큰 자료들을 출력하시오.

public class JdbcTest02 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("lprod_id값 입력 : ");
		int num = scan.nextInt();
		
		// DB작업에 사용되는 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"JSY", "java");
			
			String sql = "select * from lprod where lprod_id > " + num;
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			System.out.println();
			System.out.println(" === 결과 출력 ===");
			while(rs.next()){
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("------------------------------------");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try{ if(rs!=null) rs.close(); }catch(SQLException e){}
			try{ if(stmt!=null) stmt.close(); }catch(SQLException e){}
			try{ if(conn!=null) conn.close(); }catch(SQLException e){}
		}
		
		

	}

}







