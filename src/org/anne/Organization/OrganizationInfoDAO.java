package org.anne.Organization;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.anne.base.AnneUtil;
import org.anne.user.BlogUser;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * OrganizationInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.anne.Organization.OrganizationInfo
 * @author MyEclipse Persistence Tools
 */

@Repository
public class OrganizationInfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OrganizationInfoDAO.class);
	
	private static final String ADD_ORG_BY_USERID = "insert into organization_info(id,user_id) values(?,?)";
	private static final String UPDATE_ORG = "update organization_info set fullname=?,nickname=?,chinesename=?,publicschool=?,registerdate=?,address=?,postcode=?,brief=?,officialwebsite=?,major_brief=?,toefl=?,ielts=?,gre=?,sat=?,tag=?,undergraduate=?,postgraduate=?,fee=?,scholarship=?,email=? where user_id=?";
	private static final String QUERY_BY_USERID = "select * from organization_info where user_id=?";
	private static final String QUERY_ALL_ORG = "select * from organization_info";
	
	@Autowired
	JdbcTemplate jTemplate;
	
	/**
	 * 添加
	 * @param org
	 * @return
	 */
	public String addOrg(OrganizationInfo org) {
		String id = AnneUtil.genId();
		jTemplate.update(ADD_ORG_BY_USERID, new Object[] {id, org.getUserid()});
		return id;
	}
	
	/**
	 * 更新
	 * @param org
	 */
	public void updateOrg(OrganizationInfo org) {
		jTemplate.update(UPDATE_ORG, new Object[] { org.getFullname(),
				org.getNickname(), org.getChinesename(), org.isPublicschool(),
				org.getRegisterdate(), org.getAddress(), org.getPostcode(),
				org.getBrief(), org.getOfficialwebsite(), org.getMajorBrief(),
				org.getToefl(), org.getIelts(), org.getGre(), org.getSat(),
				org.getTag(), org.getUndergraduate(), org.getPostgraduate(),
				org.getFee(), org.getScholarship(), org.getEmail(),
				org.getUserid() });
	}
	
	public OrganizationInfo findByUserId(String userid) {
		List<OrganizationInfo> list = jTemplate.query(QUERY_BY_USERID,
				new Object[] { userid }, new OrgRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	public List<OrganizationInfo> queryAllOrg(){
		return jTemplate.query(QUERY_ALL_ORG, new OrgRowMapper());
	}
	
	class OrgRowMapper implements RowMapper<OrganizationInfo> {
		public OrganizationInfo mapRow(ResultSet rs, int index) throws SQLException {
			OrganizationInfo org = new OrganizationInfo();
			org.setId(rs.getString("id"));
			org.setAddress(rs.getString("address"));
			org.setBrief(rs.getString("brief"));
			org.setChinesename(rs.getString("chinesename"));
			org.setEmail(rs.getString("email"));
			org.setFee(rs.getInt("fee"));
			org.setFullname(rs.getString("fullname"));
			org.setGre(rs.getInt("gre"));
			org.setIelts(rs.getInt("ielts"));
			org.setMajorBrief(rs.getString("major_brief"));
			org.setNickname(rs.getString("nickname"));
			org.setOfficialwebsite(rs.getString("officialwebsite"));
			org.setPostcode(rs.getString("postcode"));
			org.setPostgraduate(rs.getInt("postgraduate"));
			org.setPublicschool(rs.getBoolean("publicschool"));
			org.setRegisterdate(rs.getString("registerdate"));
			org.setSat(rs.getInt("sat"));
			org.setScholarship(rs.getString("scholarship"));
			org.setTag(rs.getString("tag"));
			org.setToefl(rs.getInt("toefl"));
			org.setUndergraduate(rs.getInt("undergraduate"));
			org.setUserid(rs.getString("user_id"));
			return org;
		}
	}
	
}