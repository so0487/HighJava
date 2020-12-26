package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


//문제2) lprod_id값을 2개 입력 받아서 두 값중 작은값부터
//		  큰값사이의 자료들을 출력하시오.

public class JdbcTest03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.print("첫번째 lprod_id값 입력 : ");
		int num1 = scan.nextInt();
		
		System.out.print("두번째 lprod_id값 입력 : ");
		int num2 = scan.nextInt();
		
		int max, min;
		if(num1 > num2){
			max = num1;
			min = num2;
		}else{
			max = num2; 
			min = num1;
		}
		
		
		// DB작업에 사용되는 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"JSY", "java");
			
//			String sql = "select * from lprod "
//					+ " where lprod_id >= " + min 
//					+ " and lprod_id <=" + max;
			
			String sql = "select * from lprod "
					+ " where lprod_id  between " + min 
					+ " and " + max;
			
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
