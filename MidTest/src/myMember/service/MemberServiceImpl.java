package myMember.service;

import java.util.List;
import java.util.Map;

import myMember.dao.IMemberDao;
import myMember.dao.MemberDaoImpl;
import myMember.vo.MemberVO;


public class MemberServiceImpl implements IMemberService{
	
	private static MemberServiceImpl service;
	private IMemberDao dao;
	
	
	private MemberServiceImpl() {
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service==null) {
			service = new MemberServiceImpl();
			
		}
		return service;
	}
	
	

	@Override
	public int insertMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return dao.updateMember(paramMap);
	}

	@Override
	public List<MemberVO> getAllMemList() {
		// TODO Auto-generated method stub
		return dao.getAllMemList();
	}

	@Override
	public int getMemCount(String memId) {
		// TODO Auto-generated method stub
		return dao.getMemCount(memId);
	}

}
