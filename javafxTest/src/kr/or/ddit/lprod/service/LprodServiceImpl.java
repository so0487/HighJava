package kr.or.ddit.lprod.service;

import java.util.List;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDaoImpl;
import kr.or.ddit.lprod.vo.LprodVO;

public class LprodServiceImpl implements ILprodService {
	private ILprodDao dao;		// 사용할 Dao객체 변수 선언
	
	private static LprodServiceImpl service;
	
	private LprodServiceImpl() {
		dao = LprodDaoImpl.getInstance();
	}
	
	public static LprodServiceImpl getInstance() {
		if(service==null) service = new LprodServiceImpl();
		return service;
	}

	@Override
	public List<LprodVO> getAllLprod() {
		return dao.getAllLprod();
	}

}
