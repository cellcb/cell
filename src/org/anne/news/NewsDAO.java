package org.anne.news;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.anne.blog.BlogDAO;
import org.anne.news.vo.NewsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDAO {
	private static final Logger log = LoggerFactory.getLogger(BlogDAO.class);

	private final static String INSERT_NEWS = "insert into  News (id,title,content,createdate,crearetuser) values (?,?,?,?,?)";
	private final static String DELETE_NEWS = "delete  from  News  where id=? ";
	private final static String SELECT_NEWS = "SELECT * FROM news   ORDER BY createdate DESC LIMIT ?,?";
	private final static String SELECT_NEWS_COUNT = "SELECT COUNT(*) FROM NEWS";

	@Autowired
	JdbcTemplate jTemplate;

	public void addNews(News entity) {
		jTemplate.update(
				INSERT_NEWS,
				new Object[] { entity.getId(), entity.getTitle(),
						entity.getContent(), entity.getCreatedate(),
						entity.getCrearetuser() });
	}

	public void deleteNews(News entity) {
		jTemplate.update(DELETE_NEWS, new Object[] { entity.getId() });
	}

	public List<News> getNews(NewsVo vo) {
		return jTemplate.query(SELECT_NEWS,
				new Object[] { vo.getStart(), vo.getPagesize() },
				new NewsRowMapper());
	}

	public int getNewsCount() {
		return jTemplate.queryForInt(SELECT_NEWS_COUNT);
	}

	class NewsRowMapper implements RowMapper<News> {
		public News mapRow(ResultSet rs, int index) throws SQLException {
			News entity = new News();
			entity.setId(rs.getString("id"));
			entity.setTitle(rs.getString("title"));
			entity.setContent(rs.getString("content"));
			entity.setCrearetuser(rs.getString("crearetuser"));
			entity.setCreatedate(rs.getTimestamp("createdate"));
			return entity;
		}
	}

}