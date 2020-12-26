package basic.pagination;

import java.util.List;
import java.util.Map;

public class MemberService {
	private static MemberService service;
	private MemberDao dao;
	
	private MemberService() {
		dao = MemberDao.getInstance();
	}
	
	public static MemberService getInstance() {
		if(service==null) service = new MemberService();
		return service;
	}
	
	
	public int getMemberCount(Map<String, String>searchMap) {
		return dao.getMemberCount(searchMap);
		
		
		
		
	}
	
	
	
	public List<MemberVO> getMemList(Map<String, String>searchMap){
		return dao.getMemList(searchMap);
	}
}
