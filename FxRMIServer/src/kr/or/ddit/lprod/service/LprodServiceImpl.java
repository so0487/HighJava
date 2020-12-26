package kr.or.ddit.lprod.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDaoImpl;
import kr.or.ddit.lprod.vo.LprodVO;

public class LprodServiceImpl extends UnicastRemoteObject implements ILprodService {
	private ILprodDao dao;		// 사용할 Dao객체 변수 선언
	
	private static LprodServiceImpl service;
	
	
	//생성자
	private LprodServiceImpl() throws RemoteException{
		dao = LprodDaoImpl.getInstance();
	}
	
	public static LprodServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new LprodServiceImpl();
		return service;
	}

	@Override
	public List<LprodVO> getAllLprod() throws RemoteException{
		return dao.getAllLprod();
	}

}
