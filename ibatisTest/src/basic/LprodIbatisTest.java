package basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import basic.vo.LprodVO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest {
	// iBatis를 이용하여 DB자료를 처리하는 순서 및 방법
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 1. iBatis의 환경설정 파일을 읽어와서 실행한다.
		//    (sqlMapConfig.xml)
		try {
			// 1-1. 문자 인코딩 케릭터셋 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경설정 파일을 읽어온다.
			Reader rd = 
				Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경설정을
			//		완성한 후 SQL문을 호출해서 실행할 객체를 생성한다.
			SqlMapClient smc =
				SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();	// Reader객체 닫기
			
			//-------------------------------------------------
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
/*			
			// 2-1. insert 연습
			System.out.println("insert 작업 시작...");
			System.out.print("Lprod_id 입력 : ");
			int lprodId = scan.nextInt();
			
			System.out.print("Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			System.out.print("Lprod_nm 입력 : ");
			String lprodNm = scan.next();
			
			// 1) 저장할 데이터를 VO에 담는다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(lprodId);
			lvo.setLprod_gu(lprodGu);
			lvo.setLprod_nm(lprodNm);
			
			// 2) SqlMapClient 객체변수를 이용하여 처리할 쿼리문을
			//	  호출하여 실행한다.
			//   형식) smc.insert("namespace값.id값", 파라미터클래스);
			//		  반환값 : insert성공 : null, 실패: 오류객체
			Object obj = smc.insert("lprod.insertLprod", lvo);
			if(obj==null){
				System.out.println("insert 작업 성공!!!");
			}else{
				System.out.println("insert 작업 실패~~~");
			}
			System.out.println("------------------------------");
*/			
/*			
			// 2-2. update연습
			System.out.println("update 시작...");
			
			System.out.print("수정할 Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			//lprodGu = (lprodGu + "   ").substring(0,4);
			
			System.out.print("새로운 Lprod_id 입력 : ");
			int lprodId = scan.nextInt();
			
			System.out.print("새로운 Lprod_nm 입력 : ");
			String lprodNm = scan.next();
			
			// 1) 저장할 데이터를 VO에 담는다.
			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_id(lprodId);
			lvo2.setLprod_gu(lprodGu);
			lvo2.setLprod_nm(lprodNm);
			
			// 2) smc.update("namespace값.id값", 파라미터클래스);
			// 		==> 반환값 : 성공한 레코드수
			int cnt = smc.update("lprod.updateLprod", lvo2);
			
			if(cnt>0){
				System.out.println("update 성공!!");
			}else{
				System.out.println("update 실패~~");
			}
			System.out.println("---------------------------------------");
*/
/*			
			// 3. delete 작업
			System.out.println("delete 작업 시작...");
			System.out.print("삭제할 Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			// 1) smc.delete("namespace값.id속성값", 파라미터클래스);
			// 	 반환값 : 작업에 성공한 레코드 수
			int cnt2 = smc.delete("lprod.deleteLprod", lprodGu);
			if(cnt2>0){
				System.out.println("delete 작업 성공~~");
			}else{
				System.out.println("delete 작업 실패!!");
			}
*/
			// 4. select 연습
			
/*			
			// 1) 응답결과가 여러개인 경우
			System.out.println("select 연습 시작(결과가 여러개일 경우)...");
			
			// 응답 결과가 여러개일 경우에는 queryForList()메서드를 사용하는데
			// 이 메서드는 여러개의 레코드 각각을 VO에 담은 후 이 VO데이터를
			// List에 추가해 주는 작업을 자동으로 수행한다.
			// 형식) smc.queryForList("namespace값.id속성값", 파라미터클래스)
			List<LprodVO> lprodList = smc.queryForList("lprod.getAllLprod");
			
			for(LprodVO lpvo : lprodList){
				System.out.println("ID : " + lpvo.getLprod_id());
				System.out.println("GU : " + lpvo.getLprod_gu());
				System.out.println("NM : " + lpvo.getLprod_nm());
				System.out.println("-----------------------------");
			}
			System.out.println("출력 끝...");
*/
			// 2) 응답 결과가 1개일 경우
			System.out.println("select 연습 시작(결과가 1개일 경우)...");
			System.out.print("검색할 Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			// 응답 결과가 1개가 확실할 경우에는 queryForObject()메서드를
			// 사용한다.  
			// 형식) smc.queryForObject("namespace값.id값",파라미터클래스);
			LprodVO lprodVo = 
				(LprodVO) smc.queryForObject("lprod.getLprod", lprodGu);
			if(lprodVo==null){
				System.out.println("검색한 데이터가 하나도 없습니다.");
				return;
			}
			System.out.println("ID : " + lprodVo.getLprod_id());
			System.out.println("GU : " + lprodVo.getLprod_gu());
			System.out.println("NM : " + lprodVo.getLprod_nm());
			System.out.println("-----------------------------");
			System.out.println("출력 끝");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
