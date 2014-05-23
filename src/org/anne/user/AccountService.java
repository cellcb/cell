package org.anne.user;

import org.anne.Organization.OrganizationInfo;
import org.anne.Organization.OrganizationInfoDAO;
import org.anne.student.StudentInfo;
import org.anne.student.StudentInfoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
	
	private static final Logger log = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private BlogUserDAO userDAO;
	@Autowired
	private OrganizationInfoDAO orgDAO;
	@Autowired
	private StudentInfoDAO stuDAO;
	
	public void setUserDAO(BlogUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/**
	 * 验证用户名是否重名
	 * @param name
	 * @return
	 */
	public boolean validateUsername(String name) {
		//查找是否有同名账号
		BlogUser user = new BlogUser();
		user.setUsername(name);
		return this.userDAO.findBlogUserByUserName(user) == null ? true : false;
	}

	/**
	 * 学生注册
	 * @param user
	 * @throws Exception 
	 */
	@Transactional
	public void registerStudent(BlogUser user) throws UsernameConflictException, Exception {
		//验证用户名
		if(this.validateUsername(user.getUsername())) {
			//添加用户
			String id = this.userDAO.addBlogUser(user);
			StudentInfo stu = new StudentInfo();
			stu.setUserid(id);
			this.stuDAO.addStudent(stu);
		} else {
			throw new UsernameConflictException();
		}
	}
	
	/**
	 * 学校注册
	 * @param user
	 * @throws UsernameConflictException
	 * @throws Exception
	 */
	@Transactional
	public void registerSchool(BlogUser user) throws UsernameConflictException, Exception {
		//验证用户名
		if(this.validateUsername(user.getUsername())) {
			//添加用户
			String id = this.userDAO.addBlogUser(user);
			OrganizationInfo org = new OrganizationInfo();
			org.setUserid(id);
			orgDAO.addOrg(org);
		} else {
			throw new UsernameConflictException();
		}
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 */
	public BlogUser login(String username, String password) {
		//验证
		BlogUser user = new BlogUser();
		user.setUsername(username);
		user.setPasswd(password);
		BlogUser u = this.userDAO.findBlogUserByNamePass(user);
		return u;
		//其他
	}
	
	/**
	 * 注销
	 */
	public void logout() {
		
	}
	
	/**
	 * 修改密码
	 * @throws Exception 
	 */
	public void modifyPassword(String id, String oldPass, String password) throws Exception {
		//验证旧密码
		BlogUser user = this.userDAO.getById(id);
		if(user != null) {
			user.setUserid(id);
			user.setPasswd(password);
			this.userDAO.editPasswd(user);
		} else {
			throw new Exception();
		}
	}

}
