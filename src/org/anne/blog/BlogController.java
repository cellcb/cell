package org.anne.blog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.anne.base.AnneUtil;
import org.anne.base.BaseController;
import org.anne.base.ResponseInfo;
import org.anne.blog.reply.BlogReply;
import org.anne.blog.transmit.TransmitRelation;
import org.anne.blog.vo.BlogVO;
import org.anne.user.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

@Controller
@RequestMapping(value = "/blog")
public class BlogController extends BaseController {
	@Autowired
	BlogService blogService;
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@RequestMapping(value = "/addblog.html")
	public @ResponseBody
	ResponseInfo  addBlog(@ModelAttribute("blog") Blog blog,
			HttpServletRequest request,ModelMap mm) throws Exception  {
		request.setCharacterEncoding("utf-8");
		BlogUser user = getSessionUser(request);
		ResponseInfo info = new ResponseInfo();
		Map<String, Blog> blogMap = new HashMap<String, Blog>();
		Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate(
				"feed.ftl");
		blog.setId(AnneUtil.genId());
		blog.setReplyCount(0);
		blog.setTransmitCount(0);
		blog.setType(0);
		blog.setCreateuser(user.getUserid());
		blog.setCreateDate(new Date());

		try {
			blogService.addBlog(blog);
			blog.setCreateuser(user.getNickname());
			blogMap.put("blog", blog);
			String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(
					tpl, blogMap);
			info.setSuccess(true);
			info.setResult(htmlText);
			info.setMessage("success");
		} catch (Exception e) {
			info.setSuccess(false);
			info.setMessage("error");
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = "/delblog.html")
	public @ResponseBody
	ResponseInfo delBlog(@ModelAttribute("blog") Blog blog) {
		ResponseInfo info = new ResponseInfo();
		blog.setId(AnneUtil.genId());
		try {
			blogService.deleteBlog(blog);
			info.setSuccess(true);
		} catch (Exception e) {
			info.setSuccess(false);
			e.printStackTrace();
		}
		return info;
	}

	@RequestMapping(value = "/blogreply.html")
	public @ResponseBody
	ResponseInfo blogReply(@ModelAttribute("br") BlogReply br) {
		ResponseInfo info = new ResponseInfo();
		try {
			blogService.blogReply(br);
			info.setResult(br);
			info.setSuccess(true);
		} catch (Exception e) {
			info.setSuccess(false);
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 取得自己发布的BLOG
	 * 
	 * @param blog
	 * @return
	 */
	@RequestMapping(value = "/getmyblog.html")
	public @ResponseBody
	ResponseInfo getMyBlog(@ModelAttribute("blog") Blog blog,HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		try {
			// 最后改为SESSION 登陆用户
			List<Blog> blogs = blogService.getMyBlog(getSessionUser(request).getNickname(),0,10);
			int count = blogService.getMyBlogCount(getSessionUser(request).getNickname());
			info.setCount(count);
			info.setResult(blogs);
			info.setSuccess(true);
		} catch (Exception e) {
			info.setSuccess(false);
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 取得主列表BLOG 包括自己发布的和关注人的
	 * 
	 * @param blog
	 * @return
	 */
	@RequestMapping(value = "/getblog.html")
	public @ResponseBody
	ResponseInfo getBlog(@ModelAttribute("bv") BlogVO bv,
			HttpServletRequest request) {
		ResponseInfo info = new ResponseInfo();
		try {
			// 最后改为SESSION 登陆用户
			List<Blog> blogs = blogService.getBlogs(getSessionUser(request)
					.getUsername(), bv.getStart(), bv.getPagesize());
			int count = blogService.getBlogCount(bv.getCreateuser());
			info.setCount(count);
			info.setResult(blogs);
			info.setSuccess(true);
		} catch (Exception e) {
			info.setSuccess(false);
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 取得BLOG回得
	 * 
	 * @param blog
	 * @return
	 */
	@RequestMapping(value = "/getblogreply.html")
	public @ResponseBody
	ResponseInfo getBlogReply(@ModelAttribute("bv") BlogVO bv) {
		ResponseInfo info = new ResponseInfo();
		try {
			// 最后改为SESSION 登陆用户
			List<BlogReply> replys = blogService.getBlogReplyByBlogid(
					bv.getBlogid(), bv.getStart(), bv.getPagesize());
			int count = blogService.getBlogReplyCountByBlogid(bv.getBlogid());
			info.setCount(count);
			info.setResult(replys);
			info.setSuccess(true);
		} catch (Exception e) {
			info.setSuccess(false);
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 
	 * @param bv
	 *            SourceBlogId 为被转发blog的id ; transmitType 为被转发blog的type
	 */
	@RequestMapping(value = "/blogtransmit.html")
	public @ResponseBody
	ResponseInfo blogTransmit(@ModelAttribute("bv") BlogVO bv) {
		ResponseInfo info = new ResponseInfo();

		Blog blog = new Blog();
		blog.setId(AnneUtil.genId());
		blog.setContent(bv.getContent());
		blog.setCreateDate(new Date());
		// 最后改为SESSION 登陆用户
		blog.setCreateuser("");
		blog.setImageId(bv.getImageId());
		blog.setReplyCount(0);
		blog.setTransmitCount(0);
		TransmitRelation tr = new TransmitRelation();
		tr.setBlogid(blog.getId());
		tr.setSourceid(bv.getSourceBlogId());
		try {
			blogService.blogTransmit(blog, tr, bv.getTransmitType());
			info.setResult(blog);
			info.setSuccess(true);
		} catch (Exception e) {
			info.setSuccess(false);
			e.printStackTrace();
		}
		return info;
	}
}
