package service;

import java.util.List;

import dao.DaoImpl;
import dao.IDao;
import vo.Vo;

public class ServiceImpl implements IService{
	private static ServiceImpl service;
	private IDao dao;
	
	private ServiceImpl() {
		dao = DaoImpl.getInstance();
	}
	
	public static ServiceImpl getInstance() {
		if(service==null)
			service = new ServiceImpl();
		
		return service;
	}
	

	@Override
	public int insert(Vo vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	@Override
	public int update(Vo vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return dao.delete(id);
	}

	@Override
	public List<Vo> allVo() {
		// TODO Auto-generated method stub
		return dao.allVo();
	}

	@Override
	public int getVoCount(String id) {
		// TODO Auto-generated method stub
		return dao.getVoCount(id);
	}
	
	
	
	
	
	
	

}
