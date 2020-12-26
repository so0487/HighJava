package basic;

/**
 * 
 * @author 홍길동
 * @version 1.0
 * 
 * <p>
 * 파일명 : JavaDocTest.java<br>
 * 설 명 : JavaDoc문서 작성을 위한 연습용 파일<br><br>
 *  
 * 수정 이력<br>
 * ----------------------<br>
 * 수정일자 : 2020-06-18
 * 수 정 인 : 홍길동<br>
 * 수정내용 : 최초 생성<br>
 * ----------------------<br>
 *  
 * </p>
 *
 */
public interface JavaDocTest {
	/**
	 * methodTest - 반환값이 없는 메서드
	 * @param a 첫번째 매개변수 (정수형)
	 * @param b 두번째 매개변수 (정수형)
	 */
	public void methodTest(int a, int b);
	
	/**
	 * methodAdd - 반환값이 있는 메서드
	 * @param x 정수형 첫번째 변수
	 * @param y 정수형 두번째 변수
	 * @return 처리된 결과를 정수형으로 반환한다.
	 */
	public int methodAdd(int x, int y);

	/**
	 * methodEmpty - 매개변수가 없는 메서드
	 * @return 정수형을 반환한다.
	 */
	public int methodEmpty();
	
	
}


