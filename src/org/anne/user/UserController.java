package org.anne.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.anne.base.AnneUtil;
import org.anne.base.BaseController;
import org.anne.base.CommonConstant;
import org.anne.base.ResponseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/register.html")
	public @ResponseBody
	ResponseInfo register(HttpServletRequest request, BlogUser user) {
		ResponseInfo ri = new ResponseInfo();
		//获取页面信息
		String username = user.getUsername();
		String passwd = user.getPasswd();
		Integer type = user.getType();

		BlogUser u = new BlogUser();
		u.setUsername(username);
		u.setPasswd(AnneUtil.MD5Encode(passwd));
		u.setType(type);
		
		try {
			if(type.intValue() == 0) {
				//TODO 学校账号注册单独制作
				this.accountService.registerSchool(u);
				ri.setSuccess(true);
				ri.setMessage("YEEEEEE");
			} else if(type.intValue() == 1) {
				//TODO 会员等级1,2,3==铜,银,金
				this.accountService.registerStudent(u);
				ri.setSuccess(true);
				ri.setMessage("YEEEEEE");
			} else {
				ri.setSuccess(false);
				ri.setMessage("Parameter Error");
			}
		} catch (UsernameConflictException e) {
			ri.setSuccess(false);
			ri.setMessage("Username Conflict");
		} catch (Exception e) {
			ri.setSuccess(false);
			ri.setMessage("failure");
		}
		
		return ri;
	}

	/**
	 * 用户登陆
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/dologin.html")
	public @ResponseBody
	ResponseInfo login(HttpServletRequest request, BlogUser user) {
		ResponseInfo ri = new ResponseInfo();
		
		String username = user.getUsername();
		String passwd = user.getPasswd();
		if(username != null && passwd != null) {
			BlogUser blogUser = accountService.login(username, AnneUtil.MD5Encode(passwd));
			if (blogUser != null) {
				setSessionUser(request, blogUser);
				blogUser.setPasswd(null);
				ri.setResult(blogUser);
				ri.setSuccess(true);
			} else {
				ri.setSuccess(false);
				ri.setMessage("Account or password is wrong!");
			}
		} else {
			ri.setSuccess(false);
			ri.setMessage("Parameter Error!");
		}
		
		return ri;
	}

	/**
	 * 登录注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/dologout.html")
	public String logout(HttpSession session) {
		session.removeAttribute(CommonConstant.USER_CONTEXT);
		return "forward:/index.jsp";
	}

	@RequestMapping(value = "/login.html")
	public @ResponseBody
	BlogUser login(@ModelAttribute("blogUser") BlogUser blogUser) {
		return null;
	}

	@RequestMapping(value = "/logout.html")
	public String logout(@ModelAttribute("blogUser") BlogUser blogUser) {
		return null;
	}

	@RequestMapping(value = "/adduser.html")
	public @ResponseBody
	ResponseInfo addUser(@ModelAttribute("blogUser") BlogUser blogUser) {
		ResponseInfo ri = new ResponseInfo();
		blogUser.setPasswd(AnneUtil.MD5Encode(blogUser.getPasswd()));
		try{
			userService.createBlogUser(blogUser);
			ri.setSuccess(true);
		}catch(Exception e){
			ri.setSuccess(false);
		}
		return ri;
	}

	@RequestMapping(value = "/deluser.html")
	public String delUser(@ModelAttribute("blogUser") BlogUser blogUser) {
		return null;
	}

	@RequestMapping(value="profile.html")
	public String profile(HttpServletRequest request,ModelMap mm){
		mm.addAttribute("user",getSessionUser(request));
		return "profile";
	}
	
	@RequestMapping(value = "/updateUserProfile.html")
	public @ResponseBody ResponseInfo updateUserProfile(@ModelAttribute("blogUser") BlogUser user,HttpServletRequest request) {
		ResponseInfo ri = new ResponseInfo();
		BlogUser blogUser = getSessionUser(request);
		try{
			String icon = user.getIcon();
			blogUser.setIcon(icon);
			userService.updateUserProfileByUserName(blogUser);
			ri.setSuccess(true);
			ri.setResult(icon);
		}catch(Exception e){
			ri.setSuccess(false);
			ri.setMessage("更新失败!");
		}
		return ri;
	}

}
