package kr.or.ddit.student.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.student.vo.StudentVO;
import util.BuildedSqlMapClient;

public class StudentDaoImpl implements IStudentDao {
	private static StudentDaoImpl dao;
	private SqlMapClient smc;
	
	private StudentDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static StudentDaoImpl getInstance() {
		if(dao==null) dao = new StudentDaoImpl();
		return dao;
	}

	@Override
	public int insertStudent(StudentVO stdVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("student.stdInsert", stdVo);
			
			if(obj==null) cnt = 1;
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<StudentVO> getAllStudent() {
		List<StudentVO> stdList = null;
		try {
			stdList = smc.queryForList("student.getAllStudentList");
		} catch (SQLException e) {
			stdList = null;
			e.printStackTrace();
		}
		return stdList;
	}

}
