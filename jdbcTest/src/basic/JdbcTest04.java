package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"JSY", "java");
			
			System.out.println("계좌번호 정보 추가하기");
			System.out.print("계좌번호 : ");
			String bankNo = scan.next();
			
			System.out.print("은 행 명 : ");
			String bankName = scan.next();
			
			System.out.print("예금주명 : ");
			String bankUser = scan.next();
			
			/*
			// Statement객체를 이용하여 데이터 추가하기
			String sql = "insert into bankinfo "
					+ "(bank_no, bank_name, bank_user_name, bank_date) "
					+ " values('" + bankNo + "', '" + bankName + "', '" + bankUser + "', sysdate)";
			
			System.out.println(sql);
			
			stmt = conn.createStatement();
			
			// select문을 실행할 때는 executeQuery()메서드를 사용하고,
			
			// insert, update, delete문과 같이 select문이 아닌 쿼리문을
			// 실행할 때는 executeUpdate()메서드를 사용한다.
			// executeUpdate()메서드의 반환값 ==> 작업에 성공한 레코드 수
			int cnt = stmt.executeUpdate(sql);
			
			*/
			
			// PreparedStatement객체를 이용하여 처리하기
			// 쿼리문에서 데이터가 들어가 자리를 물음표(?)로 표시한다.
			String sql = "insert into bankinfo "
					+ "(bank_no, bank_name, bank_user_name, bank_date) "
					+ " values(?, ?, ?, sysdate)";
			
			// PreparedStatememt객체 생성
			// ==> 사용할 쿼리문을 매개변수에 넘겨준다.
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표번호, 데이터);
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUser);
			
			// 데이터 셋팅이 완료되면 쿼리문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			
			System.out.println("반환값 : " + cnt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try{ if(stmt!=null) stmt.close();   }catch(SQLException e){}
			try{ if(pstmt!=null) pstmt.close();   }catch(SQLException e){}
			try{ if(conn!=null) conn.close();   }catch(SQLException e){}
		}
		

	}

}
