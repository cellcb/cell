package org.anne.image.imageGroup;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ImagegroupDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ImagegroupDAO.class);
	private static final String INSERT_IMAGEGROUP = "insert into  Imagegroup (id,name,userid,coverimgid,desc) values (?,?,?,?,?)";
	private static final String DELETE_IMAGEGROUP = "delete from Imagegroup where id=? ";
	private static final String UPDATE_IMAGEGROUP = "update Imagegroup set update1 Imagegroup set   name=? , coverimgid=? , desc=?  where id=? ";
	private static final String SELECT_IMAGEGROUP_BY_ID = "SELECT * FROM  Imagegroup  where id=? ";
	private static final String SELECT_IMAGEGROUP_IMAGECOUNT_BY_USERID = "SELECT COUNT(*) COUNT,b.name FROM  imageinfo a,ImageGroup b WHERE a.groupid =b.id  AND b.userid=? GROUP BY b.name";
	@Autowired
	JdbcTemplate jTemplate;

	public void addImagegroup(Imagegroup entity) {
		jTemplate.update(INSERT_IMAGEGROUP, new Object[] { entity.getId(),
				entity.getName(), entity.getUserid(), entity.getCoverimgid(),
				entity.getDesc() });
	}

	public ImagegroupVo getImageCountByUserid(Imagegroup entity) {
		final ImagegroupVo igv = new ImagegroupVo();
		jTemplate.query(SELECT_IMAGEGROUP_IMAGECOUNT_BY_USERID,
				new Object[] { entity.getUserid() }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						igv.setName(rs.getString("name"));
						igv.setCount(rs.getInt("count"));
					}
				});
		return igv;
	}

	public void updateImagegroup(Imagegroup entity) {
		jTemplate.update(UPDATE_IMAGEGROUP, new Object[] { entity.getName(),
				entity.getCoverimgid(), entity.getDesc(), entity.getId() });
	}

	public void delImagegroup(Imagegroup entity) {
		jTemplate.update(DELETE_IMAGEGROUP, new Object[] { entity.getId() });
	}

	public Imagegroup getImagegroupById(String id) {
		final Imagegroup entity = new Imagegroup();
		jTemplate.query(SELECT_IMAGEGROUP_BY_ID, new Object[] { id },
				new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						entity.setId(rs.getString("id"));
						entity.setName(rs.getString("name"));
						entity.setUserid(rs.getString("userid"));
						entity.setCoverimgid(rs.getString("coverimgid"));
						entity.setDesc(rs.getString("desc"));
					}
				});
		return entity;
	}

	class ImagegroupRowMapper implements RowMapper<Imagegroup> {
		public Imagegroup mapRow(ResultSet rs, int index) throws SQLException {
			Imagegroup entity = new Imagegroup();
			entity.setId(rs.getString("id"));
			entity.setName(rs.getString("name"));
			entity.setUserid(rs.getString("userid"));
			entity.setCoverimgid(rs.getString("coverimgid"));
			entity.setDesc(rs.getString("desc"));
			return entity;
		}
	}
}