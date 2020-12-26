package myMember.dao;

import java.util.List;
import java.util.Map;

import myMember.vo.MemberVO;

public interface IMemberDao {
	
	
	/**
	 * 자료 삽입 메서드
	 * @param memVo		DB에 insert할 자료가 저장된 memberVO객체
	 * @return	성공하면 1, 실패 0
	 */
	
	public int insertMember(MemberVO memVo);
	
	
	
	
	/**
	 * memId를 매개변수로 받아 해당 회원의 자료를 삭제하는 메서드
	 * @param memId		삭제할 memId
	 * @return	성공하면1, 실패하면 0
	 */
	public int deleteMember(String memId);
	
	
	
	
	/**
	 * Map을 활영하여 수정하고자 하는 회원 정수를 수정하는 메서드
	 * key값 정보 : Id, 수정할 컬럼, 수정할 회원정보
	 * @param paramMap		회원Id, 수정할 컬럼, 수정할 회원정보가 담긴 Map객체
	 * @return	성공하면 1, 실패하면 0
	 */
	public int updateMember(Map<String, String>paramMap);
	
	
	
	
	/**
	 * DB의 회원테이블의 전체 레코드를 가져와 list로 변환하는 메서드
	 * @return
	 */
	public List<MemberVO> getAllMemList();
	
	
	
	
	
	/**
	 * 회원ID를 매개변수로 받아 해당하는 회원정보의 갯수를 반한하는 메서드
	 * @param memId
	 * @return
	 */
	public int getMemCount(String memId);
}
