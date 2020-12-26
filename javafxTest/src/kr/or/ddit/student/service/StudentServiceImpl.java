package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.student.dao.IStudentDao;
import kr.or.ddit.student.dao.StudentDaoImpl;
import kr.or.ddit.student.vo.StudentVO;

public class StudentServiceImpl implements IStudentService {
	private static StudentServiceImpl service;
	private IStudentDao dao;
	
	private StudentServiceImpl() {
		dao = StudentDaoImpl.getInstance();
	}
	
	public static StudentServiceImpl getInstance() {
		if(service==null) service = new StudentServiceImpl();
		return service;
	}
	

	@Override
	public int insertStudent(StudentVO stdVo) {
		return dao.insertStudent(stdVo);
	}

	@Override
	public List<StudentVO> getAllStudent() {
		return dao.getAllStudent();
	}

}
