package org.anne.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentInfoService {
	
	private static final Logger log = LoggerFactory.getLogger(StudentInfoService.class);
	
	@Autowired
	StudentInfoDAO stuDAO;
	
	@Transactional
	public void updateStudent(StudentInfo stu) {
		this.stuDAO.updateStudent(stu);
	}
	
	public StudentInfo findByUserid(String userid) {
		return this.stuDAO.findByUserId(userid);
	}

}
