package org.anne.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.anne.base.BaseController;
import org.anne.base.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/org")
public class StudentInfoController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(StudentInfoController.class);
	
	@Autowired
	StudentInfoService stuService;
	@Autowired
	ResumeService resumeService;
	
	@RequestMapping("/add_resume.html")
	public @ResponseBody
	ResponseInfo addResume(HttpServletRequest request, Resume resume) {
		ResponseInfo ri = new ResponseInfo();
		String studentId = resume.getStudentId();
		if(studentId != null) {
			resumeService.addResume(resume);
			ri.setSuccess(true);
			ri.setResult(resume);
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		return ri;
	}
	
	@RequestMapping("/delete_resume.html")
	public @ResponseBody
	ResponseInfo deleteResume(HttpServletRequest request, Resume resume) {
		ResponseInfo ri = new ResponseInfo();
		String id = resume.getId();
		try {
			if(id != null) {
				resumeService.deleteResume(id);
				ri.setSuccess(true);
				ri.setResult(resume);
			} else {
				ri.setSuccess(false);
				ri.setMessage("Parameter Error!");
			}
		} catch (NoResumeException e) {
			ri.setSuccess(false);
			ri.setMessage("Resume is not exit");
		}
		return ri;
	}
	
	@RequestMapping("/update_resume.html")
	public @ResponseBody
	ResponseInfo updateResume(HttpServletRequest request, Resume resume) {
		ResponseInfo ri = new ResponseInfo();
		String id = resume.getId();
		try {
			if(id != null) {
				resumeService.editResume(resume);
				ri.setSuccess(true);
				ri.setResult(resume);
			} else {
				ri.setSuccess(false);
				ri.setMessage("Parameter Error!");
			}
		} catch (NoResumeException e) {
			ri.setSuccess(false);
			ri.setMessage("Resume is not exit");
		}
		return ri;
	}
	
	@RequestMapping("/list_resumes_by_student_id.html")
	public @ResponseBody
	ResponseInfo listResumesByStudentId(HttpServletRequest request, Resume resume) {
		ResponseInfo ri = new ResponseInfo();
		String studentId = resume.getStudentId();
		if(studentId != null) {
			List<Resume> list = resumeService.listResumesByStudentId(studentId);
			ri.setSuccess(true);
			ri.setResult(list);
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		return ri;
	}
	
	@RequestMapping("/update_student.html")
	public @ResponseBody
	ResponseInfo updateStudent(HttpServletRequest request, StudentInfo stu) {
		ResponseInfo ri = new ResponseInfo();
		
		String userid = stu.getUserid();
		
		if(userid != null) {
			this.stuService.updateStudent(stu);
			ri.setSuccess(true);
			ri.setResult(stu);
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		
		return ri;
	}
	
	@RequestMapping("/get_student.html")
	public @ResponseBody
	ResponseInfo getStudent(HttpServletRequest request, StudentInfo stu) {
		ResponseInfo ri = new ResponseInfo();
		
		String userid = stu.getUserid();
		if(userid != null) {
			StudentInfo s = this.stuService.findByUserid(userid);
			ri.setSuccess(true);
			ri.setResult(s);
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		
		return ri;
	}

}
