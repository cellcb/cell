package org.anne.image;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ImageinfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ImageinfoDAO.class);

	private static final String INSERT_IMAGE = "insert into  imageinfo (id,filename,thumbnail_id,original_id,description,createdate,type,userid,groupid) values (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_IMAGE_GROUP = "UPDATE IMAGEINFO SET GROUPID=? WHERE ID=?";

	private static final String DELETE_IMAGE = "DELETE FROM IMAGEINFO WHERE ID=?";
	@Autowired
	JdbcTemplate jTemplate;

	public void addImage(Imageinfo entity) {
		jTemplate.update(
				INSERT_IMAGE,
				new Object[] { entity.getId(), entity.getFilename(),
						entity.getThumbnailId(), entity.getOriginalId(),
						entity.getDescription(), entity.getCreatedate(),
						entity.getType(), entity.getUserid(),
						entity.getGroupid() });
	}

	public void delImage(Imageinfo entity) {
		jTemplate.update(DELETE_IMAGE, new Object[] { entity.getId() });
	}

	public void updateImageGroup(Imageinfo entity) {
		jTemplate.update(UPDATE_IMAGE_GROUP, new Object[] {
				entity.getGroupid(), entity.getId() });
	}

	class ImageinfoRowMapper implements RowMapper<Imageinfo> {
		public Imageinfo mapRow(ResultSet rs, int index) throws SQLException {
			Imageinfo entity = new Imageinfo();
			entity.setId(rs.getString("id"));
			entity.setFilename(rs.getString("filename"));
			entity.setThumbnailId(rs.getString("thumbnail_id"));
			entity.setOriginalId(rs.getString("original_id"));
			entity.setDescription(rs.getString("description"));
			entity.setType(rs.getInt("type"));
			entity.setUserid(rs.getString("userid"));
			entity.setGroupid(rs.getString("groupid"));
			entity.setCreatedate(rs.getTimestamp("createdate"));
			return entity;
		}
	}
}