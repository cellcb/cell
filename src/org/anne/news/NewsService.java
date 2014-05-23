package org.anne.news;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.anne.blog.BlogDAO;
import org.anne.news.NewsDAO.NewsRowMapper;
import org.anne.news.vo.NewsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NewsService {
	@Autowired
	NewsDAO newsDAO;

	@Transactional
	public void addNews(News entity) {
		newsDAO.addNews(entity);
	}

	@Transactional
	public void deleteNews(News entity) {
		newsDAO.deleteNews(entity);
	}

	public List<News> getNews(NewsVo vo) {
		return newsDAO.getNews(vo);
	}

	public int getNewsCount() {
		return newsDAO.getNewsCount();
	}
}
