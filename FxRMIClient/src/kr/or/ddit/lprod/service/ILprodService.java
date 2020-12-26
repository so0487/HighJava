package kr.or.ddit.lprod.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.lprod.vo.LprodVO;

public interface ILprodService extends Remote {
	/**
	 * Lprod 테이블의 전체 데이터를 가져와 List로 반환하는 메서드
	 * @return LprodVO객체가 저장된 List객체
	 */
	public List<LprodVO> getAllLprod() throws RemoteException;
}
