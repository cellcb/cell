package org.anne.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.anne.base.AnneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeDAO {
	
	private static final Logger log = LoggerFactory
	.getLogger(ResumeDAO.class);
	
	private static final String ADD_RESUME = "insert into resume(id,title,brief,datetime,tag,student_id) values(?,?,?,?,?,?)";
	private static final String DELETE_RESUME = "delete from resume where id=?";
	private static final String EDIT_RESUME = "update resume set title=?, brief=?, datetime=? tag=? where id=?";
	private static final String QUERY_BY_ID = "select * from resume where id=?";
	private static final String QUERY_BY_STUDENT_ID = "select * from resume where student_id=?";
	
	@Autowired
	JdbcTemplate jTemplate;
	
	/**
	 * 添加
	 * @param resume
	 * @return
	 */
	public String addResume(Resume resume) {
		String id = AnneUtil.genId();
		jTemplate.update(ADD_RESUME, new Object[] { id, resume.getTitle(),
				resume.getBrief(), resume.getDatetime(), resume.getTag(), resume.getStudentId() });
		return id;
	}
	
	/**
	 * 删除履历
	 * @param id
	 */
	public void deleteResume(String id) {
		jTemplate.update(DELETE_RESUME, new Object[] {id});
	}
	
	/**
	 * 修改履历
	 * @param resume
	 */
	public void editResume(Resume resume) {
		jTemplate.update(EDIT_RESUME, new Object[] { resume.getTitle(),
				resume.getBrief(), resume.getDatetime(), resume.getTag(),
				resume.getId() });
	}
	
	public Resume findById(String id) {
		List<Resume> list = jTemplate.query(QUERY_BY_STUDENT_ID, new Object[] { id },
				new ResumeRowMapper());
		if(list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 查询学生履历列表
	 * @param studentId
	 * @return
	 */
	public List<Resume> findByStudentId(String studentId) {
		List<Resume> list = new ArrayList<Resume>();
		list = jTemplate.query(QUERY_BY_STUDENT_ID, new Object[] { studentId },
				new ResumeRowMapper());
		return list;
	}
	
	class ResumeRowMapper implements RowMapper<Resume> {
		public Resume mapRow(ResultSet rs, int index) throws SQLException {
			Resume resume = new Resume();
			resume.setId(rs.getString("id"));
			resume.setTitle(rs.getString("title"));
			resume.setBrief(rs.getString("brief"));
			resume.setDatetime(rs.getString("datetime"));
			resume.setTag(rs.getString("tag"));
			resume.setStudentId(rs.getString("student"));
			return resume;
		}
	}

}
