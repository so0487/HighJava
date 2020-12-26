package board.main;

import java.util.List;
import java.util.Scanner;

import board.service.IJdbcBoardService;
import board.service.JdbcBoardServiceImpl;
import board.vo.JdbcBoardVO;

public class JdbcBoardController {
	private IJdbcBoardService service;
	private Scanner scan;
	
	public JdbcBoardController() {
		service = JdbcBoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}
	
	// 게시판을 시작하는 메서드
	public void boardStart(){
		String boardTitle = null;
		
		int choice = -1; 
				
		while(true){
			if(choice!=3){
				boardTitle = null;
			}
			choice = displayMenu(boardTitle);
			
			switch(choice){
				case 1 : insertBoard();		// 새글작성
					break;
				case 2 : viewBoard();		// 게시글 보기
					break;
				case 3 : boardTitle = searchBoard(); // 검색
					break;
				case 0 :	// 작업끝 
					System.out.println("게시판 프로그램 종료...");
					return;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
			
		}
		
	}

	// 게시글 List를 보여주고 작업 번호를 입력받아 반환하는 메서드
	public int displayMenu(String boardTitle){
		if(boardTitle==null){
			boardTitle = "";
		}
		List<JdbcBoardVO> boardList = service.getSearchBoardList(boardTitle);
		
		System.out.println();
		System.out.println("------------------------------------------------------");
		System.out.println(" NO        제 목            작성자     조회수");
		System.out.println("------------------------------------------------------");
		if(boardList==null || boardList.size()==0){
			System.out.println("     출력할 게시글이 하나도 없습니다.");
		}else{
			for(JdbcBoardVO boardVo : boardList){
				System.out.print(boardVo.getBoard_no() + "  ");
				System.out.print(boardVo.getBoard_title() + "  ");
				System.out.print(boardVo. getBoard_writer() + "  ");
				System.out.println(boardVo.getBoard_cnt());
			}
		}
		System.out.println("------------------------------------------------------");
		System.out.println("메뉴 : 1.새글작성    2.게시글보기    3.검색    0.작업끝");
		System.out.print("작업 선택 >> ");
		int num = scan.nextInt();
		
		return num;
	}
	
	// 게시글 내용을 보여주는 메서드
	public void viewBoard(){
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();
		
		JdbcBoardVO boardVo = service.getBoard(boardNo);
		
		if(boardVo == null){
			System.out.println(boardNo + "번의 게시글이 존재하지 않습니다.");
			return;
		}
		
		System.out.println();
		System.out.println(boardNo + "번글 내용");
		System.out.println("------------------------------------");
		System.out.println("- 제  목 : " + boardVo.getBoard_title());
		System.out.println("- 작성자 : " + boardVo.getBoard_writer());
		System.out.println("- 내  용 : " + boardVo.getBoard_content());
		System.out.println("- 작성일 : " + boardVo.getBoard_date());
		System.out.println("- 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("------------------------------------");
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.print(" 작업선택 >> ");
		int choice = scan.nextInt();
		
		switch(choice){
			case 1 : updateBoard(boardNo);		// 수정
				break;
			case 2 : deleteBoard(boardNo);		// 삭제
				break;
			case 3 :		// 리스트로 가기
				return;
		}
		
	}
	
	// 게시글을 삭제하는 메서드
	public void deleteBoard(int boardNo){
		int cnt = service.deleteBoard(boardNo);
		if(cnt>0){
			System.out.println(boardNo + "번 게시글이 삭제되었습니다.");
		}else{
			System.out.println(boardNo + "번 게시글 삭제 실패!!!");
		}
	}
	
	
	// 게시글을 수정하는 메서드
	public void updateBoard(int boardNo){
		System.out.println();
		scan.nextLine();  // 버퍼비우기
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		
		// VO객체에 입력한 정보를 넣는다.
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt>0){
			System.out.println(boardNo + "번 게시글이 수정되었습니다.");
		}else{
			System.out.println(boardNo + "번 게시글 수정 실패!!!");
		}
		
		
	}
	
	
	
	// 게시글 제목으로 검색할 단어를 입력받아 반환하는 메서드
	public String searchBoard(){
		System.out.println();
		scan.nextLine();  // 입력 버퍼 비우기
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		String searchTitle = scan.nextLine();
		
		return searchTitle;
	}
	
	
	
	// 새글을 작성하는 메서드
	public void insertBoard(){
		System.out.println();
		scan.nextLine();  // 버퍼비우기
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print("- 제  목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.print("- 내  용 : ");
		String content = scan.nextLine();
		
		// 입력한 정보를 VO객체에 넣는다.
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0){
			System.out.println("새글이 추가되었습니다.");
		}else{
			System.out.println("새글 추가 실패!!!");
		}
		
		
	}
	
	
}





