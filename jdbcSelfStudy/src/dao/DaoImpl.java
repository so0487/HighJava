package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil3;
import vo.Vo;

public class DaoImpl implements IDao {
	
	
	private static DaoImpl dao;
	
	private DaoImpl() {
		
	}
	
	public static DaoImpl getInstance() {
		if(dao==null)
			dao = new DaoImpl();
		
		return dao;
	}
	
	

	@Override
	public int insert(Vo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Vo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Vo> allVo() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		List<Vo> memList = null;
		
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select * from jdbc_Test";
	
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			memList = new ArrayList<>();
			
			while(rs.next()) {
				Vo listVo = new Vo();
				
				listVo.setId(rs.getString("id"));
				listVo.setName(rs.getString("name"));
				listVo.setTel(rs.getString("tel"));
				
				
				
				memList.add(listVo);
				
			}
			
			
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}finally {
			try{if(rs!=null)rs.close();} catch(SQLException e) {}
			try{if(stmt!=null)stmt.close();} catch(SQLException e) {}
			try{if(conn!=null)conn.close();} catch(SQLException e) {}
		}
		
		
		return memList;
	}

	@Override
	public int getVoCount(String id) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		int count = 0;
		
		
		
		try {
			//DB연결
			conn = DBUtil3.getConnection();
			
			//Sql문 작성
			String sql = "select count(*) cnt from jdbc_Test"
					+ "where id= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}finally {
			try {if(rs!=null)rs.close();}catch(SQLException e){}
			try {if(pstmt!=null)pstmt.close();}catch(SQLException e){}
			try {if(conn!=null)conn.close();}catch(SQLException e){}
		}
		
		return count;

	}


}
