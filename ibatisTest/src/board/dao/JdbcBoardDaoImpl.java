package board.dao;

import java.sql.SQLException;
import java.util.List;

import util.BuildedSqlMapClient;
import board.vo.JdbcBoardVO;

import com.ibatis.sqlmap.client.SqlMapClient;

public class JdbcBoardDaoImpl implements IJdbcBoardDao {
	private static JdbcBoardDaoImpl dao;
	private SqlMapClient smc;
	
	private JdbcBoardDaoImpl() { 
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static JdbcBoardDaoImpl getInstance(){
		if(dao==null) dao = new JdbcBoardDaoImpl();
		
		return dao;
	}
	

	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("jdbcBoard.insertBoard", boardVo);
			if(obj==null) cnt = 1;
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("jdbcBoard.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("jdbcBoard.updateBoard", boardVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO boardVo = null;
		
		try {
			boardVo = 
				(JdbcBoardVO) smc.queryForObject("jdbcBoard.getBoard", boardNo);
			
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} 
		
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = null;
		
		try {
			boardList = smc.queryForList("jdbcBoard.getAllBoard");
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String boardTitle) {
		List<JdbcBoardVO> boardList = null;
		
		try {
			boardList = smc.queryForList("jdbcBoard.getSearchBoard", boardTitle);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("jdbcBoard.boardCountIncrement", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		
		return cnt;
	}

}
