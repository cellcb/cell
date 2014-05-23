package org.anne.blog;

import java.util.List;

import org.anne.base.AnneUtil;
import org.anne.blog.reply.BlogReply;
import org.anne.blog.reply.BlogReplyDAO;
import org.anne.blog.transmit.TransmitRelation;
import org.anne.blog.transmit.TransmitRelationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogService {
	@Autowired
	BlogDAO blogDAO;
	@Autowired
	BlogReplyDAO blogReplyDAO;
	@Autowired
	TransmitRelationDAO transmitRelationDAO;

	/**
	 * 取得所有blog列表 包括自己和关注的
	 * 
	 * @param userid
	 */
	public List<Blog> getBlogs(String userid, int start, int pagesize) {
		return blogDAO.getBlog(userid, start, pagesize);
	}

	/**
	 * 添加BLOG
	 * 
	 * @param blog
	 */
	@Transactional
	public void addBlog(Blog blog) throws Exception {
		blogDAO.addBlog(blog);
	}

	/**
	 * 删除blog
	 * 
	 * @param blog
	 */
	@Transactional
	public void deleteBlog(Blog blog) throws Exception {
		blogDAO.deleteBlog(blog);

	}

	/**
	 * 取得自己发布的blog
	 * 
	 * @param userid
	 */
	public List<Blog> getMyBlog(String userid, int start, int pagesize) {
		return blogDAO.getMyBlog(userid, start, pagesize);
	}

	/**
	 * 回复blog
	 * 
	 * @param br
	 */
	@Transactional
	public void blogReply(BlogReply br) throws Exception {
		blogReplyDAO.addBlogReply(br);
		Blog b = new Blog();
		b.setId(br.getBlogid());
		blogDAO.updateBlogReplycount(b);
	}

	/**
	 * 
	 * @param blog
	 * @param tr
	 * @param type
	 *            源BLOG类型 0 转发blog ,1 转发后的再次转发
	 */
	@Transactional
	public void blogTransmit(Blog blog, TransmitRelation tr, int type)
			throws Exception {
		blog.setType(1);
		/**
		 * 0 转发 添加转发关系 1 对转发的微博的转发 copy 原转发关系
		 */
		if (type == 0) {
			blog.setId(AnneUtil.genId());
			blogDAO.addBlog(blog);
			tr.setBlogid(blog.getId());
			transmitRelationDAO.addTransmitRelation(tr);
		} else {

			blog.setId(AnneUtil.genId());
			blogDAO.addBlog(blog);

			transmitRelationDAO.copyTransmitRelation(new TransmitRelation(blog
					.getId(), blog.getSourceBlogId()));
		}
		// 更新被转发BLOG的转发次数
		Blog b = new Blog();
		b.setId(blog.getSourceBlogId());
		blogDAO.updateBlogTransmitcount(b);
	}

	/**
	 * 
	 * @param userid
	 * @return
	 */
	public int getBlogCount(String userid) {
		return blogDAO.getBlogCount(userid);
	}

	public int getMyBlogCount(String userid) {
		return blogDAO.getMyBlogCount(userid);
	}

	public List<BlogReply> getBlogReplyByBlogid(String blogid, int start,
			int pagesize) {
		return blogReplyDAO.getBlogReplyByBlogid(blogid, start, pagesize);
	}

	public int getBlogReplyCountByBlogid(String blogid) {
		return blogReplyDAO.getBlogReplyCountByBlogid(blogid);
	}
}
