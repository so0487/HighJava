package kr.or.ddit.mymember.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.mymember.vo.MemberVO;



/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 Dao의 interface
 * 
 * @author SEM
 *
 */

public interface IMemberDao {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert작업이 성공하면 1이상의 정수가 반환되고 실패하면 0이 반환된다.
	 */
	public int insertMember(MemberVO memVo );
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO자료를 이용하여 DB에 update하는 메서드
	 * 
	 * @param memVo update할 회원정보가 들어있는 MemberVO객체
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB의 회원테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @return MemberVO객체를 담고 있는 List
	 */
	public List<MemberVO>  getAllMemberList();
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원의 개수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID개수
	 */
	public int getMemberCount(String memId);
	
	/**
	 * Map의 정보를 이용하여 회원정보 중 원하는 컬럼을 수정하는 메서드
	 *   key값 정보 =>  회원ID(memid), 수정할 컬럼(field), 수정할데이터(data)
	 * 
	 * @param paramMap 회원Id, 수정할 컬럼, 수정할 데이터가 저장된 Map객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember2(Map<String, String>paramMap);
	
}
