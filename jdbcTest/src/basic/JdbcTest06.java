package basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtil;
import util.DBUtil2;
import util.DBUtil3;

/*
	회원을 관리하는 프로그램을 작성하시오.
	(오라클 DB의 mymember테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오.(CRUD 구현하기)
	
	메뉴예시)
	    -- 작업 선택 --
	  1. 자료 추가
	  2. 자료 삭제
	  3. 자료 수정
	  4. 전체 자료 출력
	  0. 작업 끝.
   ---------------------
   원하는 작업 선택 >>   
	
*/
public class JdbcTest06 {
	private Scanner scan = new Scanner(System.in);
	
	// 메뉴를 출력하고 선택한 작업 번호를 반환하는 메서드
	public int displayMenu(){
		System.out.println();
		System.out.println("   -- 작업 선택 --");
		System.out.println(" 1. 자료 추가");
		System.out.println(" 2. 자료 삭제");
		System.out.println(" 3. 자료 수정");
		System.out.println(" 4. 전체 자료 출력");
		System.out.println(" 5. 자료 수정2");
		System.out.println(" 0. 작업 끝.");
		System.out.println(" ---------------------");
		System.out.print("원하는 작업 선택 >> ");
		int num = scan.nextInt();
		return num;
	}
	
	// 작업을 시작하는 메서드
	public void startMember(){
		while(true){
			int choice = displayMenu();
			switch(choice){
				case 1 : insertMember();	// 추가
					break;
				case 2 : deleteMember();	// 삭제
					break;
				case 3 : updateMember();	// 수정
					break;
				case 4 : displayMember();	// 전체 출력
					break;
				case 5 : updateMember2();	// 수정2
					break;
				case 0 :	// 작업 끝.
					System.out.println("작업을 마칩니다.");
					return;
				default : 
					System.out.println("번호를 잘못입력했습니다.");
					System.out.println("다시 입력하세요.");	
			}
			
		}
	}
	
	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 수정
	public void updateMember2(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "는(은) 없는 회원ID입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		int num;
		String updateFiled = null;
		String updateStr = null;
		do{
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println("1.회원이름  2.전화번호  3.회원주소");
			System.out.println("---------------------------------");
			System.out.print(" 수정항목 입력 >> ");
			num =scan.nextInt();
			
			switch(num){
				case 1 : 
					updateFiled = "mem_name"; 
					updateStr ="회원이름"; break;
				case 2 : 
					updateFiled = "mem_tel"; 
					updateStr ="전화번호"; break;
				case 3 : 
					updateFiled = "mem_addr"; 
					updateStr ="회원주소"; break;
				default : 
					System.out.println("수정 항목을 잘못 선택했습니다.");
					System.out.println("다시 선택하세요.");
			}
			
		}while(num<1 || num>3);
		
		System.out.println();
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("새로운 " + updateStr + " >> ");
		String updateData = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set " 
					+ updateFiled + " = ? where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateData);
			pstmt.setString(2, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println("수정 작업 성공!!!");
			}else{
				System.out.println("수정 작업 실패~~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{ if(pstmt!=null) pstmt.close();  }catch(SQLException e){}
			try{ if(conn!=null) conn.close();  }catch(SQLException e){}
		}
		
		
	}
	
	// 회원 정보를 수정하는 메서드 ==> 전체 항목 수정
	public void updateMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		int count = getMemberCount(memId);
		if(count==0){  // 없는 회원이면...
			System.out.println(memId + "는(은) 없는 회원ID입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		System.out.print("새로운 회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_name=? ,"
					+ "mem_tel=? , mem_addr=? "
					+ "where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원 정보 수정 완료!!");
			}else{
				System.out.println(memId + "회원 정보 수정 실패~~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{ if(pstmt!=null) pstmt.close();  }catch(SQLException e){}
			try{ if(conn!=null) conn.close();  }catch(SQLException e){}
		}
		
	}
	
	// 회원 정보를 삭제하는 메서드
	public void deleteMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");
		String memId = scan.next();
		
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0){
				System.out.println("회원ID가 " + memId + "인 회원 정보 삭제 성공!!");
			}else{
				System.out.println(memId + "회원은 없는 회원이거나 삭제에 실패했습니다.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{ if(pstmt!=null) pstmt.close();  }catch(SQLException e){}
			try{ if(conn!=null) conn.close();  }catch(SQLException e){}
		}
		
	}
	
	// 회원 정보를 추가하는 메서드
	public void insertMember(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요.");
		int count = 0;
		String memId = null;   // 회원ID가 저장될 변수
		do{
			System.out.print("회원ID >> ");
			memId = scan.next();
			
			count = getMemberCount(memId);
			if(count>0){
				System.out.println(memId + "은(는) 이미 등록된 회원ID입니다.");
				System.out.println("다른 회원ID를 입력하세요.");
				System.out.println();
			}
				
		}while(count>0);
		
		System.out.print("회원이름 >> ");
		String memName = scan.next();
		
		System.out.print("전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.print("회원주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "insert into mymember "
					+ "(mem_id, mem_name, mem_tel, mem_addr) "
					+ "values (?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt>0){
				System.out.println(memId + "회원 정보 추가 완료!!");
			}else{
				System.out.println(memId + "회원 정보 추가 실패~~");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{ if(pstmt!=null) pstmt.close();  }catch(SQLException e){}
			try{ if(conn!=null) conn.close();  }catch(SQLException e){}
		}
		
	}
	
	
	// 매개변수로 지정한 회원ID의 개수를 반환하는 메서드
	public int getMemberCount(String memId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;   // 회원ID의 개수가 저장될 변수
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from mymember"
					+ " where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{ if(rs!=null) rs.close();  }catch(SQLException e){}
			try{ if(pstmt!=null) pstmt.close();  }catch(SQLException e){}
			try{ if(conn!=null) conn.close();  }catch(SQLException e){}
		}
		
		return count;
		
	}
	
	// 전체 회원 정보를 출력하는 메서드
	public void displayMember(){
		System.out.println();
		
		System.out.println("------------------------------------");
		System.out.println(" ID   이름    전화번호     주소");
		System.out.println("------------------------------------");
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			//conn = DBUtil.getConnection();
			//conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");
				
				System.out.println(memId + "   " + memName + "   " 
							+ memTel + "   " + memAddr );
			}
			System.out.println("------------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{ if(rs!=null) rs.close();  }catch(SQLException e){}
			try{ if(stmt!=null) stmt.close();  }catch(SQLException e){}
			try{ if(conn!=null) conn.close();  }catch(SQLException e){}
		}
		
		
	}
	
	

	public static void main(String[] args) {
		new JdbcTest06().startMember();
	}

}
