package org.anne.blog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDAO {
	private static final Logger log = LoggerFactory.getLogger(BlogDAO.class);

	private final static String INSERT_BLOG = "insert into  blog (id,content,image_id,createuser,create_date,type,transmit_count,reply_count) values (?,?,?,?,?,?,?,?)";
	private final static String DELETE_BLOG = "delete  from  blog  where id=? ";
	private final static String UPDATE_BLOG_REPLYCOUNT = "update blog set reply_count=reply_count+1 where id=?";
	private final static String UPDATE_BLOG_TRANSMIT_COUNT = "update blog set transmit_count=transmit_count+1 where id=?";
	private final static String SELECT_MYBLOG = "SELECT * FROM BLOG WHERE createuser=? ORDER BY create_date DESC limit ?,?";
	private final static String SELECT_MYBLOG_COUNT = "SELECT COUNT(*) FROM BLOG WHERE createuser=?";
	private final static String SELECT_BLOGS = "SELECT *FROM blog WHERE createuser IN(SELECT  target_userid  FROM attention  WHERE userid = ? UNION ALL SELECT  ?  FROM DUAL)ORDER BY create_date DESC  LIMIT ?,?";
	private final static String SELECT_BLOGS_COUNT = "SELECT COUNT(*) FROM blog WHERE createuser IN(SELECT  target_userid  FROM attention  WHERE userid = ? UNION ALL SELECT  ?  FROM DUAL)";

	@Autowired
	JdbcTemplate jTemplate;

	public void addBlog(Blog entity) throws Exception {
		jTemplate.update(
				INSERT_BLOG,
				new Object[] { entity.getId(), entity.getContent(),
						entity.getImageId(), entity.getCreateuser(),
						entity.getCreateDate(), entity.getType(),
						entity.getTransmitCount(), entity.getReplyCount() });
	}

	public void deleteBlog(Blog entity) throws Exception {
		jTemplate.update(DELETE_BLOG, new Object[] { entity.getId() });
	}

	public void updateBlogReplycount(Blog entity) throws Exception {
		jTemplate.update(UPDATE_BLOG_REPLYCOUNT,
				new Object[] { entity.getId() });
	}

	public void updateBlogTransmitcount(Blog entity) throws Exception {
		jTemplate.update(UPDATE_BLOG_TRANSMIT_COUNT,
				new Object[] { entity.getId() });
	}

	public List<Blog> getMyBlog(String userid,int start,int pagesize) {
		return jTemplate.query(SELECT_MYBLOG, new Object[] { userid ,start,pagesize},
				new BlogRowMapper());
	}

	public int getMyBlogCount(String userid) {
		return jTemplate.queryForInt(SELECT_MYBLOG_COUNT,
				new Object[] { userid });
	}

	public List<Blog> getBlog(String userid, int start, int pagesize) {
		return jTemplate.query(SELECT_BLOGS, new Object[] { userid, userid,
				start, pagesize }, new BlogRowMapper());
	}

	public int getBlogCount(String userid) {
		return jTemplate.queryForInt(SELECT_BLOGS_COUNT, new Object[] { userid,
				userid });
	}

	class BlogRowMapper implements RowMapper<Blog> {
		public Blog mapRow(ResultSet rs, int index) throws SQLException {
			Blog entity = new Blog();
			entity.setId(rs.getString("id"));
			entity.setContent(rs.getString("content"));
			entity.setImageId(rs.getString("image_id"));
			entity.setCreateuser(rs.getString("createuser"));
			entity.setCreateDate(rs.getTimestamp("create_date"));
			entity.setType(rs.getInt("type"));
			entity.setTransmitCount(rs.getInt("transmit_count"));
			entity.setReplyCount(rs.getInt("reply_count"));
			entity.setCreateDate(rs.getTimestamp("create_date"));
			return entity;
		}
	}

}