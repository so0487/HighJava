package poiTest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import poiTest.vo.MemberVO;
import util.DBUtil;
import util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;  // 1번
	
	private MemberDaoImpl(){}  	// 2번 생성자
	
	// 3번
	public static MemberDaoImpl getInstance(){
		if(dao == null) dao = new MemberDaoImpl();
		
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into mymember (mem_id, mem_name, mem_tel, mem_addr) "
					+ "values (?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			try{ if(pstmt!=null) pstmt.close(); }catch(SQLException e){}
			try{ if(conn!=null) conn.close(); }catch(SQLException e){}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "delete from mymember where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally{
			try{ if(pstmt!=null) pstmt.close(); }catch(SQLException e){}
			try{ if(conn!=null) conn.close(); }catch(SQLException e){}
		}

		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set mem_name=? ,"
					+ "mem_tel=? , mem_addr=? "
					+ "where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memVo.getMem_name());
			pstmt.setString(2, memVo.getMem_tel());
			pstmt.setString(3, memVo.getMem_addr());
			pstmt.setString(4, memVo.getMem_id());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			try{ if(pstmt!=null) pstmt.close();  }catch(SQLException e){}
			try{ if(conn!=null) conn.close();  }catch(SQLException e){}
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MemberVO> memList = null;  // MemberVO가 저장될 List변수 선언
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from mymember ";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			memList = new ArrayList<>();  // List객체 생성
			
			while(rs.next()){
				MemberVO memVo = new MemberVO(); // MemberVO객체 생성
				
				// ResultSet객체의 데이터를 가져와 MemberVO객체에 넣는다.
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				
				memList.add(memVo);  // List에 MemberVO객체 추가
			}
					
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} finally {
			try{ if(rs!=null) rs.close(); }catch(SQLException e){}
			try{ if(stmt!=null) stmt.close(); }catch(SQLException e){}
			try{ if(conn!=null) conn.close(); }catch(SQLException e){}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select count(*)cnt from mymember "
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("cnt");
			}
					
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} finally {
			try{ if(rs!=null) rs.close(); }catch(SQLException e){}
			try{ if(pstmt!=null) pstmt.close(); }catch(SQLException e){}
			try{ if(conn!=null) conn.close(); }catch(SQLException e){}
		}
		
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		//  key값 정보 =>  회원ID(memid), 수정할 컬럼(field), 수정할데이터(data)
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			String sql = "update mymember set " 
					+ paramMap.get("field") + " = ? where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memid"));
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try{ if(pstmt!=null) pstmt.close();  }catch(SQLException e){}
			try{ if(conn!=null) conn.close();  }catch(SQLException e){}
		}
		
		return cnt;
	}

}
