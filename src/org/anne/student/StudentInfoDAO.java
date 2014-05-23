package org.anne.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.anne.base.AnneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * A data access object (DAO) providing persistence and search support for
 * StudentInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.anne.student.StudentInfo
 * @author MyEclipse Persistence Tools
 */
@Repository
public class StudentInfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(StudentInfoDAO.class);
	
	private static final String ADD_STUDENT_BY_USERID = "insert into student_info(id,user_id) values(?,?)";
	private static final String UPDATE_STUDENT = "update student_info set lastname=?,firstname=?,sex=?,address=?,city=?,nation=?,signature=?,tag=?,elementary_sch=?,junior_sch=?,high_sch=?,university=?,major=?,edu_bg=?,want_nation=?,want_city=?,want_university=?,want_major=?,want_scholarship=?,want_fee=? where user_id=?";
	private static final String QUERY_BY_USERID = "select * from student_info where user_id=?";
	
	@Autowired
	JdbcTemplate jTemplate;
	
	/**
	 * 添加
	 * @param stu
	 * @return
	 */
	public String addStudent(StudentInfo stu) {
		String id = AnneUtil.genId();
		jTemplate.update(ADD_STUDENT_BY_USERID, new Object[] {id, stu.getUserid()});
		return id;
	}
	
	/**
	 * 更新
	 * @param stu
	 */
	public void updateStudent(StudentInfo stu) {
		jTemplate.update(UPDATE_STUDENT, new Object[] { stu.getLastname(),
				stu.getFirstname(), stu.getSex(), stu.getAddress(),
				stu.getCity(), stu.getNation(), stu.getSignature(),
				stu.getTag(), stu.getElementarySch(), stu.getJuniorSch(),
				stu.getHighSch(), stu.getUniversity(), stu.getMajor(),
				stu.getEduBg(), stu.getWantNation(), stu.getWantCity(),
				stu.getWantUniversity(), stu.getWantMajor(),
				stu.getWantScholarship(), stu.getWantFee(), stu.getUserid() });
	}
	
	public StudentInfo findByUserId(String userid) {
		List<StudentInfo> list = jTemplate.query(QUERY_BY_USERID,
				new Object[] { userid }, new StuRowMapper());
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	class StuRowMapper implements RowMapper<StudentInfo> {
		public StudentInfo mapRow(ResultSet rs, int index) throws SQLException {
			StudentInfo stu = new StudentInfo();
			stu.setAddress(rs.getString("address"));
			stu.setCity(rs.getString("city"));
			stu.setEduBg(rs.getString("eduBg"));
			stu.setElementarySch(rs.getString("elementarySch"));
			stu.setFirstname(rs.getString("firstname"));
			stu.setHighSch(rs.getString("highSch"));
			stu.setId(rs.getString("id"));
			stu.setJuniorSch(rs.getString("juniorSch"));
			stu.setLastname(rs.getString("lastname"));
			stu.setMajor(rs.getString("major"));
			stu.setNation(rs.getString("nation"));
			stu.setSex(rs.getInt("sex"));
			stu.setSignature(rs.getString("signature"));
			stu.setTag(rs.getString("tag"));
			stu.setUniversity(rs.getString("university"));
			stu.setUserid(rs.getString("userid"));
			stu.setWantCity(rs.getString("wantCity"));
			stu.setWantFee(rs.getInt("wantFee"));
			stu.setWantMajor(rs.getString("wantMajor"));
			stu.setWantNation(rs.getString("wantNation"));
			stu.setWantScholarship(rs.getBoolean("wantScholarship"));
			stu.setWantUniversity(rs.getString("wantUniversity"));
			return stu;
		}
	}
	
}