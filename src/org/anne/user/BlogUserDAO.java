package org.anne.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.anne.base.AnneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BlogUserDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BlogUserDAO.class);

	private final static String INSERT_USER = "insert into  blog_user (userid,username,nickname,passwd,email,icon,Signature,status,type,attention_count,fans_count,blog_count) values (?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String DELETE_USER = "update  blog_user set  delete_flag=1  where userid=? ";
	private final static String UPDATE_PASSWD = "update  blog_user set  passwd=?  where userid=? ";
	private final static String QUERY_BY_ID = "select * from blog_user where userid=?";
	private final static String QUERY_BY_USERNAME = "select *  from  blog_user  where username=? ";
	private final static String QUERY_BY_EMAIL = "select *  from  blog_user  where email=? ";
	private final static String QUERY_BY_NAME_PASS = "select * from blog_user where username=? and passwd=?";
	private final static String UPDATE_USERIMG_BY_USERNAME= "update blog_user set icon=? where username=? ";
	private final static String QUERY_ORGANIZATIONS = "select * from blog_user where type=0";
	
	@Autowired
	JdbcTemplate jTemplate;

	public String addBlogUser(BlogUser entity) {
		String id = AnneUtil.genId();
		jTemplate.update(
				INSERT_USER,
				new Object[] { id, entity.getUsername(),
						entity.getNickname(), entity.getPasswd(),
						entity.getEmail(), entity.getIcon(),
						entity.getSignature(), entity.getStatus(),
						entity.getType(), entity.getAttentionCount(),
						entity.getFansCount(), entity.getBlogCount() });
		return id;
	}

	public void deleteBlogUser(BlogUser entity) {
		jTemplate.update(DELETE_USER, new Object[] { entity.getUserid() });
	}

	public void editPasswd(BlogUser entity) {
		jTemplate.update(UPDATE_PASSWD, new Object[] { entity.getPasswd(),
				entity.getUserid() });
	}

	public BlogUser findBlogUserByUserName(BlogUser entity) {
		List<BlogUser> list = jTemplate.query(QUERY_BY_USERNAME,
				new Object[] { entity.getUsername() }, new UserRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public BlogUser findBlogUserByEmail(BlogUser entity) {
		List<BlogUser> list = jTemplate.query(QUERY_BY_EMAIL,
				new Object[] { entity.getEmail() }, new UserRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public BlogUser findBlogUserByNamePass(BlogUser entity) {
		List<BlogUser> list = jTemplate.query(QUERY_BY_NAME_PASS,
				new Object[] { entity.getUsername(), entity.getPasswd() }, new UserRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public BlogUser getById(String id) {
		List<BlogUser> list = jTemplate.query(QUERY_BY_ID,
				new Object[] { id }, new UserRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public void updateUserProfile(String profileId, String userName) {
		jTemplate.update(UPDATE_USERIMG_BY_USERNAME, new Object[] { profileId,
				userName });
	}
	public List<BlogUser> queryOrganizations(){
		return jTemplate.query(QUERY_ORGANIZATIONS, new UserRowMapper());
	}
	class UserRowMapper implements RowMapper<BlogUser> {
		public BlogUser mapRow(ResultSet rs, int index) throws SQLException {
			BlogUser user = new BlogUser();
			user.setUserid(rs.getString("userid"));
			user.setUsername(rs.getString("username"));
			user.setNickname(rs.getString("nickname"));
			user.setPasswd(rs.getString("passwd"));
			user.setEmail(rs.getString("email"));
			user.setIcon(rs.getString("icon"));
			user.setSignature(rs.getString("Signature"));
			user.setStatus(rs.getInt("status"));
			user.setAttentionCount(rs.getInt("attention_count"));
			user.setFansCount(rs.getInt("fans_count"));
			user.setBlogCount(rs.getInt("blog_count"));
			user.setType(rs.getInt("type"));
			return user;
		}
	}
}