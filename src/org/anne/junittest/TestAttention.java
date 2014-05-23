package org.anne.junittest;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.anne.blog.Blog;
import org.anne.blog.BlogService;
import org.anne.blog.reply.BlogReply;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestAttention {

	@Autowired
	BlogService blogService;

	@Test
	public void addBlogTest() {

		Blog b = new Blog();
		b.setContent("测试微BO");
		b.setCreateDate(new Date());
		b.setCreateuser("chengbo");
		b.setImageId("imageid");
		b.setReplyCount(0);
		b.setTransmitCount(0);
		b.setType(0);
		try {
			blogService.addBlog(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void delBlogTest() {
		Blog b = new Blog();
		b.setId("4");
		try {
			blogService.deleteBlog(b);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void getMyBlog() {
		List<Blog> myBlog = blogService.getMyBlog("chengbo",0,10);
		for (Iterator iterator = myBlog.iterator(); iterator.hasNext();) {
			Blog blog = (Blog) iterator.next();
			System.out.println(blog.getContent());
		}

	}
}
