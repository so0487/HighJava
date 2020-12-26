package basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;

/*
	LPROD테이블에 새로운 데이터를 추가하기
	
	lprod_gu와 lprond_nm은 직접 입력받아서 처리하고,
	lprod_id는 현재의 lprod_id중에서 제일 큰값보다 1 크게 한다.
	
	입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
*/
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", 
//					"JSY", "java");
			
			conn = DBUtil.getConnection();
			
			// lprod_id는 현재의 lprod_id중에서 제일 큰값보다 1 크게 한다.
			String sql = "select max(lprod_id) maxnum from lprod";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			int maxNum = 0;
			if(rs.next()){
				//maxNum = rs.getInt(1);	// 컬럼 번호 이용
				//maxNum = rs.getInt("max(lprod)");  // 식내용 이용
				maxNum = rs.getInt("maxnum");  // 컬럼의 alias 이용
			}
			maxNum++;
			
			// 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			
			String gu;  // 상품분류코드가 저장될 변수 선언
			int count = 0; // 입력한 상품 분류 코드의 개수가 저장될 변수
			
			do{
				System.out.print("상품 분류 코드 입력 : ");
				gu = scan.next();
				
				String sql2 = "select count(*) cnt from lprod "
						+ "where lprod_gu = ?";
				
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();  // select문일 경우
				
				
				if(rs.next()){
					count = rs.getInt("cnt");
				}
				
				if(count>0){
					System.out.println("입력한 상품 분류 코드 " + gu 
							+ "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
			}while(count>0);
			
			System.out.print("상품 분류명 입력 : ");
			String nm = scan.next();
			
			
			String sql3 = "insert into lprod(lprod_id, lprod_gu, lprod_nm) "
					+ " values(?,?,?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();  // select문이 아닐때
			
			if(cnt>0){
				System.out.println("등록 성공!!!");
			}else{
				System.out.println("등록 실패~~~");
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		} finally{
			try{ if(rs!=null) rs.close(); }catch(SQLException e){}
			try{ if(stmt!=null) stmt.close(); }catch(SQLException e){}
			try{ if(pstmt!=null) pstmt.close(); }catch(SQLException e){}
			try{ if(conn!=null) conn.close(); }catch(SQLException e){}
		}
		
		
	}

}
