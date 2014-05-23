package org.anne.blog.reply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BlogReplyDAO {

	@Autowired
	JdbcTemplate jTemplate;

	private static final String INSERT_BLOGREPLY = "insert into  blog_reply (content,blogid,createdate,createuser) values (?,?,?,?)";
	private static final String SELECT_BLOGREPLY_BY_BLOGID = "SELECT * FROM blog_reply WHERE blogid=? ORDER BY  createdate DESC  LIMIT ?,?";
	private static final String SELECT_BLOGREPLY_COUNT_BY_BLOGID = "SELECT COUNT(*) FROM blog_reply WHERE blogid=? ";

	public void addBlogReply(BlogReply entity) {
		jTemplate.update(
				INSERT_BLOGREPLY,
				new Object[] { entity.getContent(), entity.getBlogid(),
						entity.getCreatedate(), entity.getCreateuser() });
	}

	public List<BlogReply> getBlogReplyByBlogid(String blogid, int start,
			int pagesize) {
		return jTemplate.query(SELECT_BLOGREPLY_BY_BLOGID, new Object[] {
				blogid, start, pagesize }, new BlogReplyRowMapper());
	}

	public int getBlogReplyCountByBlogid(String blogid) {
		return jTemplate.queryForInt(SELECT_BLOGREPLY_COUNT_BY_BLOGID,
				new Object[] { blogid });
	}

	class BlogReplyRowMapper implements RowMapper<BlogReply> {
		public BlogReply mapRow(ResultSet rs, int index) throws SQLException {
			BlogReply entity = new BlogReply();
			entity.setId(rs.getInt("id"));
			entity.setContent(rs.getString("content"));
			entity.setBlogid(rs.getString("blogid"));
			entity.setCreateuser(rs.getString("createuser"));
			entity.setCreatedate(rs.getTimestamp("createdate"));
			return entity;
		}
	}
}