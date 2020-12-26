package basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		//InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		//www.naver.com의 ip정보 가져오기
		InetAddress naverIp = InetAddress.getByName("www.naver.com");
		
		System.out.println("HostName : "+naverIp.getHostName());
		System.out.println("HostAddr : "+naverIp.getHostAddress());
		System.out.println();
		
		
		
		//자신의 컴퓨터의 ip정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		
		System.out.println("내 컴퓨터의 HostName : "+localIp.getHostName());
		System.out.println("내 컴퓨터의 HostAddress : "+localIp.getHostAddress());
		System.out.println();
		
		
		//IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] navers = InetAddress.getAllByName("www.daum.net");
		for(InetAddress naIp : navers) {
			System.out.println(naIp.toString());
		}
	}
}
