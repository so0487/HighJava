package dao;

import java.util.List;

import vo.Vo;

public interface IDao {
	
	
	
	/**
	 * insert기능 수행
	 * @param vo	insert할 자료가 저장된 Vo객체
	 * @return	성공하면 1 아니면 0
	 */
	public int insert(Vo vo);
	
	
	
	
	
	/**
	 * update기능 수행
	 * @param vo	update할 자료가 들어있는 Vo객체
	 * @return		성공하면 1 아니면 0
	 */
	public int update(Vo vo);
	
	
	
	/**
	 * 매개변수로 입력받은 id를 기준으로 해당자료의 delete기능 수행
	 * @param id
	 * @return	성공하면 1 아니면 0
	 */
	public int delete(String id);
	
	
	
	
	/**
	 * 모든 회원의 정보를 저장하는 list에 저장
	 * @return	성공하면 1 아니면 0
	 */
	public List<Vo> allVo();
	
	
	
	
	
	/**
	 * id를 기준으로 회원 정보 중 일치하는 회원의 갯수를 반환
	 * @param id
	 * @return	해당하는 id갯수
	 */
	public int getVoCount(String id);
}
