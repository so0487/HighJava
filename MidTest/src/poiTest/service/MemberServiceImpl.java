package poiTest.service;

import java.util.List;
import java.util.Map;

import poiTest.dao.IMemberDao;
import poiTest.dao.MemberDaoImpl;	
import poiTest.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private static MemberServiceImpl service;
	private IMemberDao dao;
	
	// �깮�꽦�옄
	private MemberServiceImpl() {
		//dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance(){
		if(service==null) service = new MemberServiceImpl();
		return service;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return dao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memId) {
		return dao.getMemberCount(memId);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		return dao.updateMember2(paramMap);
	}

}
