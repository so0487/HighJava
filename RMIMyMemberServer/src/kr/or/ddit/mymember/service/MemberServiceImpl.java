package kr.or.ddit.mymember.service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mymember.dao.IMemberDao;
import kr.or.ddit.mymember.dao.MemberDaoImpl;
import kr.or.ddit.mymember.vo.MemberVO;



public class MemberServiceImpl extends UnicastRemoteObject implements IMemberService {
	private static MemberServiceImpl service;
	private IMemberDao dao;
	
	// 생성자
	private MemberServiceImpl() throws RemoteException{
		//dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() throws RemoteException{
		if(service==null) service = new MemberServiceImpl();
		return service;
	}

	@Override
	public int insertMember(MemberVO memVo)throws RemoteException {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId)throws RemoteException {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo)throws RemoteException {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMemberList()throws RemoteException {
		return dao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId)throws RemoteException {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap)throws RemoteException {
		return dao.updateMember2(paramMap);
	}

}
