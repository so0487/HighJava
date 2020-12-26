package kr.or.ddit.zip.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.zip.dao.IZipTbDao;
import kr.or.ddit.zip.dao.ZipTbDaoImpl;
import kr.or.ddit.zip.vo.ZipTbVO;

public class ZipTbServiceImpl extends UnicastRemoteObject implements IZipTbService{
	private IZipTbDao dao;
	
	private static ZipTbServiceImpl service;
	
	private ZipTbServiceImpl()throws RemoteException{
		dao=ZipTbDaoImpl.getInstance();
	}
	
	public static ZipTbServiceImpl getInstance() throws RemoteException{
		if(service==null)service = new ZipTbServiceImpl();
		return service;
	}

	@Override
	public List<ZipTbVO> zipSearchDong(String dong) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.zipSearchDong(dong);
	}

	@Override
	public List<ZipTbVO> zipSearchCode(String code) throws RemoteException {
		// TODO Auto-generated method stub
		return dao.zipSearchCode(code);
	}
}
