package kr.or.ddit.zip.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.zip.service.IZipTbService;
import kr.or.ddit.zip.service.ZipTbServiceImpl;

public class ZipTbServer {
	public static void main(String[] args) {
		try {
			IZipTbService zipService = ZipTbServiceImpl.getInstance();
			
			Registry reg = LocateRegistry.createRegistry(9988);
			
			reg.rebind("zipservice", zipService);
			
			System.out.println("서버 준비 완료...");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
}
