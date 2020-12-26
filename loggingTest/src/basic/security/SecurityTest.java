package basic.security;

import util.SecurityUtil;

public class SecurityTest {

	public static void main(String[] args) throws Exception {
		SecurityUtil secUtil = new SecurityUtil();
		
		String msg = "Hello World!!";
		//String msg = "안녕하세요";
		
		System.out.println( secUtil.hashing(msg) );
		
		System.out.println("AES256방식의 암복호화 연습");
		
		System.out.println("원본 문자열 : " + msg);
		
		String enStr = secUtil.encrypt(msg);
		System.out.println("암호화된 문자열 : " + enStr);
		
		System.out.println("복호화된 문자열 : " + secUtil.decrypt(enStr));
		
		
		
		

	}

}
