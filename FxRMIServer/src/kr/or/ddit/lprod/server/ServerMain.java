package kr.or.ddit.lprod.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;

public class ServerMain {

	public static void main(String[] args) {
		try {
			ILprodService lprodService = LprodServiceImpl.getInstance();
			
			Registry reg = LocateRegistry.createRegistry(9988);
			
			reg.rebind("lprodservice", lprodService);
			
			System.out.println("서버 준비 완료...");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
