package org.anne.junittest;

import java.util.Date;

import org.anne.base.AnneUtil;
import org.anne.news.News;
import org.anne.news.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestNews {

	@Autowired
	NewsService newsService;

	@Test
	public void addNewsTest() {
		News news = new News();
		news.setId(AnneUtil.genId());
		news.setCrearetuser("xxx");
		news.setCreatedate(new Date());
		news.setContent("xxxxxxxxxxxxxxxxx");
		news.setTitle("fdsfafafs");
		newsService.addNews(news);

	}

}
