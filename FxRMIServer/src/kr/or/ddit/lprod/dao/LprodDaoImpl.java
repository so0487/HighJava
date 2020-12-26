package kr.or.ddit.lprod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.lprod.vo.LprodVO;
import util.BuildedSqlMapClient;

public class LprodDaoImpl implements ILprodDao {
	private SqlMapClient smc;		// 사용할 ibatis 객체변수 선언
	
	private static LprodDaoImpl dao;
	
	private LprodDaoImpl() {
		// ibatis객체 생성
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static LprodDaoImpl getInstance() {
		if(dao==null) dao = new LprodDaoImpl();
		return dao;
	}
	

	@Override
	public List<LprodVO> getAllLprod() {
		List<LprodVO> lprodList = null;
		try {
			lprodList = smc.queryForList("lprod.getAllLprod");
		} catch (SQLException e) {
			lprodList = null;
			e.printStackTrace();
		}
		
		return lprodList;
	}

}
