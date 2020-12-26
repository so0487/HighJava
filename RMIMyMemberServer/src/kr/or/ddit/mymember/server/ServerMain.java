package kr.or.ddit.mymember.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.mymember.service.IMemberService;
import kr.or.ddit.mymember.service.MemberServiceImpl;

public class ServerMain {
	public static void main(String[] args) {
		IMemberService iMemberService;
		try {
			iMemberService = MemberServiceImpl.getInstance();
			
			Registry reg = LocateRegistry.createRegistry(9988);
			
			reg.rebind("imemberservice", iMemberService);
			
			
			System.out.println("서버 준비 완료...");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
