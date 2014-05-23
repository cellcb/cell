package org.anne.junittest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.anne.base.AnneUtil;
import org.anne.blog.Blog;
import org.anne.blog.BlogService;
import org.anne.blog.reply.BlogReply;
import org.anne.blog.transmit.TransmitRelation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestBlog {

	@Autowired
	BlogService blogService;

	@Test
	public void addBlogTest() {

		Blog b = new Blog();
		b.setId(AnneUtil.genId());
		b.setContent("测试微BOwwwww");
		b.setCreateDate(new Date());
		b.setCreateuser("limj");
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
		b.setId("134504560903059abb5bc-9f94-4e11-a949-1fb");
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

	@Test
	public void getBlog() {
		List<Blog> myBlog = blogService.getBlogs("chengbo", 1, 2);
		for (Iterator iterator = myBlog.iterator(); iterator.hasNext();) {
			Blog blog = (Blog) iterator.next();
			System.out.println(blog.getContent() + "  " + blog.getCreateuser());
		}

	}

	@Test
	public void blogReply() {
		BlogReply br = new BlogReply();
		br.setBlogid("1");
		br.setContent("reply1 for 1");
		br.setCreatedate(new Date());
		br.setCreateuser("chengbo");
		try {
			blogService.blogReply(br);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void blogTransmit() {
		Blog b = new Blog();
		b.setId(AnneUtil.genId());
		b.setContent("转发的转发");
		b.setCreateDate(new Date());
		b.setCreateuser("qinj");
		b.setTransmitCount(0);
		b.setImageId("imageturl");
		b.setReplyCount(0);
		b.setSourceBlogId("1345104058694812f2603-a67c-4770-8fe3-e8d");
		TransmitRelation tr = new TransmitRelation();
		tr.setSourceid("134510361800627d3a402-9353-4277-b266-849");
		tr.setBlogid(b.getId());
		try {
			blogService.blogTransmit(b, tr, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testGetReply() {
		SimpleDateFormat dateformat1 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			List<BlogReply> ss = blogService.getBlogReplyByBlogid("1", 0, 1000);
			for (Iterator iterator = ss.iterator(); iterator.hasNext();) {
				BlogReply blogReply = (BlogReply) iterator.next();
				System.out.println(blogReply.getCreateuser());
				System.out
						.println(dateformat1.format(blogReply.getCreatedate()));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
