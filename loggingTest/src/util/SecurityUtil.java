package util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtil {
	// 양방향 암호화 알고리즘 = AES256 방식
	
	// 초기화 벡터(Initial Vector) 
	//			=> 암호문이 패턴화되지 않도록 사용하는 데이터
	private String iv;
	private Key keySpec;
	
	private String key = "a1b2c3d4e5f6g7h8"; // 공개키 (16글자 이상)
	
	// 생성자
	public SecurityUtil() throws UnsupportedEncodingException {
		this.iv = key.substring(0, 16);
		
		byte[] keyBytes = new byte[16];
		
		byte[] b = key.getBytes("utf-8");
		
		int len = b.length;
		if(len > keyBytes.length) {
			len = keyBytes.length;
		}
		
		System.arraycopy(b, 0, keyBytes, 0, len);
		
		// 비밀키 생성
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		
		this.keySpec = keySpec;
		
	}
	
	/**
	 * AES256방식으로 암호화하는 메서드
	 * @param str 암호화할 문자열
	 * @return 암호화된 문자열
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// Cipher 생성 및 초기화
		//   "알고리즘/모드/패딩"
		// CBC모드 ==> Cipher Block Chaining Mode
		// 		   ==> 동일한 평문 블록과 암호문 블록 쌍이 발생하지 않도록
		//			   이전단계의 암복호화 결과가 현 단계에 영향을 주는 
		//			   운영 모드를 말한다.
		
		// 패딩  ==> 마지막 블록이 블록의 길이와 항상 딱 맞아 떨어지지 않기 
		//			 때문에, 부족한 길이 만큼을 '0'으로 채우거나 임의의
		//			 비트값으로 채워 넣는 것을 말한다.
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);
		
		// 암호를 옵션 종류에 맞게 초기화 한다.
		// 옵션종류 : ENCRYPT_MODE(암호화 모드), DECRYPT_MODE(복호화모드)
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		// 암호문 생성하기
		byte[] encrypted = c.doFinal(str.getBytes("utf-8"));
		
		String enStr = Base64.getEncoder().encodeToString(encrypted);
		
		return enStr;
	}
	
	/**
	 * 암호화된 문자열을 AES256방식으로 복호화하는 메서드
	 * @param str 복호화할 암호화된 문자열
	 * @return 복호화한 문자열
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public String decrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		byte[] ivBytes = new byte[16];
		System.arraycopy(iv.getBytes(), 0, ivBytes, 0, ivBytes.length);
		
		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
		
		byte[] decrypted = Base64.getDecoder().decode(str);  
		
		return new String (c.doFinal(decrypted), "utf-8");
	}
	
	
	
	//--------------------------------------------------------------
	// 단방향 암호화 메서드
	public String hashing(String msg) throws NoSuchAlgorithmException {
		// 매개값에 단방향 암호화의 알고리즘 이름을 지정한다.
		// 알고리즘 종류 : MD5, SHA-256, SHA-512
		
		//MessageDigest md = MessageDigest.getInstance("MD5");  // 32byte
		//MessageDigest md = MessageDigest.getInstance("SHA-256"); //64byte
		MessageDigest md = MessageDigest.getInstance("SHA-512");	// 128byte
		
		// 암호화 작업 수행하기
		md.update(msg.getBytes());
		
		// md.digest() ==> 암호화된 결과를 구하는 메서드
		String str = Base64.getEncoder().encodeToString(md.digest());
		
		return str; 
		
	}
}
