package kr.or.ddit.student.service;

import java.util.List;

import kr.or.ddit.student.vo.StudentVO;

public interface IStudentService {
	/**
	 * insert하는 메서드
	 * @param stdVo
	 * @return
	 */
	public int insertStudent(StudentVO stdVo);
	
	/**
	 * 전체 학생 성적 정보를 가져오는메서드
	 * @return
	 */
	public List<StudentVO> getAllStudent();
}
