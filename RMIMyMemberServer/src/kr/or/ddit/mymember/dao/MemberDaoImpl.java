package kr.or.ddit.mymember.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mymember.vo.MemberVO;
import util.BuildedSqlMapClient;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl dao;  // 1번
	private SqlMapClient smc;	// ibatis용 SqlMapClient객체변수 선언
	
	// 2번 생성자  -- ibatis 환경설정..
	private MemberDaoImpl(){
		/*
		Reader rd = null;
		try {
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rd!=null) try{ rd.close(); }catch(IOException e){}
		}
		*/
		smc = BuildedSqlMapClient.getSqlMapClient();
	}  	
	
	// 3번
	public static MemberDaoImpl getInstance(){
		if(dao == null) dao = new MemberDaoImpl();
		
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		
		int cnt = 0;
		
		try {
			Object obj = smc.insert("member.insertMember", memVo);
			if(obj==null) cnt = 1;
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		
		int cnt = 0;
		try {
			cnt = smc.delete("member.deleteMember", memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 

		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember", memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
				
		List<MemberVO> memList = null;  // MemberVO가 저장될 List변수 선언
		try {
			memList = smc.queryForList("member.getAllMember");
					
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		} 
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		
		
		int count = 0;
		try {
			count = 
				(int) smc.queryForObject("member.getMemberCount", memId);
					
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		} 
		
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		//  key값 정보 =>  회원ID(memid), 수정할 컬럼(field), 수정할데이터(data)
		
		int cnt = 0;
		
		try {
			cnt = smc.update("member.updateMember2", paramMap);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return cnt;
	}

}
