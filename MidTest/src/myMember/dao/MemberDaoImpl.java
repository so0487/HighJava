package myMember.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import myMember.vo.MemberVO;
import util.BuildedSqlMapClient;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;
	private SqlMapClient smc;
	
	
	private MemberDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	
	public static MemberDaoImpl getInstance() {
		if(dao==null) {
			dao = new MemberDaoImpl();
			
		}
		return dao;
	}


	@Override
	public int insertMember(MemberVO memVo) {
		int cnt=0;
		 try {
			Object obj = smc.insert("member.insertMember");
			if(obj==null) {
				cnt=1;
			}
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt = smc.delete("member.deleteMember",memId);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public int updateMember(Map<String, String> paramMap) {
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMember",paramMap);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}


	@Override
	public List<MemberVO> getAllMemList() {
		List<MemberVO> memList = null;
		
		try {
			memList = smc.queryForList("member.getAllMember");
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}
		return memList;
	}


	@Override
	public int getMemCount(String memId) {
		int count = 0;
		
		try {
			count = (int) smc.queryForObject("member.getMemCount",memId);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}
		return count;
	}
}
