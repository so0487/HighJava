package kr.or.ddit.prod.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.prod.vo.LProdVo;
import kr.or.ddit.prod.vo.ProdVo;
import util.BuildedSqlMapClient;

public class ProdDaoImpl implements IprodDao{
	private static ProdDaoImpl dao;
	private SqlMapClient smc;

	private ProdDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}

	public static ProdDaoImpl getInstance() {
		if(dao==null) dao = new ProdDaoImpl();
		return dao;
	}



	@Override
	public List<LProdVo> getLprod() {
		List<LProdVo> lprodList = null;

		try {
			lprodList = smc.queryForList("prod.getLProd");
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return lprodList;
	}

	@Override
	public List<ProdVo> getProdId(String prodId) {
		List<ProdVo> prodList = null;

		try {
			prodList = smc.queryForList("prod.getProdName",prodId);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return prodList;
	}

	@Override
	public List<ProdVo> getProdLgu(String prodLgu) {
		List<ProdVo> prodList = null;
		
		try {
			prodList = smc.queryForList("prod.getProdLgu",prodLgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prodList;
	}

}
