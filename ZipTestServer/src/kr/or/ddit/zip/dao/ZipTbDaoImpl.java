package kr.or.ddit.zip.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.zip.vo.ZipTbVO;
import util.BuildedSqlMapClient;

public class ZipTbDaoImpl implements IZipTbDao {
	
	private static ZipTbDaoImpl dao;
	private SqlMapClient smc;
	
	private ZipTbDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static ZipTbDaoImpl getInstance() {
		if(dao==null) dao = new ZipTbDaoImpl();
		return dao;
	}
	

	@Override
	public List<ZipTbVO> zipSearchDong(String dong) {
		List<ZipTbVO> zipList = null;
		
		try {
			zipList = smc.queryForList("zip.getSearchDong",dong);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zipList;
	}

	@Override
	public List<ZipTbVO> zipSearchCode(String code) {
		List<ZipTbVO> zipList = null;
		try {
			zipList = smc.queryForList("zip.getSearchCode",code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return zipList;
	}

}
