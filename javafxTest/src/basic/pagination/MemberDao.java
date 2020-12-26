package basic.pagination;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;

public class MemberDao {
	private static MemberDao dao;
	
	private SqlMapClient smc;
	
	private MemberDao() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static MemberDao getInstance() {
		if(dao==null)dao = new MemberDao();
		return dao;
	}
	
	public int getMemberCount(Map<String, String>searchMap) {
		int cnt = 0;
		
		try {
			cnt = (int)smc.queryForObject("mymember.getMemberCount",searchMap);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return cnt;
	}
	
	public List<MemberVO> getMemList(Map<String, String>searchMap){
		List<MemberVO>memList=null;
		
		try {
			memList = smc.queryForList("mymember.getAllMember",searchMap);
		} catch (SQLException e) {
			memList=null;
			e.printStackTrace();
		}
		return memList;
	}

}
