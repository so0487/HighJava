package kr.or.ddit.zip.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.zip.vo.ZipTbVO;

public interface IZipTbService extends Remote{
	/**
	 * 동이름을 매개변수로 받아서 해당 동이름이 포함된 데이터를 검색하여 
	 * 리스트로 반환하는 메서드
	 * @param dong
	 * @return
	 */
	public List<ZipTbVO> zipSearchDong(String dong) throws RemoteException;
	
	
	/**
	 * 우편번호 앞부븐을 매개변수로 받아서 해당 우편번호를 포함하는
	 * 데이터를 검색하여 리스트로 반환하는 메서드
	 * @param code
	 * @return
	 */
	public List<ZipTbVO> zipSearchCode(String code) throws RemoteException;
}
