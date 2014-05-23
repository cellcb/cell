package org.anne.student;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumeService {
	
	private static final Logger log = LoggerFactory.getLogger(ResumeService.class);
	
	@Autowired
	ResumeDAO resumeDAO;
	
	/**
	 * 添加履历
	 * @param resume
	 * @return
	 */
	@Transactional
	public String addResume(Resume resume) {
		String id = resumeDAO.addResume(resume);
		return id;
	}
	
	/**
	 * 删除履历
	 * @param id
	 * @throws NoResumeException
	 */
	@Transactional
	public void deleteResume(String id) throws NoResumeException {
		Resume resume = resumeDAO.findById(id);
		if(resume != null) {
			resumeDAO.deleteResume(id);
		} else {
			throw new NoResumeException();
		}
	}
	
	/**
	 * 修改履历
	 * @param resume
	 * @throws NoResumeException
	 */
	@Transactional
	public void editResume(Resume resume) throws NoResumeException {
		Resume r = resumeDAO.findById(resume.getId());
		if(r != null) {
			resumeDAO.editResume(resume);
		} else {
			throw new NoResumeException();
		}
	}
	
	/**
	 * 查找学生的履历
	 * @param studentId
	 * @return
	 */
	public List<Resume> listResumesByStudentId(String studentId) {
		List<Resume> resumes = resumeDAO.findByStudentId(studentId);
		return resumes;
	}

}
