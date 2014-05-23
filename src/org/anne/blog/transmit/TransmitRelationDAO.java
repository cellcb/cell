package org.anne.blog.transmit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransmitRelationDAO {
	private final static String INSERT_TRANSMITRELATION = "insert into  transmit_relation (blogid,sourceid) values (?,?)";
	private final static String COPY_TRANSMITRELATION = "INSERT INTO  transmit_relation (blogid,sourceid) SELECT ?,sourceid FROM transmit_relation where blogid=? ";
	@Autowired
	JdbcTemplate jTemplate;

	public void addTransmitRelation(TransmitRelation entity) {
		jTemplate.update(INSERT_TRANSMITRELATION,
				new Object[] { entity.getBlogid(), entity.getSourceid() });
	}

	/**
	 * 
	 * @param entity
	 */
	public void copyTransmitRelation(TransmitRelation entity) {
		jTemplate.update(COPY_TRANSMITRELATION,
				new Object[] { entity.getBlogid(), entity.getSourceid() });
	}
}