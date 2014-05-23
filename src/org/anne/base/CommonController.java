package org.anne.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.anne.Organization.OrganizationInfo;
import org.anne.Organization.OrgnizationInfoService;
import org.anne.blog.Blog;
import org.anne.blog.BlogService;
import org.anne.message.Message;
import org.anne.message.MessageService;
import org.anne.message.vo.MessageVO;
import org.anne.user.BlogUser;
import org.anne.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/main")
public class CommonController extends BaseController {
	@Autowired
	MessageService messageService;
	@Autowired
	BlogService blogService;
	@Autowired
	OrgnizationInfoService orgService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/schindex.html")
	public String schIndex(HttpServletRequest request, ModelMap mm) {
		BlogUser bu = getSessionUser(request);
		OrganizationInfo org = orgService.findByUserid(bu.getUserid());
		List<OrganizationInfo> orglist = orgService.queryAllOrg();
		List<Blog> blogs = blogService.getBlogs(bu.getUserid(),0,3);//.getMyBlog(bu.getUsername(),0,3);//只取3条展示
		mm.addAttribute("user", bu);
		mm.addAttribute("org", org);
		mm.addAttribute("isPublic",org.isPublicschool()?"是":"否");
		mm.addAttribute("blogs", blogs);
		mm.addAttribute("orglist", orglist);
		
		return "schindex";
	}
	@RequestMapping(value = "/sch.html")
	public String sch(HttpServletRequest request, BlogUser user, ModelMap mm) {
		String userid = user.getUserid();
		BlogUser bu = userService.findBlogUserByUserId(userid);
		OrganizationInfo org = orgService.findByUserid(userid);
		List<OrganizationInfo> orglist = orgService.queryAllOrg();
		List<Blog> blogs = blogService.getBlogs(userid,0,5);//.getMyBlog(bu.getUsername(),0,3);//只取3条展示
		mm.addAttribute("user", bu);
		mm.addAttribute("org", org);
		mm.addAttribute("isPublic",org.isPublicschool()?"是":"否");
		mm.addAttribute("blogs", blogs);
		mm.addAttribute("orglist", orglist);
		if(getSessionUser(request).getUserid().equals(userid)){
			return "schindex";
		}
		return "sch";
	}
	@RequestMapping(value = "/stuindex.html")
	public String stuIndex(HttpServletRequest request, ModelMap mm) {
		BlogUser bu = getSessionUser(request);
		
		List<Blog> blogs =  blogService.getBlogs(bu.getUsername(),0,3);//blogService.getMyBlog(bu.getUsername(),0,3);//只取3条展示
		//int count = blogService.getMyBlogCount(bu.getNickname());
		mm.addAttribute("user", bu);
		mm.addAttribute("blogs", blogs);
		return "stuindex";
	}
	@RequestMapping(value = "/stu.html")
	public String stu(HttpServletRequest request, BlogUser user, ModelMap mm) {
//		BlogUser bu = getSessionUser(request);
		
		List<Blog> blogs = blogService.getMyBlog(user.getUsername(),0,5);//只取3条展示
		mm.addAttribute("blogs", blogs);
		return "stu";
	}
}
