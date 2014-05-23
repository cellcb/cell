package org.anne.user.attention;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.anne.user.BlogUser;
import org.anne.user.BlogUserDAO;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AttentionDAO {
	private static final Logger log = LoggerFactory
			.getLogger(BlogUserDAO.class);

	private final static String INSERT_ATTENTION = "insert into  attention (userid,target_userid,groupid) values (?,?,?)";
	private final static String DELETE_ATTENTION = "DELETE FROM  attention WHERE ID =?";
	private final static String QUERY_BY_USERID = "select *  from  attention  where userid=? ";

	@Autowired
	JdbcTemplate jTemplate;

	public void addAttention(Attention entity) {
		jTemplate.update(INSERT_ATTENTION, new Object[] { entity.getUserid(),
				entity.getTargetUserid(), entity.getGroupid() });
	}

	public void deleteAttention(Attention entity) {
		jTemplate.update(DELETE_ATTENTION, new Object[] { entity.getId() });
	}

	public List<Attention> findAttentionUserById(Attention entity) {
		return null;
	}

	class AttentionRowMapper implements RowMapper<Attention> {
		public Attention mapRow(ResultSet rs, int index) throws SQLException {
			Attention entity = new Attention();
			entity.setId(rs.getInt("id"));
			entity.setUserid(rs.getString("userid"));
			entity.setTargetUserid(rs.getString("target_userid"));
			entity.setGroupid(rs.getInt("groupid"));
			return entity;
		}
	}
}