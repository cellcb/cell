package org.anne.Organization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.anne.base.AnneUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MajorDAO {
	
	private final static String ADD_MAJOR = "insert into major(id,name,brief,org_id) values(?,?,?,?)";
	private final static String DEL_MAJOR = "delete from major where id=?";
	private final static String UPDATE_MAJOR = "update major set name=?,brief=? where id=? ";
	private final static String QUERY_BY_ID = "select * from major where id=?";
	private final static String QUERY_BY_ORG_ID = "select * from major where org_id=?";
	
	@Autowired
	JdbcTemplate jTemplate;
	
	public String addMajor(Major major) {
		String id = AnneUtil.genId();
		jTemplate.update(ADD_MAJOR, new Object[] { id,
				major.getName(), major.getBrief(), major.getOrgId()});
		return id;
	}
	
	public void deleteMajor(Major major) {
		jTemplate.update(DEL_MAJOR, new Object[] {major.getId()});
	}
	
	public void editMajor(Major major) {
		jTemplate.update(UPDATE_MAJOR, new Object[] { major.getName(),
				major.getBrief(), major.getId() });
	}
	
	public Major getById(String id) {
		List<Major> list = jTemplate.query(QUERY_BY_ID,
				new Object[] { id }, new MajorRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 列出机构的专业列表
	 * @param orgId
	 * @return
	 */
	public List<Major> listMajorByOrgId(String orgId) {
		List<Major> list = new ArrayList<Major>();
		list = jTemplate.query(QUERY_BY_ORG_ID, new Object[] { orgId },
				new MajorRowMapper());
		return list;
	}
	
	class MajorRowMapper implements RowMapper<Major> {
		public Major mapRow(ResultSet rs, int index) throws SQLException {
			Major major = new Major();
			major.setId(rs.getString("id"));
			major.setName(rs.getString("name"));
			major.setBrief(rs.getString("brief"));
			major.setOrgId(rs.getString("org_id"));
			return major;
		}
	}

}
